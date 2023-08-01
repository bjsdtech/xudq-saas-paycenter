package com.xudq.base.common;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.ByteArrayBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description
 * @date
 */

public class HttpUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	
	private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

	public static HttpReturn get(String url) {
		return get(url, DEFAULT_CHARSET, 5000, 5000);
	}

	public static HttpReturn get(String url, Charset charset, int connectTimeout, int socketTimeout) {
		if (charset == null) {
			charset = DEFAULT_CHARSET;
		}

		// 创建客户端
		CloseableHttpClient client = HttpClients.createDefault();
		int status = -1;
		String text = "";
		StopWatch watch = new StopWatch();
		watch.start();
		try {
			// 配置
			RequestConfig config = RequestConfig.copy(RequestConfig.DEFAULT).setExpectContinueEnabled(true)
					.setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout).build();
			HttpGet get = new HttpGet(url);
			get.setConfig(config);

			// 发送请求
			HttpResponse response = client.execute(get);
			status = response.getStatusLine().getStatusCode();
			// 读取响应内容
			text = readContent(charset, response);

			HttpReturn httpReturn = new HttpReturn(status, text);
			return httpReturn;
		} catch (Exception e) {
			logger.error(url, e);
		}finally {
			try{
				watch.stop();
				// 记录访问信息和执行时间
				logger.info(url + " " + status + " " + text + " " + watch.getLastTaskTimeMillis() + "ms");
				client.close();
			}catch (Exception e){
				logger.error("关闭http请求异常",e);
			}

		}
		return null;
	}

	public static HttpReturn get(String url, Map<String, String> params) {
		return get(url, params, DEFAULT_CHARSET,5000,5000);
	}

	public static HttpReturn get(String url, Map<String, String> params, Charset charset,int connectTimeout,int socketTimeout) {
		if (charset == null) {
			charset = DEFAULT_CHARSET;
		}
		int status = -1;
		String text = "";
		StopWatch watch = new StopWatch();
		watch.start();
		// 创建客户端
		CloseableHttpClient client = HttpClients.createDefault();
		try {
			if (params != null) {
				Set<Entry<String, String>> entrySet = params.entrySet();
				if (entrySet != null && entrySet.size() > 0) {
					Iterator<Entry<String, String>> iterator = entrySet.iterator();
					StringBuffer append = new StringBuffer(url);

					if(!url.contains("?")){
						append.append("?");
					}else{
						append.append("&");
					}

					while (iterator.hasNext()) {
						Entry<String, String> next = iterator.next();
						String key = URLEncoder.encode(next.getKey(),DEFAULT_CHARSET.name());
						String value = URLEncoder.encode(next.getValue(),DEFAULT_CHARSET.name());
						append.append(key).append("=").append(value).append("&");
					}
					url = append.toString();
					if (url.endsWith("?") || url.endsWith("&")) {
						url = url.substring(0, url.length() - 1);
					}
				}
			}

			// 配置
			RequestConfig config = RequestConfig.copy(RequestConfig.DEFAULT).setExpectContinueEnabled(true)
					.setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout).build();
			HttpGet get = new HttpGet(url);
			get.setConfig(config);

			// 发送请求
			HttpResponse response = client.execute(get);
			status = response.getStatusLine().getStatusCode();
			// 读取响应内容
			text = readContent(charset, response);

			get.releaseConnection();
			HttpReturn httpReturn = new HttpReturn(status, text);
			return httpReturn;
		} catch (Exception e) {
			logger.error(url, e);
		}finally {
			try{
				watch.stop();
			// 记录访问信息和执行时间
				logger.info(url + " " + JSON.toJSONString(params) + " " + status + " " + text + " " + watch.getLastTaskTimeMillis() + "ms");
				client.close();
			}catch (Exception e){
				logger.error("关闭http请求异常",e);
			}
		}
		return null;
	}

	public static HttpReturn delete(String url) {
		return delete(url, DEFAULT_CHARSET,5000,5000);
	}

	public static HttpReturn delete(String url, Charset charset,int connectTimeout,int socketTimeout) {
		if (charset == null) {
			charset = DEFAULT_CHARSET;
		}
		int status = -1;
		String text = "";
		StopWatch watch = new StopWatch();
		watch.start();
		CloseableHttpClient client = HttpClients.createDefault();
		try {

			RequestConfig config = RequestConfig.copy(RequestConfig.DEFAULT).setExpectContinueEnabled(true)
					.setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout).build();
			HttpDelete delete = new HttpDelete(url);
			delete.setConfig(config);

			HttpResponse response = client.execute(delete);
			status = response.getStatusLine().getStatusCode();
			text = readContent(charset, response);

			HttpReturn httpReturn = new HttpReturn(status, text);
			return httpReturn;
		} catch (Exception e) {
			logger.error(url, e);
		}finally {
			try{
				watch.stop();
				logger.info(url, status + "", text, "" + watch.getLastTaskTimeMillis() + "ms");
				client.close();
			}catch (Exception e){
				logger.error("关闭http请求异常",e);
			}
		}
		return null;
	}

	public static HttpReturn delete(String url, Map<String, String> params) {
		return delete(url, params, DEFAULT_CHARSET,5000,5000);
	}

	public static HttpReturn delete(String url, Map<String, String> params, Charset charset,int connectTimeout,int socketTimeout) {
		if (charset == null) {
			charset = DEFAULT_CHARSET;
		}
		int status = -1;
		String text = "";
		StopWatch watch = new StopWatch();
		watch.start();
		CloseableHttpClient client = HttpClients.createDefault();
		try {
			if (params != null) {
				Set<Entry<String, String>> entrySet = params.entrySet();
				if (entrySet != null && entrySet.size() > 0) {
					Iterator<Entry<String, String>> iterator = entrySet.iterator();
					StringBuffer append = new StringBuffer(url).append("?");
					while (iterator.hasNext()) {
						Entry<String, String> next = iterator.next();
						String key = next.getKey();
						String value = next.getValue();
						append.append(key).append("=").append(value).append("&");
					}
					url = append.toString();
					if (url.endsWith("?") || url.endsWith("&")) {
						url = url.substring(0, url.length() - 1);
					}
				}
			}

			RequestConfig config = RequestConfig.copy(RequestConfig.DEFAULT).setExpectContinueEnabled(true)
					.setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout).build();
			HttpDelete delete = new HttpDelete(url);
			delete.setConfig(config);

			HttpResponse response = client.execute(delete);
			status = response.getStatusLine().getStatusCode();
			text = readContent(charset, response);

			HttpReturn httpReturn = new HttpReturn(status, text);
			return httpReturn;
		} catch (Exception e) {
			logger.error(url, e);
		}finally {
			try{
				watch.stop();
				logger.info(url +  " " + JSON.toJSONString(params) + " " +status + " " + text + " " + watch.getLastTaskTimeMillis() + "ms");
				client.close();
			}catch (Exception e){
				logger.error("关闭http请求异常",e);
			}
		}
		return null;
	}


	public static HttpReturn postJSON(String url, String param) {
		return postJSON(url, param, DEFAULT_CHARSET,5000,5000);
	}

	public static HttpReturn postJSON(String url, String param, Charset charset,int connectTimeout,int socketTimeout) {
		if (charset == null) {
			charset = DEFAULT_CHARSET;
		}
		int status = -1;
		String text = "";
		StopWatch watch = new StopWatch();
		watch.start();
		CloseableHttpClient client = HttpClients.createDefault();
		try {

			RequestConfig config = RequestConfig.copy(RequestConfig.DEFAULT).setExpectContinueEnabled(true)
					.setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout).build();
			HttpPost post = new HttpPost(url);
			post.setConfig(config);
			post.setHeader("Content-Type", "application/json;charset=utf-8");
			HttpEntity entity = new StringEntity(param, charset);
			post.setEntity(entity);

			HttpResponse response = client.execute(post);
			status = response.getStatusLine().getStatusCode();
			text = readContent(charset, response);

			HttpReturn httpReturn = new HttpReturn(status, text);
			return httpReturn;
		} catch (Exception e) {
			logger.error(url + " :: " + param, e);
		}finally {
			try{
				watch.stop();
				logger.info(url +  " " + param + " " + status + " " + text + " " + watch.getLastTaskTimeMillis() + "ms");
				client.close();
			}catch (Exception e){
				logger.error("关闭http请求异常",e);
			}
		}
		return null;
	}

	public static HttpReturn postPlain(String url, String param) {
		return postPlain(url, param, DEFAULT_CHARSET,5000,5000);
	}

	public static HttpReturn postPlain(String url, String param, Charset charset,int connectTimeout,int socketTimeout) {
		if (charset == null) {
			charset = DEFAULT_CHARSET;
		}
		int status = -1;
		String text = "";
		StopWatch watch = new StopWatch();
		watch.start();
		CloseableHttpClient client = HttpClients.createDefault();
		try {

			RequestConfig config = RequestConfig.copy(RequestConfig.DEFAULT).setExpectContinueEnabled(true)
					.setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout).build();
			HttpPost post = new HttpPost(url);
			post.setConfig(config);
			HttpEntity entity = new StringEntity(param, charset);
			post.setEntity(entity);

			HttpResponse response = client.execute(post);
			status = response.getStatusLine().getStatusCode();
			text = readContent(charset, response);

			HttpReturn httpReturn = new HttpReturn(status, text);
			return httpReturn;
		} catch (Exception e) {
			logger.error(url + " :: " + param, e);
		}finally {
			try{
				watch.stop();
				logger.info(url + " " + param + " "+ status + " " + text + " " + watch.getLastTaskTimeMillis() + "ms");
				client.close();
			}catch (Exception e){
				logger.error("关闭http请求异常",e);
			}
		}
		return null;
	}

	public static HttpReturn putJSON(String url, String param) {
		return putJSON(url, param, DEFAULT_CHARSET,5000,5000);
	}

	public static HttpReturn putJSON(String url, String param, Charset charset,int connectTimeout,int socketTimeout) {
		if (charset == null) {
			charset = DEFAULT_CHARSET;
		}
		int status = -1;
		String text = "";
		StopWatch watch = new StopWatch();
		watch.start();
		CloseableHttpClient client = HttpClients.createDefault();
		try {

			RequestConfig config = RequestConfig.copy(RequestConfig.DEFAULT).setExpectContinueEnabled(true)
					.setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout).build();
			HttpPut put = new HttpPut(url);
			put.setHeader("Content-Type", "application/json;charset=utf-8");
			put.setConfig(config);
			HttpEntity entity = new StringEntity(param, charset);
			put.setEntity(entity);

			HttpResponse response = client.execute(put);
			status = response.getStatusLine().getStatusCode();
			text = readContent(charset, response);

			HttpReturn httpReturn = new HttpReturn(status, text);
			return httpReturn;
		} catch (Exception e) {
			logger.error(url + " :: " + param, e);
		}finally {
			try{
				watch.stop();
				logger.info(url + " " + param  + " " +  status + " " + text + " " + watch.getLastTaskTimeMillis() + "ms");
				client.close();
			}catch (Exception e){
				logger.error("关闭http请求异常",e);
			}
		}
		return null;
	}

	public static HttpReturn putPlain(String url, String param) {
		return putPlain(url, param, DEFAULT_CHARSET,5000,5000);
	}

	public static HttpReturn putPlain(String url, String param, Charset charset,int connectTimeout,int socketTimeout) {
		if (charset == null) {
			charset = DEFAULT_CHARSET;
		}
		int status = -1;
		String text = "";
		StopWatch watch = new StopWatch();
		watch.start();
		CloseableHttpClient client = HttpClients.createDefault();
		try {

			RequestConfig config = RequestConfig.copy(RequestConfig.DEFAULT).setExpectContinueEnabled(true)
					.setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout).build();
			HttpPut put = new HttpPut(url);
			put.setConfig(config);
			HttpEntity entity = new StringEntity(param, charset);
			put.setEntity(entity);

			HttpResponse response = client.execute(put);
			status = response.getStatusLine().getStatusCode();
			text = readContent(charset, response);

			HttpReturn httpReturn = new HttpReturn(status, text);
			return httpReturn;
		} catch (Exception e) {
			logger.error(url + " :: " + param, e);
		}finally {
			try{
				watch.stop();
				logger.info(url + " " + param  + " " + status + " " + text + " " + watch.getLastTaskTimeMillis() + "ms");
				client.close();
			}catch (Exception e){
				logger.error("关闭http请求异常",e);
			}
		}
		return null;
	}

	public static HttpReturn postForm(String url, Map<String, String> param) {
		return postForm(url, param, DEFAULT_CHARSET,5000,5000);
	}

	public static HttpReturn postForm(String url, Map<String, String> param, Charset charset,int connectTimeout,int socketTimeout) {
		if (charset == null) {
			charset = DEFAULT_CHARSET;
		}
		int status = -1;
		String text = "";
		StopWatch watch = new StopWatch();
		watch.start();
		CloseableHttpClient client = HttpClients.createDefault();
		try {

			RequestConfig config = RequestConfig.copy(RequestConfig.DEFAULT).setExpectContinueEnabled(true)
					.setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout).build();
			HttpPost post = new HttpPost(url);
			post.setConfig(config);
			List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
			Set<Entry<String, String>> entrySet = param.entrySet();
			for (Entry<String, String> entry : entrySet) {
				parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			HttpEntity entity = new UrlEncodedFormEntity(parameters, charset);
			post.setEntity(entity);

			HttpResponse response = client.execute(post);
			status = response.getStatusLine().getStatusCode();
			text = readContent(charset, response);

			HttpReturn httpReturn = new HttpReturn(status, text);
			return httpReturn;
		} catch (Exception e) {
			logger.error(url, e);
		}finally {
			try{
				watch.stop();
				logger.info(url + " " + JSON.toJSONString(param)  + " " + status + " " + text + " " + watch.getLastTaskTimeMillis() + "ms");
				client.close();
			}catch (Exception e){
				logger.error("关闭http请求异常",e);
			}
		}
		return null;
	}

	// RequestConfig.setCookieSpec(CookieSpecs.IGNORE_COOKIES)
	public static HttpReturn putForm(String url, Map<String, String> param) {
		return putForm(url, param, DEFAULT_CHARSET,5000,5000);
	}

	public static HttpReturn putForm(String url, Map<String, String> param, Charset charset,int connectTimeout,int socketTimeout) {
		if (charset == null) {
			charset = DEFAULT_CHARSET;
		}
		int status = -1;
		String text = "";
		StopWatch watch = new StopWatch();
		watch.start();
		CloseableHttpClient client = HttpClients.createDefault();
		try {

			RequestConfig config = RequestConfig.copy(RequestConfig.DEFAULT).setExpectContinueEnabled(true)
					.setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout).build();
			HttpPut put = new HttpPut(url);
			put.setConfig(config);
			List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
			Set<Entry<String, String>> entrySet = param.entrySet();
			for (Entry<String, String> entry : entrySet) {
				parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			HttpEntity entity = new UrlEncodedFormEntity(parameters, charset);
			put.setEntity(entity);

			HttpResponse response = client.execute(put);
			status = response.getStatusLine().getStatusCode();
			text = readContent(charset, response);

			HttpReturn httpReturn = new HttpReturn(status, text);
			return httpReturn;
		} catch (Exception e) {
			logger.error(url, e);
		}finally {
			try{
				watch.stop();
				logger.info(url + " " + JSON.toJSONString(param) + " " + status + " " +  text + " " + watch.getLastTaskTimeMillis() + "ms");
				client.close();
			}catch (Exception e){
				logger.error("关闭http请求异常",e);
			}
		}
		return null;
	}

	private static String readContent(Charset charset, HttpResponse response) throws IOException {
		InputStream is = response.getEntity().getContent();
		BufferedInputStream bis = new BufferedInputStream(is);
		ByteArrayBuffer baf = new ByteArrayBuffer(20);
		int current = 0;
		while ((current = bis.read()) != -1) {
			baf.append((byte) current);
		}
		String text = new String(baf.toByteArray(), charset);
		bis.close();
		is.close();
		return text;
	}


	public  static  void  main(String[] args){
		String url = "http://123213.123123.123123.123";
		get(url);
	}

}