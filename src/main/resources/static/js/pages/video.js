
var userId=$.cookie("userId")
$.ajax({
    url:"user/getUserName",
    dataType:JSON,
    type:"post",
    data:{userId:userId},
    success:function (data) {
        document.getElementsByClassName("functions")[0].children[1].children[0].innerHTML=data;
    }
})