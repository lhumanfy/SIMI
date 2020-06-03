$(function () {
    $(".ViewStu").on("click", function () {
        var obj = $(this);
        window.location.href = "/stu.do?method=viewStu&sno=" + obj.attr("stuSno") +
            "&majorno=" + obj.attr("stuMno") + "&facultyno=" + obj.attr("stuFno");
    });

    $(".ChangeStu").on("click", function () {
        var obj = $(this);
        window.location.href = "/stu.do?method=changeStu&sno=" + obj.attr("stuSno") +
            "&majorno=" + obj.attr("stuMno") + "&facultyno=" + obj.attr("stuFno");
    });

    $(".DeleteStu").on("click", function () {
        var obj = $(this);
        if (confirm("你确定要删除用户(" + obj.attr("stuSno") + ")吗？")) {
            $.ajax({
                type: "post",
                url: "/stu.do",
                data: {method:"delStu",sno: obj.attr("stuSno")},
                dataType: "json",
                success: function (data) {
                    if (data.result == "true") {
                        alert("删除成功");
                        obj.parents("tr").remove();
                    } else if (data.result == "false") {
                        alert("对不起，删除用户（" + obj.attr("stuSno") + "）失败");
                    }
                },
                error: function (data) {
                    alert("对不起，删除失败");
                }
            });
        }
    });

    $("#goStuAdd").on("click",function () {
        window.location.href="/stu.do?method=beforeAddStuInfo";
    })
});