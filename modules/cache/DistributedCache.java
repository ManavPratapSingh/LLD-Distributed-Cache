package modules.cache;

public interface DistributedCache {
    String get(String key);
    void put(String key, String value);
}
