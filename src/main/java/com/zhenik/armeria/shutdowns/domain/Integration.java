package com.zhenik.armeria.shutdowns.domain;


import java.util.logging.Logger;

public class Integration implements Runnable {
    private Logger logger = Logger.getLogger(Integration.class.getName());

    private Boolean willFail;

    public Integration(boolean willFail) {
        this.willFail = willFail;
    }

    @Override
    public void run() {
        logger.info("Integration started");
        int i = 0;
        while (true) {
            i++;
            if (willFail && i == 5) throw new RuntimeException("Some runtime exception");
        }
    }

    public void stop() {
        logger.info("Integration stopped");
    }
}
