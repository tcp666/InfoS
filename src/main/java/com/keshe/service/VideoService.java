package com.keshe.service;

import com.keshe.entity.RetJsonData;
import com.keshe.entity.Video;


/**
 * @InterfaceName VideoService
 * @Description TODO
 * @Author Haining
 * @Date 2020/5/27 9:40
 * @Version 1.0
 */
public interface VideoService {

    /**
     * 根据传入的类型返回video
     * 填充video页面
     * @param type
     * @return
     */
    RetJsonData videoInfoPage(String type);

    Video videoByMessageId(String messageId);
}
