package com.example.demotest.features.main.domain.use_cases.data_cases.service_cases.concretes;

import com.example.demotest.core.errors.failures.Failure;
import com.example.demotest.external.types.either.Either;
import com.example.demotest.features.main.domain.entities.ServiceEntity;
import com.example.demotest.features.main.domain.repositories.ServiceRepository;
import com.example.demotest.features.main.domain.use_cases.data_cases.service_cases.ServiceCase;

import java.util.List;

public final class AddNewService extends ServiceCase<ServiceEntity, ServiceEntity> {
    public AddNewService(ServiceRepository repository) {
        super(repository);
    }

    @Override
    public Either<Failure, List<ServiceEntity>> call(ServiceEntity service) {
        return repository.insertService(service);
    }
}
