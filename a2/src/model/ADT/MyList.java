package model.ADT;

import model.ADT.MyIList;
import java.util.List;
import java.util.ArrayList;
public class MyList<V> implements MyIList<V>{
    private List<V> list;
    public MyList(){
        list = new ArrayList<V>();
    }
    public MyList(List<V> list){
        this.list = list;
    }
    public List<V> getList(){
        return list;
    }
    public void setList(List<V> list){
        this.list = list;
    }
    public String toString(){
        return
                "MyList{" +
                "list=" + list +
                '}';
    }
    @Override
    public void add(V valueToAdd){
        list.add(valueToAdd);
    }
    @Override
    public void clear(){
        list.clear();
    }
}
