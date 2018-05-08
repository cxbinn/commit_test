package com.skywalker.basisSchedule.scheduleService;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skywalker.basisSchedule.sqlDao.ScheduleDao;
import com.skywalker.utils.CommonUtil;
import com.skywalker.utils.Constants;

@Service
public class ScheduleRepairService {
	@Autowired
	private ScheduleDao scheduleDao;
	
	@Autowired
	private ScheduleService scheduleService;
	
	private long sleeptime=1000000;
	
	public int repait(){
		
		int able2run =-1;
		
		able2run=scheduleDao.queryBeforeErrorCount();
		Date finish_date =CommonUtil.string82date(Constants.finish_date);
		Date schedule_date=CommonUtil.string82date(Constants.schedule_date);
		String newdate=null;
		try{
			if(CommonUtil.dateChangeByDays(schedule_date, -1).after(schedule_date)){
				scheduleDao.updateAllTaskList();
			}
			
			while(CommonUtil.dateChangeByDays(schedule_date,-1).after(schedule_date)){
				if(newdate==CommonUtil.date2string8(CommonUtil.dateChangeByDays(finish_date, 1))){
					Thread.sleep(sleeptime);
				}else{
					newdate=CommonUtil.date2string8(CommonUtil.dateChangeByDays(finish_date, 1));
					scheduleDao.initRepairTask_List(newdate);
				}
			}
			return Constants.SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return Constants.FAIL;
		}
		
		
	}
}
