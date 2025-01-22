package com.concert.domain.payment;

import com.concert.payment.component.PaymentManager;
import com.concert.interfaces.api.payment.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentManager paymentManager;

    public void execute(PaymentRequest request) {
        paymentManager.payment(request);
    }
}
