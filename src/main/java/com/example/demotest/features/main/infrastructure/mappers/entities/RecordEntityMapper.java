package com.example.demotest.features.main.infrastructure.mappers.entities;

import com.example.demotest.core.mappers.Mapper;
import com.example.demotest.features.main.domain.entities.ClientEntity;
import com.example.demotest.features.main.domain.entities.RecordEntity;
import com.example.demotest.features.main.domain.entities.ServiceEntity;
import com.example.demotest.features.main.infrastructure.models.ClientModel;
import com.example.demotest.features.main.infrastructure.models.RecordModel;
import com.example.demotest.features.main.infrastructure.models.ServiceModel;

import java.time.LocalTime;

public final class RecordEntityMapper implements Mapper<RecordEntity, RecordModel> {
    private final Mapper<ClientEntity, ClientModel> clientMapper;
    private final Mapper<ServiceEntity, ServiceModel> serviceMapper;
    private final Mapper<String, LocalTime> timeMapper;

    public RecordEntityMapper(Mapper<ClientEntity, ClientModel> clientMapper, Mapper<ServiceEntity, ServiceModel> serviceMapper, Mapper<String, LocalTime> timeMapper) {
        this.clientMapper = clientMapper;
        this.serviceMapper = serviceMapper;
        this.timeMapper = timeMapper;
    }

    @Override
    public RecordEntity mapTo(RecordModel param) throws Exception {
        return new RecordEntity(
                serviceMapper.mapTo(param.service()),
                clientMapper.mapTo(param.client()),
                timeMapper.mapTo(param.leftTime()),
                param.beginningDateTime().plusMinutes(
                        param.service().duration()
                )
        );
    }

    @Override
    public RecordModel mapFrom(RecordEntity param) throws Exception {
        return new RecordModel(
                serviceMapper.mapFrom(param.service()),
                clientMapper.mapFrom(param.client()),
                timeMapper.mapFrom(param.leftTime()),
                param.finishTime().minusMinutes(
                        param.service().duration()
                )
        );
    }
}
