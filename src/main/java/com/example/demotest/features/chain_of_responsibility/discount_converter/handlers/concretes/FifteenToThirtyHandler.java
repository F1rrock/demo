package com.example.demotest.features.chain_of_responsibility.discount_converter.handlers.concretes;

import com.example.demotest.common.enums.DiscountRangesFilterValues;
import com.example.demotest.features.chain_of_responsibility.discount_converter.handlers.DiscountHandler;
import com.example.demotest.features.chain_of_responsibility.discount_converter.models.DiscountRange;

public final class FifteenToThirtyHandler extends DiscountHandler<DiscountRangesFilterValues> {
    private static final DiscountRangesFilterValues VALUE = DiscountRangesFilterValues.FIFTEEN_TO_THIRTY;

    public FifteenToThirtyHandler(DiscountHandler<DiscountRangesFilterValues> next) {
        super(next);
    }

    @Override
    public DiscountRange response(DiscountRangesFilterValues param) {
        if (VALUE == param) {
            return new DiscountRange(15, 30);
        }
        return super.response(param);
    }
}
