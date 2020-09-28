var imgFile = []; //文件流
var imgSrc = []; //图片路径
var imgName = []; //图片名字
var vidorpic = true
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

$(function () {
    // 鼠标经过显示删除按钮
    $('.content-img-list').on('mouseover', '.content-img-list-item', function () {
        $(this).children('a').removeClass('hide');
    });
    // 鼠标离开隐藏删除按钮
    $('.content-img-list').on('mouseleave', '.content-img-list-item', function () {
        $(this).children('a').addClass('hide');
    });
    // 单个图片删除
    $(".content-img-list").on("click", '.content-img-list-item a', function () {
        var index = $(this).attr("index");
        imgSrc.splice(index, 1);
        imgFile.splice(index, 1);
        imgName.splice(index, 1);
        var boxId = ".content-img-list";
        addNewContent(boxId);
        if (imgSrc.length < 4) {//显示上传按钮
            $('.content-img .file').show();
        }
    });
    //图片上传
    $('#upload').on('change', function () {
        vidorpic = false;
        if (imgSrc.length >= 4) {
            return alert("最多只能上传4张图片");
        }
        var imgSize = this.files[0].size;  //b
        if (imgSize > 1024 * 1024 * 1) {//1M
            return alert("上传图片不能超过1M");
        }
        console.log(this.files[0].type)
        if (this.files[0].type != 'image/png' && this.files[0].type != 'image/jpeg' && this.files[0].type != 'image/gif') {
            return alert("图片上传格式不正确");
        }

        var imgBox = '.content-img-list';
        var fileList = this.files;
        for (var i = 0; i < fileList.length; i++) {
            var imgSrcI = getObjectURL(fileList[i]);
            imgName.push(fileList[i].name);
            imgSrc.push(imgSrcI);
            imgFile.push(fileList[i]);
        }
        if (imgSrc.length == 4) {//隐藏上传按钮
            $('.content-img .file').hide();
        }
        addNewContent(imgBox);
        this.value = null;//解决无法上传相同图片的问题
    })

    //提交请求
    $('#article-send').on('click', function () {
        var radios = document.getElementsByName("radio");
        var lable = 0;
        for (var i = 0; i < radios.length; i++) {
            if (radios[i].checked == true) {
                lable = radios[i].value;
            }
        };
        console.log(lable);
        // FormData上传图片
        var messageInfo = $("#messageInfo").val();
        var formFile = new FormData();
        let userId = $.cookie("userId");
        // 遍历图片imgFile添加到formFile里面
        formFile.append("messageInfo", messageInfo);
        formFile.append("userId", userId);
        formFile.append("lable", lable);
        if (!vidorpic) {
            $.each(imgFile, function (i, file) {
                formFile.append("files", file);
            });

            $.ajax({
                url: "message/saveMessageOnImg",
                type: 'POST',
                data: formFile,
                async: true,
                cache: false,
                contentType: false,
                processData: false,
                success: function (result) {
                    if (result.success)
                        history.go(0);
                    else
                        alert(result.errMsg);
                },
            })
        } else {
            var form = new FormData();
            var file = $('#files')[0].files[0];
            form.append("file", file);
            form.append("messageInfo", messageInfo);
            form.append("userId", userId);
            form.append("lable", lable);
            $.ajax({
                url: "message/saveMessageOnVideo",
                type: 'POST',
                data: form,
                async: true,
                cache: false,
                contentType: false,
                processData: false,
                success: function (result) {
                    if (result.success)
                        history.go(0);
                    else {
                        alert(result.errMsg);
                        console.log("hhhh");
                    }
                },

            })
        }
    })
});


//删除
function removeImg(obj, index) {
    imgSrc.splice(index, 1);
    imgFile.splice(index, 1);
    imgName.splice(index, 1);
    var boxId = ".content-img-list";
    addNewContent(boxId);
}

