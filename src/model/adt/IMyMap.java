package model.adt;
import java.util.Map;
import model.values.IValue;

public interface IMyMap<K, V> {
    V getElement(K key);
    void insert(K key, V value);
    void remove(K key);
    boolean contains(K key);
    Map<K,V> getContent();
    IMyMap<K,V> deepCopy();
}
