package com.javase.tmplate.ThreadAchieve;

/**
 * Created by jinyu on 2018/9/19.
 */
public class Thread1 implements Runnable{
    private Boolean stop;
    Thread1(Boolean stop){
        this.stop = stop;
    }
    @Override
    public void run() {
        while (!stop){
            System.out.println(Thread.currentThread().getName() + "_stop_state:" + stop);
        }
    }
}
