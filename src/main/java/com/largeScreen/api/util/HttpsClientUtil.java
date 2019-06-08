package com.largeScreen.api.util;

import com.largeScreen.api.entity.HttpResult;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpsClientUtil {

    private static CloseableHttpClient httpClient = null;

    private static CloseableHttpResponse httpResponse = null;

    public static HttpResult doGet(String url, Map<String, Object> map) throws Exception {

        if (httpClient == null) {
            httpClient = HttpClients.createDefault();
        }

        URIBuilder uriBuilder = new URIBuilder(url);

        if (map != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }

        HttpGet httpGet = new HttpGet(uriBuilder.build());

        if (httpResponse != null) {
            httpResponse = null;
        }

        try {
            httpResponse = httpClient.execute(httpGet);
        } catch (IOException e) {
            HttpResult httpResult = new HttpResult();
            httpResult.setCode(404);
            httpResult.setBody("请求失败");
            return httpResult;
        }

        HttpResult httpResult = new HttpResult();

        if (httpResponse.getEntity() != null) {
            httpResult.setCode(httpResponse.getStatusLine().getStatusCode());
            httpResult.setBody(EntityUtils.toString(httpResponse.getEntity(), "UTF-8"));

        } else {
            httpResult.setCode(httpResponse.getStatusLine().getStatusCode());
        }

        return httpResult;
    }

    public static HttpResult doPost(String url, Map<String, Object> map) throws Exception {

        if (httpClient == null) {
            httpClient = HttpClients.createDefault();
        }

        HttpPost httpPost = new HttpPost(url);

        if (map != null) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();

            for (Map.Entry<String, Object> entry : map.entrySet()) {
                params.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }

            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, "UTF-8");

            httpPost.setEntity(formEntity);
        }

        if (httpResponse != null) {
            httpResponse = null;
        }

        try {
            httpResponse = httpClient.execute(httpPost);
        } catch (IOException e) {
            HttpResult httpResult = new HttpResult();
            httpResult.setCode(404);
            httpResult.setBody("请求失败");
            return httpResult;
        }

        HttpResult httpResult = new HttpResult();

        if (httpResponse.getEntity() != null) {
            httpResult.setCode(httpResponse.getStatusLine().getStatusCode());
            httpResult.setBody(EntityUtils.toString(httpResponse.getEntity(), "UTF-8"));
        } else {
            httpResult.setCode(httpResponse.getStatusLine().getStatusCode());
        }

        return httpResult;
    }

    public static HttpResult doPut(String url, Map<String, Object> map) throws Exception {

        if (httpClient == null) {
            httpClient = HttpClients.createDefault();
        }

        HttpPut httpPut = new HttpPut(url);

        if (map != null) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();

            for (Map.Entry<String, Object> entry : map.entrySet()) {
                params.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }

            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, "UTF-8");

            httpPut.setEntity(formEntity);
        }

        if (httpResponse != null) {
            httpResponse = null;
        }

        try {
            httpResponse = httpClient.execute(httpPut);
        } catch (IOException e) {
            HttpResult httpResult = new HttpResult();
            httpResult.setCode(404);
            httpResult.setBody("请求失败");
            return httpResult;
        }

        HttpResult httpResult = new HttpResult();

        if (httpResponse.getEntity() != null) {
            httpResult.setCode(httpResponse.getStatusLine().getStatusCode());
            httpResult.setBody(EntityUtils.toString(httpResponse.getEntity(), "UTF-8"));
        } else {
            httpResult.setCode(httpResponse.getStatusLine().getStatusCode());
        }

        return httpResult;
    }

    public static HttpResult doDelete(String url, Map<String, Object> map) throws Exception {

        if (httpClient == null) {
            httpClient = HttpClients.createDefault();
        }

        URIBuilder uriBuilder = new URIBuilder(url);

        if (map != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }

        HttpDelete httpDelete = new HttpDelete(uriBuilder.build());

        if (httpResponse != null) {
            httpResponse = null;
        }

        try {
            httpResponse = httpClient.execute(httpDelete);
        } catch (IOException e) {
            HttpResult httpResult = new HttpResult();
            httpResult.setCode(404);
            httpResult.setBody("请求失败");
            return httpResult;

        }

        HttpResult httpResult = new HttpResult();

        if (httpResponse.getEntity() != null) {
            httpResult.setCode(httpResponse.getStatusLine().getStatusCode());
            httpResult.setBody(EntityUtils.toString(httpResponse.getEntity(), "UTF-8"));
        } else {
            httpResult.setCode(httpResponse.getStatusLine().getStatusCode());
        }
        return httpResult;
    }
}
