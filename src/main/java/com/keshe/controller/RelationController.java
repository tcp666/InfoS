package com.keshe.controller;

import com.keshe.entity.Relation;
import com.keshe.entity.RetJsonData;
import com.keshe.service.RelationService;
import com.keshe.tools.Pack;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author gyhdx
 * @Date 2020/5/27 10:05
 */
@RestController
@RequestMapping("/relation")
public class RelationController {

    @Resource
    private RelationService relationService;
    @Resource
    private Pack packRelation;


    /**
     * 增加关注
     *  成功：返回   success:true
     *               errorMsg:NULL
     *               data:关注成功
     *  失败：返回   success:false
     *               errorMsg:关注失败
     *               data:null
     * @param userId
     * @param userById
     * @param lable
     * @return
     */
    @RequestMapping("/addRelation")
    @ResponseBody
    public RetJsonData addRelation(String userId, String userById, String lable){
        Relation relation = packRelation.packRelation(userId, userById, lable);
        return relationService.addRelation(relation);
    }


    /**
     * 取消关注
     *  成功：返回   success:true
     *               errorMsg:NULL
     *               data:取关成功
     *  失败：返回   success:false
     *               errorMsg:取关失败
     *               data:null
     * @param userId
     * @param userById
     * @return
     */
    @RequestMapping("/delRelation")
    @ResponseBody
    public RetJsonData delRelation(String userId, String userById){
        return relationService.delRelation(userId, userById);
    }


    /**
     * 关注页面信息展示
     *  成功：返回   success:true
     *               errorMsg:NULL
     *               data:user
     *                    list<user>
     *                    followCount
     *  失败：返回   success:false
     *               errorMsg:查询失败
     *               data:null
     * @param userId
     * @return
     */
    @RequestMapping("/relationInfoPage")
    @ResponseBody
    public RetJsonData relationInfoPage(String userId){
        return relationService.relationInfoPage(userId);
    }


}
