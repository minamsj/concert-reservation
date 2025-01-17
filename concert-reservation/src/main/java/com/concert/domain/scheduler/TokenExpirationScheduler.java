package com.concert.domain.scheduler;

import com.concert.repositories.queue.QueueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TokenExpirationScheduler {
    private final QueueRepository queueRepository;

    @Scheduled(fixedRate = 1000) // 1초마다 실행
    @Transactional
    public void checkAndUpdateExpiredTokens() {
        LocalDateTime now = LocalDateTime.now();
        queueRepository.deleteByExpiredAt(now);
    }
}
