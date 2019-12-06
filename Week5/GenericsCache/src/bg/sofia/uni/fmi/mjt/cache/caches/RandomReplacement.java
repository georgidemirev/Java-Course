package bg.sofia.uni.fmi.mjt.cache.caches;

import bg.sofia.uni.fmi.mjt.cache.Cache;

import java.util.HashMap;
import java.util.Map;

public class RandomReplacement<K, V> implements Cache<K, V> {

    private long capacity;
    private Map<K, V> elements = new HashMap<>();
    private double hitRate = 0;
    private double counterHits = 0;

    public RandomReplacement(long capacity) {
        this.capacity = capacity;
    }

    @Override
    public V get(K key) {
        if (key == null || !elements.containsKey(key)) {
            if (hitRate == 0) {
                counterHits++;
            } else {
                hitRate = hitRate * counterHits / ++counterHits;
            }
            return null;
        } else {
            hitRate = (hitRate * counterHits + 1) / ++counterHits;
            return elements.get(key);
        }
    }

    @Override
    public void set(K key, V value) {
        if (key == null || value == null) {
            return;
        }
        if (elements.containsKey(key)) {
            elements.replace(key, value);
        } else {
            if (elements.size() == capacity) {
                for (K i : elements.keySet()) {
                    elements.remove(i);
                    break;
                }
            }
            elements.put(key, value);
        }
    }

    @Override
    public boolean remove(K key) {
        if (key == null || !elements.containsKey(key)) {
            return false;
        }
        elements.remove(key);
        return true;
    }

    @Override
    public long size() {
        return elements.size();
    }

    @Override
    public void clear() {
        elements.clear();
        this.hitRate = 0;
        this.counterHits = 0;
    }

    @Override
    public double getHitRate() {
        return hitRate;
    }

    @Override
    public int getUsesCount(K key) {
        throw new UnsupportedOperationException();
    }
}
