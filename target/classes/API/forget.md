判别邮箱是否存在

    请求参数：
        邮箱地址：userEmail
    
    请求地址：
        user/verifyUserEmailuser

    响应数据类型：
        响应成功：
        {
            "success":false,
            "errorMsg":"邮箱已被注册",
            "data":null
            }  
        相应失败：
        {
            "success":true,
            "errorMsg":null,
            "data":null
            }

    注释：如果邮箱地址已注册过，，则修改密码就可以成功


发送验证码

    请求参数：
        邮箱地址：userEmail
    
    请求地址：
        user/verifyCode

    响应数据类型：
        响应成功：
        {
            "success":true,
            "errorMsg":null,
            "data":
                {
                    "VerifyCode":"8sRHFH",
                    "check":"验证码已发送，三分钟内请输入验证"
                }
        }
        响应失败：
        {
            "success":false,
            "errorMsg":"验证码发送失败",
            "data":null
        }



忘记密码：
    请求参数：
        用户邮箱：userEmail
        用户密码：password
        验证码：verifyCode

    请求地址：
        user/forget
    
    响应返回类型：
        响应成功：
        {
            "success":true,
            "errorMsg":null,
            "data":null
        }
        响应失败：验证码错误
        {
            "success":false,
            "errorMsg":"验证码错误",
            "data":null
        }
        响应失败：验证超时
        {
            "success":false,
            "errorMsg":"验证超时",
            "data":null
        }
        还有一种试不出来

    错误信息有三类：验证码错误/验证超时/修改失败
        验证时间只有三分钟
