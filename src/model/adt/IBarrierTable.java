package model.adt;

import java.util.List;
import java.util.Map;

public interface IBarrierTable {
    void put(int key, Pair<Integer, List<Integer>> value);
    Pair<Integer, List<Integer>> get(int key);
    boolean containsKey(int key);
    void remove(int key);
    Map<Integer, Pair<Integer, List<Integer>>> getContent();
    int getFreeAddress();
    String toString();
}