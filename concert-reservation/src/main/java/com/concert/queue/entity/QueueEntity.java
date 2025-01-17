package com.concert.queue.entity;

import com.concert.domain.model.QueueStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "token")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QueueEntity {
    @Id
    @Column(name = "token_id")
    private String token;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "schedule_id")
    private Long scheduleId;
    @Column(name = "waiting_number")
    private Long waitingNumber;

    @Enumerated(EnumType.STRING)
    private QueueStatus status;
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "expired_at")
    private LocalDateTime expiredAt;

    @Builder
    public QueueEntity(String token, Long userId, Long scheduleId, Long waitingNumber, QueueStatus status, LocalDateTime createdAt, LocalDateTime expiredAt) {
        this.token = token;
        this.userId = userId;
        this.scheduleId = scheduleId;
        this.waitingNumber = waitingNumber;
        this.status = status;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
    }

    public void updateExpiredAt() {
        this.status = QueueStatus.READY;
        this.expiredAt = LocalDateTime.now().plusMinutes(3);
    }
}
