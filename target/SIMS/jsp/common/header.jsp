<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生信息管理系统</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <link href="${pageContext.request.contextPath}/css/boostrap/css/bootstrap.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/boostrap/css/bootstrap-select.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/boostrap/css/bootstrap-datetimepicker.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-default navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="${pageContext.request.contextPath}/jsp/index.jsp" class="navbar-brand">信息管理系统</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="${pageContext.request.contextPath}/loginout.do">退出</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li>
                    <a href="${pageContext.request.contextPath}/score.do?method=scorelist">学生成绩</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/stu.do?method=stuinfo">学生信息</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/jsp/changepwd.jsp">修改密码</a>
                </li>

            </ul>

        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">



