package com.concert.point;

import com.concert.point.dto.request.PointChargeRequest;
import com.concert.point.dto.response.PointGetResponse;
import com.concert.point.usecase.ChargePointUseCase;
import com.concert.point.usecase.GetPointUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "포인트", description = "포인트")
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

    @Operation(summary = "포인트 조회", description = "포인트 조회하는 API")
    @PostMapping("/{userId}")
    public PointGetResponse getPoint(@RequestParam @PathVariable Long userId) {
        return getPointUseCase.execute(userId);
    }

    @Operation(summary = "포인트 충전", description = "포인트 충전하는 API")
    @PostMapping("/charge")
    public void chargePoint(@RequestBody PointChargeRequest request) {
        chargePointUseCase.execute(request);
    }
}
