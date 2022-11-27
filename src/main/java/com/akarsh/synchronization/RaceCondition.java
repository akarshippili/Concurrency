package com.akarsh.synchronization;

public class RaceCondition {

    public static void main(String[] args) {
        process();
    }
    public static void process() {

        // in heap - shared memory / resource
        Counter counter = new Counter();

        Runnable run = () -> {
            for (int i = 0; i <100000; i++) {
                // not an atomic operation
                // context switch can happen in between - leads to race condition
                // non-deterministic
                counter.incrementCount();
            }
        };

        Thread t1 = new Thread(run);
        Thread t2 = new Thread(run);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(counter.getCount());

    }
    
}
