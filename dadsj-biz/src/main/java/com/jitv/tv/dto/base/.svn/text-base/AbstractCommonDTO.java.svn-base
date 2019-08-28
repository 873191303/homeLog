package com.jitv.tv.dto.base;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCommonDTO extends AbstractDTO {

	private final static String META_ID = "id";

	private String id;

	@Override
	public Map<String, Object> toDbMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (id != null){
			map.put(META_ID, id);
		}

		return map;
	}

	@Override
	public void fromDbMap(Map<String, Object> dbMap) {
		this.id = toString(dbMap, META_ID);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}



}
