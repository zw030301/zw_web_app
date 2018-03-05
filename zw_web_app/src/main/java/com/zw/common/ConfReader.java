package com.zw.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;


public class ConfReader {
	private static final String FILENAME_ALICONF="config/aliconf.properties";
	
	public static String getString(String key,String fileName) {
		return get(key,fileName);
	}
	/**
	 * 获取键值
	 * @param key
	 * @param fileName
	 * @return
	 */
	public static String get(String key,String fileName) {
		String value=null;
		FileInputStream fis=null;
		Properties props=null;
		try {
			String path=ConfReader.class.getClassLoader().getResource("").toURI().getPath();
			System.out.println(path);
			fis=new FileInputStream(new File(path+fileName));
			props.load(fis);
			value=props.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null!=fis) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				fis=null;
			}
		}
		return value;
	}
	public static String getAliConfString(String key) {
		return get(key,FILENAME_ALICONF);
	}
	public static Integer getAliConfInt(String key) {
		return Integer.valueOf(get(key, FILENAME_ALICONF));
	}
	public static Byte getAliConfByte(String key) {
		return Byte.valueOf(get(key, FILENAME_ALICONF));
	}
}
