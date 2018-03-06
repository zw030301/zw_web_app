package com.alipay.config;

public class AlipayConfig {
	// 商户appid
	public static String APPID = "2016091200496081";
	// 私钥 pkcs8格式的
	public static String RSA_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDfBmheM6oxvMhPvuI1oU1hJRNARx8n5TOuQn1MH3z/JBlKRtVDcY2PgH3fZxE7JOaG40ETu1hv6wdesRLzP1bAxCjW/HjlzUzrHii88a2EB5FepgjY4Nph+t2p7XIU3ZbWdQFv2aU5ALfNXorV3LZgk9wCX6ccA8lYnMOFoNAZ6iqfumtWv4RnLSN9zaUHGYRjI8pnzQ86T+is1SgkUOjHPsx4lFBmX9StQK6z051c/WjNK0evGjC0BqhZhkGR9ZFbyQNHWbOWCGC95LIcp0z18+t1LaHJQZM/qOP9seLuDpcxHcStENsg2sHbWvwycN9hb4GfiDXflhuW5b+/4p03AgMBAAECggEAKy5hbMPSExk7K32I7NtTURjNlmdu19mf3QCQo/mAySGjJ+d5CTrmLr+xH10kT/BAFiPQCNMAN4MpBSOByMkdsesJAT8W5V+PogJP3u1oLCUO92VsVxmKDuh0nJ8VtIaG2vUClzZrsBK8g63ncarvQNnAAlGk2F6/UEHh9XCCelGo50f9Y4bZ1HcKHXjD7Zl83il9mnOFg1jpF+gt/A/GRjN7t2pXAoPapBtWkQ6YR+VZ71Gb4KzB37FXfj4BKzMoobzngKhdKOBoUDF075t9wOGzc9RqlMWlVnW1HO6205PdEMbgWM/6hUf0EDUUxXPaEaXfp6hFT2K2S3ES29pj0QKBgQD2Lq55o9ysimwPxYfRM2BSn1XqhtRdo3v8Witn6pyV2DE1g9Shc2j3LXnJLRXev+deIefrHwIa5+tGYifqZkOStyfyvgQ7tFhJTXxcJZLMs7r6jZ03AIvUNUTkQUTMzGucOvfBSTYIiyvwrA9ejkoJqFp6HuOG9USINK/w9MnvWQKBgQDn608c+9qlHa4KVAFyS0Bq7XVPq100v8trq8AL4Go27nPmBhPyEvbsL/HvtOscek/YqXTjGpo+LbBZzcckH7mVeR8DEYR6yCzerMFn1sBNu6qewvgrKyi9xHab4JVHooU/6BZ0sunydLUa0fFk0VnoRhsyXROLmIyS4EvVMLZvDwKBgQC0Okq7fM4ydwhTp44eBtnOwx2YG+tfPi+n+K9sXWMThHnKLuA87ov64Y0WTe6D68RLstDh3gqhfgZNudB1FzcDMxmI6bmZqBc2WwbfQxaszCvoAEMHoFu+GN19qZbfxAxIDReSprFrIdAiWyTJWaw0U88hNnnmNKteeAwSgHNRyQKBgEROE6DvtHfJBAdasopdC33yrghm6i6tBlaR4SAeWmoPVOc8hwdW+o6ZmGIHIO/kPjwLvLdekGsbvcLVTn+fMGOLpsyajjAzYLO8ocHZiA3c5eWYessMDRXHFT/Xp1/Px/sIegSo5SpZoTVi+/u7hQVaEIyGyUjmBxEDKpKBpFmVAoGAGB8H5rBaIPQBwm0J0tPNIORgDfLfnBk43kzELUIzRnrtRp3lmH1Zlxs4sOEIUmJVYvEL0cdu6GUN65tCS5w7wLEbiE+FWN12gkhEnK1K12EcLCKla6R+PbU048e2WPk6UYAhPV6nEVPX1By7hlLRPGeR196genv3iE16BVk2LZY=";
	// 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://商户网关地址/alipay.trade.wap.pay-JAVA-UTF-8/notify_url.jsp";
	// 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
	public static String return_url = "http://商户网关地址/alipay.trade.wap.pay-JAVA-UTF-8/return_url.jsp";
	// 请求网关地址
	public static String URL = "https://openapi.alipaydev.com/gateway.do";
	// 编码
	public static String CHARSET = "UTF-8";
	// 返回格式
	public static String FORMAT = "json";
	// 支付宝公钥
	public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAovwyEtYEdijHxaenMLf5HN3/Xol/sTdpRzFSJy+iW1YrUMJBIGXX3/eFWVS8SBHbqdUlQtKrnS3klkX9OXRsGvvQjuop9gv3H3Rww6l/l6Y50LpAZdGxyfWMwma/ouuGy1z1yfBx9e05Bk0YgJPjH/p6dwDH0FfbfWKc0gdvqnzfzhuDTffW+0wRaX5w+uTkbCWGYJYgou6AxePXY2Q/3dmmQ3vmhekEPfi1fh+//ONeVSCJFelMWfzCJ5KiPNQZad6Do3RSVZARkF9BWl/84qhsYKhdn1TV5CnoCmDOp/OvUi3CjY8RdZ/7WPf52v3n/FpDAu5B2x8X9vyB87xFEQIDAQAB";
	// 日志记录目录
	public static String log_path = "/log";
	// RSA2
	public static String SIGNTYPE = "RSA2";
}
