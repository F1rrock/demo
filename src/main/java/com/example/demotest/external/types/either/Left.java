package com.example.demotest.external.types.either;

import java.util.function.Function;

public final class Left<L, R> implements Either<L, R> {
    private final L value;

    public Left(L value) {
        this.value = value;
    }

    @Override
    public void fold(
            Function<L, Void> onError,
            Function<R, Void> onSuccess
    ) {
        onError.apply(value);
    }
}
