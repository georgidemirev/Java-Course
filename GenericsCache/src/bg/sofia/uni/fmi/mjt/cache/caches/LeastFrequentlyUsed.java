package bg.sofia.uni.fmi.mjt.cache.caches;

import bg.sofia.uni.fmi.mjt.cache.Cache;

import java.util.HashMap;
import java.util.Map;

public class LeastFrequentlyUsed<K, V> implements Cache<K, V> {

    private static final int USES_FROM_BEGINNING = 0;
    private long capacity;
    private Map<K, V> elements = new HashMap<>();
    private Map<K, Integer> elementUses = new HashMap<>();
    private double hitRate = 0;
    private double counterHits = 0;

    public LeastFrequentlyUsed(long capacity) {
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
            elementUses.replace(key, elementUses.get(key) + 1);
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
            elementUses.replace(key, elementUses.get(key) + 1);
        } else {
            if (elements.size() == capacity) {
                K p = getLeastFrequentlyUsed();
                elements.remove(p);
                elementUses.remove(p);
            }
            elements.put(key, value);
            elementUses.put(key, USES_FROM_BEGINNING + 1); // check !!
        }
    }

    private K getLeastFrequentlyUsed() {
        int checker = Integer.MAX_VALUE;
        for (Map.Entry<K, Integer> i : elementUses.entrySet()) {
            if (i.getValue() < checker) {
                checker = i.getValue();
            }
        }
        for (Map.Entry<K, Integer> i : elementUses.entrySet()) {
            if (i.getValue() == checker) {
                return i.getKey();
            }
        }
        return null; // might be a problem
    }

    @Override
    public boolean remove(K key) {
        if (key == null || !elements.containsKey(key)) {
            return false;
        }
        elements.remove(key);
        elementUses.remove(key);
        return true;
    }

    @Override
    public long size() {
        return elements.size();
    }

    @Override
    public void clear() {
        elementUses.clear();
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
        if (key == null || !elementUses.containsKey(key)) {
            return 0;
        }
        return elementUses.get(key);
    }
}
