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

    // 서비스로, 도메인이 아님
    @Scheduled(fixedRate = 1000) // 1초마다 실행
    @Transactional
    public void checkAndUpdateExpiredTokens() {
        LocalDateTime now = LocalDateTime.now();
        queueJpaRepository.deleteByExpiredAt(now);
    }
}
