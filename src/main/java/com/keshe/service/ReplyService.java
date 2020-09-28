package com.keshe.service;

import com.keshe.entity.RetJsonData;

/**
 * @InterfaceName ReplyService
 * @Description TODO
 * @Author Haining
 * @Date 2020/5/27 9:38
 * @Version 1.0
 */
public interface ReplyService {


    /**
     * 新增回复
     * @return
     */
    RetJsonData saveReply(String userId, String parentId, String replyInfo);


    /**
     * 根据返回的Id进行查询回复
    * @param parentId
     * @return
     */
    RetJsonData getReplysById(String parentId);
}