//图片展示
function addNewContent(obj) {
    $(obj).html("");
    for (var a = 0; a < imgSrc.length; a++) {
        var oldBox = $(obj).html();
        $(obj).html(oldBox + '<li class="content-img-list-item"><img src="' + imgSrc[a] + '" alt=""><a index="' + a + '" class="hide delete-btn"><i class="ico-delete"></i></a></li>');
    }
}

//建立一個可存取到該file的url
function getObjectURL(file) {
    var url = null;
    if (window.createObjectURL != undefined) { // basic
        url = window.createObjectURL(file);
    } else if (window.URL != undefined) { // mozilla(firefox)
        url = window.URL.createObjectURL(file);
    } else if (window.webkitURL != undefined) { // webkit or chrome
        url = window.webkitURL.createObjectURL(file);
    }
    return url;


}

$("#pic").click(function () {
    document.getElementsByClassName("upload-content-img")[0].style.display = "block";
});

$("#vid").click(function () {
    document.getElementsByClassName("files")[0].style.display = "block";
});


$("#article-classify").click(function () {
    // document.getElementById("seclctlable").style.display="block";
    $.ajax({
        type: "POST",
        url: "lable/getAllLable",
        success: function (result) {
            if (result.success) {
                let parentlable = document.getElementsByClassName("seclctlable")[0];
                for (let i = 0; i < result.data.length; i++) {
                    // console.log(result.data[i].lableInfo);
                    // console.log(result.data[i].lableId);
                    let divlable = document.createElement("li");
                    divlable.className = "box";
                    divlable.innerHTML = "<div class=\"box\">\n" +
                        "        <input type=\"radio\"  id='" + result.data[i].lableId + "' name=\"radio\" value='" + result.data[i].lableInfo + "' /><label for='" + result.data[i].lableId + "'>" + result.data[i].lableInfo + "</label>\n" +
                        "    </div>\n" +
                        "    <div class=\"line\"></div>"
                    parentlable.appendChild(divlable);
                }

            }
        }
    });
});


window.onload = function () {
    let userId = $.cookie("userId");
    $.ajax({
        type: "POST",
        url: "message/messageInfoPage",//改这里
        data: {
            userId: userId,
        },
        success: function (result) {
            console.log(result.data);
            if (result.success) {
                document.getElementById("headp").src = result.data.user.userImg;
                $("#usernickname").text(result.data.user.userName);//这里改用户名
                $("#we-num").text(result.data.user.userMsgcount);//这里改说说数量
                $("#fans-num").text(result.data.user.userFans);//这里改粉丝数量
                $("#follow-num").text(result.data.user.followCount);//这里改粉丝数量
                let parentdiv = document.getElementsByClassName("blogs")[0];
                for (let i = 0; i < result.data.messages.length; i++) {
                    let article = document.createElement("li");
                    article.id = result.data.messages[i].message.messageId;
                    article.innerHTML = "<div class=\"blog-wrapper\">\n" +
                        "                                <!--个人信息分为左右两部分-->\n" +
                        "                                <div class=\"blog-user-info\"    name='"+result.data.messages[i].user.userId+"'>\n" +
                        "                                    <!--头像 昵称 发送时间-->\n" +
                        "                                    <div class=\"B-left\">\n" +
                        "                                        <div class=\"left-avatar\"><img src='" + result.data.messages[i].user.userImg + "' alt = \"\" onclick='friend(this)'></div>\n" +
                        "                                        <div class=\"name-time\">\n" +
                        "                                            <h2 class=\"blog-nickname\">" + result.data.messages[i].user.userName + "</h2>\n" +
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
                        "                                    <div class='blog-img-video' >\n" +
                        "                                    </div>\n" +
                        "                                </div>\n" +
                        "                                <!--评论 点赞等-->\n" +
                        "                                <!--评论 点赞等-->\n" +
                        "                                <div class=\"blog-views\">\n" +
                        "                                    <span>阅读&nbsp;<span class=\"read-num\">" + result.data.messages[i].message.messageReadNum + "</span></span>\n" +
                        "                                    <a href = \"javascript:;\" class=\"forward\" onclick='forward(this)'>转发" + result.data.messages[i].message.messageTranspondNum + "</a>\n" +
                        "                                    <a href = \"javascript:;\" class=\"comment\">评论" + result.data.messages[i].message.messageCommentNum + "</a>\n" +
                        "                                    <a href = \"javascript:;\" class=\"praise\" onclick='agree(this)'>点赞" + result.data.messages[i].message.messageAgreeNum + "</a>\n" +
                        "                                    <a href = \"javascript:;\" class=\"praise\" onclick='collect(this)'>收藏" + result.data.messages[i].message.messageCollectNum + "</a>\n" +
                        "                                </div>\n" +
                        "                            </div>";

                    parentdiv.appendChild(article);
                    if (result.data.messages[i].imgs.length != 0) {
                        var blogimgvideos = document.getElementsByClassName("blog-img-video")[i];
                        for (let j = 0; j < result.data.messages[i].imgs.length; j++) {
                            let images = document.createElement("img");
                            images.id = result.data.messages[i].imgs[j].imgId;
                            images.className = "artimg";
                            images.src = result.data.messages[i].imgs[j].imgUrl
                            blogimgvideos.appendChild(images);
                        }
                    }
                    if (result.data.messages[i].video != null) {
                        let blogimgvideos = document.getElementsByClassName("blog-img-video")[i];
                        let videos = document.createElement("video");
                        videos.id = result.data.messages[i].video.videoId;
                        videos.src = result.data.messages[i].video.videoUrl;
                        blogimgvideos.appendChild(videos);
                        videos.setAttribute("controls", "true");
                    }
                }

            }
        }
    })

};


