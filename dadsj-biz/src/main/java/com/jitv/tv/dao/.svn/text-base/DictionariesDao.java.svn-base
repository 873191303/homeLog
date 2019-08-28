package com.jitv.tv.dao;

import java.util.List;
import java.util.Map;
import com.jitv.tv.dto.DictionariesDTO;

public interface DictionariesDao {

	int add(DictionariesDTO dto);

	int delete(String id);

	int update(Map<String, Object> map);

	List<DictionariesDTO> selectList(String id, int type, String typeKey, String typeValue, String parent);

	int getSum(String id, int type, String typeKey, String typeValue, String parent);

	DictionariesDTO selById(String id);

	DictionariesDTO selBytypeKey(String typeKey);
	DictionariesDTO selBytypeValue(String typevalue);

	List<DictionariesDTO> selectList(List<String> ids);

	List<DictionariesDTO> selectList();

}
