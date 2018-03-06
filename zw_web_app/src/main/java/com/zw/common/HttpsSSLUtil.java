package com.zw.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
/**
 * Https���󹤾���
 * @author zhengwei
 *
 */
public class HttpsSSLUtil {

	// public static byte[] bmpToByteArray(final Bitmap bmp, final boolean
	// needRecycle) {
	// ByteArrayOutputStream output = new ByteArrayOutputStream();
	// bmp.compress(CompressFormat.PNG, 100, output);
	// if (needRecycle) {
	// bmp.recycle();
	// }
	//
	// byte[] result = output.toByteArray();
	// try {
	// output.close();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// return result;
	// }

	public static byte[] httpGet(final String url) {
		if (url == null || url.length() == 0) {
			System.out.println("httpGet, url is null");
			return null;
		}

		HttpClient httpClient = getNewHttpClient();
		HttpGet httpGet = new HttpGet(url);

		try {
			HttpResponse resp = httpClient.execute(httpGet);
			if (resp.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				System.out.println("httpGet fail, status code = "
						+ resp.getStatusLine().getStatusCode());
				return null;
			}

			return EntityUtils.toByteArray(resp.getEntity());

		} catch (Exception e) {
			System.out.println("httpGet exception, e = " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public static byte[] httpPost(String url, String entity) {
		if (url == null || url.length() == 0) {
			System.out.println("httpPost, url is null");
			return null;
		}
		// HttpClient httpClient = getNewHttpClient();
		// httpClient.getParams().setParameter(
		// CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);// ����ʱ��
		// httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
		// 10000);// ���ݴ���ʱ��
		CloseableHttpClient httpClient = HttpClients.createDefault();
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(10000).setConnectTimeout(10000).build();
		HttpPost httpPost = new HttpPost(url);

		try {
			httpPost.setConfig(requestConfig);
			httpPost.setEntity(new StringEntity(entity, "utf-8"));
			// httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");
			HttpResponse resp = httpClient.execute(httpPost);
			if (resp.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				System.out.println("httpPost fail, status code = "
						+ resp.getStatusLine().getStatusCode());
				return null;
			}

			return EntityUtils.toByteArray(resp.getEntity());
		} catch (Exception e) {
			System.out.println("httpPost exception, e = " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public static byte[] httpsPost(String url, String entity) {
		if (url == null || url.length() == 0) {
			System.out.println("httpPost, url is null");
			return null;
		}
		HttpClient httpClient = getNewHttpClient();
		// httpClient.getParams().setParameter(
		// CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);// ����ʱ��
		// httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
		// 10000);// ���ݴ���ʱ��
		// CloseableHttpClient httpClient = HttpClients.createDefault();
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(10000).setConnectTimeout(10000).build();
		HttpPost httpPost = new HttpPost(url);

		try {
			httpPost.setConfig(requestConfig);
			httpPost.setEntity(new StringEntity(entity, "utf-8"));
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");
			HttpResponse resp = httpClient.execute(httpPost);
			if (resp.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				System.out.println("httpGet fail, status code = "
						+ resp.getStatusLine().getStatusCode());
				return null;
			}

			return EntityUtils.toByteArray(resp.getEntity());
		} catch (Exception e) {
			System.out.println("httpPost exception, e = " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	private static class SSLSocketFactoryEx extends SSLSocketFactory {

		SSLContext sslContext = SSLContext.getInstance("TLS");

		public SSLSocketFactoryEx(KeyStore truststore)
				throws NoSuchAlgorithmException, KeyManagementException,
				KeyStoreException, UnrecoverableKeyException {
			super(truststore);

			TrustManager tm = new X509TrustManager() {

				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				@Override
				public void checkClientTrusted(X509Certificate[] chain,
						String authType)
						throws java.security.cert.CertificateException {
				}

				@Override
				public void checkServerTrusted(X509Certificate[] chain,
						String authType)
						throws java.security.cert.CertificateException {
				}
			};

			sslContext.init(null, new TrustManager[] { tm }, null);
		}

		@Override
		public Socket createSocket(Socket socket, String host, int port,
				boolean autoClose) throws IOException, UnknownHostException {
			return sslContext.getSocketFactory().createSocket(socket, host,
					port, autoClose);
		}

		@Override
		public Socket createSocket() throws IOException {
			return sslContext.getSocketFactory().createSocket();
		}
	}

	@SuppressWarnings("deprecation")
	private static HttpClient getNewHttpClient() {
		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore
					.getDefaultType());
			trustStore.load(null, null);

			SSLSocketFactory sf = new SSLSocketFactoryEx(trustStore);
			sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

			HttpParams params = new BasicHttpParams();
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

			SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("http", PlainSocketFactory
					.getSocketFactory(), 80));
			registry.register(new Scheme("https", sf, 443));

			ClientConnectionManager ccm = new ThreadSafeClientConnManager(
					params, registry);

			return new DefaultHttpClient(ccm, params);
		} catch (Exception e) {
			return new DefaultHttpClient();
		}
	}

	public static byte[] readFromFile(String fileName, int offset, int len) {
		if (fileName == null) {
			return null;
		}

		File file = new File(fileName);
		if (!file.exists()) {
			System.out.println("readFromFile: file not found");
			return null;
		}

		if (len == -1) {
			len = (int) file.length();
		}

		System.out.println("readFromFile : offset = " + offset + " len = " + len
				+ " offset + len = " + (offset + len));

		if (offset < 0) {
			System.out.println("readFromFile invalid offset:" + offset);
			return null;
		}
		if (len <= 0) {
			System.out.println("readFromFile invalid len:" + len);
			return null;
		}
		if (offset + len > (int) file.length()) {
			System.out.println("readFromFile invalid file len:" + file.length());
			return null;
		}

		byte[] b = null;
		try {
			RandomAccessFile in = new RandomAccessFile(fileName, "r");
			b = new byte[len];
			in.seek(offset);
			in.readFully(b);
			in.close();

		} catch (Exception e) {
			System.out.println("readFromFile : errMsg = " + e.getMessage());
			e.printStackTrace();
		}
		return b;
	}

	public static String sha1(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}

		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };

		try {
			MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
			mdTemp.update(str.getBytes());

			byte[] md = mdTemp.digest();
			int j = md.length;
			char buf[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
				buf[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(buf);
		} catch (Exception e) {
			return null;
		}
	}

	public static List<String> stringsToList(final String[] src) {
		if (src == null || src.length == 0) {
			return null;
		}
		final List<String> result = new ArrayList<String>();
		for (int i = 0; i < src.length; i++) {
			result.add(src[i]);
		}
		return result;
	}

	public static byte[] httpPostSSL(String url, String entity, String corpId,
			String keyPath) {
		if (url == null || url.length() == 0) {
			System.out.println("httpPost, url is null");
			return null;
		}
		HttpClient httpClient = getNewHttpClientSSL(corpId, keyPath);
		httpClient.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);// ����ʱ��
		httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
				10000);// ���ݴ���ʱ��
		HttpPost httpPost = new HttpPost(url);

		try {
			httpPost.setEntity(new StringEntity(entity, "utf-8"));
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");

			HttpResponse resp = httpClient.execute(httpPost);
			if (resp.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				System.out.println("httpGet fail, status code = "
						+ resp.getStatusLine().getStatusCode());
				return null;
			}

			return EntityUtils.toByteArray(resp.getEntity());
		} catch (Exception e) {
			System.out.println("httpPost exception, e = " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * ֤����֤
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private static HttpClient getNewHttpClientSSL(String corpId, String keyPath) {
		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore
					.getDefaultType());
			FileInputStream instream = new FileInputStream(new File(keyPath));
			// trustStore.load(instream, corpId.toCharArray());
			try {
				// ָ��PKCS12������(�̻�ID)
				trustStore.load(instream, corpId.toCharArray());
			} finally {
				instream.close();
			}
			SSLSocketFactory sf = new SSLSocketFactoryEx(trustStore);
			sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

			HttpParams params = new BasicHttpParams();
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

			SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("http", PlainSocketFactory
					.getSocketFactory(), 80));
			registry.register(new Scheme("https", sf, 443));

			ClientConnectionManager ccm = new ThreadSafeClientConnManager(
					params, registry);

			return new DefaultHttpClient(ccm, params);
		} catch (Exception e) {
			return new DefaultHttpClient();
		}
	}
}
