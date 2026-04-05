package modules.strategy.eviction;

import java.util.LinkedList;

public class LRUEvictionPolicy implements EvictionPolicy {
    private LinkedList<String> accessOrder = new LinkedList<>();

    @Override
    public void keyAccessed(String key) {
        accessOrder.remove(key);
        accessOrder.addLast(key);
    }

    @Override
    public String evictKey() {
        if (accessOrder.isEmpty()) {
            return null;
        }
        return accessOrder.removeFirst();
    }
}
