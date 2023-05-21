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
 * Created by Administrator on 2022/6/8 is 10:23.
 *
 * @Description
 * @Author hqqich <hqqich1314@outlook.com>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2022/6/8
 */
public class RequestInspection {

  @Test
  @SneakyThrows
  public void headers() {

    OkHttpClient client = new OkHttpClient().newBuilder()
        .readTimeout(5, TimeUnit.SECONDS)
        .build();

    Request request = new Request.Builder()
        .get()
        .url("https://httpbin.org/headers")
        .header("Cookie", "BAIDUID=4A732383315F3BA346439D8A855029F4:FG=1; BIDUPSID=49136FFE2E7933D26F85530DD41AA7AF; PSTM=1603861310; BDUSS=FuWX5ZMklYVTlXdE1mYmJ6aC0tQld2SXZsLXBzbFlQcUxzQ2dtNGMzeGdhQVpnSVFBQUFBJCQAAAAAAAAAAAEAAADRFaFnwrdGTFnCtwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAGDb3l9g295fY3; __yjs_duid=1_bb5ca929c95f5af9a8e12b8d08dca1241618319196404; BDPPN=4a2043024320e36ac808460e43ccbf71; _j54_6ae_=xlTM-TogKuTw7qYy65QEBZ9XiCn2kwXqXAmd; Hm_lvt_ad52b306e1ae4557f5d3534cce8f8bbf=1634478498; log_guid=27896b6bfdb00aa5d5f8c8e21602851b; MCITY=-%3A; Hm_lpvt_ad52b306e1ae4557f5d3534cce8f8bbf=1634478498; BDSFRCVID=OQ8OJexroG04sP3HFtumeOjHae5vjP3TDYLEOwXPsp3LGJLVgdlaEG0PtDofHUI-_wijogKKWmOTH7kF_2uxOjjg8UtVJeC6EG0Ptf8g0M5; H_BDCLCKID_SF=tJ4O_I0ytDvDqTrP-trf5DCShUFsWTcRB2Q-XPoO3KtMqUtRyMjqbR08XU6754LtbDQToMbgylRp8P3y0bb2DUA1y4vpK-3QKmTxoUJ23n7tOM5VqqnbMtIebPRiL-r9Qg-fBhQ7tt5W8ncFbT7l5hKpbt-q0x-jLTnhVn0MBCK0bKtmDjthDjPVKgTa54cbb4o2WbCQfRcT8pcN2b5oQTO3QtJnBU5--Rb-2bcdKR5vOnck0qOUWfA3XpJvQnJjt2JxaqRC5hkbhh5jDh3MQhD9Qh3We4jWW26y0hvcQb6cShP6MUjrDRLbXU6BK5vPbNcZ0l8K3l02V-bIe-t2XjQhea0JJ6-ttR3aQ5rtKRTffjrnhPF3y580XP6-hnjy3b4JKl3KWpQqqDnNXP61-tLUyU6XQl3RymJ42-39LPO2hpRjyxv4bUnWD-oxJpOJ3NrgKPJoHRoOOpjvbURv3Pug3-AfaM5dtjTO2bc_5KnlfMQ_bf--QfbQ0hOhqP-jBRIEoCIbJC_5bDIrKPt_bP4O-fObhC62aKDsVnnpBhcqEIL4ej5ZbfjBy-jJ-4r-BKQ73RKatCohVfbSj4QoX58by-4fK6Q43GQJonO4-p5nhMJFXj7JDMP0-xoQ-fcy523i-b6vQpPbjpQ3DRoWXPIqbN7P-p5Z5mAqKl0MLPbtbb0xXj_0D6ObjatOJjna--oa3RTeb6rjDnCrXpLWXUI82h5y058J-a4eapQ2bR6q8ncS-t_h54IrDnORXx74B5vvbPOMthRnOlRKbPOS-fL1Db3J0PRhWNTAsx752Rcoepvo3Pcc3MvWMPjdJJQOBKQB0KnGbUQkeq8CQft20b0EeMtjW6LEK5r2SC_5fC0K3D; H_PS_PSSID=36546_36464_36454_31660_34812_36421_36165_36487_36570_26350_36299_36469_22158; delPer=0; PSINO=5; BA_HECTOR=8h2gak210h0k8k04ag1h9t7jh15; ZFY=Ajx8dY5tnhPSO5:AOJ5l:B8N5UKkPm0lAbHwtKnSz4eH0:C; RT=\"z=1&dm=baidu.com&si=vu2yy9ghv2b&ss=l43mg5hz&sl=8&tt=ff5&bcn=https%3A%2F%2Ffclog.baidu.com%2Flog%2Fweirwood%3Ftype%3Dperf&ld=2bsmp\"; ZX_UNIQ_UID=b0ae21260b8d7c3400fadf5e3ab8c2ac; ab165457440=e3350103e49d56b6fa65c5adf199377716545775844c4;")
        .header("Referer", "https://aiqicha.baidu.com/yuqing/latestlyricallist")
        .header("Accept-Encoding", "")
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
  public void ip() {

    OkHttpClient client = new OkHttpClient().newBuilder()
        .readTimeout(5, TimeUnit.SECONDS)
        .build();

    Request request = new Request.Builder()
        .get()
        .url("https://httpbin.org/ip")
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
  public void userAgent() {

    OkHttpClient client = new OkHttpClient().newBuilder()
        .readTimeout(5, TimeUnit.SECONDS)
        .build();

    Request request = new Request.Builder()
        .get()
        .url("https://httpbin.org/user-agent")
        .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.61 Safari/537.36")
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
