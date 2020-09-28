let userId = $.cookie("userId");
let pagenum = 1;

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
                document.getElementById("headp").src = result.data.user.userImg;
                $("#nickname").text(result.data.user.userName);//这里改用户名
                $("#we-num").text(result.data.user.userMsgcount);//这里改说说数量
                $("#fans-num").text(result.data.user.userFans);//这里改粉丝数量
                let parentdiv = document.getElementsByClassName("blogs")[0];
                if (result.data.messages.length > 0) {
                    for (let i = 0; i < result.data.messages.length; i++) {
                        let article = document.createElement("li");
                        article.id = result.data.messages[i].message.messageId;
                        article.innerHTML = "<div class=\"blog-wrapper\">\n" +
                            "                                <!--个人信息分为左右两部分-->\n" +
                            "                                <div class=\"blog-user-info\">\n" +
                            "                                    <!--头像 昵称 发送时间-->\n" +
                            "                                    <div class=\"B-left\">\n" +
                            "                                        <div class=\"left-avatar\"><img src='" + result.data.user.userImg + "' alt = \"头像\"></div>\n" +
                            "                                        <div class=\"name-time\">\n" +
                            "                                            <h2 class=\"blog-nickname\">" + result.data.user.userName + "</h2>\n" +
                            "                                            <h3 class=\"send-time\">" + result.data.messages[i].message.messageCtime + "</h3>\n" +
                            "                                        </div>\n" +
                            "                                    </div>\n" +
                            "                                    <div class=\"B-right\">\n" +
                            "                                        <a href = \"javascript:;\" class=\"fas-a\"><i class=\"fas fa-angle-down\"></i></a>\n" +
                            "                                        <!--设置 删除,下拉列表呈现-->\n" +
                            "                                        <div class=\"appear\">\n" +
                            "                                            <div class=\"blog-setting\">\n" +
                            "                                                <a href = \"javascript:;\" onclick='deletemessage(this)'>删除</a>\n" +
                            "                                                <a href = \"javascript:;\">置顶</a>\n" +
                            "                                            </div>\n" +
                            "                                        </div>\n" +
                            "                                    </div>\n" +
                            "                                </div>\n" +
                            "                                <!--动态正文-->\n" +
                            "                                <div class=\"blog-content\">\n" +
                            "                                    <p class=\"blog-text\" onclick='readArticle(this)'>\n" + result.data.messages[i].message.messageInfo + "</p>\n" +
                            "                                    <!-- 可能动态中只有文字 -->\n" +
                            "                                    <div class=\"blog-img-video\">\n" +
                            "                                    </div>\n" +
                            "                                </div>\n" +
                            "                                <!--评论 点赞等-->\n" +
                            "                                <!--评论 点赞等-->\n" +
                            "                                <div class=\"blog-views\">\n" +
                            "                                    <span>阅读&nbsp;<span class=\"read-num\">" + result.data.messages[i].message.messageReadNum + "</span></span>\n" +
                            "                                    <a href = \"javascript:;\" class=\"forward\">转发" + result.data.messages[i].message.messageReadNum + "</a>\n" +
                            "                                    <a href = \"javascript:;\" class=\"comment\">评论" + result.data.messages[i].message.messageCommentNum + "</a>\n" +
                            "                                    <a href = \"javascript:;\" class=\"praise\">点赞" + result.data.messages[i].message.messageAgreeNum + "</a>\n" +
                            "                                </div>\n" +
                            "                            </div>";

                        parentdiv.appendChild(article);
                        if (result.data.messages[i].imgs.length != 0) {
                            let blogimgvideos = document.getElementsByClassName("blog-img-video")[i];
                            for (let j = 0; j < result.data.messages[i].imgs.length; j++) {
                                let images = document.createElement("img");
                                images.id = result.data.messages[i].imgs[j].imgId;
                                images.className="artimg";
                                images.src=result.data.messages[i].imgs[j].imgUrl;
                                blogimgvideos.appendChild(images);
                            }
                        }
                        if (result.data.messages[i].video != null) {
                            let blogimgvideos = document.getElementsByClassName("blog-img-video")[i];
                            let videos = document.createElement("video");
                            videos.id = result.data.messages[i].video.videoId;
                            videos.src = result.data.messages[i].video.videoUrl;
                            blogimgvideos.appendChild(videos);
                            videos.setAttribute("controls","true");
                        }
                    }
                } else {
                    alert("快去发表你的第一条动态吧");
                }
            } else {
                alert(result.errorMsg);
            }
        }
    })
}

$("#username").blur(function () {
    let userName = $("#username").val();
    if (!(/^[a-zA-Z0-9]{6,16}$/.test(userName))) {
        $("#username").val("用户名格式错误");
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

$("#save").click(function () {
    var userId = $.cookie("userId");
    var userName = $("#username").val();
    var userRealname = $("#realname").val();
    var sex = $("#sex").val();
    var birthday = $("#birthday").val();
    var personalized = $("#personalized").val();
    console.log(userName);
    console.log(userId);
    console.log(sex);
    console.log(birthday);
    console.log(personalized);
    if (userId && userName && userRealname && sex && personalized && birthday)
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
                console.log(result);
                if (result.success) {
                    let modifyWrapper = document.getElementsByClassName("modify-wrapper")[0];
                    modifyWrapper.style.display = "none";
                    history.go(0);
                }
            },
            error: function () {
                alert(result.errorMsg);
            }
        });
});

function readArticle(obj) {
    let articleId = obj.parentNode.parentNode.parentNode.id;
    $.cookie("messageId", articleId);
    window.location.href = ""//改这里
}


$("#avatar").click(function () {
    $("#modavatarwrapper")[0].style.display = "block";
})


$("#sub-confirm").click(function () {
    alert("hh");
    var formData = new FormData($("#changehead")[0]);
    console.log(formData);
    var userId = $.cookie("userId");
    formData.append("userId", userId);
    $.ajax({
        url: "user/updateHeadImg",//改这里
        type: "post",
        data: formData,
        cache: false,
        processData: false,
        contentType: false,
        success: function (result) {
            console.log(result);
            if (result.success) {
                history.go(0);
            }
        },
        error: function () {
            alert(result.errorMsg);
        }
    });
});


function deletemessage(obj) {
    let parent = obj.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode;
    let child = obj.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode;
    let childId = child.id;
    $.ajax({
        type: "POST",
        url: "message/delPersonalMessage",
        data: {
            messageId: childId
        },
        success: function (result) {
          if (result.success)
            parent.removeChild(child);
        }

    })
}