package com.akarsh.synchronization;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MyBlockingQueueV2<T> {

    private final java.util.Queue<T> queue;
    private final int maxSize;
    private final Lock lock;

    private final Condition notEmpty;
    private final Condition notFull;

    public MyBlockingQueueV2(int maxSize) {
        this.queue = new java.util.LinkedList<>();
        this.lock = new ReentrantLock();
        this.notEmpty = this.lock.newCondition();
        this.notFull = this.lock.newCondition();
        this.maxSize = maxSize;
    }

    public T take() throws InterruptedException {
        lock.lock();
        try {
            while (isEmpty()) notEmpty.await();
            T item = queue.remove();
            notFull.signalAll();
            return item;
        } finally {
            lock.unlock();
        }


    }

    public void put(T value) throws InterruptedException {
        lock.lock();
        try {
            while (isFull()) notFull.await();
            queue.add(value);
            notEmpty.signalAll();
        } finally {
            lock.unlock();
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



class ReentrantLockExample {
    public static void main(String[] args) {

        MyBlockingQueueV2<Integer> resource = new MyBlockingQueueV2<>(5);

        Runnable producer = () -> {
            for (int i = 0; i <100; i++) {
                try {
                    Thread.sleep(1);
                    resource.put(i);
                    System.out.println("producing " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        };

        Runnable consumer = () -> {
            for(int i = 0; i <100; i++){
                try {
                    Thread.sleep(500);
                    System.out.println("consuming" + resource.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    // sonar comment -- check for reason why ?
                    Thread.currentThread().interrupt();
                }
            }
        };

        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(consumer);

        t1.start();
        t2.start();


    }
}

