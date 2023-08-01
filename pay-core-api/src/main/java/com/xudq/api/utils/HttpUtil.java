package com.xudq.api.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.ByteArrayBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.*;
import java.util.Map.Entry;


public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");


    public static HttpReturn get(String url) {
        return get(url, DEFAULT_CHARSET);
    }

    public static HttpReturn get(String url, Charset charset) {
        if (charset == null) {
            charset = DEFAULT_CHARSET;
        }
        int connectTimeout = 0;
        int status = -1;
        try {
            StopWatch watch = new StopWatch();
            watch.start();
            // 配置
            RequestConfig config = RequestConfig.copy(RequestConfig.DEFAULT).setExpectContinueEnabled(true)
                    .setConnectTimeout(connectTimeout).build();
            HttpGet get = new HttpGet(url);
            get.setConfig(config);
            // 创建客户端
            HttpClient client = HttpClients.createDefault();
            // 发送请求
            HttpResponse response = client.execute(get);
            status = response.getStatusLine().getStatusCode();
            // 读取响应内容
            String text = readContent(charset, response);
            watch.stop();
            // 记录访问信息和执行时间
            logger.info(url, status + "" + watch.getLastTaskTimeMillis());
            HttpReturn httpReturn = new HttpReturn(status, text);
            return httpReturn;
        } catch (Exception e) {
            logger.error(url, e);
        }
        return null;
    }

    public static HttpReturn get(String url, Map<String, String> params) {
        return get(url, params, DEFAULT_CHARSET);
    }

    public static HttpReturn get(String url, Map<String, String> params, Charset charset) {
        if (charset == null) {
            charset = DEFAULT_CHARSET;
        }
        int connectTimeout = 0;
        int status = -1;

        try {
            if (params != null) {
                Set<Entry<String, String>> entrySet = params.entrySet();
                if (entrySet != null && entrySet.size() > 0) {
                    Iterator<Entry<String, String>> iterator = entrySet.iterator();
                    StringBuffer append = url.contains("?") ? new StringBuffer(url).append("&") : new StringBuffer(url).append("?");
                    while (iterator.hasNext()) {
                        Entry<String, String> next = iterator.next();
                        String key = next.getKey();
                        String value = next.getValue();
                        //处理空格
                        if (value != null && value.contains(" ")) {
                            value = value.replaceAll(" ", "%20");
                        }
                        /**
                         * 此处增加value是多个字符拼接的情况，为适应参数是数组的情况
                         */
                        if (value != null) {
                            String[] arr = value.split(",");
                            if (arr.length > 1) {
                                for (int i = 0; i < arr.length; i++) {
                                    append.append(key).append("[]").append("=").append(arr[i]).append("&");
                                }
                            } else {
                                append.append(key).append("=").append(arr[0]).append("&");
                            }
                        } else {
                            append.append(key).append("=").append(value).append("&");
                        }
                    }
                    url = append.toString();
                    if (url.endsWith("?") || url.endsWith("&")) {
                        url = url.substring(0, url.length() - 1);
                    }
                }
            }
            StopWatch watch = new StopWatch();
            watch.start();
            // 配置
            RequestConfig config = RequestConfig.copy(RequestConfig.DEFAULT).setExpectContinueEnabled(true)
                    .setConnectTimeout(connectTimeout).build();
            HttpGet get = new HttpGet(url);
            get.setConfig(config);
            // 创建客户端
            HttpClient client = HttpClients.createDefault();
            // 发送请求
            HttpResponse response = client.execute(get);
            status = response.getStatusLine().getStatusCode();
            // 读取响应内容
            String text = readContent(charset, response);
            watch.stop();
            // 记录访问信息和执行时间
            logger.info(status + "" + watch.getLastTaskTimeMillis());
            HttpReturn httpReturn = new HttpReturn(status, text);
            return httpReturn;
        } catch (Exception e) {
            logger.error(url, e);
        }
        return null;
    }

    public static HttpReturn delete(String url) {
        return delete(url, DEFAULT_CHARSET);
    }

    public static HttpReturn delete(String url, Charset charset) {
        if (charset == null) {
            charset = DEFAULT_CHARSET;
        }
        int timeout = 0;
        int status = -1;
        try {
            StopWatch watch = new StopWatch();
            watch.start();
            RequestConfig config = RequestConfig.copy(RequestConfig.DEFAULT).setExpectContinueEnabled(true)
                    .setConnectTimeout(timeout).build();
            HttpDelete delete = new HttpDelete(url);
            delete.setConfig(config);
            HttpClient client = HttpClients.createDefault();
            HttpResponse response = client.execute(delete);
            status = response.getStatusLine().getStatusCode();
            String text = readContent(charset, response);
            watch.stop();
            logger.info(url, status + "" + watch.getLastTaskTimeMillis());
            HttpReturn httpReturn = new HttpReturn(status, text);
            return httpReturn;
        } catch (Exception e) {
            logger.error(url, e);
        }
        return null;
    }

    public static HttpReturn delete(String url, Map<String, String> params) {
        return delete(url, params, DEFAULT_CHARSET);
    }

    public static HttpReturn delete(String url, Map<String, String> params, Charset charset) {
        if (charset == null) {
            charset = DEFAULT_CHARSET;
        }
        int timeout = 0;
        int status = -1;
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
            StopWatch watch = new StopWatch();
            watch.start();
            RequestConfig config = RequestConfig.copy(RequestConfig.DEFAULT).setExpectContinueEnabled(true)
                    .setConnectTimeout(timeout).build();
            HttpDelete delete = new HttpDelete(url);
            delete.setConfig(config);
            HttpClient client = HttpClients.createDefault();
            HttpResponse response = client.execute(delete);
            status = response.getStatusLine().getStatusCode();
            String text = readContent(charset, response);
            watch.stop();
            logger.info(url, status, "" + watch.getLastTaskTimeMillis());
            HttpReturn httpReturn = new HttpReturn(status, text);
            return httpReturn;
        } catch (Exception e) {
            logger.error(url, e);
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

    public static HttpReturn postJSON(String url, String param) {
        return postJSON(url, param, DEFAULT_CHARSET);
    }

    public static HttpReturn postJSON(String url, String param, Charset charset) {
        if (charset == null) {
            charset = DEFAULT_CHARSET;
        }
        int status = -1;
        int timeout = 0;
        try {
            StopWatch watch = new StopWatch();
            watch.start();
            RequestConfig config = RequestConfig.copy(RequestConfig.DEFAULT).setExpectContinueEnabled(true)
                    .setConnectTimeout(timeout).build();
            HttpPost post = new HttpPost(url);
            post.setConfig(config);
            post.setHeader("Content-Type", "application/json;charset=utf-8");
            HttpEntity entity = new StringEntity(param, charset);
            post.setEntity(entity);
            HttpClient client = HttpClients.createDefault();
            HttpResponse response = client.execute(post);
            status = response.getStatusLine().getStatusCode();
            String text = readContent(charset, response);
            watch.stop();
            logger.info(url, status + "" + watch.getLastTaskTimeMillis());
            HttpReturn httpReturn = new HttpReturn(status, text);
            return httpReturn;
        } catch (Exception e) {
            logger.error(e + url, param);
        }
        return null;
    }

    public static HttpReturn postPlain(String url, String param) {
        return postPlain(url, param, DEFAULT_CHARSET);
    }

    public static HttpReturn postPlain(String url, String param, Charset charset) {
        if (charset == null) {
            charset = DEFAULT_CHARSET;
        }
        int status = -1;
        int timeout = 0;
        try {
            StopWatch watch = new StopWatch();
            watch.start();
            RequestConfig config = RequestConfig.copy(RequestConfig.DEFAULT).setExpectContinueEnabled(true)
                    .setConnectTimeout(timeout).build();
            HttpPost post = new HttpPost(url);
            post.setConfig(config);
            HttpEntity entity = new StringEntity(param, charset);
            post.setEntity(entity);
            HttpClient client = HttpClients.createDefault();
            HttpResponse response = client.execute(post);
            status = response.getStatusLine().getStatusCode();
            String text = readContent(charset, response);
            watch.stop();
            logger.info(url, status + "" + watch.getLastTaskTimeMillis());
            HttpReturn httpReturn = new HttpReturn(status, text);
            return httpReturn;
        } catch (Exception e) {
            logger.error(e + url, param);
        }
        return null;
    }

    public static HttpReturn putJSON(String url, String param) {
        return putJSON(url, param, DEFAULT_CHARSET);
    }

    public static HttpReturn putJSON(String url, String param, Charset charset) {
        if (charset == null) {
            charset = DEFAULT_CHARSET;
        }
        int timeout = 0;
        int status = -1;
        try {
            StopWatch watch = new StopWatch();
            watch.start();
            RequestConfig config = RequestConfig.copy(RequestConfig.DEFAULT).setExpectContinueEnabled(true)
                    .setConnectTimeout(timeout).build();
            HttpPut put = new HttpPut(url);
            put.setHeader("Content-Type", "application/json;charset=utf-8");
            put.setConfig(config);
            HttpEntity entity = new StringEntity(param, charset);
            put.setEntity(entity);
            HttpClient client = HttpClients.createDefault();
            HttpResponse response = client.execute(put);
            status = response.getStatusLine().getStatusCode();
            String text = readContent(charset, response);
            watch.stop();
            logger.info(url, param, status + "" + watch.getLastTaskTimeMillis());
            HttpReturn httpReturn = new HttpReturn(status, text);
            return httpReturn;
        } catch (Exception e) {
            logger.error(e + url, param);
        }
        return null;
    }

    public static HttpReturn putPlain(String url, String param) {
        return putPlain(url, param, DEFAULT_CHARSET);
    }

    public static HttpReturn putPlain(String url, String param, Charset charset) {
        if (charset == null) {
            charset = DEFAULT_CHARSET;
        }
        int timeout = 0;
        int status = -1;
        try {
            StopWatch watch = new StopWatch();
            watch.start();
            RequestConfig config = RequestConfig.copy(RequestConfig.DEFAULT).setExpectContinueEnabled(true)
                    .setConnectTimeout(timeout).build();
            HttpPut put = new HttpPut(url);
            put.setConfig(config);
            HttpEntity entity = new StringEntity(param, charset);
            put.setEntity(entity);
            HttpClient client = HttpClients.createDefault();
            HttpResponse response = client.execute(put);
            status = response.getStatusLine().getStatusCode();
            String text = readContent(charset, response);
            watch.stop();
            logger.info(url, param, status + "" + watch.getLastTaskTimeMillis());
            HttpReturn httpReturn = new HttpReturn(status, text);
            return httpReturn;
        } catch (Exception e) {
            logger.error(e + url, param);
        }
        return null;
    }

    public static HttpReturn postForm(String url, Map<String, String> param) {
        return postForm(url, param, DEFAULT_CHARSET);
    }

    public static HttpReturn postForm(String url, Map<String, String> param, Charset charset) {
        if (charset == null) {
            charset = DEFAULT_CHARSET;
        }
        int timeout = 0;
        int status = -1;
        try {
            StopWatch watch = new StopWatch();
            watch.start();
            RequestConfig config = RequestConfig.copy(RequestConfig.DEFAULT).setExpectContinueEnabled(true)
                    .setConnectTimeout(timeout).build();
            HttpPost post = new HttpPost(url);
            post.setConfig(config);
            List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
            Set<Entry<String, String>> entrySet = param.entrySet();
            for (Entry<String, String> entry : entrySet) {
                parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            HttpEntity entity = new UrlEncodedFormEntity(parameters, charset);
            post.setEntity(entity);
            HttpClient client = HttpClients.createDefault();
            HttpResponse response = client.execute(post);
            status = response.getStatusLine().getStatusCode();
            String text = readContent(charset, response);
            watch.stop();
            logger.info("post: url[{}], status[{}],  lastTask[{}]", url, status, watch.getLastTaskTimeMillis());
            HttpReturn httpReturn = new HttpReturn(status, text);
            return httpReturn;
        } catch (Exception e) {
            logger.error(e + url);
        }
        return null;
    }

    // RequestConfig.setCookieSpec(CookieSpecs.IGNORE_COOKIES)
    public static HttpReturn putForm(String url, Map<String, String> param) {
        return putForm(url, param, DEFAULT_CHARSET);
    }

    public static HttpReturn putForm(String url, Map<String, String> param, Charset charset) {
        if (charset == null) {
            charset = DEFAULT_CHARSET;
        }
        int timeout = 0;
        int status = -1;
        try {
            StopWatch watch = new StopWatch();
            watch.start();
            RequestConfig config = RequestConfig.copy(RequestConfig.DEFAULT).setExpectContinueEnabled(true)
                    .setConnectTimeout(timeout).build();
            HttpPut put = new HttpPut(url);
            put.setConfig(config);
            List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
            Set<Entry<String, String>> entrySet = param.entrySet();
            for (Entry<String, String> entry : entrySet) {
                parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            HttpEntity entity = new UrlEncodedFormEntity(parameters, charset);
            put.setEntity(entity);
            HttpClient client = HttpClients.createDefault();
            HttpResponse response = client.execute(put);
            status = response.getStatusLine().getStatusCode();
            String text = readContent(charset, response);
            watch.stop();
            logger.info("put: url[{}], status[{}], lastTask[{}]", url, status, watch.getLastTaskTimeMillis());
            HttpReturn httpReturn = new HttpReturn(status, text);
            return httpReturn;
        } catch (Exception e) {
            logger.error(e + url);
        }
        return null;
    }
}
