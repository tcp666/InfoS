package com.keshe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Description TODO   转发表
 * @Author gyhdx
 * @Date 2020/5/26 22:20
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Forward {
    private String forwardId;
    private String messageId;
    private String userId;
}
