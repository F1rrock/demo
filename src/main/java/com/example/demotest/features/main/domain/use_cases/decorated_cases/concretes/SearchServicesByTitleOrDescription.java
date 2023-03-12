package com.example.demotest.features.main.domain.use_cases.decorated_cases.concretes;

import com.example.demotest.core.errors.failures.Failure;
import com.example.demotest.core.errors.failures.concretes.TransformFailure;
import com.example.demotest.external.types.either.Either;
import com.example.demotest.external.types.either.Left;
import com.example.demotest.external.types.either.Right;
import com.example.demotest.features.main.domain.entities.ServiceEntity;
import com.example.demotest.features.main.domain.use_cases.decorated_cases.DecoratedCase;
import com.example.demotest.features.main.domain.use_cases.params.DecoratedCaseParam;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class SearchServicesByTitleOrDescription implements DecoratedCase<ServiceEntity, String> {
    private Stream<ServiceEntity> filterTitles(DecoratedCaseParam<ServiceEntity, String> param) {
        return param.originalList().stream().filter(
                service -> service.title().toLowerCase().contains(param.param().toLowerCase())
        );
    }

    @Override
    public Either<Failure, List<ServiceEntity>> call(DecoratedCaseParam<ServiceEntity, String> param) {
        try {
            if (filterTitles(param).findAny().isEmpty()) {
                return new Right<>(param.originalList().stream().filter(
                        service -> service.description().toLowerCase().contains(param.param().toLowerCase())
                ).collect(Collectors.toList()));
            }
            return new Right<>(filterTitles(param).collect(Collectors.toList()));
        } catch (Exception e) {
            return new Left<>(
                    new TransformFailure(e.getMessage())
            );
        }
    }
}
