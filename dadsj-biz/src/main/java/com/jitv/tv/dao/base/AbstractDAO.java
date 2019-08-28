package com.jitv.tv.dao.base;

import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aspire.commons.Errors;
import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.jitv.tv.dto.base.AbstractCommonDTO;
import com.jitv.tv.dto.base.AbstractDTO;


public abstract class AbstractDAO {
	private static SqlMapClient sqlMapClient = null;
	private static final Logger logger = LoggerFactory
			.getLogger(AbstractDAO.class);
	public final static class Param{
		private final String key;
		private final Object value;
		
		private Param(String key, Object value) {
			super();
			this.key = key;
			this.value = value;
		}
	}
	
	protected static final Param newParam(String key, Object value){
		return new Param(key, value);
	}
	
	private final String namespace;

	public AbstractDAO(String namespace) {
		this.namespace = namespace;
	}
	
	protected String getSqlId(String name){
		return namespace + "." + name;
	}

	public SqlMapClient getSqlMapClient() {
		try {
			if (sqlMapClient == null) {
				Reader reader = Resources
						.getResourceAsReader("ibatis/SqlMapConfig.xml");
				
				sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(reader);
			}

		} catch (Exception e) {
			logger.error(" to mysql Exception:",e);
		}
		return sqlMapClient;
	}
	
	protected Object function(String name, Param... ps){
		long bLong = Calendar.getInstance().getTimeInMillis();
		Object back = null;
		String id = getSqlId(name);	
		Map<String,Object> paramMap = new HashMap<String,Object>();
		String paramMapString = "";
		
		try {
			if(ps.length == 0){
				back =  getSqlMapClient().queryForObject(id);
			}else{
				paramMap = asParamMap(ps);
				paramMapString = paramMapToString(paramMap);
				back =  getSqlMapClient().queryForObject(id, paramMap);
			}
		} catch (SQLException e) {
			logger.error(" queryForList to mysql Exception "+" name:"+name+" param:"+paramMapString,e);
			throw Errors.E303;
		}
		long eLong = Calendar.getInstance().getTimeInMillis();
		logger.warn("function time:"+(eLong-bLong) + " name:"+name+" param:"+paramMapString+" :end");
		if((eLong-bLong) > 200){
			logger.warn("sql查询使用时间" + " name:"+name + "  ;" + (eLong-bLong));
		}
		return back;
	}
	
	protected <T extends AbstractDTO> List<T> queryForList(String name, Class<T> dtoClass, Date val){
		return queryForList(name, dtoClass, newParam("val", val));
	}
	
	protected <T extends AbstractDTO> List<T> queryForList(String name, Class<T> dtoClass, int val){
		return queryForList(name, dtoClass, newParam("val", val));
	}
	
	protected <T extends AbstractDTO> List<T> queryForList(String name, Class<T> dtoClass, String val){ 
		return queryForList(name, dtoClass, newParam("val", val));
	}
	
	@SuppressWarnings("unchecked")
	protected <T extends AbstractDTO> List<T> queryForList(String name, Class<T> dtoClass, Param... ps) {
		long bLong = Calendar.getInstance().getTimeInMillis();
		List<Map<String, Object>> resultList = null;
		List<T> list = null;
		String id = getSqlId(name);
		Map<String,Object> paramMap = new HashMap<String,Object>();
		String paramMapString = "";
		try {
			if(ps.length == 0){
				resultList = getSqlMapClient().queryForList(id);
			}else{
				paramMap = asParamMap(ps);
				paramMapString = paramMapToString(paramMap);
				resultList = getSqlMapClient().queryForList(id, paramMap);
			}
			list = toDTOList(dtoClass, resultList);
		} catch (Exception e) {
			logger.error(" queryForList to mysql Exception "+" name:"+name+" param:"+paramMapString,e);
			throw Errors.E303;
		}
		long eLong = Calendar.getInstance().getTimeInMillis();
		logger.warn("queryForList time:"+(eLong-bLong) + " name:"+name+" param:"+paramMapString+" :end");
		if((eLong-bLong) > 200){
			logger.warn("sql查询使用时间" + " name:"+name + "  ;" + (eLong-bLong));
		}
		return list;
	}
	
	private String paramMapToString(Map<String, Object> map){ 
		StringBuffer sb = new StringBuffer();
		Object[] keys = map.keySet().toArray();
		for(int i=0;i<keys.length;i++){
			Object value = map.get(keys[i]);
			if(value!=null&&!"".equals(value)){
				sb.append("|"+keys[i]).append("=").append(value.toString()).append(",");
			}
			
		}
		return sb.toString();
	}
	
