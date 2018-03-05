package com.zw.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;

@Controller
@RequestMapping("/testController")
public class TestController {
	// 编码
	public static String CHARSET = "UTF-8";
	// 返回格式
	public static String FORMAT = "json";
	// 日志记录目录
	public static String log_path = "/log";
	// RSA2
	public static String signtype = "RSA2";
	public static String notify_url = "http://www.baidu.com/alipay.trade.wap.pay-JAVA-UTF-8/notify_url.jsp";
	// 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
	public static String return_url = "http://www.baidu.com/alipay.trade.wap.pay-JAVA-UTF-8/return_url.jsp";
	// 请求网关地址
	public static String pay_url = "https://openapi.alipaydev.com/gateway.do";
	public static String app_id = "2016091200496081";
	public static String pub_key="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA3wZoXjOqMbzIT77iNaFNYSUTQEcfJ+UzrkJ9TB98/yQZSkbVQ3GNj4B932cROyTmhuNBE7tYb+sHXrES8z9WwMQo1vx45c1M6x4ovPGthAeRXqYI2ODaYfrdqe1yFN2W1nUBb9mlOQC3zV6K1dy2YJPcAl+nHAPJWJzDhaDQGeoqn7prVr+EZy0jfc2lBxmEYyPKZ80POk/orNUoJFDoxz7MeJRQZl/UrUCus9OdXP1ozStHrxowtAaoWYZBkfWRW8kDR1mzlghgveSyHKdM9fPrdS2hyUGTP6jj/bHi7g6XMR3ErRDbINrB21r8MnDfYW+Bn4g135YbluW/v+KdNwIDAQAB";
	public static String pri_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDfBmheM6oxvMhPvuI1oU1hJRNARx8n5TOuQn1MH3z/JBlKRtVDcY2PgH3fZxE7JOaG40ETu1hv6wdesRLzP1bAxCjW/HjlzUzrHii88a2EB5FepgjY4Nph+t2p7XIU3ZbWdQFv2aU5ALfNXorV3LZgk9wCX6ccA8lYnMOFoNAZ6iqfumtWv4RnLSN9zaUHGYRjI8pnzQ86T+is1SgkUOjHPsx4lFBmX9StQK6z051c/WjNK0evGjC0BqhZhkGR9ZFbyQNHWbOWCGC95LIcp0z18+t1LaHJQZM/qOP9seLuDpcxHcStENsg2sHbWvwycN9hb4GfiDXflhuW5b+/4p03AgMBAAECggEAKy5hbMPSExk7K32I7NtTURjNlmdu19mf3QCQo/mAySGjJ+d5CTrmLr+xH10kT/BAFiPQCNMAN4MpBSOByMkdsesJAT8W5V+PogJP3u1oLCUO92VsVxmKDuh0nJ8VtIaG2vUClzZrsBK8g63ncarvQNnAAlGk2F6/UEHh9XCCelGo50f9Y4bZ1HcKHXjD7Zl83il9mnOFg1jpF+gt/A/GRjN7t2pXAoPapBtWkQ6YR+VZ71Gb4KzB37FXfj4BKzMoobzngKhdKOBoUDF075t9wOGzc9RqlMWlVnW1HO6205PdEMbgWM/6hUf0EDUUxXPaEaXfp6hFT2K2S3ES29pj0QKBgQD2Lq55o9ysimwPxYfRM2BSn1XqhtRdo3v8Witn6pyV2DE1g9Shc2j3LXnJLRXev+deIefrHwIa5+tGYifqZkOStyfyvgQ7tFhJTXxcJZLMs7r6jZ03AIvUNUTkQUTMzGucOvfBSTYIiyvwrA9ejkoJqFp6HuOG9USINK/w9MnvWQKBgQDn608c+9qlHa4KVAFyS0Bq7XVPq100v8trq8AL4Go27nPmBhPyEvbsL/HvtOscek/YqXTjGpo+LbBZzcckH7mVeR8DEYR6yCzerMFn1sBNu6qewvgrKyi9xHab4JVHooU/6BZ0sunydLUa0fFk0VnoRhsyXROLmIyS4EvVMLZvDwKBgQC0Okq7fM4ydwhTp44eBtnOwx2YG+tfPi+n+K9sXWMThHnKLuA87ov64Y0WTe6D68RLstDh3gqhfgZNudB1FzcDMxmI6bmZqBc2WwbfQxaszCvoAEMHoFu+GN19qZbfxAxIDReSprFrIdAiWyTJWaw0U88hNnnmNKteeAwSgHNRyQKBgEROE6DvtHfJBAdasopdC33yrghm6i6tBlaR4SAeWmoPVOc8hwdW+o6ZmGIHIO/kPjwLvLdekGsbvcLVTn+fMGOLpsyajjAzYLO8ocHZiA3c5eWYessMDRXHFT/Xp1/Px/sIegSo5SpZoTVi+/u7hQVaEIyGyUjmBxEDKpKBpFmVAoGAGB8H5rBaIPQBwm0J0tPNIORgDfLfnBk43kzELUIzRnrtRp3lmH1Zlxs4sOEIUmJVYvEL0cdu6GUN65tCS5w7wLEbiE+FWN12gkhEnK1K12EcLCKla6R+PbU048e2WPk6UYAhPV6nEVPX1By7hlLRPGeR196genv3iE16BVk2LZY=";

