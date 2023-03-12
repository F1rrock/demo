package com.example.demotest.features.main.domain.use_cases.decorated_cases.concretes;

import com.example.demotest.common.enums.SortType;
import com.example.demotest.core.errors.failures.Failure;
import com.example.demotest.core.errors.failures.concretes.TransformFailure;
import com.example.demotest.external.types.either.Either;
import com.example.demotest.external.types.either.Left;
import com.example.demotest.external.types.either.Right;
import com.example.demotest.features.main.domain.entities.ServiceEntity;
import com.example.demotest.features.main.domain.use_cases.decorated_cases.DecoratedCase;
import com.example.demotest.features.main.domain.use_cases.params.DecoratedCaseParam;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class SortServicesByCost implements DecoratedCase<ServiceEntity, SortType> {
    @Override
    public Either<Failure, List<ServiceEntity>> call(DecoratedCaseParam<ServiceEntity, SortType> param) {
        try {
            var comparator = Comparator.comparing(ServiceEntity::cost);
            if (param.param().equals(SortType.DESC)) {
                comparator = comparator.reversed();
            } else if (!param.param().equals(SortType.ASC)) {
                return new Left<>(
                        new TransformFailure("unknown value")
                );
            }
            return new Right<>(param.originalList().stream().sorted(
                    comparator
            ).collect(Collectors.toList()));
        } catch (Exception e) {
            return new Left<>(
                    new TransformFailure(e.getMessage())
            );
        }
    }
}
