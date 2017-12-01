package com.example.demo.threadPoolTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MyTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyTask.class);

    @Async("myExecutorPool")
    public   void task(int i) {

        LOGGER.info("MyTask----------" + i + "started");

    }
}
