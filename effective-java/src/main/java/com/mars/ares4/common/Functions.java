package com.mars.ares4.common;

public final class Functions {
    private Functions() {
        throw new AssertionError();
    }

    private static UnaryFunction<Object> IDENTITY_FUNCTION = new UnaryFunction<Object>() {
        @Override
        public Object apply(Object input) {
            return input;
        }
    };

    /**
     * IDENTITY_FUNCTION is stateless and its type parameter is
     * unbounded so it's safe to share one instance across all types.
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> UnaryFunction<T> identityFunction() {
        return (UnaryFunction<T>)IDENTITY_FUNCTION;
    }
}
