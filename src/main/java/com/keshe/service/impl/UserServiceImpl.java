package com.keshe.service.impl;

import com.keshe.entity.*;
import com.keshe.mapper.ImgMapper;
import com.keshe.mapper.MessageMapper;
import com.keshe.mapper.UserMapper;
import com.keshe.mapper.VideoMapper;
import com.keshe.service.UserService;
import com.keshe.tools.Md5Password;
import com.keshe.tools.SingleSession;
import com.keshe.tools.Verify;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author Haining
 * @Date 2020/5/27 9:55
 * @Version 1.0
 */

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private Md5Password md5Password;
    @Resource
    private Verify verify;
    @Resource
    private MessageMapper messageMapper;
    @Resource
    private ImgMapper imgMapper;
    @Resource
    private VideoMapper videoMapper;


    @Override
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    @Override
    public RetJsonData login(String userName, String password) {
        String encodePassword = md5Password.md5Password(password);
        User user  = userMapper.login(userName, encodePassword);
        Map<String, String> map = new HashMap<>();
        if (user != null) {
            map.put("userId",user.getUserId());
            return new RetJsonData(true, map, null);
        }
        return new RetJsonData(false, "用户名或密码错误");
    }

    @Override
    public RetJsonData selectUserByName(String username) {
        if (userMapper.userByname(username) == null){
            return new RetJsonData(true);
        }else{
            return new RetJsonData(false, "用户名已存在，请重新输入");
        }
    }

    @Override
    public RetJsonData secletUserByEmail(String email) {
        if (userMapper.userByEmile(email) == null){
            return new RetJsonData(true);
        }else{
            return new RetJsonData(false, "邮箱已被注册");
        }
    }

    @Override
    public RetJsonData verifyCode(String email){
        try {
            String checkcode =  verify.getchcekcode();
            verify.sendMsg(email, checkcode);
            Map<String, String> map = new HashMap<>();
            map.put("VerifyCode", checkcode);
            map.put("check", "验证码已发送，三分钟内请输入验证");
            //获取session
            HttpSession session = SingleSession.getSession();
            session.setAttribute("VerifyCode", checkcode);
            session.setAttribute("Time", System.currentTimeMillis());
//            System.out.println("haha"+session.getAttribute("Time"));
            return new RetJsonData(true, map, null);
        }catch (Exception e){
            e.printStackTrace();
            return new RetJsonData(false,"验证码发送失败");
        }

    }


    @Override
    public RetJsonData register(User user, String VerifyCode, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String afterVerifyCode = (String) session.getAttribute("VerifyCode");
        long time = (long) session.getAttribute("Time");
        if (afterVerifyCode.equals(VerifyCode)) {
            if ((System.currentTimeMillis() - time) < 180000){
                if (userMapper.register(user) > 0) {
                    return new RetJsonData(true);
                } else {
                    return new RetJsonData(false, "注册失败");
                }
            }
            return new RetJsonData(false, "验证超时");
        }
        return new RetJsonData(false, "验证码错误");
    }

    @Override
    public RetJsonData forget(String userEmail, String password, String VerifyCode, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String afterVerifyCode = (String) session.getAttribute("VerifyCode");
        long time = (long) session.getAttribute("Time");
        if (afterVerifyCode.equals(VerifyCode)) {
            if ((System.currentTimeMillis() - time) < 180000){
                if (userMapper.updatePassword(userEmail, password) > 0) {
                    return new RetJsonData(true);
                } else {
                    return new RetJsonData(false, "修改失败");
                }
            }
            return new RetJsonData(false, "验证超时");
        }
        return new RetJsonData(false, "验证码错误");
    }


    @Override
    public RetJsonData personalInfoPage(String userId, Integer pagenum) {
        //该用户动态总数
//        int count = messageMapper.messageCountByUserId(userId);
//        int pagenums = (count+2)/3;
//        int number = (pagenum-1)*3;   //根据页数进行查询条数
        User user = userMapper.queryUserId(userId);
        List<Message> messages = messageMapper.messageByUserId(userId);
        List<MessageAndAllInfo> messageAndImgs = new ArrayList<>();
        for (int i = 0; i < messages.size(); i++){
            MessageAndAllInfo messageAndAllInfo1 = new MessageAndAllInfo();
            List<Img> imgs = imgMapper.imgByMessageId(messages.get(i).getMessageId());
            Video video = videoMapper.videoByMessageId(messages.get(i).getMessageId());
            messageAndAllInfo1.setMessage(messages.get(i));
            messageAndAllInfo1.setImgs(imgs);
            messageAndAllInfo1.setVideo(video);
            messageAndImgs.add(messageAndAllInfo1);
        }
        user.setUserRealname(null);
        user.setUserPassword(null);
        user.setUserEmail(null);
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("messages", messageAndImgs);
        if (user != null){
            return new RetJsonData(true, map, null);
        }
        return new RetJsonData(false, "查询失败");
    }

    @Override
    public RetJsonData updateUserInfo(User user){
        if (userMapper.updateUserInfo(user) > 0){
            return new RetJsonData(true, null, null);
        }else{
            return new RetJsonData(false, null, "修改失败");
        }
    }

    @Override
    public RetJsonData updateHeadImg(String userId, String url) {
        if (userMapper.updateHeadImg(userId, url) > 0){
            Map<String, String> map = new HashMap<>();
            map.put("imgUrl",url);
            return new RetJsonData(true, map, null);
        }else {
            return new RetJsonData(false, "修改失败");
        }
    }

    @Override
    public RetJsonData queryUserId(String userId) {

        User user= userMapper.queryUserId(userId);
        user.setUserPassword(null);
        user.setUserRealname(null);
        user.setUserEmail(null);
        return new RetJsonData(true,user,null);
    }
}
