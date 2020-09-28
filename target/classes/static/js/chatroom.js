

/**
 * ws:localhost:80//websocket/
 * localhost:80分别为主机ip和端口
 * @type {jQuery|*}
 */

let comId = $.cookie("userId");
let socket = null;
let comImg=null;
let comUserName=null;
let toId=null;
let toImg;
onload = function () {
    socket = new WebSocket("ws:localhost:80//websocket/" + comId);
    socket.onopen = function () {
        console.log("454546")
    }
    socket.onmessage = function (data) {
        console.log("data:" + data)
        var record = JSON.parse(data.data);
        var div = "    <div class=\"user-left\">\n" +
            "                        <div class=\"img-wrapper-left\">\n" +
            "                            <img src = '"+toImg+"' alt = ''>\n" +
            "                        </div>\n" +
            "                        <!--向上的小三角-->\n" +
            "                        <div class=\"triangle-left\"></div>\n" +
            "                        <div class=\"mes-left\">" + record.message + "</div>\n" +
            "                    </div>"
        $("#messages").append(div);

        var messages = document.getElementById("messages");
        messages.scrollTop = messages.scrollHeight;
    }



    $.ajax({
        url:"user/getUserById",
        data:{userId:comId},
        success:function (data) {
            comImg=data.data.userImg;
            comUserName=data.data.userName;
        }

    })


    $.ajax({
            url: "user/getAllUser",
            type: "POST",
            data: {},
            dataType: "JSON",
            success: function (data) {
                console.log(data);
                for (var i = 0; i < data.length; i++) {
                    if (data[i].userId != comId) {
                        console.log(data[i].userId)
                        var li = "  <li id='"+data[i].userId+"' onclick='chat(this)'>\n" +
                            "                            <div class=\"user-avatar-wrapper\">\n" +
                            "                                <a href = \"javascript:;\">" +
                            "<img src='"+data[i].userImg+"' alt=\"\"></a>\n" +
                            "                            </div>\n" +
                            "                            <div class=\"user-info\">\n" +
                            "                                <div class=\"user-info-top\">\n" +
                            "                                    <a href = \"javascript:;\">" +
                            "<div class=\"user-name\">" + data[i].userName + "</div>" +
                            "</a>\n" +
                            "                                    <div class=\"time\">05-25</div>\n" +
                            "                                </div>\n" +
                            "                                <div class=\"user-info-content\">【签到提醒】</div>\n" +
                            "                            </div>\n" +
                            "                    </li>\n" +
                            "                  "
                        $("#left-userList").append(li);
                    }
                }
            },
            error: function () {
                console.log("400000000000")
            }

        }
    )

}
$("#send").click(function () {
    var text = $("#text-mes").val();
    console.log("text:" + text)
    var data = {
        toUserId: toId,
        comUserId: comId,
        message: text,
        recordTime: new Date()
    }
    var div = "    <div class=\"user-right\">\n" +
        "                        <div class=\"img-wrapper-right\">\n" +
        "                            <img src = '"+comImg+"'>\n" +
        "                        </div>\n" +
        "                        <!--向上的小三角-->\n" +
        "                        <div class=\"triangle-right\"></div>\n" +
        "                        <div class=\"mes-right\">"+text+"</div>\n" +
        "                    </div>"
    $("#messages").append(div);
    socket.send(JSON.stringify(data))

    var messages = document.getElementById("messages");
    messages.scrollTop = messages.scrollHeight;
})

/**
 * 选择聊天对象
 * @param obj
 */
function chat(obj) {
    var userName= obj.children[1].children[0].children[0].children[0].innerText;
    var userId=obj.id;
    toId=userId;
    var userImg=obj.children[0].children[0].children[0].src;
    toImg=userImg;
    comId = $.cookie("userId");
    console.log("to:" + userId + "com" + comId)
    $("#friend_name").html(userName)
    $("#user-right").html("")
    $.ajax({
        url: "chat/getRecords",
        data: {
            comUserId: comId,
            toUserId: userId
        },
        type: "POST",
        success: function (data) {
            console.log(data)
            $("#messages").text("");
            for (var i = 0, length = data.length; i < length; i++) {
                if (data[i].comUserId == comId) {
                    var div = "<div class=\"user-right\">\n" +
                        "                        <div class=\"img-wrapper-right\">\n" +
                        "                            <img src = '"+comImg+"' >\n" +
                        "                        </div>\n" +
                        "                        <!--向上的小三角-->\n" +
                        "                        <div class=\"triangle-right\"></div>\n" +
                        "                        <div class=\"mes-right\">"+data[i].message+"</div>\n" +
                        "                    </div>"
                    $("#messages").append(div);
                } else {
                    var divr = "    <div class=\"user-left\">\n" +
                        "                        <div class=\"img-wrapper-left\">\n" +
                        "                            <img src = '"+userImg+"'>\n" +
                        "                        </div>\n" +
                        "                        <!--向上的小三角-->\n" +
                        "                        <div class=\"triangle-left\"></div>\n" +
                        "                        <div class=\"mes-left\">" +data[i].message+ "</div>\n" +
                        "                    </div>"
                    $("#messages").append(divr);
                    var messages = document.getElementById("messages");
                    messages.scrollTop = messages.scrollHeight;
                }
            }
        }
    })
}
