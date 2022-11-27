package com.akarsh.synchronization;

public class SynchronizedExample {

    public static void main(String[] args) {
        process();
    }
    public static void process() {

        // in heap - shared memory / resource
        ThreadSafeCounter counter = new ThreadSafeCounter();

        Runnable run = () -> {
            for (int i = 0; i <100000; i++) {
                // atomic operation
                // deterministic
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
