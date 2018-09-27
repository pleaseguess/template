package com.javase.tmplate.ThreadAchieve;

/**
 * Created by jinyu on 2018/9/19.
 */
public class Thread2 implements  Runnable {
    private Boolean stop;
    Thread2(Boolean stop){
        this.stop = stop;
    }
    @Override
    public void run() {
        stop = true;
    }
}
