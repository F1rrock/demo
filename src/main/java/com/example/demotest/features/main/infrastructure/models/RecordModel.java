package com.example.demotest.features.main.infrastructure.models;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record RecordModel(
        ServiceModel service,
        ClientModel client,
        LocalTime leftTime,
        LocalDateTime beginningDateTime
) {
}
