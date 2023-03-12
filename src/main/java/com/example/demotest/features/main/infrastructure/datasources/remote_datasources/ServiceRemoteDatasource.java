package com.example.demotest.features.main.infrastructure.datasources.remote_datasources;

import com.example.demotest.core.errors.exceptions.ServerException;
import com.example.demotest.features.main.infrastructure.models.ServiceModel;

import java.util.List;

public interface ServiceRemoteDatasource {
    List<ServiceModel> selectServices() throws ServerException;
    List<String> selectPhotoPaths(ServiceModel service) throws ServerException;
    void insertService(ServiceModel service) throws ServerException;
    void insertPhotoToService(ServiceModel service, String path) throws ServerException;
    void deleteService(ServiceModel service) throws ServerException;
    void deletePhotoOfService(String path) throws ServerException;
    void updateService(ServiceModel oldService, ServiceModel newService) throws ServerException;
    void updatePhotoOfService(String oldPath, String newPath) throws ServerException;
}
