package com.solvd.block2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SomeClass {

    public static void main(String[] args) {

        final Logger LOGGER = LogManager.getLogger(SomeClass.class);

        LOGGER.info("Testing logger!");
    }
}

