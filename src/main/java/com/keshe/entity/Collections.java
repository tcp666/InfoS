package com.keshe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @Description TODO  收藏表
 * @Author gyhdx
 * @Date 2020/5/26 22:15
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Collections {
    private String collectionId;
    private Date collectionTime;
    private String collectionStatus;
    private String messageId;
    private String userId;
}
