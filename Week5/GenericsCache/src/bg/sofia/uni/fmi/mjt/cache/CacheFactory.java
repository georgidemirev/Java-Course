package bg.sofia.uni.fmi.mjt.cache;

import bg.sofia.uni.fmi.mjt.cache.caches.LeastFrequentlyUsed;
import bg.sofia.uni.fmi.mjt.cache.caches.RandomReplacement;
import bg.sofia.uni.fmi.mjt.cache.enums.EvictionPolicy;

public interface CacheFactory {

    int MAX_CAPACITY = 10000;

    /**
     * Constructs a new Cache<K, V> with the specified maximum capacity and eviction policy
     *
     * @throws IllegalArgumentException if the given capacity is less than or equal to zero.
     *                                  Note that IllegalArgumentException is a `RuntimeException` from the JDK
     */
    static <K, V> Cache<K, V> getInstance(long capacity, EvictionPolicy policy) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        Cache<K, V> cache = null;
        if (policy == EvictionPolicy.LEAST_FREQUENTLY_USED) {
            cache = new LeastFrequentlyUsed<>(capacity);
        } else if (policy == EvictionPolicy.RANDOM_REPLACEMENT) {
            cache = new RandomReplacement<>(capacity);
        }
        return cache;
    }

    /**
     * Constructs a new Cache<K, V> with maximum capacity of 10_000 items and the specified eviction policy
     */
    static <K, V> Cache<K, V> getInstance(EvictionPolicy policy) {
        Cache<K, V> cache = null;
        if (policy == EvictionPolicy.LEAST_FREQUENTLY_USED) {
            cache = new LeastFrequentlyUsed<>(MAX_CAPACITY);
        } else if (policy == EvictionPolicy.RANDOM_REPLACEMENT) {
            cache = new RandomReplacement<>(MAX_CAPACITY);
        }
        return cache;
    }

}
