package com.example.demotest.features.main.injection;

import com.example.demotest.common.enums.DiscountRangesFilterValues;
import com.example.demotest.common.enums.SortType;
import com.example.demotest.core.mappers.Mapper;
import com.example.demotest.core.mappers.MapperTo;
import com.example.demotest.features.chain_of_responsibility.discount_converter.injection.DiscountInjector;
import com.example.demotest.features.chain_of_responsibility.discount_converter.injection.concretes.DiscountRangesAscInjector;
import com.example.demotest.features.main.domain.entities.ClientEntity;
import com.example.demotest.features.main.domain.entities.RecordEntity;
import com.example.demotest.features.main.domain.entities.ServiceEntity;
import com.example.demotest.features.main.domain.repositories.ClientRepository;
import com.example.demotest.features.main.domain.repositories.RecordRepository;
import com.example.demotest.features.main.domain.repositories.ServiceRepository;
import com.example.demotest.features.main.domain.use_cases.data_cases.DataCase;
import com.example.demotest.features.main.domain.use_cases.data_cases.client_cases.ClientCase;
import com.example.demotest.features.main.domain.use_cases.data_cases.client_cases.concretes.GetAllClients;
import com.example.demotest.features.main.domain.use_cases.data_cases.record_cases.RecordCase;
import com.example.demotest.features.main.domain.use_cases.data_cases.record_cases.concretes.AddNewRecord;
import com.example.demotest.features.main.domain.use_cases.data_cases.record_cases.concretes.GetNearRecords;
import com.example.demotest.features.main.domain.use_cases.data_cases.service_cases.concretes.*;
import com.example.demotest.features.main.domain.use_cases.decorated_cases.DecoratedCase;
import com.example.demotest.features.main.domain.use_cases.decorated_cases.concretes.FilterServicesByDiscount;
import com.example.demotest.features.main.domain.use_cases.decorated_cases.concretes.SearchServicesByTitleOrDescription;
import com.example.demotest.features.main.domain.use_cases.decorated_cases.concretes.SortServicesByCost;
import com.example.demotest.features.main.domain.use_cases.params.TwoValuesParam;
import com.example.demotest.features.main.infrastructure.datasources.remote_datasources.ClientRemoteDatasource;
import com.example.demotest.features.main.infrastructure.datasources.remote_datasources.RecordRemoteDatasource;
import com.example.demotest.features.main.infrastructure.datasources.remote_datasources.ServiceRemoteDatasource;
import com.example.demotest.features.main.infrastructure.datasources.remote_datasources.concretes.ClientRemoteDatasourceImpl;
import com.example.demotest.features.main.infrastructure.datasources.remote_datasources.concretes.RecordRemoteDatasourceFromDB;
import com.example.demotest.features.main.infrastructure.datasources.remote_datasources.concretes.ServiceRemoteDatasourceFromDB;
import com.example.demotest.features.helpers.data_bases.DBHelper;
import com.example.demotest.features.helpers.data_bases.concretes.DBHelperImpl;
import com.example.demotest.features.main.infrastructure.mappers.entities.ClientEntityMapper;
import com.example.demotest.features.main.infrastructure.mappers.entities.RecordEntityMapper;
import com.example.demotest.features.main.infrastructure.mappers.entities.ServiceEntityMapper;
import com.example.demotest.features.main.infrastructure.mappers.others.StringLocalTimeMapper;
import com.example.demotest.features.main.infrastructure.mappers.result_set.ClientResultSetMapper;
import com.example.demotest.features.main.infrastructure.mappers.result_set.RecordResultSetMapper;
import com.example.demotest.features.main.infrastructure.mappers.result_set.ServiceResultSetMapper;
import com.example.demotest.features.main.infrastructure.models.ClientModel;
import com.example.demotest.features.main.infrastructure.models.RecordModel;
import com.example.demotest.features.main.infrastructure.models.ServiceModel;
import com.example.demotest.features.main.infrastructure.repositories.ClientRepositoryImpl;
import com.example.demotest.features.main.infrastructure.repositories.RecordRepositoryImpl;
import com.example.demotest.features.main.infrastructure.repositories.ServiceRepositoryImpl;

