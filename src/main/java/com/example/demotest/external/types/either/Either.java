package com.example.demotest.external.types.either;

import java.util.function.Function;

public sealed interface Either<L, R> permits Right, Left {
    void fold(Function<L, Void> onError, Function<R, Void> onSuccess);
}
