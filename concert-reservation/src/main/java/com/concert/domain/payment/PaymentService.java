package com.concert.domain.payment;

import com.concert.infrastructure.payment.PaymentRepository;
import com.concert.infrastructure.payment.PaymentReservationRepository;
import com.concert.interfaces.api.payment.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentReservationRepository paymentReservationRepository;

    public void payment(PaymentRequest request) {
        if (request.getAmount() < 10000) throw new RuntimeException();

        // 예약 건 결제 완료 상태 변경
        paymentReservationRepository.paymentReservation(request);

        // 결제 내역 저장
        Payment payment = Payment.builder()
                .userId(request.getUserId())
                .amount(request.getAmount())
                .build();

        // 결제 내역 저장
        paymentRepository.save(payment);
    }
}
