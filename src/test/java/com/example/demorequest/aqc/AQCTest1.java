package com.example.demorequest.aqc;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

/**
 * Created by Administrator on 2022/6/8 is 14:06.
 *
 * @Description
 * @Author hqqich <hqqich1314@outlook.com>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2022/6/8
 */

public class AQCTest1 {

  @Test
  @SneakyThrows
  public void test01() {

    OkHttpClient client = new OkHttpClient.Builder()
        .readTimeout(5, TimeUnit.SECONDS)
        .build();

    HashMap<String,Object> paramsMap = new HashMap<>();
    paramsMap.put("q", "安徽科盾");
    paramsMap.put("t", "0");
    FormBody.Builder requestParamsForm = new FormBody.Builder();
    for (String key : paramsMap.keySet()) {
      requestParamsForm.add(key, String.valueOf(paramsMap.get(key)));
    }
    FormBody requestBodyForm = requestParamsForm.build();


    Request request = new Request.Builder()
        .post(requestBodyForm)
        .url("https://aiqicha.baidu.com/index/suggest")
        .addHeader("Referer", "https://aiqicha.baidu.com/?from=pz")
        .addHeader("Cookie", "BIDUPSID=B3C3BD28324F74893033F000B8BB2639; PSTM=1654667910; BAIDUID=B3C3BD28324F7489EF4C67D165A780B5:FG=1; BAIDUID_BFESS=B3C3BD28324F7489EF4C67D165A780B5:FG=1; BA_HECTOR=2c2g2k2184218g2la11ha0ek515; ZFY=TtYLhit2fYh0jcHoD:AFrIdDA:AeeNbIvoSd16C6Pxs:BA:C; BDRCVFR[S4-dAuiWMmn]=I67x6TjHwwYf0; delPer=0; PSINO=5; H_PS_PSSID=36553_36455_31660_34812_36570_36482_36520_26350_36301_36469_36315; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598; kleck=2118a327ffd7d86e7a435beca3a0ec8c; BCLID=10865121101061757521; BDSFRCVID=3NKOJexroG0lbdODCaKvheZ7ZeKKg7jTDYrEZguiLEnlccDVJeC6EG0PtOqPGZu-EHtdogKKWmOTH7kF_2uxOjjg8UtVJeC6EG0Ptf8g0M5; H_BDCLCKID_SF=tR3KB4jVKbnHKRopMtOhq4tehH4eWfn9WDTOQJ7TtnRDeh7eLPQv54k8WGJzKxni5bvB-pbw-q5AeP5YXMo_h-tSQbjZKxtq3mkjbPbv-xJno4KzQf7Mb-4syPRU2xRnWnvWKfA-b4ncjRcTehoM3xI8LNj405OTbIFO0KJzJCcjqR8ZjjDbDj3P; BCLID_BFESS=10865121101061757521; BDSFRCVID_BFESS=3NKOJexroG0lbdODCaKvheZ7ZeKKg7jTDYrEZguiLEnlccDVJeC6EG0PtOqPGZu-EHtdogKKWmOTH7kF_2uxOjjg8UtVJeC6EG0Ptf8g0M5; H_BDCLCKID_SF_BFESS=tR3KB4jVKbnHKRopMtOhq4tehH4eWfn9WDTOQJ7TtnRDeh7eLPQv54k8WGJzKxni5bvB-pbw-q5AeP5YXMo_h-tSQbjZKxtq3mkjbPbv-xJno4KzQf7Mb-4syPRU2xRnWnvWKfA-b4ncjRcTehoM3xI8LNj405OTbIFO0KJzJCcjqR8ZjjDbDj3P; Hm_lvt_ad52b306e1ae4557f5d3534cce8f8bbf=1654668094; log_guid=fb683c961fe69444e15711cd3d719d21; log_chanel=pz; _j47_ka8_=57; ZX_UNIQ_UID=91ee11c9ab37f4925d490d22e560d9b5; BDUSS=VwSE1aWHFFclowdVFJfkxPWWtjQy0wT2JMVFVsRjl1VGZHbWxZcjE4eXp5TWRpRUFBQUFBJCQAAAAAAQAAAAEAAAAIq1NdAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAALM7oGKzO6BiZF; BDUSS_BFESS=VwSE1aWHFFclowdVFJfkxPWWtjQy0wT2JMVFVsRjl1VGZHbWxZcjE4eXp5TWRpRUFBQUFBJCQAAAAAAQAAAAEAAAAIq1NdAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAALM7oGKzO6BiZF; BDPPN=310c74cda1a4dc423a0b5b2f4d992c40; _t4z_qc8_=xlTM-TogKuTwx6sOO-9CgYGU%2A%2A9iVkoA6Amd; ab165466800=30b3b845f137707139d9103d6f73168616546682113e3; Hm_lpvt_ad52b306e1ae4557f5d3534cce8f8bbf=1654668212; ab_sr=1.0.1_NTZhOWNhMjc0ZDE5MzIxYTQ2NGQzNWIxNjk5YzU1NDMwZTA1YjYzY2U4OThiNjQ5ODM1ZGNlNWI0NDk4YTk0ZmNkYTJlMWNhM2E1Yjg5MjhiNDY1MWU3MzIzNzk1MTA1NTI2MDUzMDhkYzEyNDllYzYyOWQ5NjNmNDc5MjMwZjMzMWQ3Yzk1MjdkZGZjODc4YmQxYWRiMzEyNTM1OWQ1Zg==; _s53_d91_=93c39820170a0a5e748e1ac9ecc79371df45a908d7031a5e0e6df033fcc8068df8a85a45f59cb9faa0f164dd33ed0c727031a62f042c0f05bf7aa9b9ed9ab486b05601d82ef35f8ea40e9ddccd348e4f708567c976891408e9c194b8625647c023e5df08c02d25ffcccc0fe95f70fdefe4048a4de0b3544a9ae79c2056e0c4f695d25cd311e30dd5ca9c6b7689a4037dfb2f7a1168a67fafce24af8e741f4df26534084af900b8c846af1a49c5d1c52264e9244d6a27efe4e65a5b92aad8a9c25c63d5f0cfb526d146a229971b4dbf5ff14461c8c06033b24245a3a421d334f9; _y18_s21_=b6108456; RT=\"z=1&dm=baidu.com&si=koh2wvu8e3r&ss=l456npxx&sl=a&tt=tzu&bcn=https%3A%2F%2Ffclog.baidu.com%2Flog%2Fweirwood%3Ftype%3Dperf&ld=59e2\"")
        .removeHeader("User-Agent")
        .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36")
        .build();

    Call call = client.newCall(request);

    Response response = call.execute();

    String responseStr = response.body().string();

    System.out.println(responseStr);

    JsonElement jsonElement = JsonParser.parseString(responseStr);
    JsonElement data = jsonElement.getAsJsonObject().get("data");
    JsonElement queryList = data.getAsJsonObject().get("queryList");
    JsonArray asJsonArray = queryList.getAsJsonArray();
    for (JsonElement element : asJsonArray) {
      System.out.println(element.toString().replace("<em>", "").replace("</em>", ""));
    }


  }

