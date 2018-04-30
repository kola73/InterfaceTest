package com.imooc.httpclient.demo;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/*
这个是对上面那个MyHttpClient_Get的优化
基本步骤如下
1，在source里新建properties配置文件，里面配置好url，uri等参数。
2，通过代码读取配置文件，这里我们通过java自带的ResourceBundle类读取配置文件
 */
public class MyCookies {
    private String url;
    private ResourceBundle bundle;

    @BeforeTest
    public void beforeTest() {
//        读取properties配置文件（里面要加上配置文件的名称）,另后面要加上Locale.CHINA防止乱码，切记！！！
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
//        获取properties配置文件里面的url
        url = bundle.getString("test.url");
    }

    @Test
    public void testGetForCookies() throws IOException {
        String result;
//        从配置文件中读取uri
        String uri = bundle.getString("getCookies.uri");
//        从配置文件中拼接测试的url
        HttpGet get = new HttpGet(this.url + uri);
//        执行get方法
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = httpClient.execute(get);
 //      获取整个响应的全部内容，并把它转化为字符串(为了防止乱码，加上编码格式utf-8
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
    }
}

