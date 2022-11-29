package com.akarsh.synchronization;


class Resource<T> {

    private final java.util.Queue<T> queue = new java.util.LinkedList<>();
    private final int maxSize;
    private final Object lock = new Object();

    public Resource(int maxSize) {
        this.maxSize = maxSize;
    }

    public T take() throws InterruptedException {
        synchronized (lock) {
            while (isEmpty()) lock.wait();
            T item = queue.remove();
            lock.notifyAll();
            return item;
        }
    }

    public void put(T value) throws InterruptedException {
        synchronized (lock) {
            while (isFull()) lock.wait();
            queue.add(value);
            lock.notifyAll();
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

        Resource<Integer> resource = new Resource<>(5);

        Runnable producer = () -> {
            for (int i = 0; i <100; i++) {
                try {
                    Thread.sleep(100);
                    resource.put(i);
                    System.out.println("producing " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
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
                }
            }
        };

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
