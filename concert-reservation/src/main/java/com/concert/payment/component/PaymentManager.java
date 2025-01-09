package com.concert.payment.component;

import com.concert.payment.dto.request.PaymentRequest;
import com.concert.payment.entity.Payment;
import com.concert.repositories.payment.ExpiredTokenRepository;
import com.concert.repositories.payment.PaymentRepository;
import com.concert.repositories.payment.PaymentReservationRepository;
import org.springframework.stereotype.Component;

@Component
public class PaymentManager {

    private final PaymentRepository paymentRepository;
    private final PaymentReservationRepository paymentReservationRepository;
    private final ExpiredTokenRepository expiredTokenRepository;

    public PaymentManager(PaymentRepository paymentRepository, PaymentReservationRepository paymentReservationRepository, ExpiredTokenRepository expiredTokenRepository) {
        this.paymentRepository = paymentRepository;
        this.paymentReservationRepository = paymentReservationRepository;
        this.expiredTokenRepository = expiredTokenRepository;
    }

    public void payment(PaymentRequest request) {
        if (request.getAmount() < 10000) throw new RuntimeException();
        
        // 예약 건 결제 완료 상태 변경
        paymentReservationRepository.paymentReservation(request);
        
        // 결제 내역 저장
        // DTO -> 엔티티 변환
        Payment payment = Payment.builder()
                .userId(request.getUserId())
                .amount(request.getAmount())
                .build();

        // 결제 내역 저장
        paymentRepository.save(payment);
        
        // 토큰 만료 처리
        expiredTokenRepository.expireToken(request);
    }
}
