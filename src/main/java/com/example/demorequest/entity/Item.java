package com.example.demorequest.entity;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by Administrator on 2022/6/7 is 10:09.
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
public class Item {

  private long feed_id;

  private String news_id;

  private String uid;

  private String type;

  private long publish_time;

  private int from;

  private int comment_count;

  private int repost_count;

  private int digg_count;

  private int share_count;

  private int is_repost;

  private String latitude;

  private String longitude;

  private int recommend_time;

  private int click_count;

  private int has_digg;

  private int has_follow;

  private int is_audit;

  private String otherSourceInfo;

  private long rankIndex;

  @SerializedName("feed_data")
  private FeedData feed_data;

  private List<String> tag;

  private int IsFavor;

  private int index;

}
