package com.skywalker.common.dao;

import java.io.Serializable;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;

import com.skywalker.utils.Constants;

public class CommonDaoImp implements CommonDao {

	protected SqlSessionTemplate sqlSessionTemplate;
	protected String MAPPERNS;
	protected String MAPPERSUF;
	public <T extends Serializable> int save(T pojo) {
		// TODO Auto-generated method stub
		try{
			System.out.println(MAPPERNS+MAPPERSUF+"insert");
			sqlSessionTemplate.insert(MAPPERNS+pojo.getClass().getSimpleName()+MAPPERSUF+"insert",pojo);
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

	public <T extends Serializable> int update(T pojo) {
		// TODO Auto-generated method stub
		try{
			sqlSessionTemplate.update(MAPPERNS+pojo.getClass().getSimpleName()+MAPPERSUF+"update",pojo);
			return Constants.SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return Constants.FAIL;
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

	public <T extends Serializable> T getOne(T pojo) {
		// TODO Auto-generated method stub
		try{
			return sqlSessionTemplate.selectOne(MAPPERNS+pojo.getClass().getSimpleName()+MAPPERSUF+"select",pojo);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}	
	}

	public <T extends Serializable> List<T> listAll(Class<T> clazz) {
		// TODO Auto-generated method stub
		try{
			return sqlSessionTemplate.selectList(MAPPERNS+clazz.getClass().getSimpleName()+MAPPERSUF+"selectAll");
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}	
	}

}
