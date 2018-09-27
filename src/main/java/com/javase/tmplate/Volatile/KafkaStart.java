package com.javase.tmplate.Volatile;

/**
 * Created by jinyu on 2018/9/21.
 */
public class KafkaStart {

    static KafkaStart kafkaStart;

    private static volatile boolean isStart = false;

    public synchronized void start(){
        if(isStart) {
            throw new RuntimeException();
        }
        System.out.println("初始化完成");
        isStart = true;
    }

    public static void main(String[] args) {
        kafkaStart = new KafkaStart();
        TlUtil.timeTasks(10, 1, new Runnable() {
            @Override
            public void run() {
//        KafkaStart xxxStart=new KafkaStart();  //测试下
                kafkaStart.start();
            }
        });
    }
}
