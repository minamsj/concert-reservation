package com.concert.payment;

import com.concert.payment.dto.request.PaymentRequest;
import com.concert.payment.usecase.PaymentUseCase;
import com.concert.reservation.dto.response.ConcertScheduleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "결제", description = "결제")
@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentUseCase paymentUseCase;

    public PaymentController(PaymentUseCase paymentUseCase) {
        this.paymentUseCase = paymentUseCase;
    }

    @Operation(summary = "포인트 결제", description = "포인트 결제하는 API")
    @PostMapping("/{concertId}")
    public void payment(@RequestBody PaymentRequest request) {
        paymentUseCase.execute(request);
    }
}