	@SuppressWarnings("unchecked")
	protected <T> List<T> queryForLists(String name, Class<T> dtoClass, Param... ps) {	
		long bLong = Calendar.getInstance().getTimeInMillis();
		String id = getSqlId(name);
		List<Map<String, Object>> resultList = null;
		Map<String,Object> paramMap = new HashMap<String,Object>();
		String paramMapString = "";
		try {			 
			if(ps.length == 0){
				resultList = getSqlMapClient().queryForList(id);
			}else{
				paramMap = asParamMap(ps);
				paramMapString = paramMapToString(paramMap);
				resultList = getSqlMapClient().queryForList(id, paramMap);
			}
		} catch (Exception e) {
			logger.error(" queryForList to mysql Exception "+" name:"+name+" param:"+paramMapString,e);
			throw Errors.E303;
		}
		long eLong = Calendar.getInstance().getTimeInMillis();
		logger.warn("queryForList time:"+(eLong-bLong) + " name:"+name+" param:"+paramMapString+" :end");
		if((eLong-bLong) > 200){
			logger.warn("sql查询使用时间" + " name:"+name + "  ;" + (eLong-bLong));
		}
		return (List<T>) resultList;
	}
	
	@SuppressWarnings("unchecked")
	protected <T extends AbstractDTO> List<T> queryForListTwo(String name, Class<T> clz, Object o) {
		long bLong = Calendar.getInstance().getTimeInMillis();
		String id = getSqlId(name);
		List  resultList = null; 
		List<T> back = null;
		String title = "";
		if(o != null){
			title = o.toString();
		}
		try {
			resultList = getSqlMapClient().queryForList(id, o);
			back =  toDTOList(clz, resultList);
		} catch (Exception e) {
			logger.error(" queryForList to mysql Exception "+" name:"+name+" param:"+title,e);
			throw Errors.E303;
		}
		long eLong = Calendar.getInstance().getTimeInMillis();
		logger.warn("queryForList time:"+(eLong-bLong) + " name:"+name+" param:"+title+" :end");
		if((eLong-bLong) > 200){
			logger.warn("sql查询使用时间" + " name:"+name + "  ;" + (eLong-bLong));
		}
		return back;
	}
	
	
	protected <T extends AbstractDTO> T queryForObject(String name, Class<T> dtoClass, int val){
		return queryForObject(name, dtoClass, newParam("val", val));
	}
	
	protected <T extends AbstractDTO> T queryForObject(String name, Class<T> dtoClass, String val){
		return queryForObject(name, dtoClass, newParam("val", val));
	}
	
	protected <T extends AbstractDTO> T queryForObject(String name, Class<T> dtoClass, Date val){
		return queryForObject(name, dtoClass, newParam("val", val));
	}
	
	@SuppressWarnings("unchecked")
	protected <T extends AbstractDTO> T queryForObject(String name, Class<T> dtoClass, Param... ps) {
		long bLong = Calendar.getInstance().getTimeInMillis();
		String id = getSqlId(name);	
		Map<String, Object> resultMap;
		Map<String,Object> paramMap = new HashMap<String,Object>();
		String paramMapString = "";
		T back = null;
		try {
			if(ps.length == 0){
				resultMap = (Map<String, Object>) getSqlMapClient().queryForObject(id);
			}else{
				paramMap = asParamMap(ps);
				paramMapString = paramMapToString(paramMap);
				resultMap = (Map<String, Object>) getSqlMapClient().queryForObject(id, paramMap);
			}
			if(resultMap != null){
				back = toDTO(dtoClass, resultMap);
			}
		} catch (Exception e) {
			logger.error(" queryForObject to mysql Exception "+" name:"+name+" param:"+paramMapString,e);
			throw Errors.E303;
		}
		long eLong = Calendar.getInstance().getTimeInMillis();
		logger.warn("select time("+(eLong-bLong) + ") name:"+name+" param:"+paramMapString+" :end");
		if((eLong-bLong) > 200){
			logger.warn("sql查询使用时间" + " name:"+name + "  ;" + (eLong-bLong));
		}
		return back;
	}
	
	protected int delete(String name, Param... ps){
		long bLong = Calendar.getInstance().getTimeInMillis();
		String id = getSqlId(name);
		int result = -1;
		Map<String,Object> paramMap = new HashMap<String,Object>();
		String paramMapString = "";
		try {			
			if(ps.length == 0){
				result = getSqlMapClient().delete(id);
			}else{
				paramMap = asParamMap(ps);
				paramMapString = paramMapToString(paramMap);
				result = getSqlMapClient().delete(id, asParamMap(ps));
			} 
		} catch (SQLException e) {
			logger.error(" delete to mysql Exception "+" name:"+name+" param:"+paramMapString,e);
			throw Errors.E302;
		}
		long eLong = Calendar.getInstance().getTimeInMillis();
		logger.warn("delete time("+(eLong-bLong) + ") name:"+name+" param:"+paramMapString+"  result:"+result+" :end");
		if((eLong-bLong) > 200){
			logger.warn("sql查询使用时间" + " name:"+name + "  ;" + (eLong-bLong));
		}
		return result;
	}
	
