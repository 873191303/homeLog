package com.jitv.tv.dao.impl;

import java.util.List;
import java.util.Map;

import com.jitv.tv.dao.AlarmDao;
import com.jitv.tv.dao.base.AbstractDAO;
import com.jitv.tv.dto.AlarmDTO;

public class AlarmDaoImpl extends AbstractDAO implements AlarmDao {

	public AlarmDaoImpl() {
		super("alarm_tbl");
	}

	@Override
	public List<AlarmDTO> selectList(String pageIndex, String pageSum, String title, String serviceIp) {
		return queryForList("getList", AlarmDTO.class, newParam("pageIndex", Integer.parseInt(pageIndex)),
				newParam("pageSum", Integer.parseInt(pageSum)), newParam("title", title),
				newParam("serviceip", serviceIp));
	}

	@Override
	public int getSum(String title, String serviceIp) {
		return (int) function("getSum", newParam("title", title), newParam("serviceip", serviceIp));
	}

	@Override
	public int addAlarm(AlarmDTO dto) {
		try {
			insert("add", dto);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public List<AlarmDTO> selectList(String pageIndex, String pageSum, String title, String title2, String servieIP) {
		return queryForList("selectbs", AlarmDTO.class, newParam("pageIndex", Integer.parseInt(pageIndex)),
				newParam("pageSum", Integer.parseInt(pageSum)), newParam("title", title), newParam("title2", title2),
				newParam("serviceip", servieIP));
	}

	@Override
	public int getSum(String title, String title2, String serviceIp) {
		return (int) function("getSumbs", newParam("title", title), newParam("title2", title2),
				newParam("serviceip", serviceIp));
	}

	@Override
	public List<AlarmDTO> selectList(String startIndex, String pageSize, String title1, String title2, String title3,
			String title4, String title5, String title6, String title7, String title8, String searchValue) {// SFTP
		return queryForList("selectSFTP", AlarmDTO.class, newParam("pageIndex", Integer.parseInt(startIndex)),
				newParam("pageSum", Integer.parseInt(pageSize)), newParam("title1", title1), newParam("title2", title2),
				newParam("title3", title3), newParam("title4", title4), newParam("title5", title5),
				newParam("title6", title6), newParam("title7", title7), newParam("title8", title8),
				newParam("serviceip", searchValue));
	}

	@Override
	public int getSum(String title1, String title2, String title3, String title4, String title5, String title6,
			String title7, String title8, String searchValue) {// SFTP
		return (int) function("getSumSFTP", newParam("title1", title1), newParam("title2", title2),
				newParam("title3", title3), newParam("title4", title4), newParam("title5", title5),
				newParam("title6", title6), newParam("title7", title7), newParam("title8", title8),
				newParam("serviceip", searchValue));
	}

	@Override
	public List<AlarmDTO> selectList(String startIndex, String pageSize, String title1, String title2, String title3,
			String title4, String title5, String title6, String title7, String title8, String title9, String title10,
			String title11, String searchValue) {// File
		return queryForList("selectFile", AlarmDTO.class, newParam("pageIndex", Integer.parseInt(startIndex)),
				newParam("pageSum", Integer.parseInt(pageSize)), newParam("title1", title1), newParam("title2", title2),
				newParam("title3", title3), newParam("title4", title4), newParam("title5", title5),
				newParam("title6", title6), newParam("title7", title7), newParam("title8", title8),
				newParam("title9", title9), newParam("title10", title10), newParam("title11", title11),
				newParam("serviceip", searchValue));
	}

	@Override
	public int getSum(String startIndex, String pageSize, String title1, String title2, String title3, String title4,
			String title5, String title6, String title7, String title8, String title9, String title10, String title11,
			String searchValue) {// File
		return (int) function("getSumFile", newParam("title1", title1), newParam("title2", title2),
				newParam("title3", title3), newParam("title4", title4), newParam("title5", title5),
				newParam("title6", title6), newParam("title7", title7), newParam("title8", title8),
				newParam("title9", title9), newParam("title10", title10), newParam("title11", title11),
				newParam("serviceip", searchValue));
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
	public List<AlarmDTO> select(String serviceip, String manufactorid) {
		return queryForList("select", AlarmDTO.class, newParam("serviceip", serviceip),
				newParam("manufactorid", manufactorid));
	}

}
