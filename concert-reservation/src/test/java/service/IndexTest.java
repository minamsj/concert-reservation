package service;

import com.concert.ConcertApplication;
import com.concert.domain.reservation.ReservationService;
import com.concert.interfaces.api.reservation.ConcertScheduleResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@SpringBootTest(classes = ConcertApplication.class)  // ConcertApplication 클래스 지정
public class IndexTest {

    @Autowired
    private ReservationService reservationService; // 성능을 테스트할 서비스

    @Test
    public void testGetConcertSchedulesPerformance() {
        // 여러 번 실행하여 평균 측정
        int iterations = 100;
        long totalDuration = 0;

        for (int i = 0; i < iterations; i++) {
            Instant start = Instant.now();
            reservationService.getConcertSchedules(1L);
            Instant end = Instant.now();
            totalDuration += Duration.between(start, end).toMillis();
        }

        double averageTime = totalDuration / (double) iterations;
        System.out.println("Average execution time: " + averageTime + " ms");
    }
}
