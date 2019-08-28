package com.jitv.tv.dao.impl;

import java.util.List;
import java.util.Map;
import com.jitv.tv.dao.DictionariesDao;
import com.jitv.tv.dao.base.AbstractDAO;
import com.jitv.tv.dto.DictionariesDTO;

public class DictionariesDaoImpl extends AbstractDAO implements DictionariesDao {

	public DictionariesDaoImpl() {
		super("dictionaries_tbl");
	}

	@Override
	public int add(DictionariesDTO dto) {
		try {
			insert("add", dto);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public int delete(String id) {
		try {
			delete("delete", newParam("id", Long.parseLong(id)));
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public int update(Map<String, Object> map) {
		try {
			update("update", map);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public List<DictionariesDTO> selectList(String id, int type, String key, String value, String parent) {
		return queryForList("getList", DictionariesDTO.class, newParam("type", type), newParam("typeKey", key),
				newParam("typeValue", value), newParam("parent", parent));
	}

	@Override
	public int getSum(String id, int type, String key, String value, String parent) {
		return (int) function("getSum", newParam("type", type), newParam("typeKey", key), newParam("typeValue", value),
				newParam("parent", parent));
	}

	@Override
	public DictionariesDTO selById(String id) {
		return queryForObject("getById", DictionariesDTO.class, newParam("id", Long.parseLong(id)));
	}

	@Override
	public List<DictionariesDTO> selectList(List<String> ids) {
		return queryForList("getByIds", DictionariesDTO.class, newParam("ids", ids));
	}

	@Override
	public List<DictionariesDTO> selectList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DictionariesDTO selBytypeKey(String typeKey) {
		return queryForObject("selBytypeKey", DictionariesDTO.class, newParam("typeKey", typeKey));
	}

	@Override
	public DictionariesDTO selBytypeValue(String typevalue) {
		return queryForObject("selBytypeValue", DictionariesDTO.class, newParam("typeValue", typevalue));
	}

}
