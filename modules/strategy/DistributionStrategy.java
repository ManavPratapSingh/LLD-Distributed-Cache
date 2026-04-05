package modules.strategy;

public interface DistributionStrategy {
    int getComponentNodeIndex(String key, int numberOfNodes);
}
