package com.concert.infrastructure.queque;

import com.concert.domain.queque.QueueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface QueueJpaRepository extends JpaRepository<QueueEntity, String> {
    Optional<QueueEntity> findByUserIdAndScheduleId(Long userId, Long scheduleId);
    Optional<QueueEntity> findByToken(String token);

    // 삭제할 토큰들의 schedule_id 조회
    @Query("SELECT DISTINCT q.scheduleId FROM QueueEntity q WHERE q.expiredAt <= :now")
    List<Long> findExpiredScheduleIds(@Param("now") LocalDateTime now);

    // 토큰 삭제
    @Query("DELETE FROM QueueEntity q WHERE q.expiredAt <= :now")
    @Modifying
    void deleteByExpiredAt(@Param("now") LocalDateTime now);

    // waiting_number 재정렬
    @Query(value = """
            UPDATE token t1,
                   (SELECT token_id, ROW_NUMBER() OVER (PARTITION BY schedule_id ORDER BY waiting_number) as new_number 
                    FROM token) t2
            SET t1.waiting_number = t2.new_number
            WHERE t1.token_id = t2.token_id
            AND t1.schedule_id = :scheduleId
            """, nativeQuery = true)
    @Modifying
    void updateWaitingNumbers(@Param("scheduleId") Long scheduleId);

    // 상위 10개 토큰 상태 변경
    @Query(value = """
            UPDATE token
            SET status = 'READY'
            WHERE schedule_id = :scheduleId
            AND waiting_number <= 10
            """, nativeQuery = true)
    @Modifying
    void updateStatusToReady(@Param("scheduleId") Long scheduleId);
}
