package com.akarsh.synchronization;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

class Service {
    // some task for which I want to all only 5 thread at a time
    private final Semaphore semaphore;

    public Service(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    public void run() throws InterruptedException {
        semaphore.acquireUninterruptibly();
        System.out.println(Thread.currentThread().getName() + " acquired lock");
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + " released lock");
        semaphore.release();
    }

}



public class SemaphoreExample {
    public static void main(String[] args) throws InterruptedException {

        Semaphore semaphore = new Semaphore(3, true);
        Service service = new Service(semaphore);

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        IntStream.range(1, 21).forEach(i -> executorService.execute(() -> {
            try {
                service.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
    }
}
