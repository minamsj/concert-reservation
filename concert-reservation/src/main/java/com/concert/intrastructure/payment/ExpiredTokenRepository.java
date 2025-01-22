package com.concert.intrastructure.payment;

import com.concert.domain.payment.ExpiredToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpiredTokenRepository extends JpaRepository<ExpiredToken, Long> {
}
