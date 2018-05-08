package com.skywalker.common.dao;

import java.io.Serializable;
import java.util.List;

import com.skywalker.basisModel.Passport_Properties;

public interface Passport_PropertiesDao {
	public <T extends Serializable> int save(Passport_Properties pojo);
	public <T extends Serializable> int delete(T pojo);
	public <T extends Serializable> List<T> query(T pojo);
	public <T extends Serializable> List<T> selectAll(Class<T> clazz);


}
