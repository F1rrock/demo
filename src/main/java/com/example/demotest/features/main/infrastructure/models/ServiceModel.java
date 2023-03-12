package com.example.demotest.features.main.infrastructure.models;

import java.util.Set;

public record ServiceModel(
        int uid,
        double cost,
        String description,
        double discount,
        int duration,
        String mainImgPath,
        String title,
        Set<String> photoPaths
) {
}
