package com.cdu.lhj.bstest;

import java.util.HashMap;

public class test {
    public static void main(String[] args) {
        int x =1;
        float y = 2;
        System.out.println(x/y);
    }
}

class B extends Object{
    static {
        System.out.println("Load B");
    }
    public B(){
        System.out.println("C B");
    }
}

class A extends B{
    static {
        System.out.println(": A");
    }
    public A(){
        System.out.println("C A");
    }
}