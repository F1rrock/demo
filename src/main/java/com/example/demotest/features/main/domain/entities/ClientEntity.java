package com.example.demotest.features.main.domain.entities;

public record ClientEntity(
        int uid,
        String fio,
        String email,
        String phone
) {
}
