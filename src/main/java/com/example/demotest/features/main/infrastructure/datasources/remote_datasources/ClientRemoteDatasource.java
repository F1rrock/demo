package com.example.demotest.features.main.infrastructure.datasources.remote_datasources;

import com.example.demotest.core.errors.exceptions.ServerException;
import com.example.demotest.features.main.infrastructure.models.ClientModel;

import java.util.List;

public interface ClientRemoteDatasource {
    List<ClientModel> selectClients() throws ServerException;
}
