$("#sysMessage").click(
    function () {
        var div = document.createElement("div");
        div.className = "sysMessage";
        var select = document.createElement("select");
        select.innerHTML = "  <option value=\"1\">@我的</option>\n" +
            "    <option value=\"1\">评论</option>\n" +
            "    <option value=\"1\">私信</option>\n" +
            "    <option value=\"1\">消息设置</option>";
        div.append(select);
      this.styl
        this.parentNode.append(div);
    }
)

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