package com.skywalker.common.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.skywalker.common.dao.ScheduleDaoImp;

public class ScheduleCommonService implements CommonService {

	@Autowired
	private ScheduleDaoImp scheduleDaoImp;
	
	public <T extends Serializable> int save(T pojo) {
		// TODO Auto-generated method stub
		return 0;
	}

	public <T extends Serializable> int update(T pojo) {
		// TODO Auto-generated method stub
		return 0;
	}

	public <T extends Serializable> int delete(T pojo) {
		// TODO Auto-generated method stub
		return 0;
	}

	public <T extends Serializable> List<T> get(T pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T extends Serializable> T getOne(T pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T extends Serializable> List<T> listAll(Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

}
