package com.keshe.service;

import com.keshe.entity.Forward;
import com.keshe.entity.Message;
import com.keshe.entity.RetJsonData;
import org.springframework.web.multipart.MultipartFile;

/**
 * @InterfaceName MessageService
 * @Description TODO
 * @Author Haining
 * @Date 2020/5/27 9:38
 * @Version 1.0
 */
public interface MessageService {

    /**
     * 个人信息页面进行删除动态
     * @param messageId
     * @return
     */
    RetJsonData delPersonalMessage(String messageId);


    /**
     * 个人信息页面的返回
     * @param userId
     * @return
     */
    RetJsonData messageInfoPage(String userId);



    /**
     * 保存动态根据imgs
     * @param files
     * @param userId
     * @param messageInfo
     * @param lable
     * @return
     */
    RetJsonData saveMessageOnImg(MultipartFile[] files, String userId, String messageInfo, String lable) throws Exception;


    /**
     * 保存动态根据video
     * @param file
     * @param userId
     * @param messageInfo
     * @param lable
     * @return
     */
    RetJsonData saveMessageOnVideo(MultipartFile file, String userId, String messageInfo, String lable) throws Exception;


    /**
     * 根据messageId查询message所有信息
     * @param messageId
     * @return
     */
    RetJsonData getMessage(String messageId);


    /**
     * 增加浏览量
     * @param messageId
     * @return
     */
    RetJsonData addMessageReadNum(String messageId);


    /**
     * 根据传入的信息进行查找（搜索功能）
     * @param searchInfo
     * @return
     */
    RetJsonData search(String searchInfo);



    Integer forWardUpdateTranspondnum(Forward forward);

    Message findMessageById(String messageId);

    Integer saveMessage(Message message);
}
