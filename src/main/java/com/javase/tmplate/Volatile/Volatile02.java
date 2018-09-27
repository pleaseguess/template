package com.javase.tmplate.Volatile;

/**
 * Created by jinyu on 2018/9/21.
 */
public class Volatile02 implements Runnable {

    static volatile int	i = 1;

    /**
     * 线程刚开始初始化的时候有可能并发读取 i
     * 但是在操作 i 的时候都会从主从中去读取 i
     */
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " : " + i + " , " + (++i));
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Volatile02(),"a");
        Thread t2 = new Thread(new Volatile02(),"b");
        Thread t3 = new Thread(new Volatile02(),"c");
        Thread t4 = new Thread(new Volatile02(),"d");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
