package com.keshe.controller;

import com.keshe.entity.Collections;
import com.keshe.entity.RetJsonData;
import com.keshe.service.CollectionsService;
import com.keshe.tools.Pack;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author gyhdx
 * @Date 2020/5/27 9:57
 */
@RestController
@RequestMapping("/collection")
public class CollectionsController {

    @Resource
    private CollectionsService collectionsService;
    @Resource
    private Pack packCollection;


    /**
     * 查询所有收藏
     *  成功：返回   success:true
     *               data:list<collection>
     *               errorMsg:null
     *  失败：返回   success:false
     *               errorMsg:查询失败
     *               data:null
     * @return
     */
    @RequestMapping("/getAllCollection")
    @ResponseBody
    public RetJsonData getAllCollection(){
        return collectionsService.getAllCollection();
    }


     /**
     * 新增收藏
     *  成功：返回   success:true
     *               data:收藏成功
     *               errorMsg:null
     *  失败：返回   success:false
     *               errorMsg:收藏失败/已被收藏
     *               data:null
     * @param userId
     * @param messageId
     * @return
     */
    @RequestMapping("/addCollection")
    @ResponseBody
    public RetJsonData addCollection(String userId, String messageId){
        Collections collections = packCollection.packCollection(userId, messageId);
        return collectionsService.addCollection(collections);
    }



    /**
     * 删除收藏
     *  成功：返回   success:true
     *               data:删除成功
     *               errorMsg:null
     *  失败：返回   success:false
     *               errorMsg:删除失败
     *               data:null
     * @param userId
     * @param messageId
     * @return
     */
    @RequestMapping("/delCollection")
    @ResponseBody
    public RetJsonData delCollection(String userId, String messageId){
        return collectionsService.delCollection(userId, messageId);
    }



    /**
     * 收藏页面的数据展示
     *  成功：返回   success:true
     *               errorMsg:NULL
     *               data:当前user
     *                        message：
     *                              user
     *                              message
     *                              imgs
     *                              video
     *  失败：返回   success:false
     *               errorMsg:修改失败
     *               data:null
     * @param userId
     * @return
     */
    @RequestMapping("/collectionInfoPage")
    @ResponseBody
    public RetJsonData collectionInfoPage(String userId){
        return collectionsService.collectionInfoPage(userId);
    }
}
