package com.example.demorequest;

import com.example.demorequest.lamband.Jisuan;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.net.CookieManager;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.SneakyThrows;
import okhttp3.Call;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created by Administrator on 2022/6/7 is 11:57.
 *
 * @Description
 * @Author hqqich <hqqich1314@outlook.com>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2022/6/7
 */

@SpringBootTest
public class AQCTest {

  @Autowired
  private Gson gson;

  @SneakyThrows
  @Test
  void test01() {

    //挂上代理
    OkHttpClient.Builder builder = new OkHttpClient.Builder();
    builder.connectTimeout(1, TimeUnit.MINUTES);
    Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890));
    builder.proxy(proxy);
    OkHttpClient okHttpClient = builder.cookieJar(new JavaNetCookieJar(new CookieManager())).build();

    Request request = new Request.Builder()
        .url("https://aiqicha.baidu.com/yuqing/latestLyricalAjax?p=2&type=list")
        .header("Cookie", "BAIDUID=4A732383315F3BA346439D8A855029F4:FG=1; BIDUPSID=49136FFE2E7933D26F85530DD41AA7AF; PSTM=1603861310; BDUSS=FuWX5ZMklYVTlXdE1mYmJ6aC0tQld2SXZsLXBzbFlQcUxzQ2dtNGMzeGdhQVpnSVFBQUFBJCQAAAAAAAAAAAEAAADRFaFnwrdGTFnCtwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAGDb3l9g295fY3; __yjs_duid=1_bb5ca929c95f5af9a8e12b8d08dca1241618319196404; BDPPN=4a2043024320e36ac808460e43ccbf71; _j54_6ae_=xlTM-TogKuTw7qYy65QEBZ9XiCn2kwXqXAmd; Hm_lvt_ad52b306e1ae4557f5d3534cce8f8bbf=1634478498; log_guid=27896b6bfdb00aa5d5f8c8e21602851b; MCITY=-%3A; Hm_lpvt_ad52b306e1ae4557f5d3534cce8f8bbf=1634478498; BDSFRCVID=OQ8OJexroG04sP3HFtumeOjHae5vjP3TDYLEOwXPsp3LGJLVgdlaEG0PtDofHUI-_wijogKKWmOTH7kF_2uxOjjg8UtVJeC6EG0Ptf8g0M5; H_BDCLCKID_SF=tJ4O_I0ytDvDqTrP-trf5DCShUFsWTcRB2Q-XPoO3KtMqUtRyMjqbR08XU6754LtbDQToMbgylRp8P3y0bb2DUA1y4vpK-3QKmTxoUJ23n7tOM5VqqnbMtIebPRiL-r9Qg-fBhQ7tt5W8ncFbT7l5hKpbt-q0x-jLTnhVn0MBCK0bKtmDjthDjPVKgTa54cbb4o2WbCQfRcT8pcN2b5oQTO3QtJnBU5--Rb-2bcdKR5vOnck0qOUWfA3XpJvQnJjt2JxaqRC5hkbhh5jDh3MQhD9Qh3We4jWW26y0hvcQb6cShP6MUjrDRLbXU6BK5vPbNcZ0l8K3l02V-bIe-t2XjQhea0JJ6-ttR3aQ5rtKRTffjrnhPF3y580XP6-hnjy3b4JKl3KWpQqqDnNXP61-tLUyU6XQl3RymJ42-39LPO2hpRjyxv4bUnWD-oxJpOJ3NrgKPJoHRoOOpjvbURv3Pug3-AfaM5dtjTO2bc_5KnlfMQ_bf--QfbQ0hOhqP-jBRIEoCIbJC_5bDIrKPt_bP4O-fObhC62aKDsVnnpBhcqEIL4ej5ZbfjBy-jJ-4r-BKQ73RKatCohVfbSj4QoX58by-4fK6Q43GQJonO4-p5nhMJFXj7JDMP0-xoQ-fcy523i-b6vQpPbjpQ3DRoWXPIqbN7P-p5Z5mAqKl0MLPbtbb0xXj_0D6ObjatOJjna--oa3RTeb6rjDnCrXpLWXUI82h5y058J-a4eapQ2bR6q8ncS-t_h54IrDnORXx74B5vvbPOMthRnOlRKbPOS-fL1Db3J0PRhWNTAsx752Rcoepvo3Pcc3MvWMPjdJJQOBKQB0KnGbUQkeq8CQft20b0EeMtjW6LEK5r2SC_5fC0K3D; H_PS_PSSID=36546_36464_36454_31660_34812_36421_36165_36487_36570_26350_36299_36469_22158; delPer=0; PSINO=5; BA_HECTOR=8h2gak210h0k8k04ag1h9t7jh15; ZFY=Ajx8dY5tnhPSO5:AOJ5l:B8N5UKkPm0lAbHwtKnSz4eH0:C; RT=\"z=1&dm=baidu.com&si=vu2yy9ghv2b&ss=l43mg5hz&sl=8&tt=ff5&bcn=https%3A%2F%2Ffclog.baidu.com%2Flog%2Fweirwood%3Ftype%3Dperf&ld=2bsmp\"; ZX_UNIQ_UID=b0ae21260b8d7c3400fadf5e3ab8c2ac; ab165457440=e3350103e49d56b6fa65c5adf199377716545775844c4;")
        .header("Referer", "https://aiqicha.baidu.com/yuqing/latestlyricallist")
        .removeHeader("User-Agent")
        .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36")
        .build();
    Call call = okHttpClient.newCall(request);
    Response execute = call.execute();
    String result = execute.body().string();
    //System.out.println(unicodeToString(result));

    JsonElement jsonElement = JsonParser.parseString(result);
    //System.out.println(jsonElement);
    JsonElement data = jsonElement.getAsJsonObject().get("data");
    JsonArray datas = data.getAsJsonObject().getAsJsonArray("datas");

    for (JsonElement element : datas) {
      //System.out.println(element);
      System.out.println("标题："+element.getAsJsonObject().get("topicTitle"));
      System.out.println("公司名称："+element.getAsJsonObject().get("companyName"));
      System.out.println("内容："+element.getAsJsonObject().get("topicContent"));
      //System.out.println(element.getAsJsonObject().get("topicFrom"));
      //System.out.println(element.getAsJsonObject().get("imgList"));
      //System.out.println(element.getAsJsonObject().get("jumpUrl"));

      String jumpUrl = "https://aiqicha.baidu.com" + element.getAsJsonObject().get("jumpUrl").toString().replace("\"", "");

      test03(jumpUrl);

      //https://aiqicha.baidu.com/
      //System.out.println(element.getAsJsonObject().get("keywords"));

      //Document htmlDocument = Jsoup.connect(jumpUrl).get();
      //System.out.println(htmlDocument.text());
      //System.out.println("======");
      //Elements allElements = htmlDocument.getAllElements();
      //System.out.println(allElements);
      //Elements select = allElements.select("script");
      //String dataStr = select.get(0).data();
      //System.out.println(dataStr);



      System.out.println("=================================================================================");
    }


  }

  //@SneakyThrows
  //@Test
  void test03(String url) throws Exception {
    try {
      url = url.replace("/m", "");
      System.out.println(url);
      //Thread.sleep(1000);
      OkHttpClient.Builder builder = new OkHttpClient.Builder();
      builder.connectTimeout(1, TimeUnit.MINUTES);
      Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890));
      builder.proxy(proxy);
      OkHttpClient okHttpClient = builder.cookieJar(new JavaNetCookieJar(new CookieManager())).build();

      Request request = new Request.Builder()
          //.url("https://aiqicha.baidu.com/yuqing?yuqingId=4956e873f0c06c38b911ec8d332898c4&type=report")
          .url(url)
          .header("Cookie", "BAIDUID=4A732383315F3BA346439D8A855029F4:FG=1; BIDUPSID=49136FFE2E7933D26F85530DD41AA7AF; PSTM=1603861310; BDUSS=FuWX5ZMklYVTlXdE1mYmJ6aC0tQld2SXZsLXBzbFlQcUxzQ2dtNGMzeGdhQVpnSVFBQUFBJCQAAAAAAAAAAAEAAADRFaFnwrdGTFnCtwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAGDb3l9g295fY3; __yjs_duid=1_bb5ca929c95f5af9a8e12b8d08dca1241618319196404; BDPPN=4a2043024320e36ac808460e43ccbf71; _j54_6ae_=xlTM-TogKuTw7qYy65QEBZ9XiCn2kwXqXAmd; Hm_lvt_ad52b306e1ae4557f5d3534cce8f8bbf=1634478498; log_guid=27896b6bfdb00aa5d5f8c8e21602851b; MCITY=-%3A; Hm_lpvt_ad52b306e1ae4557f5d3534cce8f8bbf=1634478498; BDSFRCVID=OQ8OJexroG04sP3HFtumeOjHae5vjP3TDYLEOwXPsp3LGJLVgdlaEG0PtDofHUI-_wijogKKWmOTH7kF_2uxOjjg8UtVJeC6EG0Ptf8g0M5; H_BDCLCKID_SF=tJ4O_I0ytDvDqTrP-trf5DCShUFsWTcRB2Q-XPoO3KtMqUtRyMjqbR08XU6754LtbDQToMbgylRp8P3y0bb2DUA1y4vpK-3QKmTxoUJ23n7tOM5VqqnbMtIebPRiL-r9Qg-fBhQ7tt5W8ncFbT7l5hKpbt-q0x-jLTnhVn0MBCK0bKtmDjthDjPVKgTa54cbb4o2WbCQfRcT8pcN2b5oQTO3QtJnBU5--Rb-2bcdKR5vOnck0qOUWfA3XpJvQnJjt2JxaqRC5hkbhh5jDh3MQhD9Qh3We4jWW26y0hvcQb6cShP6MUjrDRLbXU6BK5vPbNcZ0l8K3l02V-bIe-t2XjQhea0JJ6-ttR3aQ5rtKRTffjrnhPF3y580XP6-hnjy3b4JKl3KWpQqqDnNXP61-tLUyU6XQl3RymJ42-39LPO2hpRjyxv4bUnWD-oxJpOJ3NrgKPJoHRoOOpjvbURv3Pug3-AfaM5dtjTO2bc_5KnlfMQ_bf--QfbQ0hOhqP-jBRIEoCIbJC_5bDIrKPt_bP4O-fObhC62aKDsVnnpBhcqEIL4ej5ZbfjBy-jJ-4r-BKQ73RKatCohVfbSj4QoX58by-4fK6Q43GQJonO4-p5nhMJFXj7JDMP0-xoQ-fcy523i-b6vQpPbjpQ3DRoWXPIqbN7P-p5Z5mAqKl0MLPbtbb0xXj_0D6ObjatOJjna--oa3RTeb6rjDnCrXpLWXUI82h5y058J-a4eapQ2bR6q8ncS-t_h54IrDnORXx74B5vvbPOMthRnOlRKbPOS-fL1Db3J0PRhWNTAsx752Rcoepvo3Pcc3MvWMPjdJJQOBKQB0KnGbUQkeq8CQft20b0EeMtjW6LEK5r2SC_5fC0K3D; H_PS_PSSID=36546_36464_36454_31660_34812_36421_36165_36487_36570_26350_36299_36469_22158; delPer=0; PSINO=5; BA_HECTOR=8h2gak210h0k8k04ag1h9t7jh15; ZFY=Ajx8dY5tnhPSO5:AOJ5l:B8N5UKkPm0lAbHwtKnSz4eH0:C; RT=\"z=1&dm=baidu.com&si=vu2yy9ghv2b&ss=l43mg5hz&sl=8&tt=ff5&bcn=https%3A%2F%2Ffclog.baidu.com%2Flog%2Fweirwood%3Ftype%3Dperf&ld=2bsmp\"; ZX_UNIQ_UID=b0ae21260b8d7c3400fadf5e3ab8c2ac; ab165457440=e3350103e49d56b6fa65c5adf199377716545775844c4;")
          .header("Referer", "https://aiqicha.baidu.com/yuqing/latestlyricallist")
          .removeHeader("User-Agent")
          .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36")
          .build();
      Call call = okHttpClient.newCall(request);
      Response execute = call.execute();
      String result = execute.body().string();
      //System.out.println(result);

      String regex = "window\\.pageData.*]\\};";

      Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
      Matcher matcher = pattern.matcher(result);

      while (matcher.find()) {

        String replace = matcher.group(0);

        //System.out.println(replace);

        regex = "\\{.*}";
        Matcher matcher1 = Pattern.compile(regex, Pattern.MULTILINE).matcher(replace);
        while (matcher1.find()) {
          String group = matcher1.group(0);
          //System.out.println(group);
          Map<String, Object> items = gson.fromJson(group, new TypeToken<Map<String, Object>>() {}.getType());

          String s = String.valueOf(((Map<String, Object>) items.get("result")).get("introduce"));
          System.out.println("详细內容："+s.replaceAll("<p.*/p>", ""));
        }




        //System.out.println("Full match: " + matcher.group(0));
        //
        //for (int i = 1; i <= matcher.groupCount(); i++) {
        //  System.out.println("Group " + i + ": " + matcher.group(i));
        //}
      }
    } catch (Exception e) {
      System.out.println("异常");
    }
  }


  /**
   * @param str unicode字符串
   * @return 中文的字符串
   */
  public static String unicodeToString(String str) {
    Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
    Matcher matcher = pattern.matcher(str);
    char ch;
    while (matcher.find()) {
      ch = (char) Integer.parseInt(matcher.group(2), 16);
      str = str.replace(matcher.group(1), ch+"" );
    }
    return str;
  }

  @Test
  void test02() {
    //new Thread(() -> {
    //  System.out.println("----");
    //}).start();
    System.out.println(new Jisuan((a, b) -> (a+b) * (a-b), 5, 3).getNumber());
  }



}
