package com.example.demotest.features.main.infrastructure.repositories;

import com.example.demotest.core.errors.exceptions.ServerException;
import com.example.demotest.core.errors.failures.Failure;
import com.example.demotest.core.errors.failures.concretes.ServerFailure;
import com.example.demotest.core.mappers.Mapper;
import com.example.demotest.external.types.either.Either;
import com.example.demotest.external.types.either.Left;
import com.example.demotest.external.types.either.Right;
import com.example.demotest.features.main.domain.entities.ServiceEntity;
import com.example.demotest.features.main.domain.repositories.ServiceRepository;
import com.example.demotest.features.main.infrastructure.datasources.remote_datasources.ServiceRemoteDatasource;
import com.example.demotest.features.main.infrastructure.models.ServiceModel;

import java.util.ArrayList;
import java.util.List;

public final class ServiceRepositoryImpl implements ServiceRepository {
    private final Mapper<ServiceEntity, ServiceModel> mapper;
    private final ServiceRemoteDatasource remoteDatasource;

    public ServiceRepositoryImpl(Mapper<ServiceEntity, ServiceModel> mapper, ServiceRemoteDatasource remoteDatasource) {
        this.mapper = mapper;
        this.remoteDatasource = remoteDatasource;
    }

    @Override
    public Either<Failure, List<ServiceEntity>> selectServices() {
        final List<ServiceModel> models;
        try {
            models = remoteDatasource.selectServices();
        } catch (ServerException e) {
            return new Left<>(
                    new ServerFailure(e.getMessage())
            );
        }
        final List<ServiceEntity> entities = new ArrayList<>();
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
    public Either<Failure, List<String>> selectPhotoPaths(ServiceEntity service) {
        try {
            return new Right<>(remoteDatasource.selectPhotoPaths(
                    mapper.mapFrom(service)
            ));
        } catch (Exception e) {
            return new Left<>(
                    new ServerFailure(e.getMessage())
            );
        }
    }

    @Override
    public Either<Failure, List<ServiceEntity>> insertService(ServiceEntity service) {
        try {
            remoteDatasource.insertService(
                    mapper.mapFrom(service)
            );
        } catch (Exception e) {
            return new Left<>(
                    new ServerFailure(e.getMessage())
            );
        }
        return selectServices();
    }

    @Override
    public Either<Failure, List<ServiceEntity>> insertPhotoToService(ServiceEntity service, String path) {
        try {
            remoteDatasource.insertPhotoToService(
                    mapper.mapFrom(service),
                    path
            );
        } catch (Exception e) {
            return new Left<>(
                    new ServerFailure(e.getMessage())
            );
        }
        return selectServices();
    }

    @Override
    public Either<Failure, List<ServiceEntity>> deleteService(ServiceEntity service) {
        try {
            remoteDatasource.deleteService(
                    mapper.mapFrom(service)
            );
        } catch (Exception e) {
            return new Left<>(
                    new ServerFailure(e.getMessage())
            );
        }
        return selectServices();
    }

    @Override
    public Either<Failure, List<ServiceEntity>> deletePhotoOfService(String path) {
        try {
            remoteDatasource.deletePhotoOfService(
                    path
            );
        } catch (Exception e) {
            return new Left<>(
                    new ServerFailure(e.getMessage())
            );
        }
        return selectServices();
    }

    @Override
    public Either<Failure, List<ServiceEntity>> updateService(ServiceEntity oldService, ServiceEntity newService) {
        try {
            remoteDatasource.updateService(
                    mapper.mapFrom(oldService),
                    mapper.mapFrom(newService)
            );
        } catch (Exception e) {
            return new Left<>(
                    new ServerFailure(e.getMessage())
            );
        }
        return selectServices();
    }

    @Override
    public Either<Failure, List<ServiceEntity>> updatePhotoOfService(String oldPath, String newPath) {
        try {
            remoteDatasource.updatePhotoOfService(
                    oldPath,
                    newPath
            );
        } catch (Exception e) {
            return new Left<>(
                    new ServerFailure(e.getMessage())
            );
        }
        return selectServices();
    }
}
