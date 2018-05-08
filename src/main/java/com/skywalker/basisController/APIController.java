package com.skywalker.basisController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skywalker.basisModel.API_Properties;
import com.skywalker.basisModel.Manager_Properties;
import com.skywalker.basisModel.Project_Properties;
import com.skywalker.basisModel.Sys_User;
import com.skywalker.common.dao.API_PropertiesDaoImp;
import com.skywalker.common.dao.Manager_PropertiesDaoImp;
import com.skywalker.common.dao.Project_PropertiesDaoImp;
import com.skywalker.common.dao.Sys_UserDaoImp;
import com.skywalker.common.dao.t_userDaoImp;
import com.skywalker.utils.JsonUtil;

@Controller
public class APIController {
	@Autowired
	private API_PropertiesDaoImp api_propertiesDaoImp;
	private static int i=0;

	@RequestMapping(value="/register_api.do")
	public String register(API_Properties m){

		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=sdf.format(new Date());
		
		
		System.out.println(time);
		m.setApi_sn(UUID.randomUUID().toString());
		m.setRegister_date(new Date());
		m.setApi_project_sn("test");
		api_propertiesDaoImp.save(m);
		
		//JSONObject jresult=new JSONOBject();
		
		return "/infinite/index.html";
	}

	@RequestMapping(value="/queryAll_api",
			method=RequestMethod.GET,
			produces="application/json;encoding=UTF-8;charset=UTF-8")
	@ResponseBody
	public String queryAll(){

		
		String result=JsonUtil.Object2JSONString(api_propertiesDaoImp.queryAll());
		System.out.println("result:"+result);
		//JSONObject jresult=new JSONOBject();
		
		return result;
	}
	
	@RequestMapping(value="/query/{id}",
			method=RequestMethod.GET,
			produces="application/json;encoding=UTF-8;charset=UTF-8")
	@ResponseBody
	public String query(@PathParam("id") String id){

		
		//String result=JsonUtil.Object2JSONString(api_propertiesDaoImp.query(pojo));
		String result="";
		System.out.println("result:"+result);
		//JSONObject jresult=new JSONOBject();
		
		return result;
	}
}
