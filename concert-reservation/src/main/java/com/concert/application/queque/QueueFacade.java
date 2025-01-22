package com.concert.application.queque;

import com.concert.domain.model.Queue;
import com.concert.interfaces.api.queque.QueueTokenRequest;
import com.concert.interfaces.api.queque.QueueStatusResponse;
import com.concert.interfaces.api.queque.QueueTokenResponse;
import com.concert.domain.queque.QueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@RequiredArgsConstructor
public class QueueFacade {

    private final QueueService queueService;

    @Transactional
    public QueueTokenResponse issueToken(QueueTokenRequest reqeust) {
        queueService.deleteQueue(reqeust.getUserId(), reqeust.getScheduleId());

        Queue queue = queueService.createQueue(reqeust.getUserId(), reqeust.getScheduleId());
        return QueueTokenResponse.from(queue);
    }

    @Transactional
    public QueueTokenResponse updateQueueExpiredAt(String token) {
        Queue queue = queueService.updateQueueExpiredAt(token);
        return QueueTokenResponse.from(queue);
    }

    @Transactional
    public QueueStatusResponse getQueueStatus(String token) {
        Queue queue = queueService.getQueue(token);
        return QueueStatusResponse.from(queue);
    }
}
