package com.example.demotest.features.main.domain.use_cases.data_cases.record_cases;

import com.example.demotest.core.errors.failures.Failure;
import com.example.demotest.external.types.either.Either;
import com.example.demotest.features.main.domain.repositories.RecordRepository;
import com.example.demotest.features.main.domain.use_cases.data_cases.DataCase;

import java.util.List;

public abstract class RecordCase<Type, Param> implements DataCase<Type, Param> {
    protected final RecordRepository repository;

    protected RecordCase(RecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public abstract Either<Failure, List<Type>> call(Param param);
}
