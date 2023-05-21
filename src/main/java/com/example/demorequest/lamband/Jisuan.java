package com.example.demorequest.lamband;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by Administrator on 2022/6/7 is 14:17.
 *
 * @Description
 * @Author hqqich <hqqich1314@outlook.com>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2022/6/7
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Jisuan {

  private int number;

  public Jisuan(Add add, int a, int b) {
    System.out.println("===");
    number = add.add(a, b);
  }
}
