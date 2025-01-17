package com.concert.queue.dto.response;

import com.concert.domain.model.Queue;
import com.concert.domain.model.QueueStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QueueTokenResponse {

    private String token;
    private Long waitingNumber;
    private QueueStatus status;

    public static QueueTokenResponse from(Queue queue) {
        return QueueTokenResponse.builder()
                .token(queue.getToken())
                .waitingNumber(queue.getWaitingNumber())
                .status(queue.getStatus())
                .build();
    }
}
