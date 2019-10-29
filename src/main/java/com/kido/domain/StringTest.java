package com.kido.domain;

public class StringTest {
    public static void main(String[] args) {
        String str = " 2344445fkgjklj ";
        String strs = str.trim();
        System.out.println(strs);
        int result= str.indexOf("f");
        System.out.println(result);
        String s = str.substring(8,14);
        System.out.println(s);
    }
}
