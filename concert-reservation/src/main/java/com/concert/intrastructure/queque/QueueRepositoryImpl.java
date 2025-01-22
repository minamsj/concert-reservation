package com.concert.intrastructure.queque;

import com.concert.domain.queque.QueueEntity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
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

    // JpaRepository의 기본 메서드들을 위임
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

    public void deleteByExpiredAt(LocalDateTime now) {
    }
}
