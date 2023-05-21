package com.example.demorequest.httpbin;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

/**
 * Created by Administrator on 2022/6/8 is 10:10.
 *
 * @Description
 * @Author hqqich <hqqich1314@outlook.com>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2022/6/8
 */


public class Auth {

  @Test
  @SneakyThrows
  public void basicAuth() {

    OkHttpClient client = new OkHttpClient().newBuilder()
        .readTimeout(5, TimeUnit.SECONDS)
        .build();

    Request request = new Request.Builder()
        .get()
        .url("https://httpbin.org/basic-auth/1/2")
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
