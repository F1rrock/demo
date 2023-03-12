package com.example.demotest.external.types.either;

import java.util.function.Function;

public final class Right<L, R> implements Either<L, R> {
    private final R value;

    public Right(R value) {
        this.value = value;
    }

    @Override
    public void fold(
            Function<L, Void> onError,
            Function<R, Void> onSuccess
    ) {
        onSuccess.apply(value);
    }
}
