package com.sunyard.dip.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

/**
 * http工具类
 *
 * Created by Administrator on 2018/5/25/0025.
 */
public class HttpUtils {
    private String cha = "aa";
    private static final String charset = "UTF-8";

    public static String doGet(String url, int connectTimeOut, int readTimeOut) throws Exception {
        String result = null;
        HttpGet httpGet;
        CloseableHttpClient httpClient;
        HttpClientBuilder clientBuilder;

        int defaultConnectTimeOut = 10;
        int defaultReadTimeOut = 20;
        try {
            clientBuilder = HttpClientBuilder.create();
            httpClient = clientBuilder.build();
            httpGet = new HttpGet(url);

            // 设置连接超时时间和Socket超时时间
            RequestConfig.Builder configBuilder = RequestConfig.custom()
                    .setConnectTimeout(connectTimeOut > 0 ? connectTimeOut * 1000 : defaultConnectTimeOut * 1000)
                    .setSocketTimeout(readTimeOut > 0 ? readTimeOut * 1000 : defaultReadTimeOut * 1000);
            httpGet.setConfig(configBuilder.build());

            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            if (httpResponse != null) {
                HttpEntity resEntity = httpResponse.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    public static String doPost(String url, String json, int connectTimeOut, int readTimeOut) throws Exception {
        String result = null;
        HttpPost httpPost;
        CloseableHttpClient httpClient;
        HttpClientBuilder clientBuilder;

        int defaultConnectTimeOut = 10;
        int defaultReadTimeOut = 20;

        try {
            clientBuilder = HttpClientBuilder.create();

            httpClient = clientBuilder.build();

            httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");

            StringEntity stringEntity = new StringEntity(json, charset);
            stringEntity.setContentType("text/json");
            stringEntity.setContentEncoding(new BasicHeader("Content-Type", "application/json;charset=UTF-8"));
            httpPost.setEntity(stringEntity);

            // 设置连接超时时间和Socket超时时间
            RequestConfig.Builder configBuilder = RequestConfig.custom()
                    .setConnectTimeout(connectTimeOut > 0 ? connectTimeOut * 1000 : defaultConnectTimeOut * 1000)
                    .setSocketTimeout(readTimeOut > 0 ? readTimeOut * 1000 : defaultReadTimeOut * 1000);
            httpPost.setConfig(configBuilder.build());

            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            if (httpResponse != null) {
                HttpEntity resEntity = httpResponse.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return result;
    }
}
