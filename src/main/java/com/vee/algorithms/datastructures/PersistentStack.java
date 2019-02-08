package com.vee.algorithms.datastructures;

public class PersistentStack {

    public void push(Integer item) {

    }

    public Integer pop() {
        return -1;
    }
}

class StackNode {
    PersistentStack prev;
    Integer value;

    public StackNode(PersistentStack prev, Integer value) {
        this.prev = prev;
        this.value = value;
    }

    public PersistentStack pop() {
        return prev;
    }

    public Integer peek() {
        return value;
    }
}