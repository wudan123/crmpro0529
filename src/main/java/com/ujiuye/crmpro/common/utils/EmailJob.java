package com.ujiuye.crmpro.common.utils;

import com.ujiuye.crmpro.employee.pojo.Email;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class EmailJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        Email email =(Email) jobExecutionContext.getJobDetail().getJobDataMap().get("email");
        EmailUtils.send(email);
    }
}
