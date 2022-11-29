package com.akarsh.synchronization;


class Resource<T> {

    private final java.util.Queue<T> queue = new java.util.LinkedList<>();
    private final int maxSize;
    private final Object lock = new Object();

    public Resource(int maxSize) {
        this.maxSize = maxSize;
    }

    public T take() {

        synchronized (lock) {
            return queue.remove();
        }

    }

    public void put(T value) {

        synchronized (lock) {
            queue.add(value);
        }

    }

    public int getSize() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public boolean isFull() {
        return getSize() == this.maxSize;
    }

}



class LockExample {
    public static void main(String[] args) {

        Resource resource = new Resource(5);

        Runnable producer = () -> {};
        Runnable consumer = () -> {};

        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(producer);
        Thread t3 = new Thread(producer);

        Thread t4 = new Thread(consumer);
        Thread t5 = new Thread(consumer);
        Thread t6 = new Thread(consumer);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();


    }
}


public class WaitNotifyExample {
    public static void main(String[] args) {}
}
