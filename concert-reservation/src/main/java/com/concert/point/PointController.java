package com.concert.point;

import com.concert.point.dto.request.PointChargeRequest;
import com.concert.point.dto.response.PointGetResponse;
import com.concert.point.service.ChargePointService;
import com.concert.point.service.GetPointService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "포인트", description = "포인트")
@RestController
@RequestMapping("/api/point")
@RequiredArgsConstructor
public class PointController {

    private final GetPointService getPointService;
    private final ChargePointService chargePointService;

    @Operation(summary = "포인트 조회", description = "포인트 조회하는 API")
    @PostMapping("/{userId}")
    public PointGetResponse getPoint(@RequestParam @PathVariable Long userId) {
        return getPointService.execute(userId);
    }

    @Operation(summary = "포인트 충전", description = "포인트 충전하는 API")
    @PostMapping("/charge")
    public void chargePoint(@RequestBody PointChargeRequest request) {
        chargePointService.execute(request);
    }
}
