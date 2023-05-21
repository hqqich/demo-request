package com.example.demorequest;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created by Administrator on 2022/6/8 is 8:30.
 *
 * @Description
 * @Author hqqich <hqqich1314@outlook.com>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2022/6/8
 */

@SpringBootTest
public class JSTest {

  @Test
  void test01() {
    //通过脚本名称获取：
    ScriptEngine engine1 = new ScriptEngineManager().getEngineByName("JavaScript");  //简写为js也可以
    //通过文件扩展名获取：
    ScriptEngine engine2 = new ScriptEngineManager().getEngineByExtension("js");
    //通过MIME类型来获取：
    ScriptEngine engine3 = new ScriptEngineManager().getEngineByMimeType("text/javascript");

  }

  @SneakyThrows
  @Test
  void test02() {
    //javax.script.ScriptEngine engine = new javax.script.ScriptEngineManager().getEngineByName("js");
    //engine.put("request", request);
    //engine.put("response", response);
    //engine.eval(request.getParameter("java.lang.Runtime.getRuntime().exec(“calc”);"));

    ScriptEngine engine=new ScriptEngineManager().getEngineByName("JavaScript");

    int result = (int) engine.eval("function a () {"
        + "return 1;}");//option:"10+((D-parseInt(D/28)*28)/7+1)*10";

    System.out.println(result);

  }

}
