package model.adt;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import exceptions.NullRefferenceException;
import model.values.IValue;

public class MyHeap implements IMyHeap{
    private Map<Integer,IValue> heap;
    private int currentFreeAddress=1;

    public MyHeap(){
        heap = new HashMap<>();
    }

    @Override
    public IValue getValue(int address) throws NullRefferenceException {
        if(heap.containsKey(address))
            return heap.get(address);
        throw new NullRefferenceException("Address not found in heap");
    }

    @Override
    public void setValue(int address, IValue value) throws NullRefferenceException {
        if(heap.containsKey(address))
            heap.put(address,value);
        else
            throw new NullRefferenceException("Address not found in heap");
    }

    @Override
    public void setContent(Map<Integer, IValue> newContent){
        heap = newContent;
    }

    @Override
    public Map<Integer, IValue> getContent(){
        return heap;
    }

    @Override
    public Set<Integer> getAddresses(){
        return heap.keySet();
    }

    @Override
    public int allocate(IValue value){
        heap.put(currentFreeAddress,value);
        return currentFreeAddress++;
    }

    @Override
    public void deallocate(int address) throws NullRefferenceException {
        if(heap.containsKey(address))
            heap.remove(address);
        else
            throw new NullRefferenceException("Address not found in heap");
    }

    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append("Heap:\n");
        if(heap.isEmpty()) {
            result.append("Empty\n");
            return result.toString();
        }
        for(Map.Entry<Integer,IValue> entry : heap.entrySet()){
            result.append(entry.getKey().toString()).append(" -> ").append(entry.getValue().toString()).append("\n");
        }
        return result.toString();
    }

}
