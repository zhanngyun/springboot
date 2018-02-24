package com.rabbit.mq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.ArrayList;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicNameValuePair;

/**
 * @Author: yzhang
 * @Date: 2018/2/24 15:48
 * 短信发送工具类
 */
public class NoteUtils {

//    static String requestUrl="http://api.feige.ee/SmsService/Send";
    static String requestUrl="http://api.feige.ee/SmsService/Template";
    public static void main(String[] args) {
        System.out.println("Hello World!");
        try {
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            formparams.add(new BasicNameValuePair("Account","15539730302"));//账号
            formparams.add(new BasicNameValuePair("Pwd","2e61e9ab3b97a1c30d3768b9d"));//接口秘钥
            formparams.add(new BasicNameValuePair("Content","月恩集团在这里恭贺新春,祝您每天开开心心,身体健康,往事如意"));//发送短信内容
            formparams.add(new BasicNameValuePair("Mobile","15038703422"));//发送人联系方式
            formparams.add(new BasicNameValuePair("SignId","39204"));//签名id
            Post(formparams);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void Post( List<NameValuePair> formparams) throws Exception {
        CloseableHttpAsyncClient httpClient = HttpAsyncClients.createDefault();

        httpClient.start();

        HttpPost requestPost=new HttpPost(requestUrl);

        requestPost.setEntity(new UrlEncodedFormEntity(formparams,"utf-8"));

        httpClient.execute(requestPost, new FutureCallback<HttpResponse>() {

            public void failed(Exception arg0) {

                System.out.println("Exception: " + arg0.getMessage());
            }

            public void completed(HttpResponse arg0) {
                System.out.println("Response: " + arg0.getStatusLine());
                try {

                    InputStream stram= arg0.getEntity().getContent();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stram));
                    System.out.println(	reader.readLine());

                } catch (UnsupportedOperationException e) {

                    e.printStackTrace();
                } catch (IOException e) {
                }


            }

            public void cancelled() {
                // TODO Auto-generated method stub

            }
        }).get();



        System.out.println("Done");
    }
}
