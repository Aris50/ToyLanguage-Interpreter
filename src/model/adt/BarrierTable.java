package model.adt;
import model.adt.IBarrierTable;
import model.adt.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BarrierTable implements IBarrierTable {
    private final Map<Integer, Pair<Integer, List<Integer>>> barrierTable;
    private final Lock lock = new ReentrantLock();
    private int nextFreeAddress = 0;

    public BarrierTable() {
        this.barrierTable = new HashMap<>();
    }

    @Override
    public void put(int key, Pair<Integer, List<Integer>> value) {
        lock.lock();
        try {
            barrierTable.put(key, value);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Pair<Integer, List<Integer>> get(int key) {
        lock.lock();
        try {
            return barrierTable.get(key);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean containsKey(int key) {
        lock.lock();
        try {
            return barrierTable.containsKey(key);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void remove(int key) {
        lock.lock();
        try {
            barrierTable.remove(key);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Map<Integer, Pair<Integer, List<Integer>>> getContent() {
        lock.lock();
        try {
            return new HashMap<>(barrierTable);
        } finally {
            lock.unlock();
        }
    }

    public int getFreeAddress() {
        lock.lock();
        try {
            while (barrierTable.containsKey(nextFreeAddress)) {
                nextFreeAddress++;
            }
            return nextFreeAddress;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        if (barrierTable.isEmpty()) {
            result.append("Empty\n");
            return result.toString();
        }
        for (Map.Entry<Integer, Pair<Integer, List<Integer>>> entry : barrierTable.entrySet()) {
            result.append(entry.getKey().toString())
                    .append(" -> (")
                    .append(entry.getValue().getFirst().toString())
                    .append(", ")
                    .append(entry.getValue().getSecond().toString())
                    .append(")\n");
        }
        return result.toString();
    }
}