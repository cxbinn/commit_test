package com.skywalker.basisSchedule.threadService;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.json.JSONObject;

import com.skywalker.basisSchedule.jobs.Job;
import com.skywalker.basisSchedule.resultModel.Task;
import com.skywalker.utils.Constants;
import com.skywalker.utils.LogUtil;
import com.skywalker.utils.ScheduleUtil;

public class JobsPool {
	private static JobsPool jobsPool=new JobsPool();
	public static JobsPool getInstance(){
		return jobsPool;
	}
	
	private Set<Job> jobs=new HashSet<Job>();
	
	public int addJob2Jobs(Task runTask,String rundate){
		try{
			int result=ScheduleUtil.checkJob(runTask.getTask_id(),runTask.getPara(),runTask.getTaskclassname());
			if(result==Constants.FAIL){
				runTask.setPara("{}");
			}
			Job job=new Job(runTask.getTask_id(),new JSONObject(runTask.getPara()),runTask.getTaskclassname(),rundate);
			
			synchronized(jobs){
				jobs.add(job);
			}
			LogUtil.SuccessLogAdd(Constants.LOG_INFO, "任务"+runTask.getTask_id(), "job2jobs", true);
			return Constants.SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			LogUtil.ErrorLogAdd(Constants.LOG_ERROR,
			"任务类"+runTask.getTaskclassname(),"job2jobs","未知错误",true);
			return Constants.FAIL;
		}
	}
	
	public int removeJob(int task_id){
		try{
			Iterator<Job> it=jobs.iterator();
			while(it.hasNext()){
				Job temp=it.next();
				if(temp.getTask_id()==task_id){
					synchronized(jobs){
						it.remove();
					}
					LogUtil.SuccessLogAdd(Constants.LOG_INFO,"任务"+task_id,"removeJob",true);
					return Constants.SUCCESS;
				}
			}
			return Constants.FAIL;
		}catch(Exception e){
			e.printStackTrace();
			LogUtil.ErrorLogAdd(Constants.LOG_ERROR,"任务类 任务编号: "+task_id,"removeJob","未知错误",true);
			return Constants.FAIL;
		}
	}
	
	public boolean containJobs(int task_id){
		Iterator<Job> it=jobs.iterator();
		while(it.hasNext()){
			if(it.next().getTask_id()==task_id){
				return true;
			}
		}
		return false;
	}
	public boolean checkJobsEmpty(){
		return jobs.isEmpty();
	}
	
	public int clearJobs(){
		try{
			synchronized(jobs){
				jobs.clear();
			}
			return Constants.SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return Constants.FAIL;
		}
	}
	
	public Job getJob(int task_id){
		Iterator<Job> it=jobs.iterator();
		while(it.hasNext()){
			Job target=it.next();
			if(target.getTask_id()==task_id){
				return target;
			}
		}
		return null;
	}
	
	public Job getJob(){
		Iterator<Job> it=jobs.iterator();
		if(it.hasNext()){
			return it.next();
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
