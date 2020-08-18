package br.com.acolita.neatenum;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The intend use of this class is to define maps from Enum's properties to themself.
 */
public class NeatEnumBy {

    private NeatEnumBy() {}

    /**
     * This method provides a easy way to create a map from a enum property to the enum itself.
     * With this map, one can easily implement search methods instead of using loop structures.
     *
     * @param values All enum possible values (return of values() method)
     * @param getter The property getter (will be the map key)
     * @param <T> The type of Map key
     * @param <E> The enum type (Map value)
     * @return a hashmap from T's to E's (HashMap(String,Enum) for instance)
     */
    public static <T, E> Map<T, E> getEnumBy(final E[] values, final Function<E,T> getter) {
        return getEnumBy(values, getter, Function.identity());
    }

    /**
     * See getEnumBy(final E[] values, final Function(E,T) getter)
     *
     * This method provides extra flexibility on how the key will be created by providing a extra argument.
     * The final argument is a mapper function allowing one transform the key how ever one likes.
     *
     * @param values All enum possible values (return of values() method)
     * @param getter The property getter (will be the map key)
     * @param mapper A mapper function from T's to X's
     * @param <T> The type of Map key
     * @param <E> The enum type (Map value)
     * @param <X> The final key type (can by the same as T)
     * @return a hashmap from X's to E's (HashMap(String,Enum) for instance)
     */
    public static <T, E, X> Map<X, E> getEnumBy(final E[] values, final Function<E,T> getter, Function<T,X> mapper) {
        return Stream.of(values).collect(Collectors.toMap(mapper.compose(getter), Function.identity()));
    }
}
