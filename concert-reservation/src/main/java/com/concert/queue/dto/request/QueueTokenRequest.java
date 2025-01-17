package com.concert.queue.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class QueueTokenRequest {
    Long userId;
    Long scheduleId;
}
