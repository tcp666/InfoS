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

    let userId = $.cookie("userId");
    $.ajax({
        type: "POST",
        url: "relation/relationInfoPage",//改这里的路径
        data: {userId: userId},//
        success: function (result) {
            if (result.success) {
                document.getElementById("headp").src = result.data.user.userImg;
                $("#userName").text(result.data.user.userName);
                $("#userPersonalized").text(result.data.user.userPersonalized);
                let friendlist = document.getElementsByClassName("friendlist")[0];
                for (let i = 0; i < result.data.followCount; i++) {//改这里的人数
                    let friend = document.createElement("div");
                    friend.id=result.data.users[i].userId;
                    friend.className = "friend";
                    friend.innerHTML = "<div class=\"friend_left\">\n" +
                        "                        <img src='"+result.data.users[i].userImg+"' alt=\"\">\n" +//改这里
                        "                    </div>\n" +
                        "                    <div class=\"friend_right\" onclick='person(this)'>\n" +
                        "                        <li><a>"+result.data.users[i].userName+"</a></li>\n" +
                        "                        <li><a>互相关注</a></li>\n" +
                        "                        <li><a>"+result.data.users[i].userPersonalized+"</a></li>\n" +
                        "                        <li><a>通过<font color=\"#ff6025\">微博推荐</font> 关注</a></li>\n" +
                        "                    </div>"
                    friendlist.appendChild(friend);
                }
            }
        }
    });
};

function person(obj) {
    let friendId=obj.parentNode.id;
    $.cookie("friendId", friendId);
    window.location.href="friend.html";
}