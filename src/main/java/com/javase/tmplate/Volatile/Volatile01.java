package com.javase.tmplate.Volatile;

/**
 * Created by jinyu on 2018/9/21.
 */
public class Volatile01 {

    volatile boolean stop = false;

    public void shutDown(){
        stop = true;
    }

    public void doWork(){
        while (!stop){

        }
        System.out.println("doWork() --> stop == true");
    }

    /**
     * 线程1执行的时候每次都会从主存中读取stop。这里验证volatile的线程间可见性
     */
    public static void main(String[] args) throws InterruptedException {
        final Volatile01 volatile01 = new Volatile01();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                volatile01.doWork();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                volatile01.shutDown();
            }
        });

        t1.start();
        t1.sleep(1000);
        t2.start();
    }
}
