package com.concert.reservation;

import com.concert.reservation.dto.request.ReservationRequest;
import com.concert.reservation.dto.response.*;
import com.concert.reservation.service.GetConcertSchedulesService;
import com.concert.reservation.service.GetConcertService;
import com.concert.reservation.service.GetSeatService;
import com.concert.reservation.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "콘서트", description = "콘서트")
@RestController
@RequestMapping("/api/concert")
@RequiredArgsConstructor
public class ReservationController {

    private final GetConcertSchedulesService getConcertSchedulesService;
    private final GetConcertService getConcertService;
    private final GetSeatService getSeatService;
    private final ReservationService reservationService;

    @Operation(summary = "콘서트 조회", description = "콘서트 조회하는 API")
    @GetMapping("")
    public List<ConcertResponse> getConcert() {
        return getConcertService.execute();
    }

    @Operation(summary = "콘서트 날짜, 회차 조회", description = "콘서트 날짜, 회차 조회하는 API")
    @GetMapping("/{concertId}")
    public List<ConcertScheduleResponse> getConcertSchedules(@RequestParam Long concertId) {
        return getConcertSchedulesService.execute(concertId);
    }

    @Operation(summary = "콘서트 좌석 조회", description = "콘서트 좌석 조회하는 API")
    @GetMapping("/seats")
    public List<ConcertSeatResponse> getConcert(@RequestParam Long scheduleId) {
        return getSeatService.execute(scheduleId);
    }

    @Operation(summary = "콘서트 예약", description = "콘서트 예약하는 API")
    @PostMapping("/reservation")
    public void createReservation(@RequestBody ReservationRequest request) {
        reservationService.createReservation(request);
    }
}