// 收藏
function collect(obj) {

    let parentid = obj.parentNode.parentNode.parentNode.id;
    console.log(parentid);
    let userId = $.cookie("userId");
    console.log(userId);
    $.ajax({
        type: "POST",
        url: "collection/addCollection",//改这里
        data: {
            userId: userId,
            messageId: parentid
        },
        success: function (result) {
            if (result.success) {
                var text = obj.innerText;
                var num = 1 + parseInt(text.match(/\d+/g));
                obj.innerHTML = "收藏" + num;
                // alert(result.data)
            } else {
                alert(result.errorMsg)
            }
        },
    })
}

//取消收藏
function uncollect(obj) {

    let messageid = obj.parentNode.parentNode.parentNode.id;
    // console.log(parentid);
    let userId = $.cookie("userId");
    console.log(userId);
    $.ajax({
        type: "POST",
        url: "collection/delCollection",//改这里
        data: {
            userId: userId,
            messageId: messageid
        },
        success: function (result) {
            if (result.success) {
                let uls = document.getElementsByClassName("blogs")[0];
                let lis = document.getElementById(obj.parentNode.parentNode.parentNode.id);
                uls.removeChild(lis);
                // alert(result.data)
            } else {
                alert(result.errorMsg)
            }
        },
    })
}

// 点赞
function agree(obj) {

    let parentid = obj.parentNode.parentNode.parentNode.id;
    let userId = $.cookie("userId");
    $.ajax({
        type: "POST",
        url: "agree/addAgree",//改这里
        data: {
            userId: userId,
            messageId: parentid
        },
        success: function (result) {
            if (result.success) {
                var text = obj.innerText;
                var num = 1 + parseInt(text.match(/\d+/g));
                obj.innerHTML = "点赞" + num;
            } else {
                alert(result.errorMsg)
            }
        },
    })
}


// 转发
function forward(obj) {
    let parentid = obj.parentNode.parentNode.parentNode.id;
    let userId = $.cookie("userId");
    console.log(parentid);
    console.log(userId);
    $.ajax({
        type: "POST",
        url: "forward",//改这里
        data: {
            userId: userId,
            messageId: parentid
        },
        success: function (result) {
            console.log(result);
            if (result.success) {
                history.go(0);
                alert(result.data)
            } else {
                alert(result.errorMsg)
            }
        },
    })
}


