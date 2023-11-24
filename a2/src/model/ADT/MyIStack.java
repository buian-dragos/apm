package model.ADT;

import java.util.List;
public interface MyIStack<T> {
    T pop();
    void push(T valueToPush);
    boolean isEmpty();
    List<T> getReverese();
}
