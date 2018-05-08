package com.skywalker.basisController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.skywalker.basisModel.Manager_Properties;
import com.skywalker.basisModel.Project_Properties;
import com.skywalker.basisModel.Sys_User;
import com.skywalker.common.dao.Manager_PropertiesDaoImp;
import com.skywalker.common.dao.Project_PropertiesDaoImp;
import com.skywalker.common.dao.Sys_UserDaoImp;
import com.skywalker.common.dao.t_userDaoImp;

@Controller
public class projectController {
	@Autowired
	private Project_PropertiesDaoImp project_propertiesDaoImp;
	@Autowired
	private t_userDaoImp t_userdaoImp;
	private static int i=0;

	@RequestMapping(value="/register_project.do")
	public String register(Project_Properties m){

		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=sdf.format(new Date());
		
		
		System.out.println(time);
		m.setProject_sn(UUID.randomUUID().toString());		
		m.setRegister_date(new Date());
		project_propertiesDaoImp.save(m);
		
		//JSONObject jresult=new JSONOBject();
		
		return "/infinite/index.html";
	}

}
