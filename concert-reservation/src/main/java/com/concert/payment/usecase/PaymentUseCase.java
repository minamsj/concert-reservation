package com.concert.payment.usecase;

import com.concert.payment.component.PaymentManager;
import com.concert.payment.dto.request.PaymentRequest;
import org.springframework.stereotype.Service;

@Service
public class PaymentUseCase {

    private final PaymentManager paymentManager;

    public PaymentUseCase(PaymentManager paymentManager) {
        this.paymentManager = paymentManager;
    }

    public void execute(PaymentRequest request) {
        paymentManager.payment(request);
    }
}
