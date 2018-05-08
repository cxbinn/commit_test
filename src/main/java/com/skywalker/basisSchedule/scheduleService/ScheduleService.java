package com.skywalker.basisSchedule.scheduleService;

import java.io.File;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.skywalker.basisSchedule.resultModel.Dep;
import com.skywalker.basisSchedule.resultModel.Task;
import com.skywalker.basisSchedule.sqlDao.ScheduleDao;
import com.skywalker.common.service.ScheduleCommonService;
import com.skywalker.utils.CommonUtil;
import com.skywalker.utils.Constants;


@Service
public class ScheduleService {
	@Autowired
	private ScheduleCommonService scheduleCommonService;
	
	@Autowired
	private ScheduleDao scheduleDao;
	
	public int insertTask(JSONObject input){
		Task task=new Task();
		Dep dep=new Dep();
		try{
			task.setTask_id(input.getInt("task_id"));
			task.setName(input.getString("name"));
			task.setDes(input.getString("des"));
			task.setTaskclassname(input.getString("taskclassname"));
			task.setType(input.getInt("para"));
			task.setPara(input.getString("name"));
			task.setPrior(input.getInt("prior"));
			dep.setTask_id(task.getTask_id());
			dep.setParent_id(input.getInt("parent_id"));
			if(scheduleCommonService.save(task)!=Constants.SUCCESS){
				return Constants.FAIL;
			}else{
				scheduleCommonService.save(dep);
			}
			return Constants.SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return Constants.FAIL;
		}
	}
	
	public int classFileUpload(MultipartFile file){
		try{
			if(!CommonUtil.checkDir(Constants.UPLOADDIR)){
				return Constants.FAIL;
			}
			File newFile=new File(Constants.UPLOADDIR+file.getOriginalFilename());
			file.transferTo(newFile);
			
			if(!newFile.exists()){
				return Constants.FAIL;
			}
			
			return Constants.SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return Constants.FAIL;
		}
	}
	
	public int initTask(String date){
		return scheduleDao.initTaskList(date);
	}
	
	public int initRepairTask(String date){
		return scheduleDao.initTaskList(date);
	}
	

}
