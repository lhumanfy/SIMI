var addsno = null;
var addname = null;
var addsex = null;
var addbir = null;
var addclass = null;
var addMaNo = null;
var addFaNo = null;
var addpwd = null;
var addsave = null;

var addsnoflag = false;
var addnameflag = false;
var addbirflag = false;
var addclassflag = false;
var addMaNoflag = false;
var addFaNoflag = false;
var addpwdflag = false;

$(function () {
    addsno = $("#addsno");
    addname = $("#addname");
    addsex = $("#addsex");
    addbir = $("#addbir");
    addclass = $("#addclass");
    addMaNo = $("#addMaNo");
    addFaNo = $("#addFaNo");
    addpwd = $("#addpwd");
    addsave = $("#addsave");

    addbir.datetimepicker({
        minView: 'month',
        format: 'yyyy-mm-dd',
        autoclose: true,
        language: 'zh-CN',
        todayBtn: true
    });

    addsno.on("blur", function () {
        var obj = $(this);
        $.ajax({
            type: "post",
            url: "/stu.do",
            data: {method: "examsno", sno: obj.val()},
            dataType: "json",
            success: function (data) {
                if (data.result == "success") {
                    addsnoflag = true;
                    obj.parent().parent().removeClass('has-error').addClass('has-success');
                    obj.next().html("学号可用");
                } else if (data.result == "empty") {
                    obj.parent().parent().removeClass('has-success').addClass('has-error');
                    obj.next().html("学号不可为空");
                } else if (data.result == "error") {
                    obj.parent().parent().removeClass('has-error').addClass('has-error');
                    obj.next().html("学号已存在");
                }
            }
        })
    });

    addname.on("blur", function () {
        var obj = $(this);
        if (obj.val() == "") {
            obj.parent().parent().removeClass('has-success').addClass('has-error');
            obj.next().html("姓名不可为空");
        } else {
            addnameflag = true;
            obj.parent().parent().removeClass('has-error').addClass('has-success');
            obj.next().html("可用");
        }
    });
    addsex.on("change", function () {
        var obj = $(this);
        obj.parent().parent().removeClass('has-error').addClass('has-success');
    });

    addbir.on("change", function () {
        var obj = $(this);
        if (obj.val() == "") {
            obj.parent().parent().removeClass('has-success').addClass('has-error');
        } else {
            addbirflag = true;
            obj.parent().parent().removeClass('has-error').addClass('has-success');
        }
    });

    addclass.on("blur", function () {
        var obj = $(this);
        if (obj.val() == "") {
            obj.parent().parent().removeClass('has-success').addClass('has-error');
            obj.next().html("班级不可为空");
        } else {
            addclassflag = true;
            obj.parent().parent().removeClass('has-error').addClass('has-success');
            obj.next().html("可用");
        }
    });

    addMaNo.on("change", function () {
        var obj = $(this);
        if (obj.val() == 0) {
            obj.parent().parent().removeClass('has-success').addClass('has-error');
        } else {
            addMaNoflag = true;
            obj.parent().parent().removeClass('has-error').addClass('has-success');
        }
    });

    addFaNo.on("change", function () {
        var obj = $(this);
        if (obj.val() == 0) {
            obj.parent().parent().removeClass('has-success').addClass('has-error');
        } else {
            addFaNoflag = true;
            obj.parent().parent().removeClass('has-error').addClass('has-success');
        }
    });

    addpwd.on("focus", function () {
        var obj = $(this);
        obj.parent().parent().removeClass('has-success has-error');
        obj.next().html("请输入大于6位的密码").css();
    })
        .on("blur", function () {
            var obj = $(this);
            if (obj.val() == "") {
                obj.parent().parent().removeClass('has-success').addClass('has-error');
                obj.next().html("密码不可为空");
            } else if (obj.val().length < 6) {
                obj.parent().parent().removeClass('has-success').addClass('has-error');
                obj.next().html("输入大于6位密码");
            } else {
                addpwdflag = true;
                obj.parent().parent().removeClass('has-error').addClass('has-success');
                obj.next().html("密码可用");
            }
        });

    addsave.on("click", function () {
        addsno.blur();
        addname.blur();
        addsex.change();
        addbir.change();
        addclass.blur();
        addMaNo.change();
        addFaNo.change();
        addpwd.blur();
        if (addsnoflag == true && addnameflag == true && addbirflag == true && addclassflag == true
            && addMaNoflag == true && addFaNoflag == true && addpwdflag == true) {
            if (confirm("确定保存信息?")) {
                $("#addstuinfo").submit();
            }
        }

    });
});