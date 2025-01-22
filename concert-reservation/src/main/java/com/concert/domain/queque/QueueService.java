package com.concert.domain.queque;

import com.concert.domain.model.Queue;
import com.concert.intrastructure.queque.QueueRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.undo.CannotUndoException;

@Service
@RequiredArgsConstructor
public class QueueService {

    private final QueueRepositoryImpl QueueRepositoryImpl;

    public void deleteQueue(Long userId, Long scheduleId) {
        QueueRepositoryImpl.deleteQueue(userId, scheduleId); // QueueRepositoryImpl에 위임
    }

    public Queue createQueue(Long userId, Long scheduleId) {
        Long waitingNumber = generateWaitingNumber();
        Queue queue = Queue.createNew(userId, scheduleId, waitingNumber);
        QueueEntity entity = QueueEntity.builder()
                .token(queue.getToken())
                .userId(queue.getUserId())
                .scheduleId(queue.getScheduleId())
                .waitingNumber(queue.getWaitingNumber())
                .status(queue.getStatus())
                .createdAt(queue.getCreatedAt())
                .build();

        QueueRepositoryImpl.save(entity);
        return queue;
    }

    public Queue updateQueueExpiredAt(String token) {
        QueueEntity queueEntity = QueueRepositoryImpl.findByToken(token)
                .orElseThrow(CannotUndoException::new);

        queueEntity.updateExpiredAt();
        return Queue.from(queueEntity);
    }

    public Queue getQueue(String token) {
        QueueEntity entity = QueueRepositoryImpl.findByToken(token)
                .orElseThrow(() -> new RuntimeException("대기열 정보를 찾을 수 없습니다."));
        return Queue.from(entity);
    }

    private Long generateWaitingNumber() {
        return QueueRepositoryImpl.findMaxWaitingNumber()
                .map(max -> max + 1L)
                .orElse(1L);
    }
}
