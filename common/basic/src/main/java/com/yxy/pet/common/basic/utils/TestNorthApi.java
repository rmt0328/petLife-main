package com.yxy.pet.common.basic.utils;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

public class TestNorthApi {

    //API的主机地址
    private static final String ApicHost = "10.10.10.1:1443";

    //应用程序的ID
    private static final String appId = "roma.link.test";

    //应用程序的密钥
    private static final String appKey = "ic+HsXPRCA****";

    //设备的ID
    private static final String deviceId="D6111KOQk";


    //服务名称
    private static final String serviceName="service";

    //命令的名称
    private static final String commandName="command";



    //创建支持SSL的httpclient对象,用于与服务器建立安全的HTTPS连接。在创建过程中，对SSL上下文进行配置，加载信任材料和指定SSL参数
    //最终返回一个能够发送经过SSL加密的请求的HttpClient对象
    private static CloseableHttpClient createSSLClient() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        //SSLContextBuilder()创建一个SSL上下文构造器对象
        //loadTrustMaterial:加载信任材料，始终返回true表示信任所有材料
        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (TrustStrategy) (chain, authType) -> true).build();

        //SSLConnectionSocketFactory:SSL连接套接字工厂，用于创建SSL连接
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new String[]{"TLSv1.2"}, null,
                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        return HttpClients.custom().setSSLSocketFactory(sslsf).build();
    }
    
    //为传入的HttpUriRequest对象设置必要的头信息，以便进行有效的身份验证和请求处理
    private static void setSSLHeader(HttpUriRequest httpUriRequest) {
    	long time = System.currentTimeMillis();
    	String authorization = DigestUtils.sha256Hex(appId + appKey + time);
        httpUriRequest.addHeader("Content-Type", "application/json");
        httpUriRequest.addHeader("Authorization", authorization);
        httpUriRequest.addHeader("timestamp", String.valueOf(time));
    }


    //用于发送https请求并获取响应
    private static HttpEntity getSSLResponse(HttpUriRequest httpUriRequest) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {
        //定义一个支持SSL的HttpClient对象，用来发送Https请求
        CloseableHttpClient httpClient = createSSLClient();
        //使用创建的httpclient对象执行传入的HTTP请求，并获取服务器的响应
        CloseableHttpResponse response = httpClient.execute(httpUriRequest);
        HttpEntity responseEntity = response.getEntity();
        System.out.println("��Ӧ״̬Ϊ:" + response.getStatusLine());
        if (responseEntity != null) {
            System.out.println("��Ӧ���ݳ���Ϊ:" + responseEntity.getContentLength());
            System.out.println("��Ӧ����Ϊ:" + EntityUtils.toString(responseEntity));
        }
        return responseEntity;
    }



    //构建设备命令的JSON数据体，发送HTTP POST请求到指定的URL，并获取服务器放回的响应
    public static void deviceCommands(String serviceName, String commandName, JSONObject cmdComtent) throws JSONException, IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        String url = "https://" + ApicHost + "/iot/1.0/deviceCommands?appId=" + appId;
        HttpPost httpPost = new HttpPost(url);  //����post����

        JSONObject cmdContent = new JSONObject();
        cmdContent.put("serviceId", serviceName);
        cmdContent.put("method", commandName);
        cmdContent.put("paras", cmdComtent);
        JSONObject cmdBody = new JSONObject();
        cmdBody.put("command", cmdContent);
        cmdBody.put("deviceId", deviceId);

        setSSLHeader(httpPost);	//���������header
        StringEntity stringEntity = new StringEntity(cmdBody.toString(), "utf-8");
        httpPost.setEntity(stringEntity);//���������body
        getSSLResponse(httpPost);//��ȡ��Ӧ���

    }


    public static void main(String[] args) throws Exception {
        JSONObject commandContent = new JSONObject();
        commandContent.put("status", "on");
        deviceCommands(serviceName, commandName, commandContent);

    }
}