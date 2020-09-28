package com.keshe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @ClassName Message
 * @Description TODO   动态表
 * @Author Haining
 * @Date 2020/5/27 8:36
 * @Version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Message {
    private String messageId;
    private String userId;
    private String messageLable;
    private String messageInfo;
    private Date messageCtime;
    private String messageState;
    private String messageCollectNum;
    private String messageCommentNum;
    private String messageTranspondNum;
    private String messageAgreeNum;
    private String messageReadNum;
}
