package com.example.admin.myapplication.Nodes;

import com.example.admin.myapplication.Nodes.Node;

/**
 * Created by I'm always dancing on 04/01/2016.
 */
public class Queue<T> {
    private Node<T> first;    // beginning of queue
    private Node<T> last;     // end of queue
    private int N;            // number of elements on queue

    public  Queue(){
        first = null;
        last = null;
        N = 0;
    }
    public boolean IsEmpty(){
        //If Queue is empty
        if (first == null)
            return true;
        return false;
    }

    public int size()
    {
        return N;
    }

    public void Push(T Value){
        Node<T> NextNode = new Node<T>(Value);
        if(IsEmpty()){
            first = NextNode;
            last = first;
        }
        else {
            last.setNext( NextNode );
            last = last.getNext();
    }
}

    public T Pop(){
        if(IsEmpty()){
            return null;
        }
        else {
            T var = first.Value;
            first = first.getNext();
            return var;
        }
    }

    public Node<T> getFirst() {
        return first;
    }

    public Node<T> getLast() {
        return last;
    }
}