	@RequestMapping(value="/getView")
	public String view() {
		return "test/test";
	}
	/**
	 * 电脑网站支付或者扫码支付
	 */
	@RequestMapping(value = "/pcPay")
	public void aliPcPay(HttpServletResponse response){
	    AlipayClient client = new DefaultAlipayClient(pay_url,app_id,pri_key,FORMAT,CHARSET,pub_key,signtype);
	    AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
	    // 封装请求支付信息
	    String out_trade_no = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	    System.out.println(out_trade_no);
	    AlipayTradeWapPayModel model=new AlipayTradeWapPayModel();
	    model.setOutTradeNo(out_trade_no);
	    model.setSubject("GTR 2015款3.8T Nismo");
	    model.setTotalAmount("2350000");
	    model.setBody("汽车消费2350000元");
	    model.setTimeoutExpress("10m");
	    model.setProductCode("FAST_INSTANT_TRADE_PAY");
	    request.setBizModel(model);
	    request.setReturnUrl(return_url);
	    request.setNotifyUrl(notify_url);
	    try {
	        String result = client.pageExecute(request).getBody();
//	        logger.info("返回结果为:"+result);
	        response.setContentType("text/html;charset=" + CHARSET);
	        response.getWriter().write(result);//直接将完整的表单html输出到页面
	        response.getWriter().flush();
	        response.getWriter().close();
	    } catch (AlipayApiException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	/**
	 * wap支付
	 * @param response
	 */
	@RequestMapping(value="/wapPay")
	public void wapPay(HttpServletResponse response) {
		AlipayClient client = new DefaultAlipayClient(pay_url,app_id,pri_key,FORMAT,CHARSET,pub_key,signtype);
		AlipayTradeWapPayRequest alipay_request=new AlipayTradeWapPayRequest();
		// 封装请求支付信息
		String out_trade_no=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		AlipayTradeWapPayModel model=new AlipayTradeWapPayModel();
	    model.setOutTradeNo(out_trade_no);
	    model.setSubject("123");
	    model.setTotalAmount("0.01");
	    model.setBody("描述");
	    model.setTimeoutExpress("10m");
	    model.setProductCode("QUICK_WAP_PAY");//**
	    alipay_request.setBizModel(model);
	    // 设置异步通知地址
	    alipay_request.setNotifyUrl(notify_url);
	    // 设置同步地址
	    alipay_request.setReturnUrl(return_url);
	 // form表单生产
	    String form = "";
		try {
			// 调用SDK生成表单
			form = client.pageExecute(alipay_request).getBody();
			response.setContentType("text/html;charset=" + CHARSET); 
		    response.getWriter().write(form);//直接将完整的表单html输出到页面 
		    response.getWriter().flush(); 
		    response.getWriter().close();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
