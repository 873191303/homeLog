package com.jitv.tv.service;

import java.util.Map;

public interface UserBusinessService {
	Map<String, Object> UserFwjk(String startIndex, String pageSize, String searchValue);

	Map<String, Object> UserFlow(String startIndex, String pageSize, String searchValue);

}
