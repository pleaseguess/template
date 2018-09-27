package com.javase.tmplate.ThreadSync.CyclicBarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * 若干个线程同时写数据，线程全部执行完之后，随机选一个线程执行预订的任务。
 */
public class CyclicBarrier01 {

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4, new Runnable() {
            @Override
            public void run() {
                System.out.println("当前线程 : " + Thread.currentThread().getName());
            }
        });
        for(int i = 0; i < 4; i++){
            if(i != 3){
                new Thread(new Writer(cyclicBarrier)).start();
            }else{
                Thread.sleep(5000);
                new Thread(new Writer(cyclicBarrier)).start();
            }

        }
    }


    static class Writer implements Runnable{

        private CyclicBarrier cyclicBarrier;

        public Writer(CyclicBarrier cyclicBarrier){
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程" + Thread.currentThread().getName() + " : 正在写入数据" );
            try{
                Thread.sleep(5000);
                System.out.println("线程" + Thread.currentThread().getName() + " : 写入数据完成,等待其他线程写入" );
                cyclicBarrier.await();

                /*等待到了指定的时间发现第四个线程还没有到达barrier,就抛出异常并执行后面的任务*/
                //cyclicBarrier.await(2000, TimeUnit.MILLISECONDS);
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "写入完毕,继续处理其他任务");
        }
    }

}
