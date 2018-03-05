package com.zw.common;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

import org.apache.log4j.Logger;

public class LogUtil {
	private static final String METHOD_PREFIX="get";
	/**
	 * ��־����
	 */
	private static Logger comm=Logger.getLogger("comm");
	private static Logger api=Logger.getLogger("api");
	private static Logger debug=Logger.getLogger("debug");
	private static Logger sql=Logger.getLogger("sql");
	public static void commDebug(String arg) {
		comm.debug(arg);
	}
	public static void commInfo(String arg) {
		comm.info(arg);
	}
	public static void commError(String arg) {
		comm.error(arg);
	}
	public static void commError(String arg,Exception e) {
		comm.error(arg+"\r\n"+getExpMessage(e));
	}
	public static void apiDebug(String arg) {
		api.debug(arg);
	}
	public static void apiInfo(String arg) {
		api.info(arg);
	}
	public static String getExpMessage(Exception e) {
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		e.printStackTrace(new PrintWriter(buf, true));
		return buf.toString();
	}
}