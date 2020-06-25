<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/stuview/common/header.jsp" %>
<div class="changePwd">
    <form class="form-horizontal" id="changepwd" action="${pageContext.request.contextPath}/stu.do" method="post">
        <input type="hidden" name="method" value="savepwd">

        <div class="form-group">${message}</div>
        <div class="form-group">
            <label for="oldPwd" class="col-sm-2 control-label">旧密码:</label>
            <div class="col-sm-5">
                <input type="password" name="oldPwd" id="oldPwd"><span></span>
            </div>
        </div>
        <div class="form-group">
            <label for="newPwd" class="col-sm-2 control-label">新密码:</label>
            <div class="col-sm-5">
                <input type="password" name="newPwd" id="newPwd"><span></span>
            </div>
        </div>
        <div class="form-group">
            <label for="reNewPwd" class="col-sm-2 control-label">确认新密码:</label>
            <div class="col-sm-5">
                <input type="password" name="reNewPwd" id="reNewPwd"><span></span>
            </div>
        </div>
        <div class="col-sm-offset-2">
            <input type="button" name="saveBtn" id="saveBtn" value="确定">
        </div>
    </form>
</div>
<%@include file="/jsp/common/footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/stujs/changepwd.js"></script>