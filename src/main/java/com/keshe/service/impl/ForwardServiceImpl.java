package com.keshe.service.impl;

import com.keshe.entity.*;
import com.keshe.mapper.ForwardMapper;
import com.keshe.mapper.ImgMapper;
import com.keshe.mapper.MessageMapper;
import com.keshe.mapper.VideoMapper;
import com.keshe.service.ForwardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName ForwardServiceImpl
 * @Description TODO
 * @Author Haining
 * @Date 2020/5/27 9:45
 * @Version 1.0
 */

@Service
public class ForwardServiceImpl implements ForwardService {
    @Resource
    private ForwardMapper forwardMapper;
    @Resource
    private MessageServiceImpl messageService;
    @Resource
    private UserServiceImpl userService;
    @Resource
    VideoServiceImpl videoService;
    @Resource
    VideoMapper videoMapper;
    @Resource
    MessageMapper messageMapper;
    @Resource
    ImgMapper imgMapper;

    public ForwardServiceImpl() {
    }

    public List<Forward> getForwardOne(Forward forward) {
        return this.forwardMapper.getForwardOne(forward);
    }

    @Transactional
    public RetJsonData forward(Forward forward) {

        System.out.println("forward:services");
        String result = "转发失败";

        if (getForwardOne(forward).size() == 0) {
            int flag = forwardMapper.forward(forward);
            flag += messageService.forWardUpdateTranspondnum(forward);
            RetJsonData retJsonData = userService.queryUserId(forward.getUserId());
            User user = (User) retJsonData.getData();
            Message message = messageService.findMessageById(forward.getMessageId());
            String messagesInf = message.getMessageInfo();
            System.out.println("messageuiserId:" + message);
            messagesInf = user.getUserName() + "@转发自:" + ((User) userService.queryUserId(message.getUserId()).getData()).getUserName() + "\n" + messagesInf;
            message.setMessageInfo(messagesInf);
            message.setUserId(forward.getUserId());
            System.out.println("forwardmessage:" + message);
            flag += messageService.saveMessage(message);
            String messsage_id = message.getMessageId();
            String neMessage_id = messageMapper.getMessageId(forward.getUserId(), messagesInf);
            Video video = videoMapper.videoByMessageId(messsage_id);
            System.out.println("messsage_id:"+messsage_id);

            if (video != null) {
                video.setMessageId(neMessage_id);
                videoMapper.saveVideo(video);
            }
            List<Img> imgs = imgMapper.imgByMessageId(forward.getMessageId());
            if (imgs.size() > 0) {
                for(Img img:imgs){
                    img.setMessageId(neMessage_id);
                    imgMapper.saveImg(img);
                }
            }


            if (flag == 3) {
                result = "转发成功";
            }
        }
        return new RetJsonData(true, result, result);
    }
}
