package com.akarsh.synchronization;


public class ThreadSafeCounter {
    private int count = 0;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    // this will make sure that only one thread is inside the method at a given time
    public synchronized void incrementCount() {
        count++;
    }
}