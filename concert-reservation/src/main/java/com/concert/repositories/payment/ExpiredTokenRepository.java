package com.concert.repositories.payment;

import com.concert.payment.dto.request.PaymentRequest;
import com.concert.payment.entity.ExpiredToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ExpiredTokenRepository extends JpaRepository<ExpiredToken, Long> {
}
