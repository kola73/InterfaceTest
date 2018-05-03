package com.imooc.test;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookies {
    private String url;
    private ResourceBundle bundle;
    //    用来存储cookie信息的变量
    private CookieStore store;

    @BeforeTest
    public void beforetest() {
//        读取配置文件
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
//        读取配置文件的url信息
        String url = bundle.getString("test.url");
    }

    @Test
    public void getCookies() throws IOException {
        String result;
//        读取配置文件的uri
        String uri = bundle.getString("getCookies.uri");
//        创建get方法
        HttpGet get = new HttpGet(this.url + uri);
//        执行get方法
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = httpClient.execute(get);
//        获得整个响应的全部内容，并转换为字符串
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);
//        获取全部cookie
        this.store = httpClient.getCookieStore();
        List<Cookie> cookieList = store.getCookies();
        for (Cookie cookie : cookieList) {
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("name=" + name + ",value=" + value);
        }
    }

    @Test(dependsOnMethods = {"getCookies"})
    public void getWithCookies() throws IOException {
        String uri = bundle.getString("test.get.with.cookies");
        HttpGet get = new HttpGet(this.url + uri);
        DefaultHttpClient httpClient = new DefaultHttpClient();
//        把获取的cookie放在这里/设置cookie信息
        httpClient.setCookieStore(store);
        HttpResponse response = httpClient.execute(get);
//        获取响应码
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("statusCode=" + statusCode);
        if (statusCode == 200) {
            String result = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println(result);
        }
    }
}
