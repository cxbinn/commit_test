package com.skywalker.basisSchedule.threadService;

import com.skywalker.basisSchedule.jobs.Job;

public class ScheduleTask {

	public static Job getjob() {
		if (!JobsPool.getInstance().checkJobsEmpty()) {
			Job returnjob = JobsPool.getInstance().getJob();
			return returnjob;
		} else {
			return null;
		}
	}

}
