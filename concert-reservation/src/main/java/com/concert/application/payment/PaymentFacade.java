package com.concert.application.payment;

import com.concert.domain.payment.PaymentService;
import com.concert.interfaces.api.payment.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentFacade {

    private final PaymentService paymentService;

    public void payment(PaymentRequest request) {
        paymentService.payment(request);
    }

}
