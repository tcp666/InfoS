发送动态：图片

    请求参数：
        图片文件的List：files
        用户Id：userId
        标签类型：lable
        发表动态信息：messageInfo

    请求路径：
        message/saveMessageOnImg

    响应数据类型：
        响应成功：
        {
            "success":true,
            "data":"发表成功",
            "errorMsg":null
        }
        响应失败：
        {
            "success":false,
            "data":null,
            "errorMsg":存储图片错误/动态查找失败/动态存储失败
        }

发送动态：视频

    请求参数：
        视频文件：file
        用户Id：userId
        标签类型：lable
        发表动态信息：messageInfo

    请求路径：
        message/saveMessageOnVideo

    响应数据类型：
        响应成功：
        {
            "success":true,
            "data":"发表成功",
            "errorMsg":null
        }
        响应失败：
        {
            "success":false,
            "data":null,
            "errorMsg":视频存储失败/动态查找失败/动态存储失败
        }
