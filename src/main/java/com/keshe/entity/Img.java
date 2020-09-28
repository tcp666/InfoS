package com.keshe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Description TODO  图片表
 * @Author gyhdx
 * @Date 2020/5/26 22:21
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Img {
    private String imgId;
    private String messageId;
    private String imgUrl;
}
