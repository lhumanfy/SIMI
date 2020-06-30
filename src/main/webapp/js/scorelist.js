
function getData(sno,counum,couname,sname,score){
    $("#sno").val(sno);
    $("#couname").val(couname);
    $("#counum").val(counum);
    $("#name").val(sname);
    $("#Score").val(score);
}

$(function () {
    $(".ChangeSco").on("click", function () {
        var obj = $(this);
        window.location.href = "/score.do?method=beforechangeSco&sno=" + obj.attr("stuSno") +
            "&counum=" + obj.attr("scoCouNum");
    });

    $(".DeleteSco").on("click", function () {
        var obj = $(this);
        bootbox.confirm({
            locale:'zh_CN',
            message: "你确定要删除此用户?",
            callback: function (result) {
                if (result) {
                    $.ajax({
                        type: "post",
                        url: "/score.do",
                        data: {method: "delSco", sno: obj.attr("stuSno"),counum:obj.attr("scoCouNum")},
                        dataType: "json",
                        success: function (data) {
                            if (data.result == "true") {
                                bootbox.alert("删除成功");
                                obj.parents("tr").remove();
                            } else if (data.result == "false") {
                                bootbox.alert("对不起，删除此用户（" + obj.attr("stuSno") + "）的成绩失败");
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
});