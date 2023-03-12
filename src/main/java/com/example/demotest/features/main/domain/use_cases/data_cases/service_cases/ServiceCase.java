package com.example.demotest.features.main.domain.use_cases.data_cases.service_cases;

import com.example.demotest.core.errors.failures.Failure;
import com.example.demotest.external.types.either.Either;
import com.example.demotest.features.main.domain.entities.ServiceEntity;
import com.example.demotest.features.main.domain.repositories.ServiceRepository;
import com.example.demotest.features.main.domain.use_cases.data_cases.DataCase;

import java.util.List;

public abstract class ServiceCase<Type, Param> implements DataCase<Type, Param> {
    protected final ServiceRepository repository;
    protected ServiceCase(ServiceRepository repository) {
        this.repository = repository;
    }

    @Override
    public abstract Either<Failure, List<Type>> call(Param param);
}
