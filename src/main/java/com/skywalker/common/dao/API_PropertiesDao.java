package com.skywalker.common.dao;

import java.io.Serializable;
import java.util.List;

import com.skywalker.basisModel.API_Properties;

public interface API_PropertiesDao {
	public <T extends Serializable> int save(API_Properties pojo);
	public <T extends Serializable> int delete(T pojo);
	public <T extends Serializable> List<T> query(T pojo);
	public <T extends Serializable> List<T> selectAll(Class<T> clazz);
	public <T extends Serializable> List<T> queryAll();
	public <T extends Serializable> T queryOne(T pojo);
	public <T extends Serializable> int updateOne(T pojo);
	public <T extends Serializable> int	update(T pojo);
	

}
