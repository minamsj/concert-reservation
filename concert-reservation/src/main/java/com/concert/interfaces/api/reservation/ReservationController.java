package com.concert.interfaces.api.reservation;

import com.concert.application.reservation.ReqservationFacade;
import com.concert.domain.reservation.GetConcertSchedulesService;
import com.concert.domain.reservation.GetConcertService;
import com.concert.domain.reservation.GetSeatService;
import com.concert.domain.reservation.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
    private final ReqservationFacade reqservationFacade;

    @Operation(summary = "콘서트 조회", description = "콘서트 조회하는 API")
    @GetMapping("")
    public List<ConcertResponse> getConcert() {
        return getConcertService.execute();
    }

    @Operation(summary = "콘서트 날짜, 회차 조회", description = "콘서트 날짜, 회차 조회하는 API")
    @GetMapping("/{concertId}")
    public List<ConcertScheduleResponse> getConcertSchedules(@RequestParam Long concertId) {
        return reqservationFacade.getConcertShedules(concertId);
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
