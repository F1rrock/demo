package com.example.demotest.features.main.domain.repositories;

import com.example.demotest.core.errors.failures.Failure;
import com.example.demotest.external.types.either.Either;
import com.example.demotest.features.main.domain.entities.ServiceEntity;

import java.util.List;

public interface ServiceRepository {
    Either<Failure, List<ServiceEntity>> selectServices();
    Either<Failure, List<String>> selectPhotoPaths(ServiceEntity service);
    Either<Failure, List<ServiceEntity>> insertService(ServiceEntity service);
    Either<Failure, List<ServiceEntity>> insertPhotoToService(ServiceEntity service, String path);
    Either<Failure, List<ServiceEntity>> deleteService(ServiceEntity service);
    Either<Failure, List<ServiceEntity>> deletePhotoOfService(String path);
    Either<Failure, List<ServiceEntity>> updateService(ServiceEntity oldService, ServiceEntity newService);
    Either<Failure, List<ServiceEntity>> updatePhotoOfService(String oldPath, String newPath);
}
