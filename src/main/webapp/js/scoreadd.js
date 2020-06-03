var addscosno = null;
var addscosname = null;
var addscocname = null;
var addscoscore = null;
var addscosave = null;

var addscosnoflag = false;
var addscosnameflag = false;
var addscocnameflag = false;
var addscoscoreflag = false;

$(function () {
    addscosno = $("#addscosno");
    addscosname = $("#addscosname");
    addscocname = $("#addscocname");
    addscoscore = $("#addscoscore");
    addscosave = $("#addscosave");

    addscosno.on("blur", function () {
        var obj = $(this);
        $.ajax({
            type: "post",
            url: "/stu.do",
            data: {method: "getSigleStu", sno: obj.val()},
            dataType: "json",
            success: function (data) {
                console.log(data);
                if (data.result == "success") {
                    addscosname.val(data.sname);
                    addscosnoflag = true;
                    addscosnameflag = true;
                    obj.parent().parent().removeClass('has-error').addClass('has-success');
                    obj.next().html("可用");
                } else if (data.result == "empty") {
                    obj.parent().parent().removeClass('has-success').addClass('has-error');
                    obj.next().html("学号不可为空");
                } else if (data.result == "error") {
                    obj.parent().parent().removeClass('has-success').addClass('has-error');
                    obj.next().html("学号不存在");
                }
            }
        })
    });


    addscocname.on("change", function () {
        var obj = $(this);
        $.ajax({
            type: "post",
            url: "/score.do",
            data: {method: "checkSnoCouno", sno: addscosno.val(),cname:obj.val()},
            dataType: "json",
            success: function (data) {
                console.log(data);
                if (data.result == "success") {
                    addscocnameflag = true;
                    obj.parent().parent().removeClass('has-error').addClass('has-success');
                    $("#addscocnameMsg").html("可用");
                } else if (data.result == "empty") {
                    obj.parent().parent().removeClass('has-success').addClass('has-error');
                    $("#addscocnameMsg").html("请选择课程");
                } else if (data.result == "error") {
                    obj.parent().parent().removeClass('has-success').addClass('has-error');
                    $("#addscocnameMsg").html("该生该门课程成绩已存在");
                }
            }
        })
    });


    addscoscore.on("focus", function () {
        var obj = $(this);
        obj.parent().parent().removeClass('has-success has-error');
        obj.next().html("请输入0~100之内的分数成绩");
    })
        .on("blur", function () {
            var obj = $(this);
            if (obj.val() == "") {
                obj.parent().parent().removeClass('has-success').addClass('has-error');
                obj.next().html("分数不可为空");
            } else if (obj.val() < 0 || obj.val() > 100) {
                obj.parent().parent().removeClass('has-success').addClass('has-error');
                obj.next().html("分数应该在0~100之间");
            } else {
                addscoscoreflag = true;
                obj.parent().parent().removeClass('has-error').addClass('has-success');
                obj.next().html("可用");
            }
        });


    addscosave.on("click", function () {
        addscosno.blur();
        addscocname.change();
        addscoscore.blur();
        if (addscosnoflag == true && addscosnameflag == true && addscocnameflag == true && addscoscoreflag == true) {
            if (confirm("确定保存信息?")) {
                $("#addscoinfo").submit();
            }
        }

    });
});