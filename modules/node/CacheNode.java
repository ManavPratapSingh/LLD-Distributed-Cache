package modules.node;

public interface CacheNode {
    String get(String key);
    void put(String key, String value);
}
