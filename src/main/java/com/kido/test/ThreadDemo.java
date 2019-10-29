package com.kido.test;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class ThreadDemo {
    public static void main(String[] args) {
        ThreadTrain threadTrain = new ThreadTrain();
        Thread t1 = new Thread(threadTrain,"窗口1");
        Thread t2 = new Thread(threadTrain,"窗口2");
        Thread t3 = new Thread(threadTrain,"窗口3");
        t1.start();
        t2.start();
        t3.start();
    }
}
