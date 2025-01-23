package com.concert.interfaces.api.payment;

import com.concert.application.payment.PaymentFacade;
import com.concert.domain.payment.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "결제", description = "결제")
@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentFacade paymentFacade;

    @Operation(summary = "포인트 결제", description = "포인트 결제하는 API")
    @PostMapping("/{concertId}")
    public void payment(@RequestBody PaymentRequest request) {
        paymentFacade.payment(request);
    }
}
