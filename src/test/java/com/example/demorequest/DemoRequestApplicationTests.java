package com.example.demorequest;

import com.example.demorequest.entity.Item;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.LongSerializationPolicy;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import okhttp3.Call;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoRequestApplicationTests {


  private Gson gson;


  @Autowired
  public void setGson(Gson gson) {
    this.gson = gson;
  }

  @Test
  void contextLoads() {
  }


  @SneakyThrows
  @Test
  void test01() {

    String link = "https://www.qcc.com/api/home/getNewsFlash?firstRankIndex=1&lastRankIndex=0&lastRankTime=&pageSize=50";

    URL url = new URL(link);
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("POST");
    connection.setConnectTimeout(5000);
    connection.setReadTimeout(5000);

    InputStream inputStream = connection.getInputStream();

    StringBuilder text = new StringBuilder();
    FileOutputStream fileOutputStream = new FileOutputStream("./baidu.html");

    BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

    int len = -1;
    byte[] bytes = new byte[1024];
    while ((len = bufferedInputStream.read(bytes)) != -1) {
      text.append(new String(bytes, 0, len));
      bufferedOutputStream.write(bytes, 0, len);
    }
    bufferedOutputStream.flush();
    bufferedOutputStream.close();
    bufferedInputStream.close();

    System.out.println(text);
  }


  @SneakyThrows
  @Test
  void test02() {

    OkHttpClient okHttpClient = new OkHttpClient();
    Request request = new Request.Builder()
        .url("https://www.qcc.com/api/home/getNewsFlash?firstRankIndex=1&lastRankIndex=0&lastRankTime=&pageSize=50")
        .removeHeader("User-Agent")
        .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36")
        .build();
    Call call = okHttpClient.newCall(request);
    Response execute = call.execute();
    String result = execute.body().string();
    //System.out.println(result);

    JsonElement jsonElement = JsonParser.parseString(result);
    System.out.println(jsonElement);
    JsonArray jsonArray = jsonElement.getAsJsonArray();
    //System.out.println(jsonArray);
    for (JsonElement element : jsonArray) {
      //System.out.println(element);
      JsonObject asJsonObject = element.getAsJsonObject();

      //中途转成对象
      //Object o = gson.fromJson(gson.toJson(element), new TypeToken<Map<String, Object>>() {}.getType());
      //System.out.println(o);

      JsonObject feed_data = asJsonObject.getAsJsonObject("feed_data").getAsJsonObject();
      System.out.println(feed_data.get("content"));
    }

    System.out.println("============输出结果================");

    System.out.println(result);

    System.out.println("============转成List<Map>================");

    List<Map<String, Object>> responseList = gson.fromJson(new GsonBuilder().setLongSerializationPolicy(LongSerializationPolicy.STRING).create().toJson(JsonParser.parseString(result)), new TypeToken<List<Map<String, Object>>>() {}.getType());
    for (Map<String, Object> stringObjectMap : responseList) {
      //System.out.println(stringObjectMap);
      System.out.println((Double) stringObjectMap.get("feed_id"));
    }
    //System.out.println(responseList);

    System.out.println("============转成对象================");

    List<Item> items = gson.fromJson(result, new TypeToken<List<Item>>() {}.getType());
    for (Item stringObjectMap : items) {
      //System.out.println(stringObjectMap);
      System.out.println(stringObjectMap);
    }
  }

  @SneakyThrows
  @Test
  void test03() {

    //挂上代理
    OkHttpClient.Builder builder = new OkHttpClient.Builder();
    builder.connectTimeout(1, TimeUnit.MINUTES);
    Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890));
    builder.proxy(proxy);

    //管理cookie
    CookieManager cookieManager = new CookieManager();

    OkHttpClient okHttpClient = builder
        .cookieJar(new JavaNetCookieJar(cookieManager))
        .build();

    Request request = new Request
        .Builder()
        .url("https://www.google.com")
        .build();
    Call call = okHttpClient.newCall(request);
    Response execute = call.execute();
    String result = execute.body().string();

    Document parse = Jsoup.parse(result);
    System.out.println(parse.getAllElements());

  }

  @SneakyThrows
  @Test
  void test04() {
    //挂上代理
    OkHttpClient.Builder builder = new OkHttpClient.Builder();
    builder.connectTimeout(1, TimeUnit.MINUTES);
    Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890));
    builder.proxy(proxy);

    //管理cookie
    CookieManager cookieManager = new CookieManager();

    OkHttpClient okHttpClient = builder
        .cookieJar(new JavaNetCookieJar(cookieManager))
        .build();

    Request request = new Request.Builder()
        .url("http://httpbin.org/get")
        .removeHeader("User-Agent")
        .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36")
        .build();
    Call call = okHttpClient.newCall(request);
    Response execute = call.execute();
    String result = execute.body().string();
    System.out.println(result);
  }


}