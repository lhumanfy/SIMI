var addsno =$("#addsno");
var addname = $("#addname");
var addsex =$("#addsex");
var addbir =$("#addbir");
var addclass= $("#addclass");
var addMaNo = $("#addMaNo");
var addFaNo = $("#addFaNo");
var addpwd =$("#addpwd");

var addsnoflag = false;
var addnameflag = false;
var addbirflag = false;
var addclassflag = false;
var addMaNoflag = false;
var addFaNoflag = false;
var addpwdflag = false;


function saveStuInfo() {
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
        bootbox.confirm({
            message: "是否确定保存?",
            locale: "zh_CN",
            callback: function (result) {
                if (result) {
                    $.ajax({
                        type: "post",
                        url: "/stu.do",
                        data: {
                            method: "addStuInfo",
                            addsno: addsno.val(),
                            addname: addname.val(),
                            addsex: addsex.val(),
                            addbir: addbir.val(),
                            addclass: addclass.val(),
                            addMaNo: addMaNo.val(),
                            addFaNo: addFaNo.val(),
                            addpwd: addpwd.val(),
                        },
                        dataType: "json",
                        success: function (data) {
                            if (data.result == "success") {
                                bootbox.alert("保存成功");
                                $("#add_stuinfo").modal("hide");
                                window.location.href = "/stu.do?method=stuinfo";
                            } else if (data.result == "error") {
                                bootbox.alert("保存失败");
                                addsno.val(data.sno);
                                addname.val(data.name);
                                addsex.val(data.sex);
                                addbir.val(data.bir);
                                addclass.val(data.class);
                                addMaNo.val(data.mano);
                                addFaNo.val(data.fano);
                                addpwd.val(data.pwd);
                            }
                        }
                    })
                }
            }
        });
    }
}

$(function () {

    addbir.datetimepicker({
        minView: 'month',
        format: 'yyyy-mm-dd',
        autoclose: true,
        language: 'zh-CN',
        todayBtn: true
    }).on("hide",function (event) {
        event.stopPropagation();
    }).on("show",function (event) {
        event.stopPropagation();
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
                    obj.parent().removeClass('has-error').addClass('has-success');
                    obj.next().html("学号可用");
                } else if (data.result == "empty") {
                    addsnoflag = false;
                    obj.parent().removeClass('has-success').addClass('has-error');
                    obj.next().html("学号不可为空");
                } else if (data.result == "error") {
                    addsnoflag = false;
                    obj.parent().removeClass('has-error').addClass('has-error');
                    obj.next().html("学号已存在");
                }
            }
        })
    });

    addname.on("blur", function () {
        var obj = $(this);
        if (obj.val() == "") {
            addnameflag = false;
            obj.parent().removeClass('has-success').addClass('has-error');
            obj.next().html("姓名不可为空");
        } else {
            addnameflag = true;
            obj.parent().removeClass('has-error').addClass('has-success');
            obj.next().html("可用");
        }
    });
    addsex.on("change", function () {
        var obj = $(this);
        obj.parent().removeClass('has-error').addClass('has-success');
    });

    addbir.on("change", function () {
        var obj = $(this);
        if (obj.val() == "") {
            addbirflag = false;
            obj.parent().removeClass('has-success').addClass('has-error');
        } else {
            addbirflag = true;
            obj.parent().removeClass('has-error').addClass('has-success');
        }
    });

    addclass.on("blur", function () {
        var obj = $(this);
        if (obj.val() == "") {
            addclassflag = false;
            obj.parent().removeClass('has-success').addClass('has-error');
            obj.next().html("班级不可为空");
        } else {
            addclassflag = true;
            obj.parent().removeClass('has-error').addClass('has-success');
            obj.next().html("可用");
        }
    });

    addMaNo.on("change", function () {
        var obj = $(this);
        if (obj.val() == 0) {
            addMaNoflag = false;
            obj.parent().removeClass('has-success').addClass('has-error');
        } else {
            addMaNoflag = true;
            obj.parent().removeClass('has-error').addClass('has-success');
        }
    });

    addFaNo.on("change", function () {
        var obj = $(this);
        if (obj.val() == 0) {
            obj.parent().removeClass('has-success').addClass('has-error');
        } else {
            addFaNoflag = true;
            obj.parent().removeClass('has-error').addClass('has-success');
        }
    });

    addpwd.on("focus", function () {
        var obj = $(this);
        obj.parent().removeClass('has-success has-error');
        obj.next().html("请输入大于6位的密码").css();
    })
        .on("blur", function () {
            var obj = $(this);
            if (obj.val() == "") {
                addpwdflag = false;
                obj.parent().removeClass('has-success').addClass('has-error');
                obj.next().html("密码不可为空");
            } else if (obj.val().length < 6) {
                addpwdflag = false;
                obj.parent().removeClass('has-success').addClass('has-error');
                obj.next().html("输入大于6位密码");
            } else {
                addpwdflag = true;
                obj.parent().removeClass('has-error').addClass('has-success');
                obj.next().html("密码可用");
            }
        });
    $("#add_stuinfo").on("hide.bs.modal",function () {
        //去除每个框在ajax中显示出来的的cs样式
        addsno.parent().removeClass('has-error has-success');
        addname.parent().removeClass('has-error has-success');
        addsex.parent().removeClass('has-error has-success');
        addbir.parent().removeClass('has-error has-success');
        addclass.parent().removeClass('has-error has-success');
        addMaNo.parent().removeClass('has-error has-success');
        addFaNo.parent().removeClass('has-error has-success');
        addpwd.parent().removeClass('has-error has-success');
        // 提示词置空
        addsno.next().html("");
        addname.next().html("");
        addclass.next().html("");
        addpwd.next().html("");
        //输入框置空
        addsno.val("");
        addname.val("");
        addsex.val("男");
        addbir.val("");
        addclass.val("");
        addpwd.val("");

    }).modal({
        backdrop:'static',
        show:false
    });
});