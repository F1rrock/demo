package com.example.demotest.features.main.domain.entities;

import java.time.LocalDateTime;

public record RecordEntity(
        ServiceEntity service,
        ClientEntity client,
        String leftTime,
        LocalDateTime finishTime
) {
}
