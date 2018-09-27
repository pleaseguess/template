package com.javase.tmplate.ThreadAchieve;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jinyu on 2018/9/18.
 */
public class Test {
    public volatile AtomicInteger inc = new AtomicInteger(0);
    //Lock lock = new ReentrantLock();

    public void increase() {
        //lock.lock();
        try{
            inc.getAndIncrement();
        }finally {
           // lock.unlock();
        }

    }

    public static void main(String[] args) {
        final Test test = new Test();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<1000;j++)
                        test.increase();

                    System.out.println(Thread.currentThread().getName() + "_被开启");
                };
            }.start();

        }

        while(Thread.activeCount()>1){
            Thread.yield();
        }  //保证前面的线程都执行完

        System.out.println(test.inc);
    }
}
