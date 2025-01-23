package service;

import com.concert.domain.point.Point;
import com.concert.domain.point.PointService;
import com.concert.intrastructure.point.PointRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class PointServiceTest {

    @InjectMocks
    private PointService pointService;

    @Mock
    private PointRepository pointRepository;

    @Test
    @DisplayName("동시에 100개 요청시 낙관적 락으로 처리")
    void optimisticLockTest() throws InterruptedException {
        // given
        Long userId = 1L;
        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);

        // when
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    pointService.chargePoint(userId, 10000L);
                } catch (Exception e) {
                    // retry logic
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();

        Point point = pointRepository.findById(userId).orElseThrow();
        assertEquals(1000000L, point.getPoint()); // 10000 * 100
    }

    @Test
    @DisplayName("포인트 충전 금액이 10000원 미만이면 예외 발생")
    void invalidAmountTest() {
        // given
        Long userId = 1L;
        Long amount = 5000L;

        // when & then
        assertThrows(RuntimeException.class, () ->
                pointService.chargePoint(userId, amount)
        );
    }
}
