package com.example.demotest.features.main.domain.use_cases.data_cases.record_cases.concretes;

import com.example.demotest.core.errors.failures.Failure;
import com.example.demotest.external.types.either.Either;
import com.example.demotest.features.main.domain.entities.RecordEntity;
import com.example.demotest.features.main.domain.repositories.RecordRepository;
import com.example.demotest.features.main.domain.use_cases.data_cases.record_cases.RecordCase;

import java.util.List;

public final class AddNewRecord extends RecordCase<RecordEntity, RecordEntity> {
    public AddNewRecord(RecordRepository repository) {
        super(repository);
    }

    @Override
    public Either<Failure, List<RecordEntity>> call(RecordEntity param) {
        return repository.insertRecord(param);
    }
}
