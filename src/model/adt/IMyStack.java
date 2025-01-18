package model.adt;

import java.util.stream.Stream;

public interface IMyStack<T> {
    T pop(); //pops an element from the stack and returns it
    void push(T element); //pushes an element onto the stack
    int getSize();
    boolean isEmpty();

    Stream<T> stream();
}
