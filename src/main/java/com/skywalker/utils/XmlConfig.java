package com.skywalker.utils;

import java.io.File;
import java.util.Set;

import org.json.JSONObject;

public class XmlConfig {
	public String xmlPath=null;
	private static XmlConfig xmlconfig=new XmlConfig();
	
	public static XmlConfig getInstance(){
		return xmlconfig;
	}
	
	private XmlConfig(){
		this.xmlPath=this.getClass().getResource("/").getPath()+"config/";
	}
	
	public JSONObject getXMLconfig(String xmlFilename){
		String xmlFilePath=this.xmlPath+xmlFilename;
		File xmlFile=new File(xmlFilePath);
		if(!xmlFile.exists()){
			System.out.println("xmlfile not exists");
			return null;
		}else{
			return XmlUtil.xml2JSONObject(xmlFile);
		}
	}
	
	public boolean isNodeInXml(String xmlFilename,String node){
		JSONObject xml=getXMLconfig(xmlFilename);
		if(xml==null){
			return false;
		}
		return isNodeInJSON(xml,node);
	}
	
	private boolean isNodeInJSON(JSONObject data,String node){
		Set<String> keys=data.keySet();
		for(String key:keys){
			if(key.equals(node)){
				return true;
			}
			if(data.get(key).getClass()==JSONObject.class){
				return isNodeInJSON(data.getJSONObject(key),node);
			}
		}
		return false;
		
	}

}
