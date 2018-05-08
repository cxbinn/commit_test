package com.skywalker.basisSchedule.sqlDao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.skywalker.basisSchedule.resultModel.Task;
import com.skywalker.utils.Constants;
import com.skywalker.utils.LogUtil;

@Repository
public class ScheduleDao {

	@Autowired()
	@Qualifier("schedulesqlSessionTemplate")
	private SqlSessionTemplate sqlSessionSchedule;
	
	public List<Map<String,Object>> queryRunnableList(){
		return sqlSessionSchedule.selectList(Constants.MAPPER_Schedule+".queryRunnableList",null);
		
	}
	
	public List<Task> queryRunnableListByDateString(String dateString){
		return sqlSessionSchedule.selectList(Constants.MAPPER_Schedule+".quertRunnableList",dateString);
	}
	
	public List<String> querynotfinishDate(){
		return sqlSessionSchedule.selectList(Constants.MAPPER_Schedule+".querynotfinishDate");
	}
	
	public int initTaskList(String dateString){
		return insert("initTask_List",dateString);
	}
	
	public int initRepairTask_List(String dateString){
		return insert("initRepairTask_List",dateString);		
	}
	
	public int initInitTask(String dateString){
		return insert("initTask_ListBegin",dateString);
	}
	
	public int queryBeforeErrorCount(){
		int result=Constants.FAIL;
		try{
			result=sqlSessionSchedule.selectOne(Constants.MAPPER_Schedule+".queryBeforeErrorCount");
			LogUtil.SuccessLogAdd(Constants.LOG_INFO,"方法 queryErrorCount ","执行",true);
			return result;
		}catch(Exception e){
			LogUtil.ErrorLogAdd(Constants.LOG_ERROR, "方法 queryErrorCount", "执行", "未知原因", true);
			return result;
		}
	}
	
	private int queryIsInit(){
		int result=Constants.FAIL;
		try{
			result=sqlSessionSchedule.selectOne(Constants.MAPPER_Schedule+".queryIsInit");
			LogUtil.SuccessLogAdd(Constants.LOG_INFO,"方法 queryIsInit ","执行",true);
			return result;
		}catch(Exception e){
			LogUtil.ErrorLogAdd(Constants.LOG_ERROR, "方法 queryIsInit ", "执行", "未知原因", true);
			return result;
		}
	}
	
	public int updateAllTaskList(){
		return update("updateAllTaskList",null);
	}
	
	private int update(String sqlid,Object parameter){
		try{
			if(parameter==null){
				sqlSessionSchedule.update(Constants.MAPPER_Schedule+"."+sqlid);
			}else{
				sqlSessionSchedule.update(Constants.MAPPER_Schedule+"."+sqlid,parameter);				
			}
			LogUtil.SuccessLogAdd(Constants.LOG_INFO, "方法 updateAllTaskList "+sqlid, "执行", true);
			return Constants.SUCCESS;
		}catch(Exception e){
			LogUtil.ErrorLogAdd(Constants.LOG_ERROR, "方法 updateAllTaskList "+sqlid, "执行", "未知原因", true);
			return Constants.FAIL;
		}
	}
	
	private int insert(String sqlid,Object parameter){
		try{
			if(parameter==null){
				sqlSessionSchedule.insert(Constants.MAPPER_Schedule+"."+sqlid);				
			}else{
				sqlSessionSchedule.insert(Constants.MAPPER_Schedule+"."+sqlid,parameter);				
			}
			LogUtil.SuccessLogAdd(Constants.LOG_INFO,"方法 initTaskList "+sqlid,"执行" ,true);
			return Constants.SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			LogUtil.ErrorLogAdd(Constants.LOG_ERROR,"方法 initTaskList "+sqlid,"执行","未知原因",true);
			return Constants.FAIL;
		}
	}
}
