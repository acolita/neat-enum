package br.com.acolita.neatenum;

import java.util.function.Function;

public class NeatCompose {

    private NeatCompose() {
    }

    /**
     * Utility method for composing two function
     *
     * @param getter A property getter
     * @param mapper A mapper function
     * @param <A>    The type of original class
     * @param <B>    The type of class' property
     * @param <C>    The mapped type
     * @return A new getter mapping the property value
     */
    public static <A, B, C> Function<A, C> compose(final Function<A, B> getter, final Function<B, C> mapper) {
        return mapper.compose(getter);
    }

}
