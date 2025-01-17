package com.concert.point.service;

import com.concert.point.component.PointManager;
import com.concert.point.dto.response.PointGetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPointService {

    private final PointManager pointManager;

    public PointGetResponse execute(Long userId) {
        Long point = pointManager.getPoint(userId);
        return new PointGetResponse(point);
    }
}
