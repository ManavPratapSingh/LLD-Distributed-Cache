package modules.strategy;

public class ModuloDistributionStrategy implements DistributionStrategy {
    @Override
    public int getComponentNodeIndex(String key, int numberOfNodes) {
        return Math.abs(key.hashCode()) % numberOfNodes;
    }
}
