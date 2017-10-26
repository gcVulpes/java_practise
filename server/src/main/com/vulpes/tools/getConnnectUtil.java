package vulpes.tools;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import vulpes.tools.Constants;

import javax.swing.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * 通过配置文件获取url 发送请求及获取数据
 *
 * apache httpClient 4.26版本后使用新版的代码
 *
 * Created by kadokawa on 24/10/17.
 */
public class getConnnectUtil {
    private static final CloseableHttpClient HTTP_CLIENT;
    static{
        RequestConfig config = RequestConfig.custom().setConnectTimeout(60000).setSocketTimeout(15000).build();
        HTTP_CLIENT = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
    }
    public static void getConnect(){
        HttpPost httpPost = new HttpPost("指定的url地址");
        /*
        HttpPost可通过
                addHeader/setHeader 设置请求头信息 编码格式 等信息        httppost.addHeader("Content-type", "application/json");//请求头
           相应的有 HttpGet类
         */
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("参数队列头部","调用参数"));//apache自带 用于装载参数的key-value对集合 将在拼接参数时自动补充 ? = & 等符号
        CloseableHttpResponse response = null;
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(param));
            response = HTTP_CLIENT.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode != HttpStatus.SC_OK){//状态码 类似400 404 500等  sc_ok表示200 请求成功
                httpPost.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            System.out.println("************HttpResponseProxy************" + statusCode);
            HttpEntity entity = response.getEntity();//Response接收httpEntity返回参数
            if(entity != null){
                String s = EntityUtils.toString(entity, "UTF-8");//以UTF-8格式转移返回内容
                System.out.println("***********接口返回*************" + s);
            }
            EntityUtils.consume(entity);//EntityUtils.consume(entity);关闭参数流操作。
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConncet(response,HTTP_CLIENT);
        }
    }

    private static void closeConncet(CloseableHttpResponse response, CloseableHttpClient HTTP_CLIENT){
        if (response != null) {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (HTTP_CLIENT != null) {
            try {
                HTTP_CLIENT.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}