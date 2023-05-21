import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

import com.example.demorequest.entity.People;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.Test;

/**
 * Created by Administrator on 2022/6/9 is 9:10.
 *
 * @Description
 * @Author hqqich <hqqich1314@outlook.com>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2022/6/9
 */


public class CodeTest {

  OkHttpClient client = new OkHttpClient().newBuilder()
      .readTimeout(5, TimeUnit.SECONDS)
      .build();

  @Test
  @SneakyThrows
  public void test01() {

    Request request = new Request.Builder()
        .get()
        .url("https://httpbin.org/status/304")
        .build();

    Call call = client.newCall(request);

    try(Response execute = call.execute();) {
      System.out.println(execute.code());
    }
  }

  @Test
  public void test02() {

    List<People> peopleList = new ArrayList<People>(){{
      add(new People(13));
      add(new People(14));
      add(new People(11));
      add(new People(12));
      add(new People(12));
    }};


    // 去重 + 排序
    List<People> tempList = peopleList.stream().collect(
        collectingAndThen(
            //toCollection(() -> new TreeSet<>(comparing(People::getAge))), ArrayList::new)
            toCollection(() -> new TreeSet<>(comparing(n -> n.getAge()))), ArrayList::new)
    );

    System.out.println(tempList);
  }

}