import java.sql.ResultSet;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public final class DependencyInjection {
    private static DependencyInjection instance;
    private final Set<Object> serviceLocator;
    private DependencyInjection() {
        this.serviceLocator = new HashSet<>();
    }
    public static DependencyInjection getInstance() {
        if (instance == null) {
            instance = new DependencyInjection();
        }
        return instance;
    }

    public void inject() {
        final MapperTo<ServiceModel, ResultSet> serviceMapper = new ServiceResultSetMapper();
        final DBHelper dbHelper = new DBHelperImpl();
        final ServiceRemoteDatasource serviceRemoteDatasource = new ServiceRemoteDatasourceFromDB(
                serviceMapper,
                dbHelper
        );
        final Mapper<ServiceEntity, ServiceModel> serviceEntityMapper = new ServiceEntityMapper();
        final ServiceRepository serviceRepository = new ServiceRepositoryImpl(
                serviceEntityMapper,
                serviceRemoteDatasource
        );
        final DataCase<ServiceEntity, Void> getAllServices = new GetAllServices(
                serviceRepository
        );
        serviceLocator.add(getAllServices);
        final DataCase<String, ServiceEntity> getPhotosOfService = new GetPhotosOfService(
                serviceRepository
        );
        serviceLocator.add(getPhotosOfService);
        final DataCase<ServiceEntity, TwoValuesParam<ServiceEntity, String>> addPhotoToService = new AddPhotoToService(
                serviceRepository
        );
        serviceLocator.add(addPhotoToService);
        final DataCase<ServiceEntity, ServiceEntity> addNewService = new AddNewService(
                serviceRepository
        );
        serviceLocator.add(addNewService);
        final DataCase<ServiceEntity, ServiceEntity> deleteService = new DeleteService(
                serviceRepository
        );
        serviceLocator.add(deleteService);
        final DataCase<ServiceEntity, String> deletePhotoOfService = new DeletePhotoOfService(
                serviceRepository
        );
        serviceLocator.add(deletePhotoOfService);
        final DataCase<ServiceEntity, TwoValuesParam<ServiceEntity, ServiceEntity>> updateService = new UpdateService(
                serviceRepository
        );
        serviceLocator.add(updateService);
        final DataCase<ServiceEntity, TwoValuesParam<String, String>> updatePhotoOfService = new UpdatePhotoOfService(
                serviceRepository
        );
        serviceLocator.add(updatePhotoOfService);
        final DecoratedCase<ServiceEntity, String> searchServicesByTitleOrDescription = new SearchServicesByTitleOrDescription();
        serviceLocator.add(searchServicesByTitleOrDescription);
        final DecoratedCase<ServiceEntity, SortType> sortServicesByCost = new SortServicesByCost();
        serviceLocator.add(sortServicesByCost);
        final DiscountInjector<DiscountRangesFilterValues> discountInjector = new DiscountRangesAscInjector();
        final DecoratedCase<ServiceEntity, DiscountRangesFilterValues> filterServicesByDiscount = new FilterServicesByDiscount(
                discountInjector
        );
        serviceLocator.add(filterServicesByDiscount);
        final MapperTo<ClientModel, ResultSet> clientMapper = new ClientResultSetMapper();
        final MapperTo<RecordModel, ResultSet> recordMapper = new RecordResultSetMapper(
                clientMapper,
                serviceMapper
        );
        final RecordRemoteDatasource recordRemoteDatasource = new RecordRemoteDatasourceFromDB(
                dbHelper,
                recordMapper
        );
        final Mapper<ClientEntity, ClientModel> clientEntityMapper = new ClientEntityMapper();
        final Mapper<String, LocalTime> timeMapper = new StringLocalTimeMapper();
        final Mapper<RecordEntity, RecordModel> recordEntityMapper = new RecordEntityMapper(
                clientEntityMapper,
                serviceEntityMapper,
                timeMapper
        );
        final RecordRepository recordRepository = new RecordRepositoryImpl(
                recordRemoteDatasource,
                recordEntityMapper
        );
        final RecordCase<RecordEntity, RecordEntity> addNewRecord = new AddNewRecord(
                recordRepository
        );
        serviceLocator.add(addNewRecord);
        final RecordCase<RecordEntity, Void> getNearRecords = new GetNearRecords(
                recordRepository
        );
        serviceLocator.add(getNearRecords);
        final ClientRemoteDatasource clientRemoteDatasource = new ClientRemoteDatasourceImpl(
                dbHelper,
                clientMapper
        );
        final ClientRepository clientRepository = new ClientRepositoryImpl(
                clientRemoteDatasource,
                clientEntityMapper
        );
        final ClientCase<ClientEntity, Void> getAllClients = new GetAllClients(
                clientRepository
        );
        serviceLocator.add(getAllClients);
    }
    public <T> T get(Class<T> clazz) {
        for (var value : serviceLocator) {
            try {
                return clazz.cast(value);
            } catch (Exception ignored) {
            }
        }
        return null;
    }
}
