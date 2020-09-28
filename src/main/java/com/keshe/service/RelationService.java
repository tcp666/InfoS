package com.keshe.service;

import com.keshe.entity.Relation;
import com.keshe.entity.RetJsonData;

/**
 * @InterfaceName RelationService
 * @Description TODO
 * @Author Haining
 * @Date 2020/5/27 9:38
 * @Version 1.0
 */
public interface RelationService {

    /**
     * 增加关注，并增加粉丝数
     * @param relation
     * @return
     */
    RetJsonData addRelation(Relation relation);


    /**
     * 取消关注，并减少粉丝数
     * @param userId
     * @param userById
     * @return
     */
    RetJsonData delRelation(String userId, String userById);


    /**
     * 返回关注页面的信息
     * @param userId
     * @return
     */
    RetJsonData relationInfoPage(String userId);
}
