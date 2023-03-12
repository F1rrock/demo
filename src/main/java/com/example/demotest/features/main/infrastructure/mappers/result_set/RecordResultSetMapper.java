package com.example.demotest.features.main.infrastructure.mappers.result_set;

import com.example.demotest.core.mappers.MapperTo;
import com.example.demotest.features.main.infrastructure.models.ClientModel;
import com.example.demotest.features.main.infrastructure.models.RecordModel;
import com.example.demotest.features.main.infrastructure.models.ServiceModel;

import java.sql.ResultSet;

public final class RecordResultSetMapper implements MapperTo<RecordModel, ResultSet> {
    private final MapperTo<ClientModel, ResultSet> clientMapper;
    private final MapperTo<ServiceModel, ResultSet> serviceMapper;

    public RecordResultSetMapper(MapperTo<ClientModel, ResultSet> clientMapper, MapperTo<ServiceModel, ResultSet> serviceMapper) {
        this.clientMapper = clientMapper;
        this.serviceMapper = serviceMapper;
    }

    @Override
    public RecordModel mapTo(ResultSet param) throws Exception {
        return new RecordModel(
                serviceMapper.mapTo(param),
                clientMapper.mapTo(param),
                param.getTimestamp("leftTime").toLocalDateTime().toLocalTime(),
                param.getTimestamp("beginningDateTime").toLocalDateTime()
        );
    }
}
