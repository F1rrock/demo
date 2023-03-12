package com.example.demotest.features.main.domain.use_cases.data_cases;

import com.example.demotest.core.errors.failures.Failure;
import com.example.demotest.core.use_cases.UseCase;
import com.example.demotest.external.types.either.Either;

import java.util.List;

@FunctionalInterface
public interface DataCase<Entity, Param> extends UseCase<List<Entity>, Param> {
    @Override
    Either<Failure, List<Entity>> call(Param param);
}
