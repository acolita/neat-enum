package br.com.acolita.neatenum;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * It's a way to retrieve an enum by one of its properties.
 * Create a constant instance on the enum and pass switch getter you want.
 * Then you'll be able to get an enum back using one of NeatEnumGetter methods.
 *
 * @param <E> The enum class
 * @param <K> The key class
 */
public class NeatEnumGetter<E extends Enum<?>, K> {

    private Map<K, E> innerMap;

    /**
     * Create a {@link NeatEnumGetter} from an enum {@link Class} and a property getter.
     *
     * @param clazz  The enum {@link Class}
     * @param getter The property getter (SomeEnum::getSomeProperty)
     */
    public NeatEnumGetter(Class<E> clazz, final Function<E, K> getter) {
        innerMap = NeatEnumBy.getEnumBy(clazz.getEnumConstants(), getter);
    }

    /**
     * Get the enum by key, if the key don't exist, return the default value
     *
     * @param key          Key to search enum by
     * @param defaultValue Default value to return when key is not found
     * @return An enum matching key
     */
    public E orElse(final K key, final E defaultValue) {
        return innerMap.getOrDefault(key, defaultValue);
    }

    /**
     * Get the enum by key, if the key don't exist, return null
     *
     * @param key Key to search enum by
     * @return An enum matching key or null
     */
    public E orNull(final K key) {
        return orElse(key, null);
    }

    /**
     * Get the enum by key, if the key don't exist, throws exceptionSupplier
     *
     * @param key               Key to search enum by
     * @param exceptionSupplier An exception to throw if key is not found
     * @return An enum matching key
     */
    public E orThrow(final K key, Supplier<RuntimeException> exceptionSupplier) {
        final E e = orNull(key);
        if (e == null) {
            throw exceptionSupplier.get();
        }
        return e;
    }
}
