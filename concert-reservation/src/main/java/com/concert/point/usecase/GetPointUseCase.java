package com.concert.point.usecase;

import com.concert.point.component.PointManager;
import com.concert.point.dto.response.PointGetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetPointUseCase {

    private final PointManager pointManager;

    @Autowired
    public GetPointUseCase(PointManager pointManager) {
        this.pointManager = pointManager;
    }

    public PointGetResponse execute(Long userId) {
        Long point = pointManager.getPoint(userId);
        return new PointGetResponse(point);
    }
}
