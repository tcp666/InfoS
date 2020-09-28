个人信息页面数据展示

        请求参数：（两个）
            用户Id: userId
            页码数：pageNum
        
        请求路径：
            user/personalInfoPage

        响应返回类型：
            响应成功：
            {
                "success":true,
                "data":
                {
                    "messages":
                    [
                        {
                            "message":
                            {
                                "messageId":"2",
                                "userId":"3",
                                "messageLable":"grgr",
                                "messageInfo":"haha",
                                "messageCtime":"2020-05-30 02:36:56",
                                "messageState":"1",
                                "messageCollectNum":"1",
                                "messageCommentNum":"1",
                                "messageTranspondNum":"1",
                                "messageAgreeNum":"1",
                                "messageReadNum":"1"
                            },
                            "imgs":
                            [
                                {
                                    "imgId":"3",
                                    "messageId":"2",
                                    "imgUrl":"htrnhytrn"
                                },
                                {
                                    "imgId":"4",
                                    "messageId":"2",
                                    "imgUrl":"hgfrnhgfnh"
                                }
                            ],
                                "video":null
                        },
                        {
                            "message":
                            {
                                "messageId":"1",
                                "userId":"3",
                                "messageLable":"dada","messageInfo":"fdshfdsbbf","messageCtime":"2020-05-30 02:36:41","messageState":"1",
                                "messageCollectNum":"1","messageCommentNum":"1","messageTranspondNum":"1","messageAgreeNum":"1",
                                "messageReadNum":"1"
                            },
                            "imgs":[],
                            "video":
                            {
                                "videoId":"1",
                                "messageId":"1",
                                "videoUrl":"fdshfdshgfhgfd"
                            }
                        }
                    ],
                    "pagenums":1,
                    "user":
                    {
                        "userId":"3",
                        "userEmail":"2279798428@qq.com",
                        "userPassword":"c6588a1e630a2e8e5bb64efb666b1dd0",
                        "userCtime":"2020-05-29 17:55:53",
                        "userName":"haining",
                        "userSex":"男",
                        "userStatus":"1",
                        "userImg":"",
                        "userBirthday":"2020-05-28 16:54:50",
                        "userPersonalized":"",
                        "userRealname":null,
                        "userMsgcount":"0",
                        "userFans":null
                    }
                },
                "errorMsg":null
            }

            
            响应失败：
            {
                "success":false,
                "data":null,
                "errorMsg":"查询失败"
            }

    注释：
        请求成功的整体结构： 详细结构请看上面的信息
            {
                "success":true,
                "data":
                    {
                        "messages":
                        [
                            0：{
                                "message":{},
                                "imgs":{},
                                "video":{}
                            }
                            1：{
                                "message":{},
                                "imgs":{},
                                "video":{}
                            }
                        ],
                        "user":{},
                        "pagenums":number
                    },
                "errorMsg":null
            }




判别用户名是否重复

    请求参数：
        用户名：userName

    请求地址：
        user/verifyUserName

    响应数据类型：
        响应成功：
            {
                "success":true,
                "errorMsg":null,
                "data":null
                }  
        响应失败：
            {
                "success":false,
                "errorMsg":"用户名已存在，请重新输入",
                "data":null
                }
            
    注释：如果用户名已存在，则响应失败




修改个人信息

    请求参数：
        用户Id：userId
        输入的用户名：userName
        输入的性别：sex
        输入的生日（格式 yyyy-MM--dd）：birthday
        输入的个性签名：personalized
        输入的真实姓名：userRealname

    请求路径：
        usre/updateUserInfo

    响应类型示例：
        响应成功:
        {
            "success":true,
            "errorMsg":null,
            "data":null
            }
        响应失败：
        {
            "success":false,
            "errorMsg":"修改失败",
            "data":null
            }


修改头像

    请求参数：
        头像文件：file
        用户Id：userId

    请求路径：
        user/updateHeadImg

    响应类型示例：
        响应成功：
        {
            "success":true,
            "errorMsg":null,
            "data":
                {
                    "imgUrl"："http://qb5b2c5x3.bkt.clouddn.com/Firefox_wallpaper.png"
                }
            }
        响应失败：
        {
            "success":false,
            "errorMsg":"修改失败",
            "data":null
            }


删除用户动态：

    请求参数：
        动态Id：messageId

    请求路径：
        message/delPersonalMessage

    响应数据类型：
        响应成功：
        {
            "success":true,
            "data":"删除成功",
            "errorMsg":null
        }
        响应失败：
        {
            "success":false,
            "data":null,
            "errorMsg":"该动态不存在,删除失败"
        }