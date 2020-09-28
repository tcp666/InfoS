package com.keshe.controller;

import com.keshe.entity.RetJsonData;
import com.keshe.entity.User;
import com.keshe.service.UserService;
import com.keshe.tools.DateChange;
import com.keshe.tools.Md5Password;
import com.keshe.tools.Pack;
import com.keshe.tools.QiniuUpload;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Description TODO
 * @Author haining
 * @Date 2020/5/26 22:50
 */

@RequestMapping("/user")
@RestController
@Slf4j
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private Pack packUser;
    @Resource
    private Md5Password md5Password;
    @Resource
    private DateChange dateChange;
    @Resource
    private QiniuUpload qiniuUpload;

    @RequestMapping("/getAllUser")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }


    /**
     * 登录
     * 成功：返回   success:true
     *              errorMsg:data
     *              data:null
     * 失败：返回   success:false
     *              errorMsg:用户名或密码错误
     *              data:null
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public RetJsonData login(@Param("userName") String userName, @Param("password") String password){
        return userService.login(userName, password);
    }


    /**
     * 验证用户名是否已存在
     *  成功：返回   success:true
     *               errorMsg:NULL
     *               data:null
     *  失败：返回   success:false
     *               errorMsg:用户名已存在，请重新输入
     *               data:null
     *
     * @param userName
     * @return
     */
    @RequestMapping("/verifyUserName")
    @ResponseBody
    public RetJsonData verifyUserName(@Param("userName") String userName){
        return userService.selectUserByName(userName);
    }


    /**
     * 验证邮箱是否存在
     *  成功：返回   success:true
     *               errorMsg:NULL
     *               data:null
     *  失败：返回   success:false
     *               errorMsg:邮箱已被注册
     *               data:null
     * @param userEmail
     * @return
     */
    @RequestMapping("/verifyUserEmail")
    @ResponseBody
    public RetJsonData verifyUserEmail(@Param("userEmail") String userEmail){
        return userService.secletUserByEmail(userEmail);
    }


    /**
     * 邮箱发送验证码
     *  成功：返回   success:true
     *               errorMsg:NULL
     *               data:验证码
     *  失败：返回   success:false
     *               errorMsg:验证码错误
     *               data:null
     * @param userEmail
     * @return
     * @throws MessagingException
     */
    @RequestMapping("/verifyCode")
    @ResponseBody
    public RetJsonData verifyCode(@Param("userEmail") String userEmail) throws MessagingException {
        return userService.verifyCode(userEmail);
    }


    /**
     * 注册
     *  成功：返回   success:true
     *               errorMsg:NULL
     *               data:null
     *  失败：返回   success:false
     *               errorMsg:验证码错误/验证超时/注册失败
     *               data:null
     * @param userName
     * @param password
     * @param userEmail
     * @param verifyCode
     * @return
     */
    @RequestMapping("/register")
    @ResponseBody
    public RetJsonData register(String userName, String password, String userEmail, String verifyCode, HttpServletRequest request){
        String encodePassword = md5Password.md5Password(password);
        User user1 = packUser.packUser(userName, encodePassword, userEmail);
        return userService.register(user1, verifyCode, request);
    }


    /**
     * 密码找回
     *  成功：返回   success:true
     *               errorMsg:NULL
     *               data:null
     *  失败：返回   success:false
     *               errorMsg:验证码错误/验证超时/修改失败
     *               data:null
     * @param userEmail
     * @param password
     * @param verifyCode
     * @param request
     * @return
     */
    @RequestMapping("/forget")
    @ResponseBody
    public RetJsonData forget(String userEmail, String password, String verifyCode, HttpServletRequest request){
        String encodePassword = md5Password.md5Password(password);
        return userService.forget(userEmail, encodePassword, verifyCode, request);
    }


    /**
     * 个人信息页面得数据展示
     *  成功：返回   success:true
     *               errorMsg:NULL
     *               data:总页数
     *                    user
     *                    List<Message>
     *  失败：返回   success:false
     *               errorMsg:查询失败
     *               data:null
     * @param userId
     * @param pageNum
     * @return
     */
    @RequestMapping("/personalInfoPage")
    @ResponseBody
    public RetJsonData personalInfoPage(String userId, Integer pageNum){
        return userService.personalInfoPage(userId, pageNum);
    }


    /**
     * 个人信息数据修改
     *  成功：返回   success:true
     *               errorMsg:NULL
     *               data:NULL
     *  失败：返回   success:false
     *               errorMsg:修改失败
     *               data:null
     * @param userId
     * @param userName
     * @param sex
     * @param birthday
     * @param personalized
     * @return
     * @throws ParseException
     */
    @RequestMapping("/updateUserInfo")
    @ResponseBody
    public RetJsonData updateUserInfo(String userId, String userName, String sex, String birthday, String personalized, String userRealname) throws ParseException {
        User user = new User();
        Date birthday1 = dateChange.strTodate(birthday);
        user.setUserId(userId);
        user.setUserName(userName);
        user.setUserSex(sex);
        user.setUserBirthday(birthday1);
        user.setUserPersonalized(personalized);
        user.setUserRealname(userRealname);
        return userService.updateUserInfo(user);
    }


    /**
     * 个人头像修改
     *  成功：返回   success:true
     *               errorMsg:NULL
     *               data:头像的URL
     *  失败：返回   success:false
     *               errorMsg:修改失败
     *               data:null
     * @param file
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/updateHeadImg")
    @ResponseBody
    public RetJsonData updateHeadImg(MultipartFile file, HttpServletRequest request) throws Exception {
        String userId = request.getParameter("userId");  //获取用户Id
        if (file != null){
            //上传文件并返回url(文件路径)+file.getOriginalFilename()
            String url = qiniuUpload.updateFileVideo(file, UUID.randomUUID().toString()+"-"+file.getOriginalFilename());
//            System.out.println(url);
            //存储图片
            return userService.updateHeadImg(userId, url);
        }else {
            return new RetJsonData(false, "请重新选择文件");
        }
    }


    /**
     * 根据userId获取User
     */
    @RequestMapping("/getUserById")
    @ResponseBody
    public RetJsonData getUserById(String userId){
        return userService.queryUserId(userId);
    }
}
