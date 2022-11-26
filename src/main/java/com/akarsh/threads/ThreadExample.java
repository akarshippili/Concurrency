package com.akarsh.threads;

class SequentialExample{
    public static void main(String[] args) {
        Runner1 runner1 = new Runner1();
        Runner2 runner2 = new Runner2();

        runner1.run();
        runner2.run();
    }
}


// Runnable Interface example
class ThreadRunnableExample {
    public static void main(String[] args) {
        // Runnable is a functional interface, so we can use lambda functions

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("Runner1 " + i);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("Runner2 " + i);
            }
        });

        t1.start();
        t2.start();
    }
}

// Thread class Example
class ThreadClassExample{
    public static void main(String[] args) {
        Runner1 runner1 = new Runner1();
        Runner2 runner2 = new Runner2();

        runner1.start();
        runner2.start();
    }
}

class Runner1 extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Runner1 " + i);
        }
    }
}

class Runner2 extends Thread {

    @Override
    public void run() {
        for (int i = 0; i <10; i++) {
            System.out.println("Runner2 " + i);
        }
    }
}