package com.example.demotest.core.use_cases;

import com.example.demotest.core.errors.failures.Failure;
import com.example.demotest.external.types.either.Either;

@FunctionalInterface
public interface UseCase<Type, Param> {
    Either<Failure, Type> call(Param param);
}
