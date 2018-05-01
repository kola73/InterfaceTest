package com.imooc.test;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestGet {
    @Test
    public void get() throws IOException {
//        定义一个字符串来存放结果
        String result;
//        创建get方法
        HttpGet httpGet = new HttpGet("http://www.imooc.com");
//       执行get方法
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = httpClient.execute(httpGet);
//        获取整个响应的全部内容，并把它转换为字符串并打印
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);
    }

    public void test2() throws IOException {
//        定义访问结果
        String results;
//        创建get方法
        HttpGet get = new HttpGet("http://www.baidu.com");
//        执行get方法
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = httpClient.execute(get);
        results=EntityUtils.toString (response.getEntity());
        System.out.println(results);
    }
}
