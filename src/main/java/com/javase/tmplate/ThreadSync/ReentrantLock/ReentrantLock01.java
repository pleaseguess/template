package com.javase.tmplate.ThreadSync.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by jinyu on 2018/9/21.
 */
public class ReentrantLock01 {
    static ReentrantLock01 reentrantLock01;

    /*ReentrantLock是一个排他锁，同一时间只允许一个线程访问*/
    private static final ReentrantLock reentrantLock = new ReentrantLock();
    static int i = 0;

    public void add(){
        try{
            reentrantLock.lock();
            for(int j = 0; j < 1000; j++){
                i++;
            }
        }finally {
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        reentrantLock01  = new ReentrantLock01();
        new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLock01.add();
            }
        }).start();

        //Thread.sleep(1000);//主要目的是让两个线程把事情干完
        new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLock01.add();
            }
        }).start();
        //Thread.sleep(1000);//主要目的是让两个线程把事情干完
        System.out.println(i);


    }
}
