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

    private static Semaphore s = new Semaphore(5);

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        /*这里获取线程Semaphore的执行许可证,当前最多允许获取十个,
                        有线程执行完这一步的时候,Semaphore的许可证会 -1,
                        当许可证为-1的时候还有线程访问这里就会阻塞*/
                        s.acquire();
                        Thread.sleep(1000);
                        //System.out.println("获取一个线程使用权后的可用线程数 : " + s.availablePermits());
                        System.out.println(Thread.currentThread().getName() + " : save data");
                        //System.out.println("正在等待获取使用权的线程 : " + s.getQueueLength());
                        /*枚举一个许可，返回给信号量;线程执行完这一步的时候,Semaphore的许可证会 +1*/
                        s.release();

                    } catch (InterruptedException e) {
                    }
                }
            });
        }

        threadPool.shutdown();
    }

}
