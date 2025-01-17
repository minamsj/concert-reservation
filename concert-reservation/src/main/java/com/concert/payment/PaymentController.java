package com.concert.payment;

import com.concert.payment.dto.request.PaymentRequest;
import com.concert.payment.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "결제", description = "결제")
@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @Operation(summary = "포인트 결제", description = "포인트 결제하는 API")
    @PostMapping("/{concertId}")
    public void payment(@RequestBody PaymentRequest request) {
        paymentService.execute(request);
    }
}
