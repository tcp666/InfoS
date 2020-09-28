let userId = $.cookie("userId");
let pagenum = 1;
let pagenums = 0;

window.onload = function () {
    /**
     * socket
     * @type {jQuery|*}
     */

    comId = $.cookie("userId");
    var wsUrl="ws:"+window.location.host+"//websocket/"+comId

    socket = null;

// 初始化websocket
    socket = new WebSocket(wsUrl);
    socket.onopen = function () {
        console.log("hkjllllllllllllllllllll");
    }
    socket.onmessage = function (data) {
        var mesnum = document.getElementById('notices').innerText;
        mesnum = parseInt(mesnum) + 1;
        document.getElementById('notices').innerText = mesnum;
        document.getElementById('notices').style.display = 'block'
        console.log("data:" + data);
        var record = JSON.parse(data.data);

        console.log("你收到了来自" + record.comUserId + "的消息");
    }
////////////////////////////////////////////////////


    page(pagenum);
};

function page(page) {
    $.ajax({
        type: "POST",
        url: "user/personalInfoPage",
        data: {
            userId: userId,
            pageNum: page
        },
        success: function (result) {
            console.log(result.data);
            if (result.success) {
                $("#modifyinfomation").css("background-image", "url("+result.data.user.userImg+")");//这里改大头像
                $("#username").text(result.data.user.userName);//这里改用户名
                $("#Personalized").text(result.data.user.userPersonalized);//这里改个性签名
                $("#Msgcount").text(result.data.user.userMsgcount);//这里改说说数量
                $("#fanscount").text(result.data.user.userFansCount);//这里改粉丝数量
                pagenums = result.data.messageNums;//改这里
                let parentdiv = document.getElementsByClassName("articles")[0];
                if (result.data.messages.length) {
                    for (let i = 0; i < result.data.messages.length; i++) {
                        let message = document.createElement("div");
                        message.className = "bottom-right";
                        message.id = result.data.messages[i].message.messageId;
                        message.innerHTML =
                            "            <div class=\"bottom-top\">\n" +
                            "                <div class=\"headp\"></div>\n" +
                            "                <div class=\"info\">\n" +
                            "                    <a class=\"username\">" + result.data.user.userName + "</a><br>\n" +
                            "                    <a class=\"time\">" + result.data.messages[i].message.messageCtime + "发布于weibo.com</a><br>\n" +
                            "                </div>\n" +
                            "                <div class=\"article\">\n" +
                            "                    <a>" + result.data.messages[i].message.messageInfo + "</a>\n" +
                            "                    <p class=\"readall\" onclick=\"read(this)\"></p>\n" +
                            "                </div>\n" +
                            "                <div class=\"picture\">\n" +
                            "                    <img src='' alt=\"\">\n" +
                            "                </div>\n" +
                            "            </div>\n" +
                            "            <div class=\"bottom-middle link-top\"></div>\n" +
                            "            <div class=\"bottom-bottom\">\n" +
                            "                <ul>\n" +
                            "                    <li class=\"read link-right\">阅读<a class=\"messageReadNum\">"+result.data.messages[i].message.messageReadNum+"</a></li>\n" +
                            "                    <li class=\"collect link-right\">收藏 <a class=\"messageCollectNum\">"+result.data.messages[i].message.messageCollectNum+"</a></li>\n" +
                            "                    <li class=\"share link-right\">转发 <a class=\"messageTranspondNum\">"+result.data.messages[i].message.messageTranspondNum+"</a></li>\n" +
                            "                    <li class=\"comment link-right\">评论 <a class=\"messageCommentNum\">"+result.data.messages[i].message.messageCommentNum+"</a></li>\n" +
                            "                    <li class=\"agree\">点赞 <a class=\"messageAgreeNum\">"+result.data.messages[i].message.messageAgreeNum+"</a></li>\n" +
                            "                </ul>\n" +
                            "            </div>";
                        parentdiv.appendChild(message);
                    }
                }else {
                    alert("快去发表你的第一条动态吧");
                }
            } else {
                alert(result.errorMsg);
            }
        }
    })
}



// 1.监听网页的滚动
$(window).scroll(function () {
    // 1.1获取网页滚动的偏移位
    var offset = $("html,body").scrollTop();
    // 1.2判断网页是否滚动到了指定的位置
    if (offset >= 10) {
        // 1.3显示广告
        $(".page").show(1000);
    } else {
        // 1.4隐藏广告
        $(".page").hide(1000);
    }
});


function beforepage() {
    if (pagenum > 1)//改这里
        page(pagenum--);
    else
        alert("已经是第一页")
}

function nextpage() {
    if (pagenum < pagenums)//改这里
        page(pagenum++);
    else
        alert("已经是最后一页");
}


let readAll = false;

function read(obj) {
    let div = obj.parentNode;
    let a = div.firstElementChild;
    readAll = !readAll;
    if (readAll)
        a.style.display = "inline";
    else
        a.style.display = "inline-block";
}


//修改信息
function moinf() {
    let dis = document.getElementById("modify");
    dis.style.display = "inline";
};

$("#reviseusername").blur(function () {
    let userName = $("#reviseusername").val();
    if (!(/^[a-zA-Z0-9]{6,16}$/.test(userName))) {
        $("#reviseusername").val("用户名格式错误");
        // return false;
    } else {
        $.ajax({//查重
            type: "POST",
            url: "user/verifyUserName",//
            data: {userName: userName},//
            success: function (result) {
                if (!result.success) {
                    alert(result.errorMsg);
                }
            }
        });
    }
});


$(function () {
    $(".modify").click(function () {
        $(".bottom-right").slideUp(1000, function () {
        });
        $(".modinf").slideDown(1000, function () {
        });
    });

    $("#save").click(function () {
        var userId = $.cookie("userId");
        var userName = $("#reviseusername").val();
        var userRealname = $("#reviserealname").val();
        var sex = $("#revisesex").val();
        var birthday = $("#revisebirthday").val();
        var personalized = $("#revisePersonalized").val();
        var reg = /^(19|20)\d{2}-(1[0-2]|0?[1-9])-(0?[1-9]|[1-2][0-9]|3[0-1])$/;
        if (userId && userName && userRealname && sex && personalized && (reg.test(birthday)))
            $.ajax({
                url: "user/updateUserInfo",//改这里
                type: "post",
                data: {
                    userId: userId,
                    userName: userName,
                    userRealname: userRealname,
                    sex: sex,
                    birthday: birthday,
                    personalized: personalized
                },
                success: function (result) {
                    if (result.success) {
                        alert("修改成功");
                        $(".modinf").slideUp(1000, function () {
                        });
                        $(".bottom-right").slideDown(1000, function () {
                        });
                        let modify = document.getElementById("modify");
                        modify.style.display = "none";
                    }
                },
                error: function () {
                    alert(result.errorMsg);
                }
            });

    });


    $("#change").click(function () {
        var formData = new FormData(document.getElementById('changeheadp')[0]);
        var userId = $.cookie("userId");
        formData.append("userId",userId);
        $.ajax({
            url: "user/updateHeadImg",//改这里
            type: "post",
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (result) {
                if (result.success) {
                    $('#headimg').css("background-image","url("+result.data.imgUrl+")");
                }
            },
            error: function () {
                alert(result.errorMsg);
            }
        });
    })
});

