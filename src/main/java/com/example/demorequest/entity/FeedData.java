package com.example.demorequest.entity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by Administrator on 2022/6/7 is 10:21.
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
public class FeedData {

  private List<String> companyList;

  private String content;

  //private List<String> entityList;

  private List<Links> links;

  private List<String> newTags;

  private List<String> pic_sizes;

  private List<String> pics;

  private SourceInfo sourceInfo;

  private List<String> topics;

  private String type;

}
