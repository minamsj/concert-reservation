package com.concert.interfaces.api.reservation;

import com.concert.domain.reservation.DataGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/data")
public class DataGeneratorController {
    private final DataGenerator dataGenerator;

    @PostMapping("/generate")
    public ResponseEntity<String> generateData() {
        long startTime = System.currentTimeMillis();
        dataGenerator.generateData();
        long duration = System.currentTimeMillis() - startTime;
        return ResponseEntity.ok("Generated in: " + duration + "ms");
    }
}
