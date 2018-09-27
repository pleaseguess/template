package com.javase.tmplate.ThreadPoolAchieve;

import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * Created by jinyu on 2018/9/19.
 */
public class ThreadPoolExecuterTmp {

    private static final long DEFAULT_KEEPALIVE_MILLIS = 10L;
    public static void main(String[] args) {
        /**
         *  FixedThreadPool
         *  线程数少于核心线程数，也就是设置的线程数时，新建线程执行任务
         *  线程数等于核心线程数后，将任务加入阻塞队列(队列容量非常大，可以一直加加加)
         *  执行完任务的线程反复去队列中取任务执行
         *  用于负载比较重的服务器，为了资源的合理利用，需要限制当前线程数量。
         */
        new ThreadPoolExecutor(10, 10,0L, MILLISECONDS,new LinkedBlockingQueue<Runnable>());

       /**
        *   CachedThreadPool
        *   没有核心线程，直接向 SynchronousQueue 中提交任务
        *   如果有空闲线程，就去取出任务执行；如果没有空闲线程，就新建一个
        *   执行完任务的线程有 60 秒生存时间，如果在这个时间内可以接到新任务，就可以继续活下去，否则就拜拜
        *   用于并发执行大量短期的小任务，或者是负载较轻的服务器。
        */
        new ThreadPoolExecutor(0,Integer.MAX_VALUE,60L,TimeUnit.SECONDS,new SynchronousQueue<Runnable>());

        /**
         *  SingleThreadExecutor
         *  线程池中没有线程时，新建一个线程执行任务
         *  有一个线程以后，将任务加入阻塞队列，不停加加加
         *  唯一的这一个线程不停地去队列里取任务执行
         *  用于串行执行任务的场景，每个任务必须按顺序执行，不需要并发执行
         */
        new ThreadPoolExecutor(0, Integer.MAX_VALUE,60L, TimeUnit.SECONDS,new SynchronousQueue<Runnable>());

        /**
         *  ScheduledThreadPool
         *
         *
         *
         */

    }



}
