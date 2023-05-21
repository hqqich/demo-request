package com.example.demorequest.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2022/6/7 is 9:02.
 *
 * @Description
 * @Author hqqich <hqqich1314@outlook.com>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2022/6/7
 */

@Component
public class GsonConfig {

  @Bean
  public Gson gson() {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.setDateFormat("yyyy-MM-dd");
    gsonBuilder.serializeNulls();
    gsonBuilder.setPrettyPrinting();
    gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
    return gsonBuilder.create();
  }

}
