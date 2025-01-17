package com.concert.payment.service;

import com.concert.payment.component.PaymentManager;
import com.concert.payment.dto.request.PaymentRequest;
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
