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
            log.info("��ð�");
            log.debug("����debug");
            log.error("����");
            log.trace("����ʲô");
        }

    }
}