  @SneakyThrows
  @Test
  public void test02() {

    System.out.println(URLDecoder.decode("%E5%AE%89%E5%BE%BD%E7%A7%91%E7%9B%BE", "utf-8"));

    System.out.println(URLEncoder.encode("合肥讯百", "utf-8"));

  }



  @SneakyThrows
  @Test
  public void test03() {

    OkHttpClient okHttpClient = new Builder().cookieJar(new CookieJar() {
      private final HashMap<String, List<Cookie>> cookieStore = new HashMap<String, List<Cookie>>();
      @Override
      public void saveFromResponse(@NotNull HttpUrl url, @NotNull List<Cookie> cookies) {
        cookieStore.put(url.host(), cookies);
      }
      @Override
      public List<Cookie> loadForRequest(@NotNull HttpUrl url) {
        List<Cookie> cookies = cookieStore.get(url.host());
        return cookies != null ? cookies : new ArrayList<Cookie>();
      }
    }).build();


    Request request = new Request.Builder().url("https://www.zhihu.com/#signin").build();
    okHttpClient.newCall(request).enqueue(new Callback() {
      @Override
      public void onFailure(@NotNull Call call, @NotNull IOException e) {
      }
      @Override
      public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        String resp = response.body().string();

        System.out.println(resp);

        Document parse = Jsoup.parse(resp);
        Elements select = parse.select("input[type=hidden]");
        Element element = select.get(0);
        String xsrf = element.attr("value");
        System.out.println(xsrf);
        //Message msg = mHandler.obtainMessage();
        //msg.what = 1;
        //msg.obj = xsrf;
        //Log.d("google_lenve_fb", "onResponse: xsrf:" + xsrf);
        //mHandler.sendMessage(msg);
      }
    });

    Thread.sleep(10000);
  }


}
