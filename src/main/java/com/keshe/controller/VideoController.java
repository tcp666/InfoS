package com.keshe.controller;

import com.keshe.entity.RetJsonData;
import com.keshe.service.VideoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author gyhdx
 * @Date 2020/5/27 10:09
 */
@RestController
@RequestMapping("/video")
public class VideoController {

    @Resource
    private VideoService videoService;


    /**
     * 返回视频界面地数据
     *  成功：返回   success:true
     *               errorMsg:NULL
     *               data:user
     *                    video
     *  失败：返回   success:false
     *               errorMsg:查询失败
     *               data:null
     * @param type
     * @return
     */
    @RequestMapping("/videoInfoPage")
    @ResponseBody
    public RetJsonData videoInfoPage(String type){
        return videoService.videoInfoPage(type);
    }
}
