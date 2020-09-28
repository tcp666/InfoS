package com.keshe.controller;

import com.keshe.entity.RetJsonData;
import com.keshe.service.MessageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description TODO
 * @Author gyhdx
 * @Date 2020/5/27 10:04
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Resource
    private MessageService messageService;

    /**
     * 删除动态（个人页面）
     *  成功：返回   success:true
     *               errorMsg:NULL
     *               data:删除成功
     *  失败：返回   success:false
     *               errorMsg:删除失败/该动态不存在
     *               data:null
     * @param messageId
     * @return
     */
    @RequestMapping("/delPersonalMessage")
    @ResponseBody
    public RetJsonData delPersonalMessage(String messageId){
        return messageService.delPersonalMessage(messageId);
    }


    /**
     * 发表动态（包含图片）
     *  成功：返回   success:true
     *               errorMsg:NULL
     *               data:发表成功
     *  失败：返回   success:false
     *               errorMsg:存储图片错误/动态查找失败/动态存储失败
     *               data:null
     * @param files
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/saveMessageOnImg")
    public RetJsonData saveMessageOnImg(MultipartFile[] files, HttpServletRequest request) throws Exception {
        String userId = request.getParameter("userId");
        String lable = request.getParameter("lable");
        String messageInfo = request.getParameter("messageInfo");
//        System.out.println(messageInfo);
        if (messageInfo != null){
            return messageService.saveMessageOnImg(files, userId, messageInfo, lable);
        }
        return new RetJsonData(false, "信息为空");
    }


    /**
     * 发表动态（包含视频）
     *  成功：返回   success:true
     *               errorMsg:NULL
     *               data:发表成功
     *  失败：返回   success:false
     *               errorMsg:视频存储失败/动态查找失败/动态存储失败
     *               data:null
     * @param file
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/saveMessageOnVideo")
    @ResponseBody
    public RetJsonData saveMessageOnVideo(MultipartFile file, HttpServletRequest request) throws Exception {
        String userId = request.getParameter("userId");
        String lable = request.getParameter("lable");
        String messageInfo = request.getParameter("messageInfo");
//        System.out.println(lable);
        if (messageInfo != null){
            return messageService.saveMessageOnVideo(file, userId, messageInfo, lable);
        }
        return new RetJsonData(false, "信息为空");

    }


    /**
     *返回主页信息
     * @param userId
     * @return
     */
    @RequestMapping("/messageInfoPage")
    @ResponseBody
    public RetJsonData messageInfoPage(String userId){
        return messageService.messageInfoPage(userId);
    }


    /**
     * 获取单个message信息
     *  成功：返回   success:true
     *               errorMsg:NULL
     *               data:user
     *                    message
     *                    list<comment>
     *  失败：返回   success:false
     *               errorMsg:查询数据失败
     *               data:null
     * @param messageId
     * @return
     */
    @RequestMapping("/getMessage")
    @ResponseBody
    public RetJsonData getMessage(String messageId){
        return messageService.getMessage(messageId);
    }


    /**
     * 增加阅读量
     *  成功：返回   success:true
     *               errorMsg:NULL
     *               data:阅读量增加成功
     *  失败：返回   success:false
     *               errorMsg:阅读量增加失败
     *               data:null
     * @param messageId
     * @return
     */
    @RequestMapping("/addMessageReadNum")
    @ResponseBody
    public RetJsonData addMessageReadNum(String messageId){
        return messageService.addMessageReadNum(messageId);
    }


    /**
     *
     * @param searchInfo
     * @return
     */
    @RequestMapping("/search")
    @ResponseBody
    public RetJsonData search(String searchInfo){
        return messageService.search(searchInfo);
    }


}
