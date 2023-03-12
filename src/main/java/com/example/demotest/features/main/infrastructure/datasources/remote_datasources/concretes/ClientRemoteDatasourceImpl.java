package com.example.demotest.features.main.infrastructure.datasources.remote_datasources.concretes;

import com.example.demotest.core.errors.exceptions.ServerException;
import com.example.demotest.core.mappers.MapperTo;
import com.example.demotest.features.helpers.data_bases.DBHelper;
import com.example.demotest.features.main.infrastructure.datasources.remote_datasources.ClientRemoteDatasource;
import com.example.demotest.features.main.infrastructure.models.ClientModel;

import java.sql.ResultSet;
import java.util.List;

public final class ClientRemoteDatasourceImpl implements ClientRemoteDatasource {
    private final DBHelper dbHelper;
    private final MapperTo<ClientModel, ResultSet> mapper;

    public ClientRemoteDatasourceImpl(DBHelper dbHelper, MapperTo<ClientModel, ResultSet> mapper) {
        this.dbHelper = dbHelper;
        this.mapper = mapper;
    }

    @Override
    public List<ClientModel> selectClients() throws ServerException {
        return dbHelper.executeSelect(
                "SELECT * FROM client",
                mapper,
                null
        );
    }
}
