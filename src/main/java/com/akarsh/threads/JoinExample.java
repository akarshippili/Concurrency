package com.akarsh.threads;

public class JoinExample {
    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(Math.round(Math.random() * 3));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("one " + i);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(Math.round(Math.random() * 3));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("two " + i);
            }
        });

        t1.start();
        t2.start();

        System.out.println("Join Example");

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Waiting for t1 to finish");


    }
}
