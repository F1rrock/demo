package com.example.demotest.features.main.infrastructure.datasources.remote_datasources;

import com.example.demotest.core.errors.exceptions.ServerException;
import com.example.demotest.features.main.infrastructure.models.RecordModel;

import java.util.List;

public interface RecordRemoteDatasource {
    List<RecordModel> selectNearRecords() throws ServerException;
    void insertRecord(RecordModel record) throws ServerException;
}
