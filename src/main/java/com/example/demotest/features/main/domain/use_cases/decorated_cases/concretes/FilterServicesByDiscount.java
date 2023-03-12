package com.example.demotest.features.main.domain.use_cases.decorated_cases.concretes;

import com.example.demotest.common.enums.DiscountRangesFilterValues;
import com.example.demotest.core.errors.failures.Failure;
import com.example.demotest.core.errors.failures.concretes.TransformFailure;
import com.example.demotest.external.types.either.Either;
import com.example.demotest.external.types.either.Left;
import com.example.demotest.external.types.either.Right;
import com.example.demotest.features.chain_of_responsibility.discount_converter.injection.DiscountInjector;
import com.example.demotest.features.main.domain.entities.ServiceEntity;
import com.example.demotest.features.main.domain.use_cases.decorated_cases.DecoratedCase;
import com.example.demotest.features.main.domain.use_cases.params.DecoratedCaseParam;

import java.util.List;
import java.util.stream.Collectors;

public final class FilterServicesByDiscount implements DecoratedCase<ServiceEntity, DiscountRangesFilterValues> {
    private final DiscountInjector<DiscountRangesFilterValues> injector;

    public FilterServicesByDiscount(DiscountInjector<DiscountRangesFilterValues> injector) {
        this.injector = injector;
    }
    @Override
    public Either<Failure, List<ServiceEntity>> call(DecoratedCaseParam<ServiceEntity, DiscountRangesFilterValues> param) {
        try {
            final var responder = injector.getResponder();
            final var range = responder.response(param.param());
            return new Right<>(param.originalList().stream().filter(
                    value -> (value.discount() >= range.lower()) && (value.discount() < range.higher())
            ).collect(Collectors.toList()));
        } catch (Exception e) {
            return new Left<>(
                    new TransformFailure(e.getMessage())
            );
        }
    }
}
