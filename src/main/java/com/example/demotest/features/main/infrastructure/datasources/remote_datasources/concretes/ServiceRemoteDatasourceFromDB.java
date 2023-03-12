package com.example.demotest.features.main.infrastructure.datasources.remote_datasources.concretes;

import com.example.demotest.core.errors.exceptions.ServerException;
import com.example.demotest.core.mappers.MapperTo;
import com.example.demotest.features.main.infrastructure.datasources.remote_datasources.ServiceRemoteDatasource;
import com.example.demotest.features.helpers.data_bases.DBHelper;
import com.example.demotest.features.main.infrastructure.models.ServiceModel;

import java.util.ArrayList;
import java.util.List;

import java.sql.*;

public final class ServiceRemoteDatasourceFromDB implements ServiceRemoteDatasource {
    private final MapperTo<ServiceModel, ResultSet> mapper;
    private final DBHelper dbHelper;

    public ServiceRemoteDatasourceFromDB(MapperTo<ServiceModel, ResultSet> mapper, DBHelper dbHelper) {
        this.mapper = mapper;
        this.dbHelper = dbHelper;
    }

    @Override
    public List<ServiceModel> selectServices() throws ServerException {
        final var services = dbHelper.executeSelect(
                "SELECT * FROM service",
                mapper,
                null
        );
        for (var service : services) {
            service.photoPaths().addAll(
                    selectPhotoPaths(service)
            );
        }
        return services;
    }

    @Override
    public List<String> selectPhotoPaths(ServiceModel service) throws ServerException {
        return dbHelper.executeSelect(
                        "SELECT path FROM serviceImg WHERE serviceId = ?",
                        param -> param.getString("path"),
                        new ArrayList<>(
                                List.of(
                                        service.uid()
                                )
                        )
                );
    }

    @Override
    public void insertService(ServiceModel service) throws ServerException {
        dbHelper.executeUpdate(
                "INSERT INTO service VALUES (default, ?, ?, ?, ?, ?, ?)",
                new ArrayList<>(
                        List.of(
                                service.title(),
                                service.description(),
                                service.duration(),
                                service.cost(),
                                service.discount(),
                                service.mainImgPath()
                        )
                )
        );
    }

    @Override
    public void insertPhotoToService(ServiceModel service, String path) throws ServerException {
        dbHelper.executeUpdate(
                "INSERT INTO serviceImg VALUES (? , ?)",
                new ArrayList<>(
                        List.of(
                                path,
                                service.uid()
                        )
                )
        );
    }

    @Override
    public void deleteService(ServiceModel service) throws ServerException {
        dbHelper.executeUpdate(
                "DELETE FROM service WHERE uid = ?",
                new ArrayList<>(
                        List.of(
                                service.uid()
                        )
                )
        );
    }

    @Override
    public void deletePhotoOfService(String path) throws ServerException {
        dbHelper.executeUpdate(
                "DELETE FROM serviceImg WHERE path = ?",
                new ArrayList<>(
                        List.of(
                                path
                        )
                )
        );
    }

    @Override
    public void updateService(ServiceModel oldService, ServiceModel newService) throws ServerException {
        dbHelper.executeUpdate(
                "UPDATE service SET cost = ?, description = ?, " +
                        "discount = ?, duration = ?, mainImgPath = ?, " +
                        "title = ? WHERE uid = ?",
                new ArrayList<>(
                        List.of(
                                newService.cost(),
                                newService.description(),
                                newService.discount(),
                                newService.duration(),
                                newService.mainImgPath(),
                                newService.title(),
                                oldService.uid()
                        )
                )
        );
    }

    @Override
    public void updatePhotoOfService(String oldPath, String newPath) throws ServerException {
        dbHelper.executeUpdate(
                "UPDATE serviceImg SET `path` = ? WHERE `path` = ?",
                new ArrayList<>(
                        List.of(
                                newPath,
                                oldPath
                        )
                )
        );
    }
}
