package com.concert.queue.service;

import com.concert.domain.model.Queue;
import com.concert.queue.entity.QueueEntity;
import com.concert.repositories.queue.QueueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.undo.CannotUndoException;

@Service
@RequiredArgsConstructor
public class QueueService {

    private final QueueRepository queueRepository;

    public void deleteQueue(Long userId, Long scheduleId) {
        queueRepository.findByUserIdAndScheduleId(userId, scheduleId)
                .ifPresent(queueRepository::delete);
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

        queueRepository.save(entity);
        return queue;
    }

    public Queue updateQueueExpiredAt(String token) {
        QueueEntity queueEntity = queueRepository.findByToken(token)
                .orElseThrow(CannotUndoException::new);

        queueEntity.updateExpiredAt();
        queueRepository.flush();
        return Queue.from(queueEntity);
    }

    public Queue getQueue(String token) {
        QueueEntity entity = queueRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("대기열 정보를 찾을 수 없습니다."));
        return Queue.from(entity);
    }

    private Long generateWaitingNumber() {
        return queueRepository.findMaxWaitingNumber()
                .map(max -> max + 1L)
                .orElse(1L);
    }
}
