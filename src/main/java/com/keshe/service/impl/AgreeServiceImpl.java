package com.keshe.service.impl;

import com.keshe.entity.Agree;
import com.keshe.entity.RetJsonData;
import com.keshe.mapper.ArgeeMapper;
import com.keshe.mapper.MessageMapper;
import com.keshe.service.AgreeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName AgreeServiceImpl
 * @Description TODO
 * @Author Haining
 * @Date 2020/5/27 9:42
 * @Version 1.0
 */

@Service
public class AgreeServiceImpl implements AgreeService {
    @Resource
    private ArgeeMapper agreeMapper;
    @Resource
    private MessageMapper messageMapper;

    @Override
    public RetJsonData addAgree(Agree agree) {
        if (agreeMapper.verifyAgree(agree.getUserId(), agree.getMessageId()) == null) {
            if (agreeMapper.addAgree(agree) > 0 && messageMapper.addAgreenum(agree.getMessageId()) > 0) {
                return new RetJsonData(true, "点赞成功", null);
            }
            return new RetJsonData(false, "点赞失败");
        }else{
            return new RetJsonData(false, "已被点赞");
        }
    }

    @Override
    public RetJsonData redAgree(String userId, String messageId) {
        if (agreeMapper.delAgree(userId, messageId) > 0 && messageMapper.redAgreenum(messageId) > 0) {
            return new RetJsonData(true, "取赞成功", null);
        }
        return new RetJsonData(false, "取赞失败");
    }
}
