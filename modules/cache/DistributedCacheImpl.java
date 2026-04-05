package modules.cache;

import modules.db.Database;
import modules.node.CacheNode;
import modules.strategy.DistributionStrategy;
import java.util.List;

public class DistributedCacheImpl implements DistributedCache {
    private List<CacheNode> nodes;
    private DistributionStrategy distributionStrategy;
    private Database database;

    public DistributedCacheImpl(List<CacheNode> nodes, DistributionStrategy distributionStrategy, Database database) {
        this.nodes = nodes;
        this.distributionStrategy = distributionStrategy;
        this.database = database;
    }

    @Override
    public String get(String key) {
        int nodeIndex = distributionStrategy.getComponentNodeIndex(key, nodes.size());
        CacheNode node = nodes.get(nodeIndex);
        String value = node.get(key);

        if (value == null) {
            value = database.get(key);
            if (value != null) {
                node.put(key, value);
            }
        }
        return value;
    }

    @Override
    public void put(String key, String value) {
        database.put(key, value);
        int nodeIndex = distributionStrategy.getComponentNodeIndex(key, nodes.size());
        nodes.get(nodeIndex).put(key, value);
    }
}
