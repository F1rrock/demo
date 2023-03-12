package com.example.demotest.features.main.domain.use_cases.data_cases.client_cases;

import com.example.demotest.core.errors.failures.Failure;
import com.example.demotest.external.types.either.Either;
import com.example.demotest.features.main.domain.repositories.ClientRepository;
import com.example.demotest.features.main.domain.use_cases.data_cases.DataCase;

import java.util.List;

public abstract class ClientCase<Type, Param> implements DataCase<Type, Param> {
    protected final ClientRepository repository;

    public ClientCase(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public abstract Either<Failure, List<Type>> call(Param param);
}
