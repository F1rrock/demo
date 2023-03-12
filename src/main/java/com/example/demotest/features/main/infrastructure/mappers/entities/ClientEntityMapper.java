package com.example.demotest.features.main.infrastructure.mappers.entities;

import com.example.demotest.core.mappers.Mapper;
import com.example.demotest.features.main.domain.entities.ClientEntity;
import com.example.demotest.features.main.infrastructure.models.ClientModel;

public final class ClientEntityMapper implements Mapper<ClientEntity, ClientModel> {
    @Override
    public ClientEntity mapTo(ClientModel param) {
        return new ClientEntity(
                param.uid(),
                param.name() + " " + param.secondName() + " " + param.lastName(),
                param.email(),
                param.phone()
        );
    }

    @Override
    public ClientModel mapFrom(ClientEntity param) {
        final var strArr = param.fio().split(" ");
        return new ClientModel(
                param.uid(),
                strArr[0],
                strArr[1],
                strArr[2],
                param.email(),
                param.phone()
        );
    }
}
