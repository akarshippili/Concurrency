package com.akarsh.leetcode;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class Foo {

    private ReentrantLock lock;
    private int next = 1;
    private Condition firstCondition;
    private  Condition secondCondition;

    public Foo() {
        lock = new ReentrantLock();
        firstCondition = lock.newCondition();
        secondCondition = lock.newCondition();
    }

    public void first(Runnable printFirst) throws InterruptedException {
        lock.lock();
        printFirst.run();
        firstCondition.signal();
        next++;
        lock.unlock();
    }

    public void second(Runnable printSecond) throws InterruptedException {

        // printSecond.run() outputs "second". Do not change or remove this line.
        lock.lock();
        while(next!=2) firstCondition.await();
        printSecond.run();
        secondCondition.signal();
        next++;
        lock.unlock();
    }

    public void third(Runnable printThird) throws InterruptedException {

        // printThird.run() outputs "third". Do not change or remove this line.
        lock.lock();
        while(next!=3) secondCondition.await();
        printThird.run();
        lock.unlock();
    }

}