package com.example.demotest.features.chain_of_responsibility.discount_converter.handlers.concretes;

import com.example.demotest.common.enums.DiscountRangesFilterValues;
import com.example.demotest.features.chain_of_responsibility.discount_converter.handlers.DiscountHandler;
import com.example.demotest.features.chain_of_responsibility.discount_converter.models.DiscountRange;

public final class FiveToFifteenHandler extends DiscountHandler<DiscountRangesFilterValues> {
    private static final DiscountRangesFilterValues VALUE = DiscountRangesFilterValues.FIVE_TO_FIFTEEN;

    public FiveToFifteenHandler(DiscountHandler<DiscountRangesFilterValues> next) {
        super(next);
    }

    @Override
    public DiscountRange response(DiscountRangesFilterValues param) {
        if (VALUE == param) {
            return new DiscountRange(5, 15);
        }
        return super.response(param);
    }
}
