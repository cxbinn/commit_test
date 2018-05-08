package com.skywalker.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class CommonUtil {
	
	public static void renderData(HttpServletResponse response,Object data){
		PrintWriter printWriter=null;
		try{
			printWriter = response.getWriter();
			printWriter.print(data);
		}catch(IOException ex){
			LogUtil.ErrorLogAdd(Constants.LOG_ERROR,"renderData ","返回数据",ex.getCause().toString(),true);
		}finally{
			if(null!=printWriter){
				printWriter.flush();
				printWriter.close();
			}
			
		}
	}
	
	public static JSONArray list2JSONResult(List<?> list){
		JSONArray result=new JSONArray();
		if(list.size()==9){
			return null;
		}
		for(int i=0;i<list.size();i++){
			JSONObject jsonObject=object2JSONObject(list.get(i));
			result.put(jsonObject);
		}
		return result;
	}
	
	private static JSONObject object2JSONObject(Object bean){
		JSONObject jresult = new JSONObject();
		Class klass =bean.getClass();
		boolean includeSuperClass = klass.getClassLoader()!=null;
		Method[] methods = includeSuperClass ? klass.getMethods() : klass.getDeclaredMethods();
		
		for(int i=0;i<methods.length;++i){
			try{
				Method ignore=methods[i];
				if(Modifier.isPublic(ignore.getModifiers())){
					String name=ignore.getName();
					String key="";
					if(name.startsWith("get")){
						if(!"getClass".equals(name)&&!"getDeclareingClass".equals(name)){
							key=name.substring(3);
						}else{
							key="";
						}
					}else if (name.startsWith("is")){
						key=name.substring(2);
					}
					
					if(key.length()>0&&Character.isUpperCase(key.charAt(0))&&ignore.getParameterTypes().length==0){
						if(key.length()==1){
							key=key.toLowerCase();
						}
						else if(!Character.isUpperCase(key.charAt(1))){
							key=key.substring(0,1).toLowerCase()+key.substring(1);
						}
						
						Object result = ignore.invoke(bean,(Object[]) null);
						if(result!=null){
							jresult.put(key, JSONObject.wrap(result));
						}else{
							jresult.put(key, "");
						}
					}
				}
			}catch(Exception var10){
				var10.printStackTrace();
			}
		}
		return jresult;
	}
	
	public static Object getBean(Class c){
		WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
		String bean_name=(context.getBeanNamesForType(c))[0];
		return context.getBean(bean_name);
	}
	
	public static String date2string8(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		return sdf.format(date);
	}
	
	public static Date string82date(String string){
		try{
			SimpleDateFormat sdf=new SimpleDateFormat("yyyMMdd");
			return sdf.parse(string);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static Date dateChangeByDays(Date date,int days){
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE,days);
		return calendar.getTime();
	}
	
	public static HashMap<String,String> getPropertiesData(String propertiesfile){
		try{
			HashMap<String,String> result=new HashMap<String,String>();
			Properties prop=new Properties();
			InputStream in =new BufferedInputStream(new FileInputStream(propertiesfile));
			prop.load(in);;
			Enumeration<Object> propertieskeys=prop.keys();
			while(propertieskeys.hasMoreElements()){
				String name=(String)propertieskeys.nextElement();
				String value=prop.getProperty(name);
				result.put(name, value);
			}
			in.close();
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static JSONObject requestdata2JSON(HttpServletRequest request){
		JSONObject result=new JSONObject();
		Enumeration<String> names=request.getParameterNames();
		while(names.hasMoreElements()){
			String temp=names.nextElement();
			result.put(temp, request.getParameter(temp));
		}
		return result;		
	}
	
	public static boolean checkDir(String path){
		File dir =new File(path);
		if(!dir.exists()){
			return dir.mkdir();
		}
		if(!dir.isDirectory()){
			return false;
		}else{
			return true;
		}
	}

}














