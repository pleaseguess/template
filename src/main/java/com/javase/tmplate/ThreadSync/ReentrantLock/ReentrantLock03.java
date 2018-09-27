package com.javase.tmplate.ThreadSync.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by jinyu on 2018/9/21.
 */
public class ReentrantLock03 {

    private static final ReentrantLock reentrantLock=new ReentrantLock();
    static int i = 0;

    public void add(){
        /*仅在调用时锁未被另一个线程保持的情况下，才获取该锁*/
        if(reentrantLock.tryLock()){
            try{
                for(;;){
                    i++;
                    //System.out.println(Thread.currentThread().getName() + " : ");
                }
            }finally {
                System.out.println(Thread.currentThread().getName() + " : 线程功能执行完毕");
                reentrantLock.unlock();
            }
        }else {
            System.out.println(Thread.currentThread().getName() + " : 没有获取到锁");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final ReentrantLock03 reentrantLock03 = new ReentrantLock03();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLock03.add();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLock03.add();
            }
        });

        thread1.start();
        thread1.sleep(1000);


        thread2.start();
        thread2.sleep(1000);

        //thread2.interrupt();

        System.out.println(i);
    }

}
