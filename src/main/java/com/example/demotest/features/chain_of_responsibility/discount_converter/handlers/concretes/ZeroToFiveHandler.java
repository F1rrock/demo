package com.example.demotest.features.chain_of_responsibility.discount_converter.handlers.concretes;

import com.example.demotest.common.enums.DiscountRangesFilterValues;
import com.example.demotest.features.chain_of_responsibility.discount_converter.handlers.DiscountHandler;
import com.example.demotest.features.chain_of_responsibility.discount_converter.models.DiscountRange;

public final class ZeroToFiveHandler extends DiscountHandler<DiscountRangesFilterValues> {
    private static final DiscountRangesFilterValues VALUE = DiscountRangesFilterValues.ZERO_TO_FIVE;

    public ZeroToFiveHandler(DiscountHandler<DiscountRangesFilterValues> next) {
        super(next);
    }

    @Override
    public DiscountRange response(DiscountRangesFilterValues param) {
        if (VALUE == param) {
            return new DiscountRange(0, 5);
        }
        return super.response(param);
    }
}
