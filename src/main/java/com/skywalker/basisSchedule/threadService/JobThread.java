package com.skywalker.basisSchedule.threadService;

import java.util.Date;

import org.json.JSONObject;

import com.skywalker.basisSchedule.jobs.Job;
import com.skywalker.basisSchedule.jobs.JobInterface;
import com.skywalker.basisSchedule.resultModel.Task_List;
import com.skywalker.common.service.ScheduleCommonService;
import com.skywalker.utils.ClassUtil;
import com.skywalker.utils.CommonUtil;
import com.skywalker.utils.Constants;
import com.skywalker.utils.LogUtil;

public class JobThread implements Runnable{

	private Job job;
	private JSONObject param=null;
	
	private ScheduleCommonService scheduleCommonService;
	
	public JobThread(Job job){
		this.job=job;
		initJobThread();
	}
	
	public void run() {
		// TODO Auto-generated method stub
		int st=Constants.TASK_FAIL;
		Task_List task_list=new Task_List();
		task_list.setTask_id(job.getTask_id());
		try{
			Class jobclass;
			try{
				jobclass=Class.forName(job.getJobClass());
			}catch(ClassNotFoundException e){
				String classname=job.getJobClass().substring(job.getJobClass().lastIndexOf(".")+1);
				jobclass=ClassUtil.getInstance().getClass(classname);
			}
			
			if(param==null){
				param=job.getParam();
			}else{
				param=job.getParam().append("EXParam",param);
			}
			JobInterface work=(JobInterface)jobclass.newInstance();
			
			int result=ClassUtil.getInstance().invokemethod(jobclass,"work",param);
			Date end=new Date();
			
			if(result==Constants.SUCCESS){
				st=Constants.TASK_SUCCESS;
			}
			
			task_list.setT_date(job.getJob_date());
			task_list=scheduleCommonService.getOne(task_list);
			task_list.setSt(st);
			task_list.setEnd_time(end);
			scheduleCommonService.update(task_list);
			LogUtil.SuccessLogAdd(Constants.LOG_INFO, "JobThread task_id "+job.getTask_id(), "执行", true);
			ThreadAnalyze.analyze();
		}catch(Exception e){
			e.printStackTrace();
			task_list.setSt(st);
			scheduleCommonService.update(task_list);
			LogUtil.ErrorLogAdd(Constants.LOG_ERROR, "JobThread task_id "+job.getTask_id(),"执行", e.getClass().getName(),  true);
		}
		
	}
	private void initJobThread(){
		scheduleCommonService=(ScheduleCommonService)CommonUtil.getBean(ScheduleCommonService.class);
	}

}
