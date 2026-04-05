package modules.node;

import modules.strategy.eviction.EvictionPolicy;
import java.util.HashMap;
import java.util.Map;

public class CacheNodeImpl implements CacheNode {
    private int capacity;
    private Map<String, String> cache = new HashMap<>();
    private EvictionPolicy evictionPolicy;

    public CacheNodeImpl(int capacity, EvictionPolicy evictionPolicy) {
        this.capacity = capacity;
        this.evictionPolicy = evictionPolicy;
    }

    @Override
    public String get(String key) {
        if (cache.containsKey(key)) {
            evictionPolicy.keyAccessed(key);
            return cache.get(key);
        }
        return null;
    }

    @Override
    public void put(String key, String value) {
        if (cache.containsKey(key)) {
            cache.put(key, value);
            evictionPolicy.keyAccessed(key);
        } else {
            if (cache.size() >= capacity) {
                String keyToEvict = evictionPolicy.evictKey();
                if (keyToEvict != null) {
                    cache.remove(keyToEvict);
                }
            }
            cache.put(key, value);
            evictionPolicy.keyAccessed(key);
        }
    }
}
