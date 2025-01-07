package model.adt;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import exceptions.NullRefferenceException;
import model.values.IValue;

///HEAP USES A MAP TO STORE VALUES
///A JAVA MAP NOT IMYMAP!!!!!
/// MAKE IT TEMPLATED


public interface IMyHeap {
    IValue getValue(int address) throws NullRefferenceException;
    void setValue(int address, IValue value) throws NullRefferenceException;
    void setContent(Map<Integer, IValue> newContent);
    Map<Integer, IValue> getContent();
    Set<Integer> getAddresses();
    int allocate(IValue value);
    void deallocate(int address) throws NullRefferenceException;
}
