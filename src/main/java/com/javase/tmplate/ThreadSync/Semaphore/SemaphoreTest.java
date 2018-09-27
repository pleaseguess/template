package com.javase.tmplate.ThreadSync.Semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by jinyu on 2018/9/25.
 */
public class SemaphoreTest {

    private static final int THREAD_COUNT = 30;

    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);

    private static Semaphore s = new Semaphore(0);

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        /*这里获取线程Semaphore的执行许可证,当前最多允许获取十个*/
                        s.acquire();
                        //Thread.sleep(1000);
                        //System.out.println("获取一个线程使用权后的可用线程数 : " + s.availablePermits());
                        System.out.println(Thread.currentThread().getName() + " : save data");
                        //System.out.println("正在等待获取使用权的线程 : " + s.getQueueLength());
                        s.release();

                    } catch (InterruptedException e) {
                    }
                }
            });
        }

        threadPool.shutdown();
    }

}
