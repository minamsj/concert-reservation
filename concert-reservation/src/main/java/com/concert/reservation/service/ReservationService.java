package com.concert.reservation.service;

import com.concert.reservation.component.ReservationManager;
import com.concert.reservation.dto.request.ReservationRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationManager reservationManager;

    public void createReservation(@Valid ReservationRequest request) {
        reservationManager.createReservation(request);
    }
}
