package com.javase.tmplate.ThreadSync.ReentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by jinyu on 2018/9/21.
 */
public class ReentrantLock04 implements Runnable {

    /*ReentrantLock是一个排他锁，同一时间只允许一个线程访问 默认实现的是非公平锁*/
    /**
     * 非公平锁(NonfairSync)
     *  :lock() 由本身实现。先抢占锁，成功则改变状态，不成功则调用父类的acquire(1)方法，父类的acquire(1)方法里直接调用tryAcquire()。
     *      无视等待队列的存在直接抢占锁,通过cas算法视图修改线程的state,操作成功则当前线程单独持有锁
     *  :tryAcquire(acquires)-->nonfairTryAcquire(acquires),nonfairTryAcquire(acquires)由Sync实现。
     *      先判断锁的状态，通过cas来抢占，如果锁是当前线程的话则通过累加标记重入次数,返回true;如果抢占成功,返回true。如果抢占不成功，则返回false
     * 公平锁(FairSync):
     *  :lock() 调用AbstractQueuedSynchronizer的acquire()方法。
     *  :tryAcquire(acquires)  由自己本身实现
     *      线程的当前锁状态为0，等待队列没有前继线程获取锁的情况下，通过cas抢占锁。
     */

    public static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) {
        ReentrantLock04 reentrantLock04 = new ReentrantLock04();
        for(int i = 0; i < 2; i++){
            new Thread(reentrantLock04).start();
        }
    }

    @Override
    public void run() {
        try{
            if(reentrantLock.tryLock(5, TimeUnit.SECONDS)){
                Thread.sleep(6000);
                System.out.println("获取锁");
            }else{
                System.out.println("获取失败");
            }
        }catch (Exception e){
             e.printStackTrace();
        }finally {
            /*检查当前线程是否拥有该锁*/
            if(reentrantLock.isHeldByCurrentThread()){
                reentrantLock.unlock();
            }
        }
    }
}
