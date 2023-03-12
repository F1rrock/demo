package com.example.demotest.features.chain_of_responsibility.discount_converter.injection.concretes;

import com.example.demotest.common.enums.DiscountRangesFilterValues;
import com.example.demotest.features.chain_of_responsibility.discount_converter.handlers.concretes.*;
import com.example.demotest.features.chain_of_responsibility.discount_converter.injection.DiscountInjector;

public final class DiscountRangesAscInjector implements DiscountInjector<DiscountRangesFilterValues> {
    @Override
    public ZeroToFiveHandler getResponder() {
        final var responder = new ThirtyToSeventyHandler(
                new SeventyToHundredHandler(
                        null
                )
        );
        return new ZeroToFiveHandler(
                new FiveToFifteenHandler(
                        new FifteenToThirtyHandler(
                                responder
                        )
                )
        );
    }
}
