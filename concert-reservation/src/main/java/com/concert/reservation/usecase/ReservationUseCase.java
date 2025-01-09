package com.concert.reservation.usecase;

import com.concert.reservation.component.ReservationManager;
import com.concert.reservation.dto.request.ReservationRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class ReservationUseCase {

    private final ReservationManager reservationManager;

    public ReservationUseCase(ReservationManager reservationManager) {
        this.reservationManager = reservationManager;
    }

    public void createReservation(@Valid ReservationRequest request) {
        reservationManager.createReservation(request);
    }
}
