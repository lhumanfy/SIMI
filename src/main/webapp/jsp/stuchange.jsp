<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/common/header.jsp" %>
<form class="form-horizontal" id="changestuinfo" method="post" action="${pageContext.request.contextPath}/stu.do">
    <input type="hidden" name="method" value="upStuInfo"/>
    <input type="hidden" name="changesno" id="changesno" value="${stuSingle.sNo }"/>
    <div class="form-group">${message}</div>
    <div class="form-group">
        <label class="col-sm-1 control-label" for="changename">姓名</label>
        <div class="col-sm-5">
            <input type="text" name="changename" id="changename" class="form-control" value="${stuSingle.sName}">
        </div>
        <span class="help-block"></span>
    </div>
    <div class="form-group">
        <label class="col-sm-1 control-label" for="changesex">性别</label>
        <select class="selectpicker col-sm-5" name="changesex" id="changesex">
            <c:choose>
                <c:when test="${stuSingle.sSex eq '男'}">
                    <option value="男" selected="selected">男</option>
                    <option value="女">女</option>
                </c:when>
                <c:otherwise>
                    <option value="男">男</option>
                    <option value="女" selected="selected">女</option>
                </c:otherwise>
            </c:choose>
        </select>
    </div>
    <div class="form-group">
        <label class="col-sm-1 control-label" for="changebir">生日</label>
        <div class="col-sm-5">
            <input type="text" name="changebir" id="changebir" class="form-control" value="${stuSingle.sBir}">
            <span class="help-block"></span>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-1 control-label" for="changeclass">班级</label>
        <div class="col-sm-5">
            <input type="text" name="changeclass" id="changeclass" class="form-control" value="${stuSingle.sClass}">
            <span class="help-block"></span>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-1 control-label" for="changeMaNo">专业</label>
        <select name="changeMaNo" id="changeMaNo" class="selectpicker col-sm-5" data-live-search="true" data-size="5">
            <c:if test="${maList!=null}">
                <option value="0">选择</option>
                <c:forEach var="major" items="${maList}">
                    <option
                            <c:if test="${stuSingle.majorNo==major.majorNo}">selected="selected"</c:if>
                            value="${major.majorNo}">${major.majorName}
                    </option>
                </c:forEach>
            </c:if>
        </select>
    </div>
    <div class="form-group">
        <label class="col-sm-1 control-label" for="changeFaNo">学院</label>
        <select name="changeFaNo" id="changeFaNo" class="selectpicker col-sm-5" data-live-search="true" data-size="5">
            <c:if test="${faultyList!=null}">
                <option value="0">选择</option>
                <c:forEach var="faculty" items="${faultyList}">
                    <option
                            <c:if test="${stuSingle.facltyNo==faculty.facultyNo}">selected="selected"</c:if>
                            value="${faculty.facultyNo}">${faculty.facultyName}
                    </option>
                </c:forEach>
            </c:if>
        </select>
    </div>
    <div class="col-sm-offset-1">
        <input class="btn btn-default" type="button" name="changesave" id="changesave" value="保存"/>
    </div>
</form>
<%@include file="/jsp/common/footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/stuchange.js"></script>
