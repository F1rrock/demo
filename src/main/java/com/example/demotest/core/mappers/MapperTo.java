package com.example.demotest.core.mappers;

@FunctionalInterface
public interface MapperTo<To, From> {
    To mapTo(From param) throws Exception;
}
