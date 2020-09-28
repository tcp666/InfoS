package com.keshe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;


/**
 * @Description TODO  评论表
 * @Author gyhdx
 * @Date 2020/5/26 22:18
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Comment {
    private String commentId;
    private String commentInfo;
    private Date commentTime;
    private String messageId;
    private String userId;
}
