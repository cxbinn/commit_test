package com.skywalker.common.dao;

import java.io.Serializable;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import com.skywalker.basisModel.Sys_User;
import com.skywalker.utils.Constants;

@Repository
public class Sys_UserDaoImp implements Sys_UserDao {

	protected SqlSessionTemplate sqlSessionTemplate;
	protected String MAPPERNS;
	protected String MAPPERSUF;
	
	@Autowired()
	public Sys_UserDaoImp(@Qualifier( "sqlSessionTemplate")SqlSessionTemplate sqlSessionSchedule){
		this.sqlSessionTemplate=sqlSessionSchedule;
		this.MAPPERNS="mapperNS.";
		this.MAPPERSUF=".";
	}
	
	public <T extends Serializable> int save(Sys_User pojo) {
		// TODO Auto-generated method stub
		try{
		
			for(String a:sqlSessionTemplate.getConfiguration().getParameterMapNames()){
				System.out.println("a:");
				System.out.println(a);
			}
			System.out.println("com.skywalker.common.dao.Sys_UserDao."+"save");
			sqlSessionTemplate.insert("com.skywalker.common.dao.Sys_UserDao."+"save",pojo);
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
		try{
			sqlSessionTemplate.delete(MAPPERNS+pojo.getClass().getSimpleName()+MAPPERSUF+"update",pojo);
			return Constants.SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return Constants.FAIL;
		}	
	}

	public <T extends Serializable> List<T> get(T pojo) {
		// TODO Auto-generated method stub
		try{
			return sqlSessionTemplate.selectList(MAPPERNS+pojo.getClass().getSimpleName()+MAPPERSUF+"select",pojo);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}	
	}

	public <T extends Serializable> List<T> query(T pojo) {
		// TODO Auto-generated method stub
		try{
			return sqlSessionTemplate.selectOne(MAPPERNS+pojo.getClass().getSimpleName()+MAPPERSUF+"select",pojo);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}	
	}

	public <T extends Serializable> List<T> selectAll(Class<T> clazz) {
		// TODO Auto-generated method stub
		try{
			return sqlSessionTemplate.selectList(MAPPERNS+clazz.getClass().getSimpleName()+MAPPERSUF+"selectAll");
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}	
	}
	
	/*
	@Autowired()
	public Sys_UserDaoImp(@Qualifier( "sqlSessionTemplate")SqlSessionTemplate sqlSessionSchedule){
		this.sqlSessionTemplate=sqlSessionSchedule;
		this.MAPPERNS="";
		this.MAPPERSUF=".";
	}
	*/
	
	
	
}
