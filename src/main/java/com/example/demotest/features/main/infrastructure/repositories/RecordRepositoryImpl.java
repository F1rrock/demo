package com.example.demotest.features.main.infrastructure.repositories;

import com.example.demotest.core.errors.exceptions.ServerException;
import com.example.demotest.core.errors.failures.Failure;
import com.example.demotest.core.errors.failures.concretes.ServerFailure;
import com.example.demotest.core.mappers.Mapper;
import com.example.demotest.external.types.either.Either;
import com.example.demotest.external.types.either.Left;
import com.example.demotest.external.types.either.Right;
import com.example.demotest.features.main.domain.entities.RecordEntity;
import com.example.demotest.features.main.domain.repositories.RecordRepository;
import com.example.demotest.features.main.infrastructure.datasources.remote_datasources.RecordRemoteDatasource;
import com.example.demotest.features.main.infrastructure.models.RecordModel;

import java.util.ArrayList;
import java.util.List;

public final class RecordRepositoryImpl implements RecordRepository {
    private final RecordRemoteDatasource remoteDatasource;
    private final Mapper<RecordEntity, RecordModel> mapper;

    public RecordRepositoryImpl(RecordRemoteDatasource remoteDatasource, Mapper<RecordEntity, RecordModel> mapper) {
        this.remoteDatasource = remoteDatasource;
        this.mapper = mapper;
    }

    @Override
    public Either<Failure, List<RecordEntity>> selectNearRecords() {
        final List<RecordModel> models;
        try {
            models = remoteDatasource.selectNearRecords();
        } catch (ServerException e) {
            return new Left<>(
                    new ServerFailure(e.getMessage())
            );
        }
        final List<RecordEntity> entities = new ArrayList<>();
        for (var model : models) {
            try {
                entities.add(mapper.mapTo(model));
            } catch (Exception e) {
                return new Left<>(
                        new ServerFailure(e.getMessage())
                );
            }
        }
        return new Right<>(entities);
    }

    @Override
    public Either<Failure, List<RecordEntity>> insertRecord(RecordEntity record) {
        try {
            remoteDatasource.insertRecord(
                    mapper.mapFrom(record)
            );
        } catch (Exception e) {
            return new Left<>(
                    new ServerFailure(e.getMessage())
            );
        }
        return selectNearRecords();
    }
}
