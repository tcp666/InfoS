package com.keshe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @ClassName Reply
 * @Description TODO  回复表
 * @Author Haining
 * @Date 2020/5/27 8:51
 * @Version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Reply {
    private String replyId;
    private String replyUserId;
    private String replyByUserId;
    private String replyInfo;
    private String commentParent;
    private Date replyTime;
}
