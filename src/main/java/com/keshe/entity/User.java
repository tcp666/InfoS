package com.keshe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @Description TODO USER用户表
 * @Author gyhdx
 * @Date 2020/5/26 22:01
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class User {
   private String userId;
   private String userEmail;
   private String userPassword;
   private Date userCtime;
   private String userName;
   private String userSex;
   private String userStatus;
   private String userImg;
   private Date userBirthday;
   private String userPersonalized;
   private String userRealname;
   private String userMsgcount;
   private String userFans;
}
