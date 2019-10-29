package com.kido.test;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        final CyclicBarrier cb = new CyclicBarrier(3,new Runnable(){
            public void run(){

            }
        });
    }

}
