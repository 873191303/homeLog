package com.jitv.tv.util;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jitv.tv.constant.Constant;

public class StringToDateUtil {
    
	private static final Logger logger = LoggerFactory
			.getLogger(StringToDateUtil.class);

	public static Date toDate(String StrTime) {
		if (StringUtils.isNotEmpty(StrTime)) {
			try {
			    return DateUtils.parseDate(StrTime, Constant.DATEFORMAT);
			} catch (ParseException e) {
				logger.error("parse error", e);
				return null;
			}
		} else {
			return null;
		}
	}

}
