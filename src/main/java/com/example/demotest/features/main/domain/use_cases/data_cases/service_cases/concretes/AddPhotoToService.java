package com.example.demotest.features.main.domain.use_cases.data_cases.service_cases.concretes;

import com.example.demotest.core.errors.failures.Failure;
import com.example.demotest.external.types.either.Either;
import com.example.demotest.features.main.domain.entities.ServiceEntity;
import com.example.demotest.features.main.domain.repositories.ServiceRepository;
import com.example.demotest.features.main.domain.use_cases.data_cases.service_cases.ServiceCase;
import com.example.demotest.features.main.domain.use_cases.params.TwoValuesParam;

import java.util.List;

public final class AddPhotoToService extends ServiceCase<ServiceEntity, TwoValuesParam<ServiceEntity, String>> {
    public AddPhotoToService(ServiceRepository repository) {
        super(repository);
    }

    @Override
    public Either<Failure, List<ServiceEntity>> call(TwoValuesParam<ServiceEntity, String> param) {
        return repository.insertPhotoToService(
                param.param1(),
                param.param2()
        );
    }
}
