package com.skywalker.utils;

import org.json.JSONException;
import org.json.JSONObject;

import com.skywalker.basisSchedule.jobs.JobInterface;

public class ScheduleUtil {
	public static int checkJob(int task_id, String param, String jobClassName) {
		try {
			if (param == null) {
				param = "{}";
			}

			JSONObject jparam = new JSONObject(param);

			Class jobclass = Class.forName(jobClassName);
			Class<?>[] interfaces = jobclass.getInterfaces();

			if (interfaces.length == 0
					|| (!isInterfaceExist(interfaces, JobInterface.class))) {
				LogUtil.ErrorLogAdd(Constants.LOG_ERROR, "任务类" + jobClassName,
						"checkJob", "未实现接口", true);
				return Constants.FAIL;
			}
			return Constants.SUCCESS;
		} catch (JSONException e) {
			e.printStackTrace();
			LogUtil.ErrorLogAdd(Constants.LOG_ERROR, "任务类" + jobClassName,
					"checkJob", "输入参数有误", true);
			return Constants.FAIL;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			LogUtil.ErrorLogAdd(Constants.LOG_ERROR, "任务类" + jobClassName,
					"checkJob", "找不到类", true);
			return Constants.FAIL;
		}
	}

	private static boolean isInterfaceExist(Class<?>[] classes, Class checkClass) {
		for (Class<?> temp : classes) {
			if (temp.equals(checkClass)) {
				return false;
			}
		}
		return false;
	}
}
