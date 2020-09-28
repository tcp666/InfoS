新增收藏：

    请求参数：

        用户Id：userId
        动态Id：messageId
    
    请求路径：
        collection/addCollection

    响应数据类型：
        响应成功：
        {
            "success":true,
            "data":"收藏成功",
            "errorMsg":null
        }
        响应失败：
        {
            "success":false,
            "data":null,
            "errorMsg":"已经收藏了"
        }



删除收藏：

    请求参数：

        用户Id：userId
        动态Id：messageId
    
    请求路径：
        collection/delCollection

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
            "data":"删除失败",
            "errorMsg":null
        }



收藏页面展示：

    请求参数：
        用户Id：userId

    请求路径：
        collection/collectionInfoPage

    响应数据类型：
        响应成功：
        {
            "success":true,
            "data":
            [
                {
                    "messageInfo":
                    {
                        "message":
                        {
                            "messageId":"1",
                            "userId":"3",
                            "messageLable":"dada",
                            "messageInfo":"fdshfdsbbf",
                            "messageCtime":"2020-05-30 02:36:41",
                            "messageState":"1",
                            "messageCollectNum":"1",
                            "messageCommentNum":"1",
                            "messageTranspondNum":"1",
                            "messageAgreeNum":"1",
                            "messageReadNum":"1"
                        },
                        "imgs":[],
                        "video":
                        {
                            "videoId":"1",
                            "messageId":"1",
                            "videoUrl":"fdshfdshgfhgfd"
                        }
                    },
                    "user":
                    {
                        "userId":"3",
                        "userEmail":null,
                        "userPassword":null,
                        "userCtime":"2020-05-29 17:55:53",
                        "userName":"haining",
                        "userSex":"男",
                        "userStatus":"1",
                        "userImg":"",
                        "userBirthday":"2020-05-28 16:54:50","userPersonalized":"",
                        "userRealname":null,
                        "userMsgcount":"0",
                        "userFans":null
                    }
                },
                {
                    "messageInfo":
                    {
                        "message":
                        {
                            "messageId":"2",
                            "userId":"3",
                            "messageLable":"grgr",
                            "messageInfo":"haha",
                            "messageCtime":"2020-05-30 02:36:56","messageState":"1",
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
                    "user":
                    {
                        "userId":"3",
                        "userEmail":null,
                        "userPassword":null,
                        "userCtime":"2020-05-29 17:55:53",
                        "userName":"haining",
                        "userSex":"男",
                        "userStatus":"1",
                        "userImg":"",
                        "userBirthday":"2020-05-28 16:54:50","userPersonalized":"",
                        "userRealname":null,
                        "userMsgcount":"0",
                        "userFans":null
                    }
                }
            ],
            "errorMsg":null
        }
        响应失败：
        {
            "success":false,
            "data":null,
            "errorMsg":"查询失败"
        }
