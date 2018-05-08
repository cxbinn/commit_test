package com.skywalker.basisSchedule.threadService;

import java.util.Date;

import com.skywalker.basisSchedule.jobs.Job;
import com.skywalker.basisSchedule.resultModel.Task_List;
import com.skywalker.common.service.ScheduleCommonService;
import com.skywalker.utils.CommonUtil;
import com.skywalker.utils.Constants;

public class Schedule_Thread extends Thread {
	private static Schedule_Thread thread = new Schedule_Thread();

	public static Schedule_Thread getInstance() {
		return thread;
	}

	private ScheduleCommonService scheduleCommonService;

	@Override
	public void run() {
		try {
			scheduleCommonService = (ScheduleCommonService) CommonUtil
					.getBean(ScheduleCommonService.class);
			while (true) {
				if (!JobsPool.getInstance().checkJobsEmpty()) {
					Job runJob = null;
					while (!JobsPool.getInstance().checkJobsEmpty()) {
						runJob = ScheduleTask.getjob();
						if (runJob == null) {
							continue;
						}
						Date begin = new Date();
						Task_List task_list = new Task_List(
								runJob.getTask_id(), Constants.TASK_RUNNING);
						task_list.setT_date(runJob.getJob_date());
						task_list.setBeg_time(begin);
						scheduleCommonService.update(task_list);
						JobsPool.getInstance().removeJob(runJob.getTask_id());
						JobThread jobThread = new JobThread(runJob);
						jobThread.run();
					}
				} else {
					sleep(Constants.SLEEPTIME);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
