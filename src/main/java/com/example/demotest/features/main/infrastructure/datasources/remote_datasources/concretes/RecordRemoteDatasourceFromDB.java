package com.example.demotest.features.main.infrastructure.datasources.remote_datasources.concretes;

import com.example.demotest.core.errors.exceptions.ServerException;
import com.example.demotest.features.main.infrastructure.datasources.remote_datasources.RecordRemoteDatasource;
import com.example.demotest.features.main.infrastructure.models.RecordModel;

import java.util.List;

public final class RecordRemoteDatasourceFromDB implements RecordRemoteDatasource {
    @Override
    public List<RecordModel> selectNearRecords() throws ServerException {
        return null;
    }

    @Override
    public void insertRecord(RecordModel record) throws ServerException {

    }
}
