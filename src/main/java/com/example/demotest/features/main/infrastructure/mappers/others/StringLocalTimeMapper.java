package com.example.demotest.features.main.infrastructure.mappers.others;

import com.example.demotest.core.mappers.Mapper;

import java.time.LocalTime;

public final class StringLocalTimeMapper implements Mapper<String, LocalTime> {
    @Override
    public String mapTo(LocalTime param) throws Exception {
        return param.toString();
    }

    @Override
    public LocalTime mapFrom(String param) {
        return LocalTime.parse(param);
    }
}
