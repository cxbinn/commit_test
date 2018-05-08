package com.skywalker.common.dao;

import java.io.Serializable;
import java.util.List;

import com.skywalker.basisModel.API_Passport;

public interface API_PassportDao {
	public <T extends Serializable> int save(API_Passport pojo);
	public <T extends Serializable> int delete(T pojo);
	public <T extends Serializable> List<T> query(T pojo);
	public <T extends Serializable> List<T> selectAll(Class<T> clazz);

}
