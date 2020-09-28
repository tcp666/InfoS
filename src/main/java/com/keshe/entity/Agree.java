package com.keshe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Description TODO  点赞表
 * @Author gyhdx
 * @Date 2020/5/26 22:14
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Agree {
      private String agreeId;
      private String messageId;
      private String userId;
}
