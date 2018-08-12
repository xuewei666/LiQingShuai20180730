package com.bwei.httputis;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUrlConnection {
    private static HttpListenter httpListenter;
    private String url_01 = "http://www.xieast.com/api/news/news.php?page=";
    private static final HttpUrlConnection ourInstance = new HttpUrlConnection();
    private int index;

    public static HttpUrlConnection getInstance() {
        return ourInstance;
    }

    private HttpUrlConnection() {
    }

    public void getData(int page) {
        index = page;
        //创建异步
        MyAsynoTack myAsynoTack = new MyAsynoTack();
        myAsynoTack.execute();
    }

    private class MyAsynoTack extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                //封装网络地址
                URL url = new URL(url_01+index);
               HttpURLConnection connection= (HttpURLConnection) url.openConnection();
               //设置请求方式
                connection.setRequestMethod("GET");
                //判断状态码
                if (connection.getResponseCode()==200){
                    //从服务器得到输入流吧
                    InputStream inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuilder builder = new StringBuilder();
                    String str = "";
                    while ((str=bufferedReader.readLine())!=null){
                        builder.append(str);
                    }

                    inputStreamReader.close();
                    bufferedReader.close();
                    return builder.toString();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            httpListenter.getDataJson(s);
        }
    }
    //定义一个借口
    public interface HttpListenter{
        public void getDataJson(String json);

    }

    public static void setHttpListenter(HttpListenter httpListenter) {
        HttpUrlConnection.httpListenter = httpListenter;
    }
}
