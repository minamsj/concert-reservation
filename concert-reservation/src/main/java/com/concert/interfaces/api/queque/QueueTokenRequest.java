package com.concert.interfaces.api.queque;

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
