var changeSore = null;
var changesave = null;

var changeSoreflag = false;


$(function () {
    changeSore = $("#changeSore");
    changesave = $("#changesave");

    changeSore.on("blur", function () {
        var obj = $(this);
        if (obj.val() == "") {
            obj.parent().parent().removeClass('has-success').addClass('has-error');
            obj.next().html("分数不可以为空");
        } else if (obj.val() < 0 || obj.val() > 100) {
            obj.parent().parent().removeClass('has-success').addClass('has-error');
            obj.next().html("分数应该在0~100之间");
        }else {
            changeSoreflag = true;
            obj.parent().parent().removeClass('has-error').addClass('has-success');
            obj.next().html("可用");
        }
    });

    changesave.on("click", function () {
        changeSore.blur();
        if (changeSoreflag==true) {
            if (confirm("确定保存信息?")) {
                $("#changescoreinfo").submit();
            }
        }

    });
});