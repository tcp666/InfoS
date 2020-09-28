点赞：

    请求参数：
        用户Id：userId
        动态Id：messageId

    请求路径：
        agree/addAgree

    响应返回类型：
        响应成功：
        {
            "success":true,
            "data":"点赞成功",
            "errorMsg":null
        }
        响应失败：
        {
            "success":false,
            "data":null,
            "errorMsg":"已被点赞"
        }


取消点赞：

    请求参数：
        用户Id：userId
        动态Id：messageId

    请求路径：
        agree/redAgree

    响应返回类型：
        响应成功：
        {
            "success":true,
            "data":"取赞成功",
            "errorMsg":null
        }
        响应失败：
        {
            "success":false,
            "data":null,
            "errorMsg":"取赞失败"
        }