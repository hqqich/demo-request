package com.example.demorequest.httpbin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

/**
 * Created by Administrator on 2022/6/8 is 11:38.
 *
 * @Description
 * @Author hqqich <hqqich1314@outlook.com>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2022/6/8
 */

public class Images {

    public static final MediaType TYPE_MEDIA_JPEG = MediaType.parse("image/jpeg");
    public static final MediaType TYPE_MEDIA_WEBP = MediaType.parse("image/webp");
    public static final MediaType TYPE_MEDIA_SVG = MediaType.parse("image/svg+xml");
    public static final MediaType TYPE_MEDIA_PNG = MediaType.parse("image/PNG");
    public static final MediaType TYPE_MEDIA_IMAGE_ALL = MediaType.parse("image/*");

    @Test
    @SneakyThrows
    public void image() {

        OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(5, TimeUnit.SECONDS)
            .build();

        Request request = new Request.Builder()
            .get()
            .url("https://httpbin.org/image")
            .addHeader("accept", "image/PNG")
            .build();

        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

/*
                //图片使用byte[]
                byte[] bytes = response.body().bytes();
                try(ImageOutputStream imageOutputStream = new FileImageOutputStream(new File("a.png"));) {
                    imageOutputStream.write(bytes);
                }
*/



                //文件使用流
                InputStream inputStream = null;
                if (response.body() != null) {
                    inputStream = response.body().byteStream();
                }

                FileOutputStream fileOutputStream = new FileOutputStream(new File("a.webp"));

                byte[] b = new byte[1024];
                while ((inputStream.read(b)) != -1) {
                    fileOutputStream.write(b);
                }
                inputStream.close();
                fileOutputStream.close();
            }
        });
        Thread.sleep(10000);
    }
}
