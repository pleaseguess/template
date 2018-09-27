package com.springboot.template.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by jinyu on 2018/8/23.
 */
public class PrintJob implements Job{
    public PrintJob() {}

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("this is a printJob" + System.currentTimeMillis());
    }



}
