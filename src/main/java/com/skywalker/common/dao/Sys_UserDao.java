package com.skywalker.common.dao;

import java.io.Serializable;
import java.util.List;

import com.skywalker.basisModel.Sys_User;

public interface Sys_UserDao {
	public <T extends Serializable> int save(Sys_User pojo);
	public <T extends Serializable> int delete(T pojo);
	public <T extends Serializable> List<T> query(T pojo);
	public <T extends Serializable> List<T> selectAll(Class<T> clazz);


}
