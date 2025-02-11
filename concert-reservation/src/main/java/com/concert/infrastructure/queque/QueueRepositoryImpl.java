package com.concert.infrastructure.queque;

import com.concert.domain.queque.QueueEntity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class QueueRepositoryImpl {
    private final QueueJpaRepository queueJpaRepository;
    private final EntityManager entityManager;

    public Optional<Long> findMaxWaitingNumber() {
        return Optional.ofNullable(
                entityManager.createQuery(
                        "SELECT MAX(q.waitingNumber) FROM QueueEntity q",
                        Long.class
                ).getSingleResult()
        );
    }

    public void deleteExpiredQueues(LocalDateTime now) {
        entityManager.createQuery(
                        "DELETE FROM QueueEntity q WHERE q.expiredAt <= :now"
                )
                .setParameter("now", now)
                .executeUpdate();
    }

    public QueueEntity save(QueueEntity entity) {
        return queueJpaRepository.save(entity);
    }

    public Optional<QueueEntity> findByUserIdAndScheduleId(Long userId, Long scheduleId) {
        return queueJpaRepository.findByUserIdAndScheduleId(userId, scheduleId);
    }

    public Optional<QueueEntity> findByToken(String token) {
        return queueJpaRepository.findByToken(token);
    }

    public void deleteQueue(Long userId, Long scheduleId) {
        queueJpaRepository.findByUserIdAndScheduleId(userId, scheduleId)
                .ifPresent(queueJpaRepository::delete);
    }

    public void processExpiredTokens(LocalDateTime now) {
        // 삭제할 토큰들의 schedule_id 조회
        List<Long> expiredScheduleIds = queueJpaRepository.findExpiredScheduleIds(now);

        // 토큰 삭제
        queueJpaRepository.deleteByExpiredAt(now);

        // 대기번호, 상태 업데이트
        for (Long scheduleId : expiredScheduleIds) {
            queueJpaRepository.updateWaitingNumbers(scheduleId);
            queueJpaRepository.updateStatusToReady(scheduleId);
        }
    }
}
