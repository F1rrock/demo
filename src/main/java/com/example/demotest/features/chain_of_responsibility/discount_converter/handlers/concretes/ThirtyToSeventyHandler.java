package com.example.demotest.features.chain_of_responsibility.discount_converter.handlers.concretes;

import com.example.demotest.common.enums.DiscountRangesFilterValues;
import com.example.demotest.features.chain_of_responsibility.discount_converter.handlers.DiscountHandler;
import com.example.demotest.features.chain_of_responsibility.discount_converter.models.DiscountRange;

public final class ThirtyToSeventyHandler extends DiscountHandler<DiscountRangesFilterValues> {
    private static final DiscountRangesFilterValues VALUE = DiscountRangesFilterValues.THIRTY_TO_SEVENTY;

    public ThirtyToSeventyHandler(DiscountHandler<DiscountRangesFilterValues> next) {
        super(next);
    }

    @Override
    public DiscountRange response(DiscountRangesFilterValues param) {
        if (VALUE == param) {
            return new DiscountRange(30, 70);
        }
        return super.response(param);
    }
}
