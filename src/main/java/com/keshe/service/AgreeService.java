package com.keshe.service;

import com.keshe.entity.Agree;
import com.keshe.entity.RetJsonData;

/**
 * @InterfaceName AgreeService
 * @Description TODO
 * @Author Haining
 * @Date 2020/5/27 9:35
 * @Version 1.0
 */
public interface AgreeService {

    /**
     * 新增点赞
     * @param agree
     * @return
     */
    RetJsonData addAgree(Agree agree);


    /**
     * 取消点赞
     * @param userId
     * @param messageId
     * @return
     */
    RetJsonData redAgree(String userId, String messageId);
}
