package com.concert.intrastructure.queque;

import com.concert.domain.queque.QueueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QueueJpaRepository extends JpaRepository<QueueEntity, String> {
    Optional<QueueEntity> findByUserIdAndScheduleId(Long userId, Long scheduleId);
    Optional<QueueEntity> findByToken(String token);
}
