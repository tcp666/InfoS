package com.keshe.service;

import com.keshe.entity.Collections;
import com.keshe.entity.RetJsonData;

/**
 * @InterfaceName CollectionsService
 * @Description TODO
 * @Author Haining
 * @Date 2020/5/27 9:36
 * @Version 1.0
 */
public interface CollectionsService {


    /**
     * 获取全部收藏
     * @return
     */
    RetJsonData getAllCollection();


    /**
     * 新增收藏
     * 如果已经被收藏则返回已被收藏
     * @param collections
     * @return
     */
    RetJsonData addCollection(Collections collections);


    /**
     * 删除收藏
     * @param userId
     * @param messageId
     * @return
     */
    RetJsonData delCollection(String userId, String messageId);


    /**
     * 根据传入的用户Id返回用户收藏列表

     * @param userId
     * @return
     */
    RetJsonData collectionInfoPage(String userId);



}
