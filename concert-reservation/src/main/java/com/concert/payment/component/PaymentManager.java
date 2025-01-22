package com.concert.payment.component;

import com.concert.interfaces.api.payment.PaymentRequest;
import com.concert.domain.payment.Payment;
import com.concert.intrastructure.payment.PaymentRepository;
import com.concert.intrastructure.payment.PaymentReservationRepository;
import com.concert.intrastructure.point.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentManager {

    private final PaymentRepository paymentRepository;
    private final PaymentReservationRepository paymentReservationRepository;
    private final PointRepository pointRepository;

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
