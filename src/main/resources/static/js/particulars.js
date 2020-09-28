/**
* Author:tcp
*
*
* */
var comment_replyId;
var comment_user;
var messageId = $.cookie("messageId")
var userName = $.cookie("userName");
onload = function () {
// console.log("hello,world")
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



// console.log(messageId)
$.ajax({
url: "message/getMessage",
type: "POST",
data: {messageId: messageId},
success: function (data) {


var user = data.data.user;
var message = data.data.message;
var commens = data.data.comments;

var message1 = document.createElement("div");
message1.className = "message";
message1.innerHTML = " <div class=\"head\">\n" +
    "                    <img src='" + user.userImg + "' class=\"round_icon\">\n" +
    "                </div>\n" +
"                <div class=\"msg\">\n" +
    "                    <div class=\"name\">\n" +
        "\n" +
        "                        <a id=\"but1\">" + user.userName + "</a>\n" +
        "\n" +
        "                        <a id=\"but2\">➕ 关注</a>\n" +
        "                    </div>\n" +
    "\n" +
    "                    <div class=\"sub\">\n" +

        "                       " + message.message.messageInfo + " <div id=\"mediaList\" style=\"width: 100%;height: 400px;display: none;float: left;background-color:red;\"></div>\n" +
    "\n" +
    "\n" +
    "                    </div>\n" +
"                </div>"

// console.log(message1)
document.getElementById("thing").prepend(message1)

// console.log(commens);
for (var i = 0; i < commens.length; i++) {
var comment = document.createElement("div");
comment.className = "comment"
comment.user = "self";
comment.innerHTML = " <div class=\"comment-right\" id='" + commens[i].comment.commentId + "'>\n" +
    "                                        <div class=\"comment-text\"><span class=\"user\">" + commens[i].userName + ":</span>" + commens[i].comment.commentInfo + "</div>\n" +
    "                                        <div class=\"comment-date\">\n" +
        "                                            <br>\n" +
        "                                            " + commens[i].comment.commentTime + "" +
        "                                            <a class=\"comment-dele\" href=\"javascript:;\" onclick='reply(this)'>回复</a>\n" +
        "                                        </div>\n" +
    "                                    </div>"
document.getElementById("comment-list").append(comment);

}

}
})
}

function send(obj) {


var textinput = obj.parentNode.children[0];
var userId = $.cookie("userId");
var commentInfo = textinput.value;
var sendType = textinput.getAttribute("name");
// console.log(sendType + ":sendtype")
if (sendType == "root") {

$.ajax({
url: "comment/addComment",
type: "post",
data: {
userId: userId,
messageId: messageId,
commentInfo: commentInfo
},
dataType: "JSON",
success: function (data) {
// console.log(data);
/**
* data:{
*     userName,commentId,commentTime
* }
* @type {string}
*
*
*/

var div_comment = document.createElement("div");
div_comment.className = "comment"
div_comment.user = "self";
div_comment.innerHTML = "<div class=\"comment-right\" id='" + data.data.commentId + "'>\n" +
    "                                        <div class=\"comment-text\"><span class=\"user\">" + userName + ":</span>" + commentInfo + "</div>\n" +
    "                                        <div class=\"comment-date\">\n" +
        "                                            <br>\n" +
        "                                            " + data.data.commentTime + "" +
        "                                            <a class=\"comment-dele\" href=\"javascript:;\" onclick='reply(this)'>回复</a>\n" +
        "                                        </div>\n" +
    "                                    </div>"


document.getElementById("comment-list").append(div_comment);

}

})
} else if (sendType == "son") {
var replyInfo = $("#text").val();
// console.log("userId:" + userId + "\ncomment_replyId:" + comment_replyId + "\nreplyInfo:" + replyInfo + "...........................")
$.ajax({
url: "reply/saveReply",
type: "POST",
data: {
userId: userId,
parentId: comment_replyId,
replyInfo: replyInfo,
},
success: function (data) {

if (comment_user.indexOf(':') > 0) {
comment_user = comment_user.substr(0, comment_user.indexOf(':'))
}

// console.log("comment_user:"+comment_user)
var reply = data.data;
// console.log(data)
var comment = "<div class=\"comment\" user=\"self\">\n" +
    "                                    <div class=\"comment-right\" id='" + reply.replyId + "'>\n" +
        "                                        <div class=\"comment-text\"><span class=\"user\">" + userName + ":@回复" + comment_user + "</span>" + ':' + replyInfo + "</div>\n" +
        "                                        <div class=\"comment-date\">\n" +
            "                                            <br>\n" +
            "                                           " + reply.replyTime + "\n" +
            "                                            <a class=\"comment-dele\" href=\"javascript:;\" onclick='reply(this)'>回复</a>\n" +
            "                                        </div>\n" +
        "                                    </div>\n" +
    "                                </div>";


/**
* 将生成的comment追加到 document.getElementById(comment_replyId)之后
*/
$(comment).insertAfter($(document.getElementById(comment_replyId)));
document.getElementById("text").setAttribute("name", "root");
document.getElementById("text").innerText = "";


},
error: function (data) {
console.log(data)

}
})

}


// console.log("change className....")
document.getElementById("text").parentNode.className = 'hf';
document.getElementById("text").value = '评论…';


}

function reply(obj) {

// obj.onclick=null;
document.getElementById("text").parentNode.className = 'hf hf-on';
document.getElementById("text").value = document.getElementById("text").value == '评论…' ? '' : document.getElementById("text").value;
document.getElementById("text").focus()
comment_replyId = obj.parentNode.parentNode.id;

// console.log("comment_replyId:" + comment_replyId)
comment_user = obj.parentNode.parentNode.children[0].children[0].innerText;
// comment_user = comment_user.substr(0, comment_user.indexOf(":"))

$.ajax({
url: "reply/getReplysById",
data: {parentId: comment_replyId},
type: "POST",
success: function (data) {
// console.log(data)
var reples = data.data;
if (reples != "没有数据") {
for (var i = 0; i < reples.length; i++) {
var reply = reples[i];
var comment = "<div class=\"comment\" user=\"self\">\n" +
    "                                    <div class=\"comment-right\"  id='" + reply.reply.replyId + "' >\n" +
        "                                        <div class=\"comment-text\"><span class=\"user\">" + reply.userName + ":@回复" + reply.byUserName + "</span>" + ':' + reply.reply.replyInfo + "</div>\n" +
        "                                        <div class=\"comment-date\">\n" +
            "                                            <br>\n" +
            "                                           " + reply.reply.replyTime + "\n" +
            "                                            <a class=\"comment-dele\" href=\"javascript:;\" onclick='reply(this)'>回复</a>\n" +
            "                                        </div>\n" +
        "                                    </div>\n" +
    "                                </div>";


/**
* 将生成的comment追加到 document.getElementById(comment_replyId)之后
*/
$(comment).insertAfter($(document.getElementById(comment_replyId).parentNode));
}
}


},
}
);
var text = document.getElementById("text");
text.setAttribute("name", "son");
text.focus();

}

