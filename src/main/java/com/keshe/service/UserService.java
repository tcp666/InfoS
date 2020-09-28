package com.keshe.service;

import com.keshe.entity.RetJsonData;
import com.keshe.entity.User;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @InterfaceName UserService
 * @Description TODO
 * @Author Haining
 * @Date 2020/5/27 9:40
 * @Version 1.0
 */
public interface UserService {

    /**
     * user表查询所有
     */
    List<User> getAllUser();

    /**
     * 登录
     * @param userName
     * @param password
     * @return
     */
    RetJsonData login(String userName, String password);

    /**
     * 根据用户名进行查询user对象
     * @param username
     * @return
     */
    RetJsonData selectUserByName(String username);

    /**
     * 根据用户邮箱进行查询用户
     * @param email
     * @return
     */
    RetJsonData secletUserByEmail(String email);


    /**
     * 发送验证码
     * @param email
     * @return
     */
    RetJsonData verifyCode(String email) throws MessagingException;

    /**
     * 注册
     * @param user
     * @return
     */
    RetJsonData register(User user, String VerifyCode, HttpServletRequest request);


    /**
     * 忘记密码找回
     * @param userEmail
     * @param password
     * @param VerifyCode
     * @param request
     * @return
     */
    RetJsonData forget(String userEmail, String password, String VerifyCode, HttpServletRequest request);


    /**
     * 根据userId返回用户数据包括user对象message对象imgs对象
     * @param userId
     * @param pagenum
     * @return
     */
    RetJsonData personalInfoPage(String userId, Integer pagenum);


    /**
     * 根据userId进行修改用户信息
     * @param user
     * @return
     */
    RetJsonData updateUserInfo(User user);


    /**
     * 根据userId进行修改用户头像
     * @param userId
     * @param url
     * @return
     */
    RetJsonData updateHeadImg(String userId, String url);

    /**
     * 根据id获取User信息
     * @param userId
     * @return
     */
    RetJsonData queryUserId(String userId);

}
