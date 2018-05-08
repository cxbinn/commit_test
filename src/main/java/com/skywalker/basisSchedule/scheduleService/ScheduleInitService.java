package com.skywalker.basisSchedule.scheduleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skywalker.basisSchedule.sqlDao.ScheduleDao;
import com.skywalker.basisSchedule.threadService.Monitor_Thread;
import com.skywalker.basisSchedule.threadService.Schedule_Thread;
import com.skywalker.utils.Constants;

@Service
public class ScheduleInitService {

	@Autowired
	private ScheduleDao scheduleDao;
	
	public int init(){
		Constants.init();
		
		scheduleDao.updateAllTaskList();
		Schedule_Thread schedule_thread=Schedule_Thread.getInstance();
		if(!schedule_thread.isAlive()){
			schedule_thread.start();
		}
		
		Monitor_Thread monitor_thread=Monitor_Thread.getInstance();
		if(!monitor_thread.isAlive()){
			monitor_thread.start();
		}
		return Constants.SUCCESS;
		
		
		
	}
}
