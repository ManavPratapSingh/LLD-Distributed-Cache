# Distributed Cache System Assignment

This project implements a distributed cache system in Java, as part of a low-level design (LLD) exercise.

## Features
- **Multiple Cache Nodes**: The cache is distributed across multiple nodes.
- **Pluggable Strategies**: 
  - **Distribution Strategy**: Modulo-based distribution (`hash(key) % numberOfNodes`).
  - **Eviction Strategy**: LRU (Least Recently Used) eviction policy.
- **Database Fallback**: On a cache miss, the system fetches the value from the database.

## Project Structure
```
ll-distributed-cache/
├── modules/
│   ├── node/
│   │   ├── CacheNode.java
│   │   └── CacheNodeImpl.java
│   ├── strategy/
│   │   ├── DistributionStrategy.java
│   │   ├── ModuloDistributionStrategy.java
│   │   └── eviction/
│   │       ├── EvictionPolicy.java
│   │       └── LRUEvictionPolicy.java
│   ├── cache/
│   │   ├── DistributedCache.java
│   │   └── DistributedCacheImpl.java
│   └── db/
│       ├── Database.java
│       └── DatabaseImpl.java
├── Main.java
└── README.md
```

## Compilation and Execution
To compile the project and run the mock test:

1. **Compile**:
   ```bash
   javac -d bin Main.java modules/db/*.java modules/strategy/eviction/*.java modules/strategy/*.java modules/node/*.java modules/cache/*.java
   ```

2. **Run**:
   ```bash
   java -cp bin Main
   ```

## Expected Mock Test Output
When running the `Main` class, the following output demonstrates the cache initialization, misses (database fetch), hits, and distribution logic:

```
Getting key1 (miss, from db): val1
Getting key1 (hit, from cache): val1
Putting key4: val4
Checking keys to see distribution and eviction...
key1: val1
key2: val2
key3: val3
key4: val4
```
