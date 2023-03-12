package com.example.demotest.core.mappers;

public interface Mapper<To, From> extends MapperFrom<To, From>, MapperTo<To, From> {
    @Override
    To mapTo(From param) throws Exception;
    @Override
    From mapFrom(To param) throws Exception;
}