	protected <T extends AbstractDTO>  void insert(String name, T dto){
		long bLong = Calendar.getInstance().getTimeInMillis();
		String id = getSqlId(name);		
		Map<String, Object> map = dto.toDbMap();
		String paramMapString = "";
		paramMapString = paramMapToString(map);
		Object result="";
		try {
			result = getSqlMapClient().insert(id, map);			
			if(dto instanceof AbstractCommonDTO){
				String generateId = (String) result;
				((AbstractCommonDTO)dto).setId(generateId);
			}
		} catch (SQLException e) {
			logger.error(" insert to mysql Exception "+" name:"+name+" param:"+paramMapString,e);
			throw Errors.E302;
		}
		if(result==null){
			result = "";
		}
		long eLong = Calendar.getInstance().getTimeInMillis();
		logger.warn("insert time("+(eLong-bLong) + ") name:"+name+" param:"+paramMapString+"  result:"+result.toString()+" :end");
		if((eLong-bLong) > 200){
			logger.warn("sql查询使用时间" + " name:"+name + "  ;" + (eLong-bLong));
		}
	}
	 
	protected <T extends AbstractDTO> int update(String name, T dto, Param... ps){
		long bLong = Calendar.getInstance().getTimeInMillis();
		String id = getSqlId(name);
		Map<String, Object> map = joinUpdateMap(dto.toDbMap(), asParamMap(ps));
		int reuslt = -1;
		String paramMapString = "";
		paramMapString = paramMapToString(map);
		try {
			reuslt = getSqlMapClient().update(id, map);
		} catch (SQLException e) {
			logger.error(" update to mysql Exception "+" name:"+name+" param:"+paramMapString,e);
			throw Errors.E302;
		}
		long eLong = Calendar.getInstance().getTimeInMillis();
		logger.warn("update time("+(eLong-bLong) + ") name:"+name+" param:"+paramMapString+" reuslt:"+reuslt+" :end");
		if((eLong-bLong) > 200){
			logger.warn("sql查询使用时间" + " name:"+name + "  ;" + (eLong-bLong));
		}
		return reuslt;
	}
	
	protected   int update(String name, Map<String, Object> param){
		long bLong = Calendar.getInstance().getTimeInMillis();
		String id = getSqlId(name);
		int reuslt = -1;
		String paramMapString = "";
		paramMapString = paramMapToString(param);
		try {
			reuslt = getSqlMapClient().update(id, param); 
		} catch (SQLException e) {
			logger.error(" update to mysql Exception "+" name:"+name+" param:"+paramMapString,e);
			throw Errors.E302;
		}
		long eLong = Calendar.getInstance().getTimeInMillis();
		logger.warn("update time("+(eLong-bLong) + ") name:"+name+" param:"+paramMapString+"  result:"+reuslt+" :end");
		if((eLong-bLong) > 200){
			logger.warn("sql查询使用时间" + " name:"+name + "  ;" + (eLong-bLong));
		}
		return reuslt;
	}

	
	private Map<String, Object> joinUpdateMap(Map<String, Object> objectMap, Map<String, Object> paramMap){
		//防止更新数据名字与参数重名，导致Ibatis SqlMap执行出错
		for (String key : objectMap.keySet()) {
			if(paramMap.containsKey(key)){
				throw new RuntimeException("SQL:Duplication Key For Update");
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.putAll(objectMap);
		map.putAll(paramMap);
		
		return map;
	}
	
	private Map<String, Object> asParamMap(Param... ps){
		Map<String, Object> map = new HashMap<String, Object>();
		for (Param param : ps) {
			map.put(param.key, param.value);
		}
		return map;
	}

	private <T extends AbstractDTO> T toDTO(Class<T> clz, Map<String, Object> map) throws InstantiationException,
			IllegalAccessException {

		T t = clz.newInstance();
		t.fromDbMap(map);
		return t;
	}

	private <T extends AbstractDTO> List<T> toDTOList(Class<T> clz, List<Map<String, Object>> maps)
			throws InstantiationException, IllegalAccessException {

		List<T> list = new ArrayList<T>();
		for (Map<String, Object> map : maps) {

			list.add(toDTO(clz, map));
		}

		return list;
	} 
}
