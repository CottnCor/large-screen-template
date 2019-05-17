package com.largeScreen.api;

import com.largeScreen.api.entity.HttpResult;
import com.largeScreen.api.util.HttpsClientUtil;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiApplicationTests {

    private HttpsClientUtil httpsClientUtil;

    @Before
    public void init() {
        this.httpsClientUtil = new HttpsClientUtil();
    }

    // 查询
    @Test
    public void testQueryItemById() throws Exception {
        String url = "http://www.apiopen.top/weatherApi";
        Map<String, Object> map = new HashMap<>();
        HttpResult httpResult = httpsClientUtil.doGet(url, map);
        System.out.println(httpResult.getCode());
        System.out.println(httpResult.getBody());

    }
}
