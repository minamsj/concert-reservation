package service;

import com.concert.domain.model.Queue;
import com.concert.domain.model.QueueStatus;
import com.concert.intrastructure.queque.QueueRepositoryImpl;
import com.concert.domain.queque.QueueEntity;
import com.concert.domain.queque.QueueService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class QueueServiceTest {
    @InjectMocks
    private QueueService queueService;

    @Mock
    private QueueRepositoryImpl queueJpaRepository;

    @Test
    @DisplayName("대기열 생성 성공")
    void createQueue_Success() {
        // given
        Long userId = 1L;
        Long scheduleId = 13L;
        given(queueJpaRepository.findMaxWaitingNumber())
                .willReturn(Optional.of(10L));
        given(queueJpaRepository.save(any(QueueEntity.class)))
                .willAnswer(invocation -> invocation.getArgument(0));

        // when
        Queue queue = queueService.createQueue(userId, scheduleId);

        // then
        assertThat(queue.getUserId()).isEqualTo(userId);
        assertThat(queue.getWaitingNumber()).isEqualTo(11);
        assertThat(queue.getStatus()).isEqualTo(QueueStatus.WAITING);
        assertThat(queue.getToken()).isNotNull();
        verify(queueJpaRepository).save(any(QueueEntity.class));
    }

    @Test
    @DisplayName("대기열 상태 조회 성공")
    void getQueue_Success() {
        // given
        String token = UUID.randomUUID().toString();
        QueueEntity mockEntity = QueueEntity.builder()
                .token(token)
                .userId(1L)
                .waitingNumber(1L)
                .status(QueueStatus.WAITING)
                .build();

        given(queueJpaRepository.findByToken(token))
                .willReturn(Optional.of(mockEntity));

        // when
        Queue queue = queueService.getQueue(token);

        // then
        assertThat(queue.getToken()).isEqualTo(token);
        assertThat(queue.getStatus()).isEqualTo(QueueStatus.WAITING);
        verify(queueJpaRepository).findByToken(token);
    }
}

