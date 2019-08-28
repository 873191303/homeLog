package com.jitv.tv.service;

import java.util.List;
import java.util.Map;

public interface DictionariesService {

	int add(int type, String key, String value, String parent, String other, String COL);

	int delete(String id);

	int update(int type, String key, String value, String parent, String other, String COL);

	List<Map<String, Object>> selectList(String id, int type, String key, String value, String parent);

	int getSum(String id, int type, String key, String value, String parent);

	Map<String, Object> selById(String id);
	
	Map<String, Object> selBytypeValue(String typevalue);

	List<Map<String, Object>> selectList(List<String> ids);

	List<Map<String, Object>> selectList();

}
