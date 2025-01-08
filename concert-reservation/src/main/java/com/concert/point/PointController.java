package com.concert.point;

import com.concert.point.dto.request.PointChargeRequest;
import com.concert.point.dto.response.PointGetResponse;
import com.concert.point.usecase.ChargePointUseCase;
import com.concert.point.usecase.GetPointUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/point")
public class PointController {

    private final GetPointUseCase getPointUseCase;
    private final ChargePointUseCase chargePointUseCase;

    @Autowired
    public PointController(GetPointUseCase getPointUseCase, ChargePointUseCase chargePointUseCase) {
        this.getPointUseCase = getPointUseCase;
        this.chargePointUseCase = chargePointUseCase;
    }

    @PostMapping("/{userId}")
    public PointGetResponse getPoint(@RequestParam @PathVariable Long userId) {
        return getPointUseCase.execute(userId);
    }

    @PostMapping("/charge")
    public void chargePoint(@RequestBody PointChargeRequest request) {
        chargePointUseCase.execute(request);
    }
}
