package com.example.demotest.features.main.infrastructure.mappers.result_set;

import com.example.demotest.core.mappers.MapperTo;
import com.example.demotest.features.main.infrastructure.models.ClientModel;

import java.sql.ResultSet;

public final class ClientResultSetMapper implements MapperTo<ClientModel, ResultSet> {
    @Override
    public ClientModel mapTo(ResultSet param) throws Exception {
        return new ClientModel(
                param.getInt("clientUid"),
                param.getString("name"),
                param.getString("secondName"),
                param.getString("lastName"),
                param.getString("email"),
                param.getString("phone")
        );
    }
}
