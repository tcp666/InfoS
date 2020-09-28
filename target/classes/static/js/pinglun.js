/**
 * 平论功能；
 *
 */
var comment_replyId;
var comment_user;
var messageId = $.cookie("messageId")
var userName = $.cookie("userName");
onload = function () {

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



    $.ajax({
        url: "message/getMessageById",
        type: "POST",
        data: {messageId: messageId},
        success: function (data) {
            var user = data.user;
            var message = data.message;
            var commens = data.comments;

            var message = "   <div class=\"message\">\n" +
                "                <div class=\"head\" id='" + user.userId + "'>\n" +
                "                    <img src='" + user.userImg + "' class=\"round_icon\">\n" +
                "                </div>\n" +
                "                <div class=\"msg\">\n" +
                "                    <div class=\"name\">\n" +
                "                        <a id=\"but1\">" + user.userName + "</a>\n" +
                "                        <a id=\"but2\" onclick='guanzhu(" + user.userId + ")'>➕ 关注</a>\n" +
                "                    </div>\n" +
                "                    <div class=\"sub\">\n" + message.messageInfo +

                "                    </div>\n" +
                "                    \n" +
                "                </div>\n" +
                "            </div>"

            $("#thing").appendChild(message);

            for (var i = 0; i < commens.length; i++) {
                var comment = "    <div class=\"comment\" user=\"self\">\n" +
                    "                                    <div class=\"comment-right\" id='" + commens[i].comment.commentId + "'>\n" +
                    "                                        <div class=\"comment-text\"><span class=\"user\">" + commens[i].comment.userName + "：</span>" + commens[i].comment.commentInfo + "</div>\n" +
                    "                                        <div class=\"comment-date\">\n" +
                    "                                            <br>\n" +
                    "                                           " + commens[i].comment.commentTime + "\n" +
                    "                                            <a class=\"comment-dele\" href=\"javascript:;\" onclick='reply(this)'>回复</a>\n" +
                    "                                        </div>\n" +
                    "                                    </div>\n" +
                    "                                </div>"

                $("#comment-list").appendChild(comment);

            }

        }
    })
}

function send(obj) {

    console.log("0000000000000000000000000")


    var textinput = obj.parentNode.children[0];
    var userId = $.cookie("userId");
    var commentInfo = textinput.value;
    var sendType = textinput.getAttributeNode("name")
    if (sendType == "root") {
        $.ajax({
            url: "comment/save",
            type: "post",
            data: {
                userId: userId,
                messageId: messageId,
                commentInfo: commentInfo
            },
            dataType: "JSON",
            success: function (data) {
                /**
                 * data:{
                 *     userName,commentId,commentTime
                 * }
                 * @type {string}
                 */
                var comment = "<div class=\"comment\" user=\"self\">\n" +
                    "                                    <div class=\"comment-right\" id='" + data.data.commentId + "'>\n" +
                    "                                        <div class=\"comment-text\"><span class=\"user\">" + userName + "：</span>" + data.data.commentInfo + "</div>\n" +
                    "                                        <div class=\"comment-date\">\n" +
                    "                                            <br>\n" +
                    "                                           " + data.data.commentTime + "\n" +
                    "                                            <a class=\"comment-dele\" href=\"javascript:;\" onclick='reply(this)'>回复</a>\n" +
                    "                                        </div>\n" +
                    "                                    </div>\n" +
                    "                                </div>"
                $("#comment-list").appendChild(comment);

            }

        })
    } else if (sendType == "son") {
        var replyInfo = $("#text").val();
        $.ajax({
            url: "reply/saveReply",
            type: "POST",
            data: {
                UserId: userId,
                parentId: comment_replyId,
                replyInfo: replyInfo,
            },
            success: function (data) {
                var reply = data.data;
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


            }
        })

    }


    console.log("change className....")
    document.getElementById("text").parentNode.className = 'hf';
    document.getElementById("text").value = '评论…';


}

function reply(obj) {
    console.log(obj.parentNode)
    document.getElementById("text").parentNode.className = 'hf hf-on';
    document.getElementById("text").value = document.getElementById("text").value == '评论…' ? '' : document.getElementById("text").value;
    document.getElementById("text").focus()
    comment_replyId = obj.parentNode.parentNode.id;
    comment_user = obj.parentNode.parentNode.children[0].children[0].innerText;
    comment_user = comment_user.substr(0, comment_user.indexOf(":"))

    $.ajax({
            url: "reply/getReplysById",
            data: {parentId: comment_replyId},
            type: "POST",
            success: function (data) {
                var reples = data.data;

                for (var i = 0; i < reples.length; i++) {
                    var reply = reples[i];

                    var comment = "<div class=\"comment\" user=\"self\">\n" +
                        "                                    <div class=\"comment-right\" id='" + reply.reply.replyId + "'>\n" +
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
                    $(comment).insertAfter($(document.getElementById(comment_replyId)));
                }

            },
        }
    )


    var text = document.getElementById("text");
    text.setAttribute("name", "son");
    text.focus();

}

function guanzhu(userId) {

}
