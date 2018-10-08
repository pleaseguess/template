package com.springboot.template.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * Created by jinyu on 2018/10/8.
 */

@Component
@EnableScheduling
public class MyFirstExerciseJob {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void myJobBusinessMethod() {
        this.logger.info("哇被触发了哈哈哈哈哈 --ExerciseJobA");
    }

}
