package modules.strategy.eviction;

public interface EvictionPolicy {
    void keyAccessed(String key);
    String evictKey();
}
