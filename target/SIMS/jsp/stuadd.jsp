<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/jsp/common/header.jsp" %>
<form class="form-horizontal" id="addstuinfo" method="post" action="${pageContext.request.contextPath}/stu.do">
    <input type="hidden" name="method" value="addStuInfo"/>
    <div class="form-group">${message}</div>
    <div class="form-group">
        <label class="col-sm-1 control-label" for="addsno">学号</label>
        <div class="col-sm-5">
            <input type="text" name="addsno" id="addsno" class="form-control" value="${stu.sNo}">
            <span class="help-block"></span>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-1 control-label" for="addname">姓名</label>
        <div class="col-sm-5">
            <input type="text" name="addname" id="addname" class="form-control" value="${stu.sName}">
            <span class="help-block"></span>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-1 control-label" for="addsex">性别</label>
        <select class="selectpicker col-sm-5" name="addsex" id="addsex">
            <c:choose>
                <c:when test="${stu.sSex eq '男'}">
                    <option value="男" selected="selected">男</option>
                    <option value="女">女</option>
                </c:when>
                <c:otherwise>
                    <option value="男">男</option>
                    <option value="女" selected="selected">女</option>
                </c:otherwise>
            </c:choose>
        </select>
        <span class="help-block"></span>
    </div>
    <div class="form-group">
        <label class="col-sm-1 control-label " for="addbir">生日</label>
        <div class="col-sm-5">
            <input type="text" name="addbir" id="addbir" class="form-control" value="${stu.sBir}" readonly>
            <span class="help-block"></span>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-1 control-label" for="addclass">班级</label>
        <div class="col-sm-5">
            <input type="text" name="addclass" id="addclass" class="form-control" value="${stu.sClass}">
            <span class="help-block"></span>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-1 control-label" for="addMaNo">专业</label>
        <select name="addMaNo" id="addMaNo" class="selectpicker col-sm-5" data-live-search="true" data-size="5">
            <c:if test="${maList!=null}">
                <option value="0">选择</option>
                <c:forEach var="major" items="${maList}">
                    <option
                            <c:if test="${stu.majorNo==major.majorNo}">selected="selected"</c:if>
                            value="${major.majorNo}">${major.majorName}
                    </option>
                </c:forEach>
            </c:if>
        </select>
    </div>
    <div class="form-group">
        <label class="col-sm-1 control-label" for="addFaNo">学院</label>
        <select name="addFaNo" id="addFaNo" class="selectpicker col-sm-5" data-live-search="true" data-size="5">
            <c:if test="${faultyList!=null}">
                <option value="0">选择</option>
                <c:forEach var="faculty" items="${faultyList}">
                    <option
                            <c:if test="${stu.facltyNo==faculty.facultyNo}">selected="selected"</c:if>
                            value="${faculty.facultyNo}">${faculty.facultyName}
                    </option>
                </c:forEach>
            </c:if>
        </select>
    </div>
    <div class="form-group">
        <label class="col-sm-1 control-label" for="addpwd">密码</label>
        <div class="col-sm-5">
            <input type="text" name="addpwd" id="addpwd" class="form-control" value="${stu.passWord}">
            <span class="help-block"></span>
        </div>
    </div>
    <div class="col-sm-offset-1">
        <input class="btn btn-default" type="button" name="addsave" id="addsave" value="保存"/>
    </div>
</form>
<%@include file="/jsp/common/footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/stuadd.js"></script>
