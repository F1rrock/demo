package com.example.demotest.features.main.infrastructure.datasources.remote_datasources.concretes;

import com.example.demotest.core.errors.exceptions.ServerException;
import com.example.demotest.core.mappers.MapperTo;
import com.example.demotest.features.helpers.data_bases.DBHelper;
import com.example.demotest.features.main.infrastructure.datasources.remote_datasources.RecordRemoteDatasource;
import com.example.demotest.features.main.infrastructure.models.RecordModel;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public final class RecordRemoteDatasourceFromDB implements RecordRemoteDatasource {
    private final DBHelper dbHelper;
    private final MapperTo<RecordModel, ResultSet> mapper;

    public RecordRemoteDatasourceFromDB(DBHelper dbHelper, MapperTo<RecordModel, ResultSet> mapper) {
        this.dbHelper = dbHelper;
        this.mapper = mapper;
    }

    @Override
    public List<RecordModel> selectNearRecords() throws ServerException {
        return dbHelper.executeSelect(
                "SELECT * FROM nearRecords",
                mapper,
                null
        );
    }

    @Override
    public void insertRecord(RecordModel record) throws ServerException {
        dbHelper.executeUpdate(
                "INSERT INTO serviceClient VALUES (default, ?, ?, ?)",
                new ArrayList<>(
                        List.of(
                                record.service().uid(),
                                record.beginningDateTime(),
                                record.client().uid()
                        )
                )
        );
    }
}
