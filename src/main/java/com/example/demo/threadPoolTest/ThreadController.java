package com.example.demo.threadPoolTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThreadController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MyTask asyncTask;

    @RequestMapping("/task_executor")
    public void testExecutor() {
        for (int i = 0; i < 50; i++) {
            asyncTask.task(i);
        }
        logger.info("--------All tasks finished.");
        //return "All tasks finished.";

    }
}
