package com.example.demorequest.interceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSink;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 *
 * 自定义拦截器，添加参数
 *
 *
 * Created by Administrator on 2022/6/9 is 10:28.
 *
 * @Description
 * @Author hqqich <hqqich1314@outlook.com>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2022/6/9
 */

public class BasicParamsInterceptor implements Interceptor {

  Map<String, String> queryParamsMap = new HashMap<>();
  Map<String, String> paramsMap = new HashMap<>();
  Map<String, String> headerParamsMap = new HashMap<>();
  List<String> headerLinesList = new ArrayList<>();

  private BasicParamsInterceptor() {

  }



  @NotNull
  @Override
  public Response intercept(Chain chain) throws IOException {

    Request request = chain.request();
    Request.Builder requestBuilder = request.newBuilder();

    //==========================================添加header
    Headers.Builder headerBuilder = request.headers().newBuilder();
    if (headerParamsMap.size() > 0) {
      Iterator iterator = headerParamsMap.entrySet().iterator();
      while (iterator.hasNext()) {
        Map.Entry entry = (Map.Entry) iterator.next();
        headerBuilder.add((String) entry.getKey(), (String) entry.getValue());
      }
    }
    if (headerLinesList.size() > 0) {
      for (String line: headerLinesList) {
        headerBuilder.add(line);
      }
    }
    requestBuilder.headers(headerBuilder.build());



    //==========================================添加参数
    // get
    if (queryParamsMap.size() > 0) {
      request = injectParamsIntoUrl(request.url().newBuilder(), requestBuilder, queryParamsMap);
    }

    // post
    if (paramsMap != null && paramsMap.size() > 0 && request.method().equals("POST")) {
      if (request.body() instanceof FormBody) {
        FormBody.Builder newFormBodyBuilder = new FormBody.Builder();
        if (paramsMap.size() > 0) {
          Iterator iterator = paramsMap.entrySet().iterator();
          while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            newFormBodyBuilder.add((String) entry.getKey(), (String) entry.getValue());
          }
        }

        FormBody oldFormBody = (FormBody) request.body();
        int paramSize = oldFormBody.size();
        if (paramSize > 0) {
          for (int i=0;i<paramSize;i++) {
            newFormBodyBuilder.add(oldFormBody.name(i), oldFormBody.value(i));
          }
        }

        requestBuilder.post(newFormBodyBuilder.build());
        request = requestBuilder.build();
      } else if (request.body() instanceof MultipartBody) {  //是文件
        MultipartBody.Builder multipartBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);

        Iterator iterator = paramsMap.entrySet().iterator();
        while (iterator.hasNext()) {
          Map.Entry entry = (Map.Entry) iterator.next();
          multipartBuilder.addFormDataPart((String) entry.getKey(), (String) entry.getValue());
        }

        List<MultipartBody.Part> oldParts = ((MultipartBody)request.body()).parts();
        if (oldParts != null && oldParts.size() > 0) {
          for (MultipartBody.Part part : oldParts) {
            multipartBuilder.addPart(part);
          }
        }

        requestBuilder.post(multipartBuilder.build());
        request = requestBuilder.build();
      } else if (request.body() instanceof RequestBody) {
        //System.out.println("RequestBody");

        requestBuilder.post(new RequestBody() {
          @Nullable
          @Override
          public MediaType contentType() {
            return MediaType.parse("application/json; charset=utf-8");
          }

          @Override
          public void writeTo(@NotNull BufferedSink bufferedSink) throws IOException {
            bufferedSink.write("{\"name\":\"chenhao\"}".getBytes());
          }
        });
        request = requestBuilder.build();
      }

    }
    return chain.proceed(request);
  }

  /**
   * get 请求，向URL上拼接参数
   * @param httpUrlBuilder
   * @param requestBuilder
   * @param paramsMap
   * @return
   */
  private Request injectParamsIntoUrl(HttpUrl.Builder httpUrlBuilder, Request.Builder requestBuilder, Map<String, String> paramsMap) {
    if (paramsMap.size() > 0) {
      Iterator iterator = paramsMap.entrySet().iterator();
      while (iterator.hasNext()) {
        Map.Entry entry = (Map.Entry) iterator.next();
        httpUrlBuilder.addQueryParameter((String) entry.getKey(), (String) entry.getValue());
      }
      requestBuilder.url(httpUrlBuilder.build());
      return requestBuilder.build();
    }

    return null;
  }

  private static String bodyToString(final RequestBody request){
    try {
      final RequestBody copy = request;
      final Buffer buffer = new Buffer();
      if(copy != null)
        copy.writeTo(buffer);
      else
        return "";
      return buffer.readUtf8();
    }
    catch (final IOException e) {
      return "did not work";
    }
  }

  public static class Builder {

    BasicParamsInterceptor interceptor;

    public Builder() {
      interceptor = new BasicParamsInterceptor();
    }

    public Builder addParam(String key, String value) {
      interceptor.paramsMap.put(key, value);
      return this;
    }

    public Builder addParamsMap(Map<String, String> paramsMap) {
      interceptor.paramsMap.putAll(paramsMap);
      return this;
    }

    public Builder addHeaderParam(String key, String value) {
      interceptor.headerParamsMap.put(key, value);
      return this;
    }

    public Builder addHeaderParamsMap(Map<String, String> headerParamsMap) {
      interceptor.headerParamsMap.putAll(headerParamsMap);
      return this;
    }

    public Builder addHeaderLine(String headerLine) {
      int index = headerLine.indexOf(":");
      if (index == -1) {
        throw new IllegalArgumentException("Unexpected header: " + headerLine);
      }
      interceptor.headerLinesList.add(headerLine);
      return this;
    }

    public Builder addHeaderLinesList(List<String> headerLinesList) {
      for (String headerLine: headerLinesList) {
        int index = headerLine.indexOf(":");
        if (index == -1) {
          throw new IllegalArgumentException("Unexpected header: " + headerLine);
        }
        interceptor.headerLinesList.add(headerLine);
      }
      return this;
    }

    public Builder addQueryParam(String key, String value) {
      interceptor.queryParamsMap.put(key, value);
      return this;
    }

    public Builder addQueryParamsMap(Map<String, String> queryParamsMap) {
      interceptor.queryParamsMap.putAll(queryParamsMap);
      return this;
    }

    public BasicParamsInterceptor build() {
      return interceptor;
    }

  }


  public static void main(String[] args) throws Exception {
    BasicParamsInterceptor basicParamsInterceptor =
        new BasicParamsInterceptor.Builder()
            .addHeaderParam("device_id", "device_id")
            .addParam("uid", "uid")
            .addQueryParam("api_version", "1.1")
            .addQueryParam("name", "hqqich")
            .build();

    OkHttpClient client = new OkHttpClient.Builder()
        .addInterceptor(basicParamsInterceptor)
        .build();

    Request requestGet = new Request.Builder()
        .url("https://httpbin.org/get")
        .get()
        .build();

    Request requestPost = new Request.Builder()
        .post(new RequestBody() {
          @Nullable
          @Override
          public MediaType contentType() {
            return MediaType.parse("application/json; charset=utf-8");
          }

          @Override
          public void writeTo(@NotNull BufferedSink bufferedSink) throws IOException {
            bufferedSink.write("{\"name\":\"hqqich\"}".getBytes());
          }
        })
        .url("https://httpbin.org/post")
        .build();

    //Response execute = client.newCall(requestGet).execute();
    Response execute = client.newCall(requestPost).execute();

    System.out.println(execute.body().string());


  }


}