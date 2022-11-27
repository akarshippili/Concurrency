package com.akarsh.synchronization;


public class ThreadSafeCounter {
    private int count = 0;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public synchronized void incrementCount() {
        count++;
    }
}