package com.example.demotest.features.main.domain.use_cases.data_cases.client_cases.concretes;

import com.example.demotest.core.errors.failures.Failure;
import com.example.demotest.external.types.either.Either;
import com.example.demotest.features.main.domain.entities.ClientEntity;
import com.example.demotest.features.main.domain.repositories.ClientRepository;
import com.example.demotest.features.main.domain.use_cases.data_cases.client_cases.ClientCase;

import java.util.List;

public final class GetAllClients extends ClientCase<ClientEntity, Void> {
    public GetAllClients(ClientRepository repository) {
        super(repository);
    }

    @Override
    public Either<Failure, List<ClientEntity>> call(Void unused) {
        return repository.selectClients();
    }
}
