package com.skywalker.basisController;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.skywalker.basisModel.Sys_User;
import com.skywalker.basisModel.t_user;
import com.skywalker.common.dao.Sys_UserDaoImp;
import com.skywalker.common.dao.t_userDaoImp;

@Controller
public class userController {
	
	@Autowired
	private Sys_UserDaoImp sys_userDaoImp;
	@Autowired
	private t_userDaoImp t_userdaoImp;
	private static int i=0;

	@RequestMapping(value="/register.do")
	public String register(Sys_User u){

		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=sdf.format(new Date());
		
		
		System.out.println(time);
		System.out.println(u.getName());		
		u.setId(1);
		sys_userDaoImp.save(u);
		
		//JSONObject jresult=new JSONOBject();
		
		return "/infinite/index.html";
	}
	
	@RequestMapping(value="/t_registerUser.do")
	public String t_register(t_user u){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		String time=sdf.format(date);
		
		System.out.println(time);
		System.out.println(u.getName());		
		u.setId(new String().valueOf(++i));
		u.setRegisterTime(date);
		t_userdaoImp.save(u);
		
		
		//JSONObject jresult=new JSONOBject();
		
		return "/infinite/index.html";
	}
	
}
