package com.example.demotest.core.mappers;

@FunctionalInterface
public interface MapperFrom<To, From> {
    From mapFrom(To param) throws Exception;
}
