package com.keshe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName Video
 * @Description TODO  电影表
 * @Author Haining
 * @Date 2020/5/27 9:06
 * @Version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Video {
    private String videoId;
    private String messageId;
    private String videoUrl;
}
