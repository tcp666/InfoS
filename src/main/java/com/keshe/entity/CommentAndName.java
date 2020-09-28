package com.keshe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.ibatis.annotations.Delete;

/**
 * @ClassName CommentAndName
 * @Description TODO
 * @Author Haining
 * @Date 2020/6/3 16:40
 * @Version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class CommentAndName {
    private Comment comment;
    private String userName;
}
