显示视频页面：

    请求参数：  

        视频类型：type

    请求路径：
        video/videoInfoPage

    响应数据类型：
        响应成功：
        {
            "success":true,
            "data":
            [
                {
                    "messageInfo":"fdshfdsbbf",
                    "video":
                    {
                        "videoId":"1",
                        "messageId":"1",
                        "videoUrl":"fdshfdshgfhgfd"
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
                    "messageInfo":"gfhdsd",
                    "video":
                    {
                        "videoId":"2",
                        "messageId":"3",
                        "videoUrl":"fdsfdsfds"
                    },
                    "user":
                    {
                        "userId":"4",
                        "userEmail":null,
                        "userPassword":null,
                        "userCtime":"2020-06-01 02:40:38",
                        "userName":"haha",
                        "userSex":"女",
                        "userStatus":"1",
                        "userImg":null,
                        "userBirthday":"2020-01-01 08:00:00","userPersonalized":null,
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
