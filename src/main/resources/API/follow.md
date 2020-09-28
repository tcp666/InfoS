关注页面信息展示：

    请求参数：
        用户Id：userId

    请求路径：
        relation/relationInfoPage

    响应数据类型：
        响应成功：
        {
            "success":true,
            "data":
            {
                "followCount":2,
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
                },
                "users":
                [
                    {
                        "userId":"4",
                        "userEmail":"2722249709@qq.com",
                        "userPassword":"c6588a1e630a2e8e5bb64efb666b1dd0",
                        "userCtime":"2020-06-01 02:40:38",
                        "userName":"haha",
                        "userSex":"女",
                        "userStatus":"1",
                        "userImg":null,
                        "userBirthday":"2020-01-01 08:00:00","userPersonalized":null,
                        "userRealname":null,
                        "userMsgcount":"0",
                        "userFans":null
                    },
                    {
                        "userId":"5",
                        "userEmail":"4524325432@qq.com","userPassword":"c6588a1e630a2e8e5bb64efb666b1dd0","userCtime":"2020-06-01 02:44:07",
                        "userName":"hehe",
                        "userSex":"男",
                        "userStatus":"1",
                        "userImg":null,
                        "userBirthday":"2020-01-01 08:00:00","userPersonalized":null,
                        "userRealname":null,
                        "userMsgcount":"1",
                        "userFans":null
                    }
                ]
            },
            "errorMsg":null
        }
        响应失败：
        {
            "success":false,
            "data":null,
            "errorMsg":"查询失败"
        }
    注释：当前本人的userId不存在时候，会失败
        user下面的是自己的信息  
        users下面的都是关注的人的信息



新增关注

    请求参数：
        关注用户Id：userId
        被关注用户Id：userById
        关注类型：lable

    请求路径：
        relation/addRelation

    响应数据类型：
        响应成功：
        {
            "success":true,
            "errorMsg":null,
            "data": "关注成功"
            }
        响应失败：
        {
            "success":false,
            "errorMsg":"关注失败",
            "data":null
        }



取消关注

    请求参数：
        关注用户Id：userId
        被关注用户Id：userById

    请求路径：
        relation/delRelation

    响应数据类型：
        响应成功：
        {
            "success":true,
            "errorMsg":null,
            "data": "取关成功"
            }
        响应失败：
        {
            "success":false,
            "errorMsg":"取关失败",
            "data":null
            }