package com.skywalker.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;

import org.json.JSONObject;

public class ClassUtil {
	private class JobsLoader extends ClassLoader{
		private String path=null;
		public void setPath(String path){
			this.path=path;
		}
		
		@Override
		protected Class<?> findClass(String name) throws ClassNotFoundException{
			Class clazz=null;
			byte[] classData=getClassData(name);
			clazz=defineClass(name,classData,0,classData.length);
			return clazz;
		}
		
		private byte[] getClassData(String name){
			String classFilePath="";
			
			if(name.length()-name.lastIndexOf(".class")!=6||name.lastIndexOf(".class")<0){
				name=name+".class";
			}
			
			if(name.indexOf(".")==name.lastIndexOf(".")){
				classFilePath=path+name;
			}
			else{
				classFilePath=path+name.substring(name.substring(0,name.lastIndexOf(".")).lastIndexOf("."+1));
			}
			
			File classFile=new File(classFilePath);
			try{
				InputStream in = new FileInputStream(classFile);
				byte[] result = new byte[(int) classFile.length()];
				in.read(result);
				in.close();
				return result;
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
				
		}
	}
	
	private static ClassUtil classUtil=new ClassUtil();
	
	public static ClassUtil getInstance(){
		return classUtil;
	}
	
	private JobsLoader loader;
	
	private ClassUtil(){
		loader=new JobsLoader();
		loader.setPath(Constants.UPLOADDIR);
		
	}
	
	public boolean hasClass(String classname){
		if(getClass(classname)!=null){
			return true;
		}else{
			return false;
		}
	}
	
	public Class getClass(String classname){
		Class result=null;
		try{
			result=Class.forName(classname);
			return result;
		}catch(ClassNotFoundException e){
			try{
				result=loader.loadClass(classname);
				return result;
			}catch(ClassNotFoundException e1){
				return result;
			}
		}
	}
	
	public int invokemethod(Class classname,String methodname,Object... args){
		try{
			Object target=classname.newInstance();
			Method method=classname.getDeclaredMethod(methodname, JSONObject.class);
			
			//#######
			return new Integer(method.invoke(target, args).toString());
			//###
		}catch(Exception e){
			e.printStackTrace();
			return Constants.FAIL;
		}
	}
	
}
