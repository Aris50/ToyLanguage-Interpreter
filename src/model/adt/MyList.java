package model.adt;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements IMyList<T> {
    private List<T> list;

    public MyList(){
        list = new ArrayList<T>(); //we initialise our list member with a new ArrayList
    }

    public List<T> getAll(){
        return list;
    }

    public void add(T element){
        list.add(element);
    }

    public void remove(T element){
        list.remove(element);
    }

    public void setList(List<T> newList){
        this.list = newList;
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        for(T elem:list){
            str.append(elem.toString());
            str.append("\n");
        }
        return "" + str;
    }
}