$("#collect").click(function () {

    let parentdiv = document.getElementsByClassName("blogs")[0];
    parentdiv.innerHTML = "";
    let writearticle = document.getElementsByClassName("writearticle")[0];
    writearticle.innerHTML = "";
    let userId = $.cookie("userId");
    $.ajax({
        type: "POST",
        url: "collection/collectionInfoPage",//改这里
        data: {
            userId: userId,
        },
        success: function (result) {
            console.log(result.data);
            if (result.success) {
                for (let i = 0; i < result.data.length; i++) {
                    let article = document.createElement("li");
                    article.id = result.data[i].messageInfo.message.messageId;
                    article.innerHTML = "<div class=\"blog-wrapper\">\n" +
                        "                                <!--个人信息分为左右两部分-->\n" +
                        "                                <div class=\"blog-user-info\">\n" +
                        "                                    <!--头像 昵称 发送时间-->\n" +
                        "                                    <div class=\"B-left\">\n" +
                        "                                        <div class=\"left-avatar\"><img src='" + result.data[i].user.userImg + "' alt = \"\"></div>\n" +
                        "                                        <div class=\"name-time\">\n" +
                        "                                            <h2 class=\"blog-nickname\">" + result.data[i].user.userName + "</h2>\n" +
                        "                                            <h3 class=\"send-time\">" + result.data[i].messageInfo.message.messageCtime + "</h3>\n" +
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
                        "                                    <p class=\"blog-text\" onclick='readArticle(this)'>\n" + result.data[i].messageInfo.message.messageInfo + "</p>\n" +
                        "                                    <!-- 可能动态中只有文字 -->\n" +
                        "                                    <div class='blog-img-video' >\n" +
                        "                                    </div>\n" +
                        "                                </div>\n" +
                        "                                <!--评论 点赞等-->\n" +
                        "                                <!--评论 点赞等-->\n" +
                        "                                <div class=\"blog-views\">\n" +
                        "                                    <span>阅读&nbsp;<span class=\"read-num\">" + result.data[i].messageInfo.message.messageReadNum + "</span></span>\n" +
                        "                                    <a href = \"javascript:;\" class=\"comment\">评论" + result.data[i].messageInfo.message.messageCommentNum + "</a>\n" +
                        "                                    <a href = \"javascript:;\" class=\"praise\" onclick='agree(this)'>点赞" + result.data[i].messageInfo.message.messageAgreeNum + "</a>\n" +
                        "                                    <a href = \"javascript:;\" class=\"praise\" onclick='uncollect(this)'>取消收藏</a>\n" +
                        "                                </div>\n" +
                        "                            </div>";

                    parentdiv.appendChild(article);
                    if (result.data[i].messageInfo.imgs.length != 0) {
                        var blogimgvideos = document.getElementsByClassName("blog-img-video")[i];
                        for (let j = 0; j < result.data[i].messageInfo.imgs.length; j++) {
                            let images = document.createElement("img");
                            images.id = result.data[i].messageInfo.imgs[j].imgId;
                            images.className = "artimg";
                            images.src = result.data[i].messageInfo.imgs[j].imgUrl
                            blogimgvideos.appendChild(images);
                        }
                    }
                    if (result.data[i].messageInfo.video != null) {
                        let blogimgvideos = document.getElementsByClassName("blog-img-video")[i];
                        let videos = document.createElement("video");
                        videos.id = result.data[i].messageInfo.video.videoId;
                        videos.src = result.data[i].messageInfo.video.videoUrl;
                        blogimgvideos.appendChild(videos);
                        videos.setAttribute("controls", "true");
                    }
                }

            }
        }
    })

})

function readArticle(obj) {
    $.cookie("messageId", obj.parentNode.parentNode.parentNode.id);
    window.location.href = "particulars.html";
}


