package com.skywalker.common.dao;

import java.io.Serializable;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import com.skywalker.basisModel.API_Passport;
import com.skywalker.utils.Constants;

@Repository
public class API_PassportDaoImp implements API_PassportDao {
	protected SqlSessionTemplate sqlSessionTemplate;
	protected String MAPPERNS;
	protected String MAPPERSUF;
	
	@Autowired()
	public API_PassportDaoImp(@Qualifier( "sqlSessionTemplate")SqlSessionTemplate sqlSessionSchedule){
		this.sqlSessionTemplate=sqlSessionSchedule;
		this.MAPPERNS="mapperNS.";
		this.MAPPERSUF=".";
	}

	public <T extends Serializable> int save(API_Passport pojo) {
		// TODO Auto-generated method stub
		try{
			
			sqlSessionTemplate.insert("com.skywalker.common.dao.API_PassportDao."+"save",pojo);
			return Constants.SUCCESS;
		}catch(DuplicateKeyException e){
			return Constants.DUPLICATEKEYERROR;
		}catch(DataIntegrityViolationException e){
			return Constants.DATAVIOLATIONERROR;			
		}catch(Exception e){
			e.printStackTrace();
			return Constants.UNKNOWNERROR;
		}
	}

	public <T extends Serializable> int delete(T pojo) {
		// TODO Auto-generated method stub
		return 0;
	}

	public <T extends Serializable> List<T> query(T pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T extends Serializable> List<T> selectAll(Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

}
