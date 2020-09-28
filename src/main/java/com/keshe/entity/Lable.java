package com.keshe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Description TODO  动态标签表
 * @Author gyhdx
 * @Date 2020/5/26 22:23
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Lable {
    private String lableId;
    private String messageId;
    private String lableInfo;
}
