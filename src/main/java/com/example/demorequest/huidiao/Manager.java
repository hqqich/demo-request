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

public class Manager implements Task{

  private Employee employee;

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public void doOther() {
    System.out.println("项目经理去做别的了");
  }

  public void doOtherContinue() {
    System.out.println("项目经理收到员工信息后继续去做别的了");
  }

  //这是整个小项目需要做的事
  public void setTask(){
    System.out.println("经理给员工布置任务");
    doOther();
    //员工完成经理布置的任务(含有回调方法)
    employee.doTask(this);
    doOtherContinue();
  }

  @Override
  public void callback(String result) {
    System.out.println("收到员工处理结果" +result);
  }
}

