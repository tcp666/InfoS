package com.keshe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @ClassName Relation
 * @Description TODO  关注表
 * @Author Haining
 * @Date 2020/5/27 8:46
 * @Version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Relation {
    private String relationId;
    private Date relationTime;
    private String relationType;
    private String userId;
    private String userById;
}
