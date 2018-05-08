package com.skywalker.utils;

import com.google.gson.Gson;

public class JsonUtil {
	private static Gson g=new Gson();
	static{
		
	}
	public static String Object2JSONString(Object o){
		
		return g.toJson(o);
	}
}
