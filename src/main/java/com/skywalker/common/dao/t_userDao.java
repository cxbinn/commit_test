package com.skywalker.common.dao;

import java.io.Serializable;
import java.util.List;

import com.skywalker.basisModel.t_user;

public interface t_userDao {
	public <T extends Serializable> int save(t_user pojo);
	public <T extends Serializable> int delete(T pojo);
	public <T extends Serializable> List<T> query(T pojo);
	public <T extends Serializable> List<T> selectAll(Class<T> clazz);


}
