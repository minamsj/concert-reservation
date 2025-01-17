package com.concert.domain.model;

import com.concert.queue.entity.QueueEntity;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Queue {
    private final String token;
    private final Long userId;
    private final Long scheduleId;
    private final Long waitingNumber;
    private final QueueStatus status;
    private final LocalDateTime createdAt;
    private final LocalDateTime expiredAt;

    private Queue(String token, Long userId, Long scheduleId, Long waitingNumber, QueueStatus status, LocalDateTime createdAt, LocalDateTime expiredAt) {
        this.token = token;
        this.userId = userId;
        this.scheduleId = scheduleId;
        this.waitingNumber = waitingNumber;
        this.status = status;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
    }

    public static Queue createNew(Long userId, Long scheduleId, Long waitingNumber) {
        return new Queue(
                UUID.randomUUID().toString(),
                userId,
                scheduleId,
                waitingNumber,
                QueueStatus.WAITING,
                LocalDateTime.now(),
                null
        );
    }

    public static Queue from(QueueEntity entity) {
        return new Queue(
                entity.getToken(),
                entity.getUserId(),
                entity.getScheduleId(),
                entity.getWaitingNumber(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getExpiredAt()
        );
    }
}
