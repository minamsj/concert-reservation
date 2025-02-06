package com.concert.interfaces.api.queque;

import com.concert.application.queque.QueueFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "토큰", description = "토큰")
@RestController
@RequestMapping("/api/queue")
@RequiredArgsConstructor
public class QueueController {

    private final QueueFacade queueFacade;

    @Operation(summary = "대기열 토큰 발급", description = "대기열 토큰을 발급합니다.")
    @PostMapping("/token")
    public ResponseEntity<QueueTokenResponse> issueToken(@RequestBody QueueTokenRequest reqeust) {
        return ResponseEntity.ok(queueFacade.issueToken(reqeust));
    }

    @Operation(summary = "대기열 토큰 업데이트", description = "대기열 토큰을 업데이트합니다.")
    @PatchMapping("/token")
    public ResponseEntity<QueueTokenResponse> updateQueueExpiredAt(@RequestParam String token) {
        return ResponseEntity.ok(queueFacade.updateQueueExpiredAt(token));
    }

    @Operation(summary = "대기열 조회", description = "대기열 조회합니다.")
    @GetMapping("/status/{token}")
    public ResponseEntity<QueueStatusResponse> getQueueStatus(@PathVariable String token) {
        return ResponseEntity.ok(queueFacade.getQueueStatus(token));
    }
}


