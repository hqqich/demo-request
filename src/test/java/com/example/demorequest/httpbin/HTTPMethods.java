package com.example.demorequest.httpbin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

/**
 * Created by Administrator on 2022/6/8 is 9:08.
 *
 * @Description
 * @Author hqqich <hqqich1314@outlook.com>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2022/6/8
 */

//@SpringBootTest
public class HTTPMethods {


  public static final MediaType TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

  public static final MediaType TYPE_MEDIA_JPG = MediaType.parse("image/jpg");

  @SneakyThrows
  @Test
  public void delete() {

    OkHttpClient okHttpClient = new OkHttpClient.Builder()
        .readTimeout(5, TimeUnit.SECONDS)
        .build();

    Request request = new Request.Builder()
        .url("https://httpbin.org/delete")
        .delete()
        .removeHeader("User-Agent")
        .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36")
        .build();

    Call call = okHttpClient.newCall(request);

    call.enqueue(new Callback() {
      @Override
      public void onFailure(@NotNull Call call, @NotNull IOException e) {
        System.out.println("失败");
      }

      @Override
      public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        if (response.body() != null) {
          System.out.println(response.body().string());
        }
      }
    });

    //主线程等待
    Thread.sleep(10000);
  }


  @SneakyThrows
  @Test
  public void get() {

    OkHttpClient client = new OkHttpClient().newBuilder()
        .readTimeout(5, TimeUnit.SECONDS)
        .build();

    Request request = new Request.Builder()
        .get()
        .url("https://httpbin.org/get?name=aaa")
        .removeHeader("User-Agent")
        .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36")
        .build();

    Call call = client.newCall(request);
    call.enqueue(new Callback() {
      @Override
      public void onFailure(@NotNull Call call, @NotNull IOException e) {

      }

      @Override
      public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        System.out.println(response.body().string());
      }
    });

    Thread.sleep(10000);

  }


  @Test
  @SneakyThrows
  public void patch() {

    OkHttpClient client = new OkHttpClient().newBuilder()
        .readTimeout(5, TimeUnit.SECONDS)
        .build();


    RequestBody requestBody = RequestBody.create("{\"name\" : \"hqqich\",\"age\" : 18,\"sex\" : true}", MediaType.parse("application/json; charset=utf-8"));

    Request request = new Request.Builder()
        .patch(requestBody)
        .url("https://httpbin.org/patch")
        .build();

    Call call = client.newCall(request);

    call.enqueue(new Callback() {
      @Override
      public void onFailure(@NotNull Call call, @NotNull IOException e) {

      }

      @Override
      public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        System.out.println(response.body().string());
      }
    });


    Thread.sleep(10000);

  }


  @SneakyThrows
  @Test
  public void post() {



    //=================================================================================表单
    HashMap<String,Object> paramsMap = new HashMap<>();
    paramsMap.put("name", "hqqich");
    paramsMap.put("age", 25);
    paramsMap.put("sex", true);
    FormBody.Builder requestParamsForm = new FormBody.Builder();
    for (String key : paramsMap.keySet()) {
      requestParamsForm.add(key, String.valueOf(paramsMap.get(key)));
    }
    FormBody requestBodyForm = requestParamsForm.build();


    //=================================================================================json
    String requestParamsJson = "{\"name\" : \"hqqich\",\"age\" : 18,\"sex\" : true}";
    RequestBody requestBodyJson = RequestBody.create(requestParamsJson, TYPE_JSON);



    //=================================================================================文件
    File file=new File("image.jpg");
    RequestBody filebody = MultipartBody.create(file, TYPE_MEDIA_JPG);
    MultipartBody.Builder multiBuilder=new MultipartBody.Builder();
    multiBuilder.addFormDataPart("file", file.getName(), filebody);
    multiBuilder.setType(MultipartBody.FORM);
    //multiBuilder.addFormDataPart("file", file.getName(), filebody);
    //再带个表单
    HashMap<String, String> params = new HashMap<>();
    params.put("token","1911173227afe098143caf4d315a436d");
    params.put("uuid","A000005566DA77");
    //以header方式封装
    if (params != null && !params.isEmpty()) {
      for (String key : params.keySet()) {
        multiBuilder.addPart(
            Headers.of("Content-Disposition", "form-data; name=\"" + key + "\""),
            RequestBody.create(params.get(key), null));
      }
    }
    RequestBody requestBodyFormMulti = multiBuilder.build();




    OkHttpClient client = new OkHttpClient().newBuilder()
        .readTimeout(5, TimeUnit.SECONDS)
        .build();

    Request request = new Request.Builder()
        .post(requestBodyFormMulti)
        .url("https://httpbin.org/post")
        .build();

    Call call = client.newCall(request);

    call.enqueue(new Callback() {
      @Override
      public void onFailure(@NotNull Call call, @NotNull IOException e) {

      }
      @Override
      public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        System.out.println(response.body().string());
      }
    });

    Thread.sleep(10000);
  }



  @Test
  @SneakyThrows
  public void put() {


    RequestBody requestBody = RequestBody.create("{\"name\": \"hqqich\"}", TYPE_JSON);



    OkHttpClient client = new OkHttpClient().newBuilder()
        .readTimeout(5, TimeUnit.SECONDS)
        .build();

    Request request = new Request.Builder()
        .put(requestBody)
        .url("https://httpbin.org/put")
        .build();

    Call call = client.newCall(request);

    call.enqueue(new Callback() {
      @Override
      public void onFailure(@NotNull Call call, @NotNull IOException e) {

      }

      @Override
      public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        System.out.println(response.body().string());
      }
    });

    Thread.sleep(10000);
  }



}
