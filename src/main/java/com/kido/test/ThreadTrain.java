package com.kido.test;

public class ThreadTrain implements Runnable {
    private int countTotal = 100;
    public  void  run(){
         while(countTotal>0){
             try {
                 Thread.sleep(50);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             sale();
         }
    }
    //售票窗口
//    public void sale() {
//    Object obj = new Object();
//        //同步代码块 使用的是obj任意锁  就是在可能产生线程安全问题的时候将代码包裹起来
//        synchronized (obj) {  也可以是this
//            if (countTotal > 0) {
//                System.out.println(Thread.currentThread().getName() + ",出售第" + (100 - countTotal + 1) + "张票");
//                countTotal--;
//            }
//        }
//    }
    //同步函数锁 用的是this
    public synchronized void sale() {
            if (countTotal > 0) {
                System.out.println(Thread.currentThread().getName() + ",出售第" + (100 - countTotal + 1) + "张票");
                countTotal--;
            }
    }
}
