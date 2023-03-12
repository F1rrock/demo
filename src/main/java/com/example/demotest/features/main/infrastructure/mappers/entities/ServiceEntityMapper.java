package com.example.demotest.features.main.infrastructure.mappers.entities;

import com.example.demotest.core.mappers.Mapper;
import com.example.demotest.features.main.domain.entities.ServiceEntity;
import com.example.demotest.features.main.infrastructure.models.ServiceModel;

public final class ServiceEntityMapper implements Mapper<ServiceEntity, ServiceModel> {
    @Override
    public ServiceEntity mapTo(ServiceModel param) {
        return new ServiceEntity(
                param.uid(),
                param.cost(),
                param.description(),
                param.discount(),
                param.duration(),
                param.mainImgPath(),
                param.title(),
                param.photoPaths()
        );
    }

    @Override
    public ServiceModel mapFrom(ServiceEntity param) {
        return new ServiceModel(
                param.uid(),
                param.cost(),
                param.description(),
                param.discount(),
                param.duration(),
                param.mainImgPath(),
                param.title(),
                param.photoPaths()
        );
    }
}
