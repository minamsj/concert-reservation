package com.concert.queue.facade;

import com.concert.domain.model.Queue;
import com.concert.queue.dto.request.QueueTokenRequest;
import com.concert.queue.dto.response.QueueStatusResponse;
import com.concert.queue.dto.response.QueueTokenResponse;
import com.concert.queue.service.QueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class QueueFacade {

    private final QueueService queueService;

    public QueueTokenResponse issueToken(QueueTokenRequest reqeust) {
        queueService.deleteQueue(reqeust.getUserId(), reqeust.getScheduleId());

        Queue queue = queueService.createQueue(reqeust.getUserId(), reqeust.getScheduleId());
        return QueueTokenResponse.from(queue);
    }

    public QueueTokenResponse updateQueueExpiredAt(String token) {
        Queue queue = queueService.updateQueueExpiredAt(token);
        return QueueTokenResponse.from(queue);
    }

    public QueueStatusResponse getQueueStatus(String token) {
        Queue queue = queueService.getQueue(token);
        return QueueStatusResponse.from(queue);
    }
}
