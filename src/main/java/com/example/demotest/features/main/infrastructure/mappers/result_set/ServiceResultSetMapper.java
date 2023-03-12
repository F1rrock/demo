package com.example.demotest.features.main.infrastructure.mappers.result_set;

import com.example.demotest.core.mappers.MapperTo;
import com.example.demotest.features.main.infrastructure.models.ServiceModel;

import java.sql.ResultSet;
import java.util.HashSet;

public final class ServiceResultSetMapper implements MapperTo<ServiceModel, ResultSet> {
    @Override
    public ServiceModel mapTo(ResultSet param) throws Exception {
        return new ServiceModel(
                param.getInt("serviceUid"),
                param.getDouble("cost"),
                param.getString("description"),
                param.getDouble("discount"),
                param.getInt("duration"),
                param.getString("mainImgPath"),
                param.getString("title"),
                new HashSet<>()
        );
    }
}
