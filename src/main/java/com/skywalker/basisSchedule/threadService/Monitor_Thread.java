package com.skywalker.basisSchedule.threadService;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.skywalker.basisSchedule.resultModel.T_param;
import com.skywalker.basisSchedule.resultModel.Task;
import com.skywalker.basisSchedule.resultModel.Task_List;
import com.skywalker.basisSchedule.sqlDao.ScheduleDao;
import com.skywalker.common.service.ScheduleCommonService;
import com.skywalker.utils.CommonUtil;
import com.skywalker.utils.Constants;
import com.skywalker.utils.LogUtil;

public class Monitor_Thread extends Thread {

	private static Monitor_Thread thread = new Monitor_Thread();

	public static Monitor_Thread getInstance() {
		return thread;
	}

	private ScheduleDao scheduleDao;

	private ScheduleCommonService scheduleCommonService;

	private boolean newFinish_datehasInit = false;

	private Monitor_Thread() {
		scheduleDao = (ScheduleDao) CommonUtil.getBean(ScheduleDao.class);
		scheduleCommonService = (ScheduleCommonService) CommonUtil
				.getBean(ScheduleCommonService.class);
	}

	@Override
	public void run() {
		try {
			while (true) {
				List<Map<String, Object>> runnableLists = scheduleDao
						.queryRunnableList();
				if (!runnableLists.isEmpty()) {
					for (Map<String, Object> temp : runnableLists) {
						Task runnableTask = new Task();
						runnableTask.setTask_id(Integer.parseInt(String
								.valueOf(temp.get("task_id"))));
						runnableTask
								.setPara((String) temp.get("taskclassname"));
						runnableTask.setTaskclassname((String) temp
								.get("Taskclassname"));

						if (JobsPool.getInstance().containJobs(
								runnableTask.getTask_id())) {
							continue;
						}

						if (JobsPool.getInstance().addJob2Jobs(runnableTask,
								(String) temp.get("t_date")) == Constants.FAIL) {
							continue;
						}

						Task_List task_list = new Task_List(
								runnableTask.getTask_id(), Constants.TASK_WAIT);
						task_list.setT_date((String) temp.get("t_date"));
						scheduleCommonService.update(task_list);
						LogUtil.SuccessLogAdd(
								Constants.LOG_INFO,
								"Monitor_Thread task_id "
										+ runnableTask.getTask_id(),
								Monitor_Thread.class.getName(), true);
					}
				} else {
					List<String> notFinishDate = scheduleDao
							.querynotfinishDate();
					if (notFinishDate == null || notFinishDate.size() == 0) {
						T_param t_param = new T_param();
						t_param.setName("finish_date");
						t_param = scheduleCommonService.getOne(t_param);
						if (!Constants.finish_date.equals(t_param.getValue())) {
							Constants.finish_date = t_param.getValue();
						}

						if (CommonUtil.string82date(t_param.getValue()).after(
								CommonUtil.string82date(Constants.finish_date))) {
							t_param.setValue(Constants.finish_date);
							scheduleCommonService.update(t_param);
						}

						if (!Constants.schedule_date.equals(t_param.getValue())) {
							String newFinish_date = CommonUtil
									.date2string8(CommonUtil.dateChangeByDays(
											CommonUtil
													.string82date(Constants.finish_date),
											1));

							if (newFinish_datehasInit) {
								t_param.setValue(newFinish_date);
								scheduleCommonService.update(t_param);
								Constants.finish_date = newFinish_date;
								newFinish_datehasInit = false;
							} else {
								scheduleDao.initInitTask(newFinish_date);
								newFinish_datehasInit = true;
							}
						}

					}
					Date nowDate = new Date();
					if (nowDate.after(CommonUtil
							.string82date(Constants.schedule_date))) {
						Constants.schedule_date = CommonUtil
								.date2string8(nowDate);
						T_param param = new T_param();
						param.setName("schedule_date");
						param.setValue(Constants.schedule_date);
						scheduleCommonService.update(param);
					}
					sleep(Constants.MONITORSLEEPTIME);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.ErrorLogAdd(Constants.LOG_INFO, "Monitor_Thread ",
					Monitor_Thread.class.getName(), e.getClass().getName(),
					true);
		}
	}

}
