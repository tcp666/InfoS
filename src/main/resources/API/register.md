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





判别邮箱是否重复

    请求参数：
        邮箱地址：userEmail
    
    请求地址：
        user/verifyUserEmailuser

    响应数据类型：
        响应成功：
        {
            "success":true,
            "errorMsg":null,
            "data":null
            }
        相应失败：
        {
            "success":false,
            "errorMsg":"邮箱已被注册",
            "data":null
            }

    注释：如果邮箱地址已存在，则响应失败



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

    
注册
    请求参数：
        用户名：userName
        密码：password
        邮箱地址：userEmail
        验证码：verifyCode
    
    请求地址：
        user/register

    响应数据类型
        响应成功：
        {
            "success":true,
            "errorMsg":null,
            "data":null
        }
        响应失败：
        {
            "success":false,
            "errorMsg":"验证码错误",
            "data":null
        }

    如果验证码错误就会返回“验证码错误”，如果在3分钟之内没有进行验证则会出现“验证超时”
    如果数据库没有插入成功会返回“注册失败”。