package com.example.demotest.features.main.domain.repositories;

import com.example.demotest.core.errors.failures.Failure;
import com.example.demotest.external.types.either.Either;
import com.example.demotest.features.main.domain.entities.ClientEntity;

import java.util.List;

public interface ClientRepository {
    Either<Failure, List<ClientEntity>> selectClients();
}
