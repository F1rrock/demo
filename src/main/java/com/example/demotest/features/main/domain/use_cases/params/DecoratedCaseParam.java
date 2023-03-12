package com.example.demotest.features.main.domain.use_cases.params;

import java.util.List;

public record DecoratedCaseParam<Entity extends Record, Param>(
        List<Entity> originalList,
        Param param
) {
}
