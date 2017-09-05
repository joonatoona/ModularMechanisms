/*
* Copyright (C) 2017 Joona <joonatoona@digitalfishfun.com>
* SPDX-License-Identifier: GPL-3.0+
*/

package com.digitalfishfun.modularmechanisms.utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

public class MMLogger {
    public static Logger logger;

    public static void log(Level logLevel, Object object) {
        logger.log(logLevel, String.valueOf(object));
    }

    public static void error(Object object) {
        logger.log(Level.ERROR, object);
    }

    public static void warn(Object object) {
        logger.log(Level.WARN, object);
    }

    public static void info(Object object) {
        logger.log(Level.INFO, object);
    }

    public static void debug(Object object) {
        // TODO: Debug option in configuration file
        if (true) {
            logger.log(Level.DEBUG, object);
        }
    }
}
