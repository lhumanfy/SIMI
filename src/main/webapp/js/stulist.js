function getStuData(sNo,sName,sSex,sBir,sClass,majorName,facultyName){
    $("#show_sno").val(sNo);
    $("#show_sname").val(sName);
    $("#show_sex").val(sSex);
    $("#show_sbir").val(sBir);
    $("#show_sclass").val(sClass);
    $("#show_smaj").val(majorName);
    $("#show_sfac").val(facultyName);
}
$(function () {
    $(".ChangeStu").on("click", function () {
        var obj = $(this);
        window.location.href = "/stu.do?method=changeStu&sno=" + obj.attr("stuSno") +
            "&majorno=" + obj.attr("stuMno") + "&facultyno=" + obj.attr("stuFno");
    });

    $(".DeleteStu").on("click", function () {
        var obj = $(this);
        bootbox.confirm({
            locale: 'zh_CN',
            message: "你确定要删除用户(" + obj.attr("stuSno") + ")?",
            callback: function (result) {
                if (result) {
                    $.ajax({
                        type: "post",
                        url: "/stu.do",
                        data: {method: "delStu", sno: obj.attr("stuSno")},
                        dataType: "json",
                        success: function (data) {
                            if (data.result == "true") {
                                bootbox.alert("删除成功");
                                obj.parents("tr").remove();
                            } else if (data.result == "false") {
                                bootbox.alert("对不起，删除用户（" + obj.attr("stuSno") + "）失败");
                            }
                        },
                        error: function (data) {
                            alert("对不起，删除失败");
                        }
                    });
                }
            }
        });
    });
    // $("#goStuAdd").on("click",function () {
    //     window.location.href="/stu.do?method=beforeAddStuInfo";
    // })
});