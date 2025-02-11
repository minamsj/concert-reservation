package com.concert.domain.reservation;

import com.concert.domain.point.Point;
import com.concert.interfaces.api.reservation.ConcertResponse;
import com.concert.interfaces.api.reservation.ConcertScheduleResponse;
import com.concert.infrastructure.point.PointRepository;
import com.concert.infrastructure.reservation.ConcertRepository;
import com.concert.infrastructure.reservation.ConcertReservationJpaRepository;
import com.concert.infrastructure.reservation.ConcertScheduleRepository;
import com.concert.interfaces.api.reservation.ReservationRequest;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ConcertRepository concertRepository;
    private final ConcertScheduleRepository concertScheduleRepository;
    private final ConcertReservationJpaRepository concertReservationRepository;
    private final PointRepository pointRepository;
    private static final int MAX_RETRIES = 3;
    private final RedissonClient redissonClient;
    private static final String LOCK_PREFIX = "concert:";

    public void createReservation(ReservationRequest request) {
        String lockKey = LOCK_PREFIX + request.getSchedulesId() + ":" + request.getSeatNum();
        RLock lock = redissonClient.getLock(lockKey);

        try {
            if (!lock.tryLock(5, 3, TimeUnit.SECONDS)) {
                throw new RuntimeException("좌석 예약에 실패했습니다");
            }

            ConcertReservation reservation = ConcertReservation.builder()
                    .schedulesId(request.getSchedulesId())
                    .seatNum(request.getSeatNum())
                    .userId(request.getUserId())
                    .concertId(request.getConcertId())
                    .build();

            concertReservationRepository.save(reservation);

            chargePoints(request);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("예약 처리 중 오류가 발생했습니다", e);
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    private void chargePoints(ReservationRequest request) {
        for (int attempt = 0; attempt < MAX_RETRIES; attempt++) {
            try {
                Point point = pointRepository.findByUserId(request.getUserId())
                        .orElseThrow(() -> new RuntimeException("포인트 정보가 없습니다"));

                pointRepository.chargePoint(request.getUserId(), -request.getPoint(), point.getVersion());
                return; // 성공시 즉시 리턴

            } catch (OptimisticLockingFailureException e) {
                if (attempt == MAX_RETRIES - 1) {
                    throw new RuntimeException("포인트 차감 실패");
                }
                // 실패시 잠시 대기 후 재시도
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("처리 중단");
                }
            }
        }
    }

    public List<ConcertScheduleResponse> getConcertSchedules(Long concertId) {
        return concertScheduleRepository.getConcertSchedule(concertId);
    }

    @Cacheable(value = "concerts", unless = "#result == null")
    public List<ConcertResponse> getConcert() {
        return concertRepository.getConcert();
    }

}
