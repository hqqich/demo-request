package com.example.demorequest.huidiao;

/**
 * Created by Administrator on 2022/6/7 is 14:09.
 *
 * @Description
 * @Author hqqich <hqqich1314@outlook.com>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2022/6/7
 */

public class Employee {

  public void doTask(Task task){
    try {
      System.out.println("Employee开始执行任务");
      //模拟情景是员工完成任务花费了5秒钟
      Thread.sleep(5000);
      System.out.println("Employee处理完这项任务了");
      //员工干完活后通知经理任务已完成
      task.callback("finish");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
