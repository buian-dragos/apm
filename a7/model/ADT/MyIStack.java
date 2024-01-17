package model.ADT;

import java.util.List;
import java.util.Stack;

public interface MyIStack<T> {
    T pop();
    void push(T valueToPush);
    boolean isEmpty();
    String toString();
    public Stack<T> getStack();
    List<T> getAll();
    List<T> getReverese();

}
