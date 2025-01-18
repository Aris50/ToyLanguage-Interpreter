package model.adt;

import java.util.Stack;
import java.util.stream.Stream;

import exceptions.EmptyStackException;

public class MyStack<T> implements IMyStack<T>{
    public Stack<T> stack;
    public MyStack(){
        this.stack = new Stack<T>();
    }
    public void push(T element){
        stack.push(element);
    }
    public T pop() throws EmptyStackException {
        if(stack.isEmpty()){
            throw new EmptyStackException("Stack is empty cannot pop element");
        }
        return this.stack.pop();
    }
    public int getSize(){
        return stack.size();
    }
    public boolean isEmpty(){
        return stack.isEmpty();
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        int nr=stack.size();
        int num=0;
        for(int i=stack.size()-1; i>=0; i--){
            num++;
            str.append(num);
            str.append(": ");
            str.append(stack.get(i).toString());
            str.append("\n");
        }
        return "" + str;
    }

    public Stream<T> stream(){
        return stack.stream();
    }

}
