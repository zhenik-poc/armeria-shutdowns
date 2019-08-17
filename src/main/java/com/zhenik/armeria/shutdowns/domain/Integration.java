package com.zhenik.armeria.shutdowns.domain;

public class Integration implements Runnable {
    @Override
    public void run() {
        int i = 0;
        while(true) {
            i++;
            if (i==5) throw new RuntimeException("Some runtime exception");
        }
    }
}