function search() {
    let parentdiv = document.getElementsByClassName("blogs")[0];
    parentdiv.innerHTML = "";
    console.log("jj");
    var searchInfo = $("#search").val();
    $.ajax({
        type: "POST",
        url: "message/search",//改这里
        data: {
            searchInfo: searchInfo,
        },
        success: function (result) {
            console.log(result.data);
            if (result.success) {
                let parentdiv = document.getElementsByClassName("blogs")[0];
                let messageinfos = result.data.messageAndAllInfos;
                console.log(messageinfos);
                for (let i = 0; i < messageinfos.length; i++) {
                    let article = document.createElement("li");
                    article.id = messageinfos[i].message.messageId;
                    article.innerHTML = "<div class=\"blog-wrapper\">\n" +
                        "                                <!--个人信息分为左右两部分-->\n" +
                        "                                <div class=\"blog-user-info\" >\n" +
                        "                                    <!--头像 昵称 发送时间-->\n" +
                        "                                    <div class=\"B-left\">\n" +
                        "                                        <div class=\"left-avatar\"><img src='" + messageinfos[i].user.userImg + "' alt = \"\" ></div>\n" +
                        "                                        <div class=\"name-time\">\n" +
                        "                                            <h2 class=\"blog-nickname\">" + messageinfos[i].user.userName + "</h2>\n" +
                        "                                            <h3 class=\"send-time\">" + messageinfos[i].message.messageCtime + "</h3>\n" +
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
                        "                                    <p class=\"blog-text\" onclick='readArticle(this)'>\n" + messageinfos[i].message.messageInfo + "</p>\n" +
                        "                                    <!-- 可能动态中只有文字 -->\n" +
                        "                                    <div class='blog-img-video' >\n" +
                        "                                    </div>\n" +
                        "                                </div>\n" +
                        "                                <!--评论 点赞等-->\n" +
                        "                                <!--评论 点赞等-->\n" +
                        "                                <div class=\"blog-views\">\n" +
                        "                                    <span>阅读&nbsp;<span class=\"read-num\">" + messageinfos[i].message.messageReadNum + "</span></span>\n" +
                        "                                    <a href = \"javascript:;\" class=\"forward\" onclick='forward(this)'>转发" + messageinfos[i].message.messageTranspondNum + "</a>\n" +
                        "                                    <a href = \"javascript:;\" class=\"comment\">评论" + messageinfos[i].message.messageCommentNum + "</a>\n" +
                        "                                    <a href = \"javascript:;\" class=\"praise\" onclick='agree(this)'>点赞" + messageinfos[i].message.messageAgreeNum + "</a>\n" +
                        "                                    <a href = \"javascript:;\" class=\"praise\" onclick='collect(this)'>收藏" +messageinfos[i].message.messageCollectNum + "</a>\n" +
                        "                                </div>\n" +
                        "                            </div>";

                    parentdiv.appendChild(article);

                    if (messageinfos[i].imgs.length != 0) {
                        var blogimgvideos = document.getElementsByClassName("blog-img-video")[i];
                        for (let j = 0; j < messageinfos[i].imgs.length; j++) {
                            let images = document.createElement("img");
                            images.id = messageinfos[i].imgs[j].imgId;
                            images.className = "artimg";
                            images.src = messageinfos[i].imgs[j].imgUrl
                            blogimgvideos.appendChild(images);
                        }
                    }
                    if (messageinfos[i].video != null) {
                        let blogimgvideos = document.getElementsByClassName("blog-img-video")[i];
                        let videos = document.createElement("video");
                        videos.id = messageinfos[i].video.videoId;
                        videos.src = messageinfos[i].video.videoUrl;
                        blogimgvideos.appendChild(videos);
                        videos.setAttribute("controls", "true");
                    }
                }

            }
        }
    })

}

function friend(obj) {
    // let userId = $.cookie("userId");

    let friendName = obj.parentNode.parentNode.parentNode;

    var friendId=friendName.getAttribute('name');
    // console.log(typeof friendId.toString());
    // console.log(typeof userId);
    console.log(friendId);
    // console.log(userId);
    $.cookie("friendId", friendId);
    window.location.href = "friend.html";

}