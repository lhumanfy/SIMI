var changename = null;
var changesex = null;
var changebir = null;
var changeclass = null;
var changeMaNo = null;
var changeFaNo = null;
var changesave = null;

var changenameflag = false;
var changebirflag = false;
var changeclassflag = false;
var changeMaNoflag = false;
var changeFaNoflag = false;

$(function () {
    changename = $("#changename");
    changesex = $("#changesex");
    changebir = $("#changebir");
    changeclass = $("#changeclass");
    changeMaNo = $("#changeMaNo");
    changeFaNo = $("#changeFaNo");
    changesave = $("#changesave");

    changebir.datetimepicker({
        minView: 'month',
        format: 'yyyy-mm-dd',
        autoclose: true,
        language: 'zh-CN',
        todayBtn: true
    });

    changename.on("blur", function () {
        var obj = $(this);
        if (obj.val() == "") {
            obj.parent().parent().removeClass('has-success').addClass('has-error');
            obj.next().html("姓名不可为空");
        } else {
            changenameflag = true;
            obj.parent().parent().removeClass('has-error').addClass('has-success');
            obj.next().html("可用");
        }
    });
    changesex.on("change", function () {
        var obj = $(this);
        obj.parent().parent().removeClass('has-error').addClass('has-success');
    });

    changebir.on("change", function () {
        var obj = $(this);
        console.log(obj.val());
        if (obj.val() == "") {
            obj.parent().parent().removeClass('has-success').addClass('has-error');
        } else {
            changebirflag = true;
            obj.parent().parent().removeClass('has-error').addClass('has-success');
        }
    });

    changeclass.on("blur", function () {
        var obj = $(this);
        if (obj.val() == "") {
            obj.parent().parent().removeClass('has-success').addClass('has-error');
            obj.next().html("班级不可为空");
        } else {
            changeclassflag = true;
            obj.parent().parent().removeClass('has-error').addClass('has-success');
            obj.next().html("可用");
        }
    });

    changeMaNo.on("change", function () {
        var obj = $(this);
        if (obj.val() == 0) {
            obj.parent().parent().removeClass('has-success').addClass('has-error');
        } else {
            changeMaNoflag = true;
            obj.parent().parent().removeClass('has-error').addClass('has-success');
        }
    });

    changeFaNo.on("change", function () {
        var obj = $(this);
        if (obj.val() == 0) {
            obj.parent().parent().removeClass('has-success').addClass('has-error');
        } else {
            changeFaNoflag = true;
            obj.parent().parent().removeClass('has-error').addClass('has-success');
        }
    });

    changesave.on("click", function () {
        changename.blur();
        changesex.change();
        changebir.change();
        changeclass.blur();
        changeMaNo.change();
        changeFaNo.change();
        if (changenameflag == true && changebirflag == true && changeclassflag == true
            && changeMaNoflag == true && changeFaNoflag == true) {
            if (confirm("确定保存信息?")) {
                $("#changestuinfo").submit();
            }
        }

    });
});