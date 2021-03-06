package com.zw.common;

import java.lang.reflect.Type;
import java.sql.Date;
import java.sql.Timestamp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
/**
 * json������
 * @author zhengwei
 *
 */
public class JsonUtil {
	private static final SerializeConfig SERIALIZE_CONFIG=new SerializeConfig();
	static {
		SERIALIZE_CONFIG.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd"));
		SERIALIZE_CONFIG.put(Timestamp.class, new SimpleDateFormatSerializer("yyyy-MM-dd"));
	}
	public static String getJsonFromObject(Object obj) {
		return JSON.toJSONString(obj,SERIALIZE_CONFIG,SerializerFeature.DisableCircularReferenceDetect);
	}
	public static String toJson(Object obj) {
		return JSON.toJSONString(obj,SERIALIZE_CONFIG,SerializerFeature.DisableCircularReferenceDetect);
	}
	public static <T> T fromJson(String str, Class<T> clazz) {
		return JSON.parseObject(str, clazz);
	}
	public static <T> T fromResourceJson(String str, Class<T> clazz) {
		return JSON.parseObject(str, clazz);
	}
	public static <T> T fromJson(String str, Type type) {
		return JSON.parseObject(str, type);
	}
	public static <T> T fromJson(Object object, Class<T> clazz){
		return fromJson(getJsonFromObject(object), clazz);
	}
	
}
