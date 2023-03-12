package com.example.demotest.features.chain_of_responsibility.discount_converter.handlers;

import com.example.demotest.features.chain_of_responsibility.discount_converter.models.DiscountRange;

public abstract class DiscountHandler<Param> {
    private final DiscountHandler<Param> next;

    protected DiscountHandler(DiscountHandler<Param> next) {
        this.next = next;
    }

    public DiscountRange response(Param param) {
        if (next != null) {
            return next.response(param);
        }
        return null;
    }
}
