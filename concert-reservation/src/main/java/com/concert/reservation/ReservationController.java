package com.concert.reservation;

import com.concert.point.usecase.ChargePointUseCase;
import com.concert.reservation.dto.request.ReservationRequest;
import com.concert.reservation.dto.response.*;
import com.concert.reservation.usecase.GetConcertSchedulesUseCase;
import com.concert.reservation.usecase.GetConcertUseCase;
import com.concert.reservation.usecase.GetSeatUseCase;
import com.concert.reservation.usecase.ReservationUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "콘서트", description = "콘서트")
@RestController
@RequestMapping("/api/concert")
public class ReservationController {

    private final GetConcertSchedulesUseCase getConcertSchedulesUseCase;
    private final GetConcertUseCase getConcertUseCase;
    private final GetSeatUseCase getSeatUseCase;
    private final ReservationUseCase reservationUseCase;


    @Autowired
    public ReservationController(GetConcertSchedulesUseCase getConcertSchedulesUseCase, GetConcertUseCase getConcertUseCase, GetSeatUseCase getSeatUseCase, ReservationUseCase reservationUseCase) {
        this.getConcertSchedulesUseCase = getConcertSchedulesUseCase;
        this.getConcertUseCase = getConcertUseCase;
        this.getSeatUseCase = getSeatUseCase;
        this.reservationUseCase = reservationUseCase;
    }

    @Operation(summary = "콘서트 조회", description = "콘서트 조회하는 API")
    @GetMapping("")
    public List<ConcertResponse> getConcert() {
        return getConcertUseCase.execute();
    }

    @Operation(summary = "콘서트 날짜, 회차 조회", description = "콘서트 날짜, 회차 조회하는 API")
    @GetMapping("/{concertId}")
    public List<ConcertScheduleResponse> getConcertSchedules(@RequestParam Long concertId) {
        return getConcertSchedulesUseCase.execute(concertId);
    }

    @Operation(summary = "콘서트 좌석 조회", description = "콘서트 좌석 조회하는 API")
    @GetMapping("/seats")
    public List<ConcertSeatResponse> getConcert(@RequestParam Long scheduleId) {
        return getSeatUseCase.execute(scheduleId);
    }

    @Operation(summary = "콘서트 예약", description = "콘서트 예약하는 API")
    @PostMapping("/reservation")
    public void createReservation(@RequestBody ReservationRequest request) {
        reservationUseCase.createReservation(request);
    }
}
