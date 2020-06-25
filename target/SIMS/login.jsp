<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>登录</title>
    <link href="${pageContext.request.contextPath}/css/boostrap/css/bootstrap.css" rel="stylesheet"/>
    <style>
        .container {
            display: table;
            height: 100%;
        }

        .col-center {
            display: inline-block;
            float: none;
            text-align: left;
            margin-right: -4px;
        }

        .row-center {
            text-align: center;
        }

        .row {
            display: table-cell;
            vertical-align: middle;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row row-center">
        <div class="col-sm-4 col-center">
            <form class="form-horizontal" action="${pageContext.request.contextPath }/login.do" method="post">
                <h3 class="text-center">用户登录</h3>
                <div class="form-group"> ${error}</div>
                <div class="form-group">
                    <input type="text" class="form-control" name="username" autocomplete="off" placeholder="用户名"
                           required>
                </div>
                <div class="form-group">
                    <input type="password" name="password" class="form-control" placeholder="密码" required>
                </div>
                <div class="form-group">
                    <label class="radio-inline">
                        <input type="radio" name="identity" value="student"/>学生
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="identity" value="admin"/>管理员
                    </label>
                </div>
                <div class="form-group">
                    <input class="btn btn-default" type="submit" value="登录">
                    <input class="btn btn-default" type="reset" value="重写">
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.5.1.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
</body>
</html>
