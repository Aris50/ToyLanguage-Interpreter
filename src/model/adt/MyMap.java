package model.adt;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import exceptions.KeyNotFoundException;
import model.values.IValue;

public class MyMap<K, V> implements IMyMap<K, V> {
    private Map<K, V> map;
    public MyMap(){
        map = new HashMap<K, V>();
    }
    public V getElement(K key) throws KeyNotFoundException {
        if(!map.containsKey(key)){
            throw new KeyNotFoundException("key not found");
        }
        return this.map.get(key);
    }
    public void insert(K key, V value){
        map.put(key, value);
    }
    public boolean contains(K key){
        return map.containsKey(key);
    }
    public void remove(K key){
        map.remove(key);
    }

    public Map<K, V> getContent(){
        return map;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (K key : map.keySet()) {
            str.append(key.toString()).append(" -> ").append(map.get(key));
            str.append("\n");
        }
        return "" + str;
    }
    public IMyMap<K, V> deepCopy() {
        MyMap<K, V> deepCopiedMap = new MyMap<>();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue() instanceof IValue) {
                IValue valueCopy = ((IValue) entry.getValue()).deepCopy();
                deepCopiedMap.insert(entry.getKey(), (V) valueCopy);
            } else {
                deepCopiedMap.insert(entry.getKey(), entry.getValue());
            }
        }
        return deepCopiedMap;
    }

    public Stream<Map.Entry<K, V>> stream() {
        return map.entrySet().stream();
    }
}
