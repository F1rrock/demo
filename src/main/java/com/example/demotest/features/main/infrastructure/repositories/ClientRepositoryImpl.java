package com.example.demotest.features.main.infrastructure.repositories;

import com.example.demotest.core.errors.exceptions.ServerException;
import com.example.demotest.core.errors.failures.Failure;
import com.example.demotest.core.errors.failures.concretes.ServerFailure;
import com.example.demotest.core.mappers.Mapper;
import com.example.demotest.external.types.either.Either;
import com.example.demotest.external.types.either.Left;
import com.example.demotest.external.types.either.Right;
import com.example.demotest.features.main.domain.entities.ClientEntity;
import com.example.demotest.features.main.domain.repositories.ClientRepository;
import com.example.demotest.features.main.infrastructure.datasources.remote_datasources.ClientRemoteDatasource;
import com.example.demotest.features.main.infrastructure.models.ClientModel;

import java.util.ArrayList;
import java.util.List;

public final class ClientRepositoryImpl implements ClientRepository {
    private final ClientRemoteDatasource remoteDatasource;
    private final Mapper<ClientEntity, ClientModel> mapper;

    public ClientRepositoryImpl(ClientRemoteDatasource remoteDatasource, Mapper<ClientEntity, ClientModel> mapper) {
        this.remoteDatasource = remoteDatasource;
        this.mapper = mapper;
    }

    @Override
    public Either<Failure, List<ClientEntity>> selectClients() {
        final List<ClientModel> models;
        try {
            models = remoteDatasource.selectClients();
        } catch (ServerException e) {
            return new Left<>(
                    new ServerFailure(e.getMessage())
            );
        }
        final List<ClientEntity> entities = new ArrayList<>();
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
}
