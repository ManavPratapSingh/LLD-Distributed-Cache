import modules.cache.DistributedCache;
import modules.cache.DistributedCacheImpl;
import modules.db.Database;
import modules.db.DatabaseImpl;
import modules.node.CacheNode;
import modules.node.CacheNodeImpl;
import modules.strategy.DistributionStrategy;
import modules.strategy.ModuloDistributionStrategy;
import modules.strategy.eviction.LRUEvictionPolicy;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Database db = new DatabaseImpl();
        db.put("key1", "val1");
        db.put("key2", "val2");
        db.put("key3", "val3");

        int numNodes = 2;
        int nodeCapacity = 1;
        List<CacheNode> nodes = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) {
            nodes.add(new CacheNodeImpl(nodeCapacity, new LRUEvictionPolicy()));
        }

        DistributionStrategy strategy = new ModuloDistributionStrategy();
        DistributedCache cache = new DistributedCacheImpl(nodes, strategy, db);

        System.out.println("Getting key1 (miss, from db): " + cache.get("key1"));
        System.out.println("Getting key1 (hit, from cache): " + cache.get("key1"));

        System.out.println("Putting key4: val4");
        cache.put("key4", "val4");

        System.out.println("Checking keys to see distribution and eviction...");
        System.out.println("key1: " + cache.get("key1"));
        System.out.println("key2: " + cache.get("key2"));
        System.out.println("key3: " + cache.get("key3"));
        System.out.println("key4: " + cache.get("key4"));
    }
}
