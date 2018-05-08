package com.skywalker.utils;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import com.skywalker.basisSchedule.resultModel.T_param;
import com.skywalker.common.service.ScheduleCommonService;

public class Constants {
	
	private static String separator=File.separator;
	
	public final static String MAPPER_TEST="mapperNS.Test";
	public final static String MAPPER_TASK="mapperNS.Task";
	public final static String MAPPER_T_LOG="mapperNS.T_Log";
	public final static String MAPPER_TASK_LIST="mapperNS.Task_List";
	public final static String MAPPER_Schedule="mapperNS.Schedule";
	
	
	public final static int SUCCESS=0;
	public final static int FAIL=-1;
	
	public final static int LOG_DEBUG=0;
	public final static int LOG_INFO=1;
	public final static int LOG_WARN=2;
	public final static int LOG_ERROR=3;
	public final static int LOG_FATAL=4;
	
	public final static int TASK_READY=0;
	public final static int TASK_RUNNING=1;
	public final static int TASK_WAIT=2;
	public final static int TASK_SUCCESS=3;
	public final static int TASK_FAIL=4;
	public final static int TASK_PASS=5;
	
	public final static int BEFOREERRORXIOST=1;
	
	
	private final static String PropertyFile="system-property.xml";
	
	public final static int DUPLICATEKEYERROR=-1;
	public final static int DATAVIOLATIONERROR=-2;
	
	public final static int UNKNOWNERROR=-99;
	
	
	public final static int SLEEPTIME=-1000;
	public static int MONITORSLEEPTIME=10000;
	public static int THREADCOUNT=10;
	public static String UPLOADDIR=System.getProperty("webApp.root")+separator+"WEB_INF"+separator+"upload"+separator;
	private static boolean initflg=true;
	public static String schedule_date=null;
	public static String finish_date=null;
	
	private static int LOGTYPE=-1;
	
	private static ScheduleCommonService scheduleCommonService;
	
	public static void init(){
		scheduleCommonService=(ScheduleCommonService)CommonUtil.getBean(ScheduleCommonService.class);
		if(initflg){
			initSystemProperties();
			initPropertiesFromDB();
			initflg=false;
		}
	}
	public static int getLogType(){
		if(LOGTYPE==-1){
			initSystemProperties();
			return LOGTYPE;
		}else{
			return LOGTYPE;
		}
			
	}
	
	private static void initSystemProperties(){
		try{
			XmlConfig config=XmlConfig.getInstance();
			JSONObject xml=config.getXMLconfig("properties");
			JSONObject properties=xml.getJSONObject("properties");
			LOGTYPE=properties.get("logtype")==null ? LOG_INFO:properties.getInt("logtype");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	private static void initPropertiesFromDB(){
		List<T_param> params=scheduleCommonService.listAll(T_param.class);
		HashMap<String,String> temps=new HashMap<String,String>();
		for(T_param temp :params){
			temps.put(temp.getName(), temp.getValue());
		}
		schedule_date=getProperties(temps,"schedule_date",CommonUtil.date2string8(new Date()));
		finish_date=getProperties(temps,"finish_date",CommonUtil.date2string8(new Date()));
	}
	
	private static String getProperties(HashMap<String,String> temps,String name,String devalue){
		if(temps.containsKey(name)){
			return temps.get(name);
		}else{
			T_param newParam=new T_param();
			newParam.setName(name);
			newParam.setValue(devalue);
			scheduleCommonService.save(newParam);
			return devalue;
		}
	}

}
