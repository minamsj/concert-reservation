package com.concert.repositories.queue;

import com.concert.queue.entity.QueueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface QueueRepository extends JpaRepository<QueueEntity, String> {
    Optional<QueueEntity> findByUserIdAndScheduleId(Long userId, Long scheduleId);
    Optional<QueueEntity> findByToken(String token);
    @Query("SELECT MAX(q.waitingNumber) FROM QueueEntity q")
    Optional<Long> findMaxWaitingNumber();
    @Modifying // 필수
    @Query("DELETE FROM QueueEntity q WHERE q.expiredAt <= :now")
    void deleteByExpiredAt(@Param("now") LocalDateTime now);
}
