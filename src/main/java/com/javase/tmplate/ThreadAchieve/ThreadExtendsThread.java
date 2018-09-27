package com.javase.tmplate.ThreadAchieve;

/**
 * Created by jinyu on 2018/9/17.
 */
public class ThreadExtendsThread extends Thread{

    public static void main(String[] args){
        ThreadExtendsThread t1 = new ThreadExtendsThread();
        ThreadExtendsThread t2 = new ThreadExtendsThread();
        /*线程启动start()方法后就会立刻返回*/
        t1.start();
        t2.start();
    }
    /*访问当前线程直接使用this即可获得当前线程; 单继承*/
    public void run() {
        try{
        this.sleep(3000L);
        System.out.println(this.getName() + "线程休眠完毕开始执行");
        System.out.println(this.getName() + "线程已经状态" + Thread.currentThread().getState());
        int count =10;
        System.out.println(this.getName() +" got count from " + count);
        while(count > 0)
        {
            System.out.println(this.getName() + ":" +  count--);
        }
        System.out.println("this is extends Thread's thread" + this.getName() + "existing count=" + count);
        }catch (Exception e){

        }
    }

}
