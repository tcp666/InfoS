package com.keshe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @ClassName MessageAndAllInfo
 * @Description TODO  动态的所有数据
 * @Author Haining
 * @Date 2020/5/30 23:33
 * @Version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class MessageAndAllInfo {
    private Message message;
    private List<Img> imgs;
    private Video video;
    private User user;
}
