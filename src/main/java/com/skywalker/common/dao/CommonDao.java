package com.skywalker.common.dao;

import java.io.Serializable;
import java.util.List;

public interface CommonDao {
	public <T extends Serializable> int save(T pojo);
	public <T extends Serializable> int update(T pojo);
	public <T extends Serializable> int delete(T pojo);
	public <T extends Serializable> List<T> get(T pojo);
	public <T extends Serializable> T getOne(T pojo);
	public <T extends Serializable> List<T> listAll(Class<T> clazz);

}
