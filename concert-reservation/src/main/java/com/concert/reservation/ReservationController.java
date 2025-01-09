package com.concert.reservation;

import com.concert.point.usecase.ChargePointUseCase;
import com.concert.reservation.dto.response.ConcertResponse;
import com.concert.reservation.dto.response.ConcertScheduleResponse;
import com.concert.reservation.dto.response.ConcertSeatResponse;
import com.concert.reservation.usecase.GetConcertSchedulesUseCase;
import com.concert.reservation.usecase.GetConcertUseCase;
import com.concert.reservation.usecase.GetSeatUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "콘서트", description = "콘서트")
@RestController
@RequestMapping("/api/concert")
public class ReservationController {

    private final GetConcertSchedulesUseCase getConcertSchedulesUseCase;
    private final GetConcertUseCase getConcertUseCase;
    private final GetSeatUseCase getSeatUseCase;

    @Autowired
    public ReservationController(GetConcertSchedulesUseCase getConcertSchedulesUseCase, GetConcertUseCase getConcertUseCase, GetSeatUseCase getSeatUseCase) {
        this.getConcertSchedulesUseCase = getConcertSchedulesUseCase;
        this.getConcertUseCase = getConcertUseCase;
        this.getSeatUseCase = getSeatUseCase;
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
}
