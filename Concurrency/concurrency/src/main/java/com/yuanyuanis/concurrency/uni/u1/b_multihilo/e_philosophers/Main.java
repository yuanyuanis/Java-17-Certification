package com.yuanyuanis.concurrency.uni.u1.b_multihilo.e_philosophers;

import java.util.logging.Logger;

public class Main {

    private static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String ...args) throws InterruptedException {
        logger.info("Set up dinner...");
        Table table = new Table(5);
        Thread dinner = new Thread(table);
        logger.info("Starting dinner...");
        dinner.start();
        dinner.join();
    }
}
