package com.example.demorequest;

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
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created by Administrator on 2022/6/8 is 8:50.
 *
 * @Description
 * @Author hqqich <hqqich1314@outlook.com>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2022/6/8
 */

@SpringBootTest
public class HttpBinTest {

  @SneakyThrows
  @Test
  void test01() {

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

}
