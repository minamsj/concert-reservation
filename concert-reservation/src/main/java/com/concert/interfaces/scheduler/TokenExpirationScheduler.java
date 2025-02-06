package com.concert.interfaces.scheduler;

import com.concert.intrastructure.queque.QueueJpaRepository;
import com.concert.intrastructure.queque.QueueRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TokenExpirationScheduler {
    private final QueueRepositoryImpl queueJpaRepository;

    @Scheduled(fixedRate = 1000)
    @Transactional
    public void checkAndUpdateExpiredTokens() {
        LocalDateTime now = LocalDateTime.now();
        queueJpaRepository.processExpiredTokens(now);

    }
}
