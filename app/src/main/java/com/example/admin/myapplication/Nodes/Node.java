package com.example.admin.myapplication.Nodes;

/**
 * Created by I'm always dancing on 31/12/2015.
 */
public class Node<T> {

    public T Value;
    Node<T> next;

    public Node(T Value){
        this.Value = Value;
        next = null;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
    public Node getNext() {
        return next;
    }
}
