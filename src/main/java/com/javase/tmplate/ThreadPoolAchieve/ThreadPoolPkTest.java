package com.javase.tmplate.ThreadPoolAchieve;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by jinyu on 2018/9/19.
 */
public class ThreadPoolPkTest {

    public static void main(String[] args) throws InterruptedException {
        HashMap<String,Object> map = new HashMap<>();
        map.size();
        Long start= System.currentTimeMillis();
        final List<Integer> list=new ArrayList<Integer>();
        ExecutorService executorService= Executors.newSingleThreadExecutor();
        final Random random=new Random();
        for(int i=0;i<10000;i++){
            final int j=i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println();
                    list.add(j);
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
        System.out.println(System.currentTimeMillis()-start);
        System.out.println(list.size());


    }
}
