package com.example.demotest.features.main.infrastructure.models;

public record ClientModel(
        int uid,
        String name,
        String secondName,
        String lastName,
        String email,
        String phone
) {
}
