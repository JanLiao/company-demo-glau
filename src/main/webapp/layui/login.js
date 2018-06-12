layui.config({
    base: "js/"
}).use(['form', 'layer'], function () {
    var form = layui.form(),
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery;
    //登录按钮事件
    form.on("submit(login)", function (data) {
    	var account = $("#userAccount").val();
    	alert(account);
        $.ajax({
            type: "POST",
            url: "<%=request.getContextPath()%>/login/reqLogin.do",
            contentType: "application/json",
            data: JSON.stringify(data.field),
            dataType: "json",
            success: function (result) {
                if (result.code == 0) {//登录成功
                    parent.location.href = '/index.html';
                } else {
                    layer.msg(result.msg, {icon: 5});
                    //refreshCode();
                }
            }
        });
        return false;
    })
});
function refreshCode(){
    var captcha = document.getElementById("captcha");
    captcha.src = "/captcha.jpg?t=" + new Date().getTime();
}
