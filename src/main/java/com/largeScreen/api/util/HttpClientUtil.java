package com.largeScreen.api.util;

import com.largeScreen.api.entity.SSLClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.*;

public class HttpClientUtil {

    private static final String ENCODING = "UTF-8";

    private static final int CONNECT_TIMEOUT = 6000;

    private static final int SOCKET_TIMEOUT = 6000;

    private static HttpClient httpClient = null;

    public static Map<String, String> doPost(String url, Map<String,String> map){

        String result = null;

        Map<String, String> paramsMap = new HashMap<String, String>();

        try{

            if(httpClient == null){
                httpClient = new SSLClient();
            }

            HttpPost httpPost =  new HttpPost(url);

            List<NameValuePair> list = new ArrayList<NameValuePair>();

            Iterator iterator = map.entrySet().iterator();

            while(iterator.hasNext()){
                Map.Entry<String,String> elem = (Map.Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
            }

            if(list.size() > 0){
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, ENCODING);
                httpPost.setEntity(entity);
            }

            HttpResponse response = httpClient.execute(httpPost);

            if(response != null){
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null){
                    result = EntityUtils.toString(resEntity, ENCODING);
                }
            }

            com.alibaba.fastjson.JSONObject jsObj = com.alibaba.fastjson.JSONObject.parseObject(result);

            if (jsObj != null) {

                Iterator<String> temp = jsObj.keySet().iterator();

                while (temp.hasNext()) {
                    String key = temp.next();
                    paramsMap.put(key, jsObj.getString(key));
                }

            }

        }catch(Exception ex){
            ex.printStackTrace();
        }

        return paramsMap;
    }
}
