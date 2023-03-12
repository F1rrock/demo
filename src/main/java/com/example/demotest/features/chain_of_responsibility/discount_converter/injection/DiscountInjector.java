package com.example.demotest.features.chain_of_responsibility.discount_converter.injection;

import com.example.demotest.features.chain_of_responsibility.discount_converter.handlers.DiscountHandler;

@FunctionalInterface
public interface DiscountInjector<Param> {
    DiscountHandler<Param> getResponder();
}

