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
    let friendId= $.cookie("friendId");
    $.ajax({
        type: "POST",
        url: "user/personalInfoPage",
        data: {
            userId: friendId,
            pageNum: page
        },
        success: function (result) {
            console.log(result.data);
            if (result.success) {
                document.getElementById("headp").src = result.data.user.userImg;
                $("#nickname").text(result.data.user.userName);//这里改用户名
                $("#we-num").text(result.data.user.userMsgcount);//这里改说说数量
                $("#fans-num").text(result.data.user.userFans);//这里改粉丝数量
                pagenums = result.data.messageNums;//这里改页数
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
                            "                                                <a href = \"javascript:;\">删除</a>\n" +
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
                            var blogimgvideos = document.getElementsByClassName("blog-img-video")[i];
                            for (let j = 0; j < result.data.messages[i].imgs.length; j++) {
                                let images = document.createElement("img");
                                images.id = result.data.messages[i].imgs[j].imgUrl;
                                blogimgvideos.appendChild(images);
                            }
                        }
                        if (result.data.messages[i].video != null) {
                            var blogimgvideos = document.getElementsByClassName("blog-img-video")[i];
                            let videos = document.createElement("video");
                            videos.id = result.data.messages[i].video.videoId;
                            videos.src = result.data.messages[i].video.videoUrl;
                            blogimgvideos.appendChild(videos);
                        }
                    }
                } else {
                    alert("他还没发表过任何动态");
                }
            } else {
                alert(result.errorMsg);
            }
        }
    })
}



function readArticle(obj) {
    let articleId = obj.parentNode.parentNode.parentNode.id;
    $.cookie("messageId", articleId);
    window.location.href = "particulars.js";//改这里
}

var follows=true;
$("#modify").click(function () {
    let userId = $.cookie("userId");
    let friendId = $.cookie("friendId");
    if(follows) {
        follows=false;
        let lable = prompt("请输入关注好友的类型(必填)");
        if(lable != null || lable != ""){
        $.ajax({
            type: "POST",
            url: "relation/addRelation",
            data: {
                userId: userId,
                userById: friendId,
                lable:lable
            },
            success: function (result) {
                if (result.success){
                    alert(result.data);
                    $("#modify").text("取消关注")
                }
            }
        })
        }else {
            alert("未填写关注类型，关注失败")
        }
        }else {
        follows=true;
        $.ajax({
            type: "POST",
            url: "relation/delRelation",
            data: {
                userId: userId,
                userById: friendId
            },
            success: function (result) {
                if (result.success){
                    alert(result.data);
                    $("#modify").text("取消关注")
                }
            }
    })
    }
})
