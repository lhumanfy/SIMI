$(function () {
    $(".ViewSco").on("click", function () {
        var obj = $(this);
        window.location.href = "/score.do?method=viewSco&sno=" + obj.attr("stuSno") +
            "&counum=" + obj.attr("scoCouNum");
    });

    $(".ChangeSco").on("click", function () {
        var obj = $(this);
        window.location.href = "/score.do?method=beforechangeSco&sno=" + obj.attr("stuSno") +
            "&counum=" + obj.attr("scoCouNum");
    });

    $(".DeleteSco").on("click", function () {
        var obj = $(this);
        if (confirm("你确定要删除此用户(" + obj.attr("stuSno") + ")的成绩吗？")) {
            $.ajax({
                type: "post",
                url: "/score.do",
                data: {method: "delSco", sno: obj.attr("stuSno"),counum:obj.attr("scoCouNum")},
                dataType: "json",
                success: function (data) {
                    if (data.result == "true") {
                        alert("删除成功");
                        obj.parents("tr").remove();
                    } else if (data.result == "false") {
                        alert("对不起，删除此用户（" + obj.attr("stuSno") + "）的成绩失败");
                    }
                },
                error: function (data) {
                    alert("对不起，删除失败");
                }
            });
        }
    });

    $("#goScoAdd").on("click", function () {
        window.location.href = "/score.do?method=beforeAddScore";
    })
});