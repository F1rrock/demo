package com.example.demotest.features.main.domain.repositories;

import com.example.demotest.core.errors.failures.Failure;
import com.example.demotest.external.types.either.Either;
import com.example.demotest.features.main.domain.entities.RecordEntity;

import java.util.List;

public interface RecordRepository {
    Either<Failure, List<RecordEntity>> selectNearRecords();
    Either<Failure, List<RecordEntity>> insertRecord(RecordEntity record);
}
