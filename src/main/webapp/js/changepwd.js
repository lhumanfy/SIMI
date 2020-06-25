var oldPwd = null;
var newPwd = null;
var reNewPwd = null;
var saveBtn = null;
var oldPwdFlag = false;
var newPwdFlag = false;
var reNewPwdFlag = false;

$(function () {
    oldPwd = $("#oldPwd");
    newPwd = $("#newPwd");
    reNewPwd = $("#reNewPwd");
    saveBtn = $("#saveBtn");
    oldPwd.on("blur", function () {
        $.post("/adm.do", {
            method: "pwdajax",
            oldPwd: oldPwd.val()
        }, function (data) {
            if (data.result == "true") {
                oldPwd.next().html("原密码正确").removeClass("alert alert-danger").addClass("alert alert-success");
                oldPwdFlag = true;
            } else if (data.result == "false") {
                oldPwd.next().html("原密码错误").removeClass("alert alert-success").addClass("alert alert-danger");
            } else if (data.result == "sessionerror") {
                oldPwd.next().html("登录信息失效，重新登录").removeClass("alert alert-success").addClass("alert alert-danger");
            } else if (data.result == "empty") {
                oldPwd.next().html("原密码不可为空").removeClass("alert alert-success").addClass("alert alert-danger");
            }
        }, "json")
    });

    newPwd.on("blur", function () {
        if (newPwd.val() != null && newPwd.val().length >= 6) {
            newPwd.next().html("密码可用").removeClass("alert alert-danger").addClass("alert alert-success");
            newPwdFlag = true;
        } else {
            newPwd.next().html("密码格式不对").removeClass("alert alert-success").addClass("alert alert-danger");
        }
    });

    reNewPwd.on("blur", function () {
        if (reNewPwd.val() != newPwd.val()) {
            reNewPwd.next().html("两次密码不一样").removeClass("alert alert-success").addClass("alert alert-danger");
        } else if (reNewPwd.val() != null && reNewPwd.val().length >= 6 && reNewPwd.val() == newPwd.val()) {
            reNewPwd.next().html("密码可用").removeClass("alert alert-danger").addClass("alert alert-success");
            reNewPwdFlag = true;
        } else {
            reNewPwd.next().html("密码格式不对").removeClass("alert alert-success").addClass("alert alert-danger");
        }
    });

    saveBtn.on("click", function () {
        oldPwd.blur();
        newPwd.blur();
        reNewPwd.blur();
        if (oldPwdFlag == true && newPwdFlag == true && reNewPwdFlag == true) {
            if (confirm("确定修改密码？")) {
                $("#changepwd").submit();
            }
        }
    });
});
