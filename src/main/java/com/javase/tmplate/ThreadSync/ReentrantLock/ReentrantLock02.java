package com.javase.tmplate.ThreadSync.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by jinyu on 2018/9/21.
 */
public class ReentrantLock02 {

    private static final ReentrantLock reentrantLock = new ReentrantLock();
    static int i = 0;
    static ReentrantLock02 reentrantLock02;

    public  void add() {
        try {
            try {
                /*如果当前线程未被中断，则获取锁。*/
                reentrantLock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (; ; ) {
                i++;
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        reentrantLock02 = new ReentrantLock02();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLock02.add();
            }
        });
        thread1.start();
        Thread.sleep(1000);

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLock02.add();
            }
        });
        thread2.start();
        Thread.sleep(1000);//主要目的是让下面的interrupt执行
        thread2.interrupt();
        System.out.println(i);
    }

}
