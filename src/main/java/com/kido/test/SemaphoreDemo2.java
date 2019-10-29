package com.kido.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 业务场景
 * 业务需求1：假如现在有20个人去售票厅窗口买票，但是窗口只有2个，那么同时能够买票的只能有2个人，
 *           当2个人中任意1个人买完票离开窗口之后，等待的18个人中又会有一个人可以占用窗口买票。
 */
public class SemaphoreDemo2 implements Runnable {
    private Semaphore semaphore;//信号量
    private int users;//第几个用户

    public SemaphoreDemo2(){

    }
    public SemaphoreDemo2(Semaphore semaphore, int users) {
        this.semaphore = semaphore;
        this.users = users;
    }

    public void run (){
        try {
            //允许获取信号量才可以占用窗口
            semaphore.acquire();
            //用户进入窗口开始买票
            System.out.println("用户"+users+"进入窗口,准备买票......");

            //模拟买票时间
            Thread.sleep((long) Math.random()*10000);
            System.out.println("用户"+users+"购票成功,准备离开......");

            Thread.sleep((long)Math.random()*10000);
            System.out.println("用户"+ users + "离开售票窗口...");
            //释放信号量许可证
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void execute(){
        //定义窗口的个数
        final Semaphore s = new Semaphore(2);

        //创建线程池
        ExecutorService threadPool = Executors.newCachedThreadPool();

        //模拟用户数量
        for(int i=0;i<20;i++){
            threadPool.execute(new SemaphoreDemo2(s,(i+1)));
        }

        //关闭线程池
        threadPool.shutdown();
    }

    public static void main(String[] args) {
        SemaphoreDemo2 semaphoreDemo = new SemaphoreDemo2();
        semaphoreDemo.execute();
    }
}
