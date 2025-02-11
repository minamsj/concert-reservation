package com.concert.domain.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class DataGenerator {
    private final JdbcTemplate jdbcTemplate;
    private final int TOTAL_RECORDS = 1_000_000;
    private final int BATCH_SIZE = 10_000;

    @Transactional
    public void generateData() {
        long startTime = System.currentTimeMillis();  // 시작 시간 측정

        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (int i = 0; i < TOTAL_RECORDS; i += BATCH_SIZE) {
            final int start = i;
            futures.add(CompletableFuture.runAsync(() -> {
                insertBatch(start, Math.min(start + BATCH_SIZE, TOTAL_RECORDS));
            }, executor));
        }

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        executor.shutdown();

        long endTime = System.currentTimeMillis();  // 종료 시간 측정
        long duration = endTime - startTime;  // 걸린 시간 계산

        System.out.println("Data generation completed in: " + duration + " ms");
    }

    private void insertBatch(int start, int end) {
        String sql = "INSERT INTO concert_schedules (concert_id, schedules_date, concert_price) VALUES (?, ?, ?)";
        List<Object[]> batchArgs = new ArrayList<>();

        for (int i = start; i < end; i++) {
            batchArgs.add(new Object[]{
                    1,
                    LocalDateTime.now().plusDays(ThreadLocalRandom.current().nextInt(1, 366)),
                    ThreadLocalRandom.current().nextInt(1, 9) * 100
            });
        }

        jdbcTemplate.batchUpdate(sql, batchArgs);
    }
}

