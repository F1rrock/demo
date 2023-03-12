package com.example.demotest.features.main.domain.use_cases.data_cases.service_cases.concretes;

import com.example.demotest.core.errors.failures.Failure;
import com.example.demotest.external.types.either.Either;
import com.example.demotest.features.main.domain.entities.ServiceEntity;
import com.example.demotest.features.main.domain.repositories.ServiceRepository;
import com.example.demotest.features.main.domain.use_cases.data_cases.service_cases.ServiceCase;

import java.util.List;

public final class DeletePhotoOfService extends ServiceCase<ServiceEntity, String> {
    public DeletePhotoOfService(ServiceRepository repository) {
        super(repository);
    }

    @Override
    public Either<Failure, List<ServiceEntity>> call(String param) {
        return repository.deletePhotoOfService(param);
    }
}
