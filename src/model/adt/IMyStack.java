package model.adt;

public interface IMyStack<T> {
    T pop(); //pops an element from the stack and returns it
    void push(T element); //pushes an element onto the stack
    int getSize();
    boolean isEmpty();
}
