package com.concert.interfaces.api.queque;

import com.concert.domain.model.Queue;
import com.concert.domain.model.QueueStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class QueueStatusResponse {
    private String token;
    private QueueStatus status;
    private LocalDateTime expiredAt;

    public static QueueStatusResponse from(Queue queue) {
        return QueueStatusResponse.builder()
                .token(queue.getToken())
                .status(queue.getStatus())
                .expiredAt(queue.getCreatedAt())
                .build();
    }
}
