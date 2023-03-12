package com.example.demotest.features.main.domain.use_cases.decorated_cases;

import com.example.demotest.core.errors.failures.Failure;
import com.example.demotest.core.use_cases.UseCase;
import com.example.demotest.external.types.either.Either;
import com.example.demotest.features.main.domain.use_cases.params.DecoratedCaseParam;

import java.util.List;

@FunctionalInterface
public interface DecoratedCase<Entity extends Record, Param> extends UseCase<List<Entity>, DecoratedCaseParam<Entity, Param>> {
    @Override
    Either<Failure, List<Entity>> call(DecoratedCaseParam<Entity, Param> param);
}
