package com.jitv.tv.util;

import java.io.IOException;

import com.jitv.tv.dto.BehaviorDto;

public class RedisQueue {

	public static byte[] redisKey = "keyLog".getBytes();

	public static void init(BehaviorDto dto) throws IOException {
		RedisUtil.lpush(redisKey, ObjectUtil.object2Bytes(dto));

	}

}
