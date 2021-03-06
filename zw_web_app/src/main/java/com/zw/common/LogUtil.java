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
	private static Logger sql=Logger.getLogger("sql");
	public static void commDebug(String message) {
		comm.debug(message);
	}
	public static void commInfo(String message) {
		comm.info(message);
	}
	public static void commError(String message) {
		comm.error(message);
	}
	public static void commError(String message,Exception e) {
		comm.error(message+"\r\n"+getExpMessage(e));
	}
	public static void apiDebug(String message) {
		api.debug(message);
	}
	public static void apiInfo(String message) {
		api.info(message);
	}
	public static void apiError(String message) {
		api.error(message);
	}
	public static void apiError(String message,Exception e) {
		api.error(message+"\r\n"+getExpMessage(e));
	}
	public static void sqlDebug(String message) {
		sql.debug(message);
	}
	public static void sqlInfo(String message) {
		sql.info(message);
	}
	public static void sqlError(String message) {
		sql.error(message);
	}
	public static void sqlError(String message,Exception e) {
		sql.error(message+"\r\n"+getExpMessage(e));
	}
	public static String getExpMessage(Exception e) {
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		e.printStackTrace(new PrintWriter(buf, true));
		return buf.toString();
	}
}
