package com.concert.interfaces.api.point;

import com.concert.application.point.PointFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "포인트", description = "포인트")
@RestController
@RequestMapping("/api/point")
@RequiredArgsConstructor
public class PointController {

    private final PointFacade pointFacade;

    @Operation(summary = "포인트 조회", description = "포인트 조회하는 API")
    @PostMapping("/{userId}")
    public PointGetResponse getPoint(@RequestParam @PathVariable Long userId) {
        return pointFacade.getPoint(userId);
    }

    @Operation(summary = "포인트 충전", description = "포인트 충전하는 API")
    @PostMapping("/charge")
    public void chargePoint(@RequestBody PointChargeRequest request) {
        pointFacade.chargePoint(request);
    }
}
