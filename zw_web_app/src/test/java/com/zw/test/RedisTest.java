package com.zw.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.zw.common.RedisUtil;

public class RedisTest {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext ctx =new FileSystemXmlApplicationContext("classpath:spring/spring-db.xml");
		RedisUtil redisUtil=(RedisUtil) ctx.getBean("redisUtil");
		/*redisUtil.set("name", "zhw");
		redisUtil.set("age", 24);
		redisUtil.set("address", "nj");
		System.out.println(redisUtil.set("address", "js nj", 1000));
		System.out.println(redisUtil.get("age"));
		redisUtil.set("age", 1000);
		System.out.println(redisUtil.get("age"));
		redisUtil.del("address");
		redisUtil.set("class", 15);*/
//		long incr = redisUtil.incr("a", 10000);
//		System.out.println(incr);
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("name", "zw");
		map.put("age", 24);
		map.put("address", "������Ǩ");
		redisUtil.hmset("15751592918", map, 100);
		
//		redisUtil.del("15751592918");
		
		redisUtil.hset("15751592918", "class", "������Ǩ", 1000);
	}
}
