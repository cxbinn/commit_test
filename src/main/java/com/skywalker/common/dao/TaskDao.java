package com.skywalker.common.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import com.skywalker.basisSchedule.resultModel.Task;
import com.skywalker.utils.Constants;
import com.skywalker.utils.LogUtil;

@Repository
public class TaskDao {
	
	private static Logger logger = LogManager.getLogger(TaskDao.class.getName());
	
	@Autowired
	private SqlSessionTemplate sqlSessionSchedule;
	
	public List<Task> query(int page,int rows){
		return sqlSessionSchedule.selectList(Constants.MAPPER_TASK+".queryTask",new RowBounds((page-1)*rows,rows));				
	}
	
	public List<Task> query(){
		return sqlSessionSchedule.selectList(Constants.MAPPER_TASK+".queryTask");
	}
	
	public int insert(Task task){
		try{
			sqlSessionSchedule.insert(Constants.MAPPER_TASK+".insertTask",task);
			LogUtil.SuccessLogAdd(Constants.LOG_INFO,"任务 "+task.getTask_id(),"插入",true);
			return task.getTask_id();
		}catch(DuplicateKeyException e){
			LogUtil.ErrorLogAdd(Constants.LOG_ERROR, "任务 "+task.getTask_id(), "插入", "违反完整性约束", true);
			return Constants.UNKNOWNERROR;
		}
	}
	
	public Map<Integer,Integer> delete(List<Integer> task_id){
		Map<Integer,Integer> resultMap=new HashMap<Integer,Integer>();
		for(int i:task_id){
			try{
				sqlSessionSchedule.delete(Constants.MAPPER_TASK+".deleteTask",i);
				resultMap.put(i, Constants.SUCCESS);
				LogUtil.SuccessLogAdd(Constants.LOG_INFO,"任务 "+i,"删除",true);
			}catch(Exception e){
				resultMap.put(i, Constants.FAIL);
				LogUtil.ErrorLogAdd(Constants.LOG_ERROR, "任务 "+i, "删除", "未知原因", true);
			}
		}
		return resultMap;
	}

}
