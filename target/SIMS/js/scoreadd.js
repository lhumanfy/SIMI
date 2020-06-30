var addscosno = $("#addscosno");
var addscosname = $("#addscosname");
var addscocname = $("#addscocname");
var addscoscore = $("#addscoscore");

var addscosnoflag = false;
var addscosnameflag = false;
var addscocnameflag = false;
var addscoscoreflag = false;

function saveScoreInfo() {
    addscosno.blur();
    addscocname.change();
    addscoscore.blur();
    if (addscosnoflag == true && addscosnameflag == true && addscocnameflag == true && addscoscoreflag == true) {
        bootbox.confirm({
            message: "是否确定保存?",
            locale: "zh_CN",
            callback: function (result) {
                if (result) {
                    $.ajax({
                        type: "post",
                        url: "/score.do",
                        data: {
                            method: "addScoInfo",
                            addscosno: addscosno.val(),
                            addscosname: addscosname.val(),
                            addscocname: addscocname.val(),
                            addscoscore: addscoscore.val(),
                        },
                        dataType: "json",
                        success: function (data) {
                            if (data.result == "success") {
                                bootbox.alert("保存成功");
                                $("#add_scoinfo").modal("hide");
                                window.location.href = "/score.do?method=scorelist";
                            }else if (data.result=="error"){
                                bootbox.alert("保存失败");
                                addscosno.val(data.addscosno);
                                addscosname.val(data.addscosname);
                                addscocname.val(data.addscocname);
                                addscoscore.val(data.addscoscore);
                            }
                        }
                    })
                }
            }
        });
    }
}

$(function () {

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
                    obj.parent().removeClass('has-error').addClass('has-success');
                    obj.next().html("可用");
                } else if (data.result == "empty") {
                    addscosnoflag = false;
                    addscosnameflag = false;
                    obj.parent().removeClass('has-success').addClass('has-error');
                    obj.next().html("学号不可为空");
                } else if (data.result == "error") {
                    addscosnoflag = false;
                    addscosnameflag = false;
                    obj.parent().removeClass('has-success').addClass('has-error');
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
            data: {method: "checkSnoCouno", sno: addscosno.val(), cname: obj.val()},
            dataType: "json",
            success: function (data) {
                console.log(data);
                if (data.result == "success") {
                    addscocnameflag = true;
                    obj.parent().parent().removeClass('has-error').addClass('has-success');
                    $("#addscocnameMsg").html("可用");
                } else if (data.result == "empty") {
                    addscocnameflag = false;
                    obj.parent().parent().removeClass('has-success').addClass('has-error');
                    $("#addscocnameMsg").html("请选择课程");
                } else if (data.result == "error") {
                    addscocnameflag = false;
                    obj.parent().parent().removeClass('has-success').addClass('has-error');
                    $("#addscocnameMsg").html("该生该门课程成绩已存在");
                }
            }
        })
    });


    addscoscore.on("focus", function () {
        var obj = $(this);
        obj.parent().removeClass('has-success has-error');
        obj.next().html("请输入0~100之内的分数成绩");
    })
        .on("blur", function () {
            var obj = $(this);
            if (obj.val() == "") {
                addscoscoreflag = false;
                obj.parent().removeClass('has-success').addClass('has-error');
                obj.next().html("分数不可为空");
            } else if (obj.val() < 0 || obj.val() > 100) {
                addscoscoreflag = false;
                obj.parent().removeClass('has-success').addClass('has-error');
                obj.next().html("分数应该在0~100之间");
            } else {
                addscoscoreflag = true;
                obj.parent().removeClass('has-error').addClass('has-success');
                obj.next().html("可用");
            }
        });

    $("#add_scoinfo").on("hide.bs.modal",function () {
        //去除每个框在ajax中显示出来的的cs样式
        addscosno.parent().removeClass('has-error has-success');
        addscocname.parent().parent().removeClass('has-error has-success');
        addscoscore.parent().removeClass('has-error has-success');
        // 提示词置空
        addscosno.next().html("");
        $("#addscocnameMsg").html("");
        addscoscore.next().html("");
        //输入框置空
        addscosno.val("");
        addscosname.val("");
        addscoscore.val("");
    }).modal({
        backdrop:'static',
        show:false
    });
});