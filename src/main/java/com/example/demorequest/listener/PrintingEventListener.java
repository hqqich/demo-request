package com.example.demorequest.listener;

import com.example.demorequest.interceptor.GzipRequestInterceptor;
import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Administrator on 2022/6/9 is 8:42.
 *
 * @Description
 * @Author hqqich <hqqich1314@outlook.com>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2022/6/9
 */

public class PrintingEventListener extends EventListener {

  private long callStartNanos;

  private void printEvent(String name) {
    long nowNanos = System.nanoTime();
    if (name.equals("callStart")) {
      callStartNanos = nowNanos;
    }
    long elapsedNanos = nowNanos - callStartNanos;
    System.out.printf("%.3f %s%n", elapsedNanos / 1000000000d, name);
  }

  @Override
  public void callEnd(@NotNull Call call) {
    printEvent("callEnd");
  }

  @Override
  public void callStart(@NotNull Call call) {
    printEvent("callStart");
  }

  @Override
  public void dnsEnd(@NotNull Call call, @NotNull String domainName, @NotNull List<InetAddress> inetAddressList) {
    printEvent("dnsEnd");
  }

  @Override
  public void dnsStart(@NotNull Call call, @NotNull String domainName) {
    printEvent("dnsStart");
  }


  public static void main(String[] args) throws IOException {

    OkHttpClient client = new OkHttpClient().newBuilder()
        .readTimeout(5, TimeUnit.SECONDS)
        .eventListener(new PrintingEventListener())
        //.addInterceptor(new LoggingInterceptor())
        //.addNetworkInterceptor(new LoggingInterceptor())
        .addNetworkInterceptor(new GzipRequestInterceptor())
        .build();

    Request request = new Request.Builder()
        .url("http://publicobject.com/helloworld.txt")
        .addHeader("User-Agent", "OkHttp Example")
        .build();

    System.out.println("REQUEST 1 (new connection)");
    try (Response response = client.newCall(request).execute()) {
      // Consume and discard the response body.
      //System.out.println(response.body().source().readByteString());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    System.out.println("======================================================");

    System.out.println("REQUEST 2 (pooled connection)");
    try (Response response = client.newCall(request).execute()) {
      // Consume and discard the response body.
      //System.out.println(response.body().source().readByteString());
    }
  }


}
