package com.example.demotest.features.main.domain.entities;

import java.util.Set;

public record ServiceEntity(
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
