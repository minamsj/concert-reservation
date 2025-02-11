package service;

import com.concert.ConcertApplication;
import com.concert.domain.reservation.ReservationService;
import com.concert.interfaces.api.reservation.ConcertResponse;
import com.concert.infrastructure.reservation.ConcertRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

@Import(TestRedisConfig.class)
@SpringBootTest(
        classes = ConcertApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@EnableCaching
@TestPropertySource(properties = {
        "spring.cache.type=redis",
        "spring.data.redis.host=localhost",
        "spring.data.redis.port=6379",
        "spring.data.redis.timeout=3000",
        "spring.datasource.url=jdbc:mariadb://localhost:3306/concert",
        "spring.datasource.username=root",
        "spring.datasource.password=ub!02$",
        "spring.datasource.driver-class-name=org.mariadb.jdbc.Driver",
        "spring.jpa.database-platform=org.hibernate.dialect.MariaDB103Dialect",
        "spring.jpa.hibernate.ddl-auto=update",
        "spring.jpa.show-sql=true"
})
class ReservationServiceCacheTest {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ConcertRepository concertRepository;

    @Autowired
    private RedisCacheManager cacheManager;


    @Test
    @DisplayName("Redis 캐시 성능 테스트")
    void getConcert_CachePerformance() {
        // given
        int numberOfRequests = 100;

        // when - 캐시 적용 전 (캐시 비우기)
        cacheManager.getCache("concerts").clear();

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < numberOfRequests; i++) {
            reservationService.getConcert();
        }
        long withoutCacheTime = System.currentTimeMillis() - startTime;

        // when - 캐시 적용 후 (첫 번째 호출로 캐시 준비)
        reservationService.getConcert();

        startTime = System.currentTimeMillis();
        for (int i = 0; i < numberOfRequests; i++) {
            reservationService.getConcert();
        }
        long withCacheTime = System.currentTimeMillis() - startTime;

        // then
        System.out.println("Without Cache Time: " + withoutCacheTime + "ms");
        System.out.println("With Cache Time: " + withCacheTime + "ms");
        System.out.println("Performance Improvement: " +
                ((withoutCacheTime - withCacheTime) / (double)withoutCacheTime * 100) + "%");

        assertThat(withCacheTime)
                .isLessThan(withoutCacheTime)
                .as("캐시 적용 후 성능이 개선되어야 합니다");
    }

    @Test
    @DisplayName("Redis 캐시 동작 검증")
    void getConcert_CacheOperation() {
        // given
        cacheManager.getCache("concerts").clear(); // 캐시 초기화

        // when - 첫 번째 호출 (캐시 미스)
        List<ConcertResponse> firstCall = reservationService.getConcert();

        // when - 두 번째 호출 (캐시 히트)
        List<ConcertResponse> secondCall = reservationService.getConcert();

        // then
        // 객체의 내용을 상세히 비교
        assertThat(secondCall)
                .hasSize(firstCall.size())
                .usingRecursiveComparison()
                .ignoringFieldsOfTypes(Class.class)
                .isEqualTo(firstCall);

        // 각 객체의 상세 내용 확인
        for (int i = 0; i < firstCall.size(); i++) {
            assertThat(secondCall.get(i).getConcert_id())
                    .isEqualTo(firstCall.get(i).getConcert_id());
            assertThat(secondCall.get(i).getConcert_title())
                    .isEqualTo(firstCall.get(i).getConcert_title());
        }
    }
}
