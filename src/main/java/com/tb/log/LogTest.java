package com.tb.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * Created by yangzhuo02 on 2016/1/20.
 *
 */
public class LogTest {

    private static Logger log = LogManager.getLogger(LogTest.class.getName());

    public static void main(String[] args) {

        for(int index = 0; index < 100; ++index) {
            log.info("你好啊");
            log.debug("我是debug");
            log.error("错了");
            log.trace("这是什么");
        }

    }
}
