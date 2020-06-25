<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/stuview/common/header.jsp" %>

<div>
    <form class="form-inline" method="post" action="${pageContext.request.contextPath}/stu.do">
        <input type="hidden" name="method" value="stuinfo">
        <div class="form-group">
            <label for="sno">学号</label>
            <input type="text" name="sno" id="sno" autocomplete="off" value="${querySno}" class="form-control">
        </div>
        <div class="form-group">
            <label for="queryMaNo">专业</label>
            <select name="queryMaNo" id="queryMaNo" class="selectpicker" data-live-search="true" data-size="5">
                <c:if test="${maList!=null}">
                    <option value="0">选择</option>
                    <c:forEach var="major" items="${maList}">
                        <option
                                <c:if test="${queryMajor==major.majorNo}">selected="selected"</c:if>
                                value="${major.majorNo}">${major.majorName}
                        </option>
                    </c:forEach>
                </c:if>
            </select>
        </div>
        <div class="form-group">
            <label for="queryFacultyNo">学院</label>
            <select name="queryFacultyNo" id="queryFacultyNo" class="selectpicker" data-live-search="true"
                    data-size="5">
                <c:if test="${faultyList!=null}">
                    <option value="0">选择</option>
                    <c:forEach var="faculty" items="${faultyList}">
                        <option
                                <c:if test="${queryfaculty==faculty.facultyNo}">selected="selected"</c:if>
                                value="${faculty.facultyNo}">
                                ${faculty.facultyName}
                        </option>
                    </c:forEach>
                </c:if>
            </select>
        </div>
        <input type="submit" id="searchBtn" value="查询" class="btn btn-default">
        <input type="button" id="goStuAdd" value="添加新用户" class="btn btn-default">
    </form>
</div>
<table class="table table-striped">
    <thead>
    <tr>
        <th>学号</th>
        <th>姓名</th>
        <th>性别</th>
        <th>生日</th>
        <th>班级</th>
        <th>专业</th>
        <th>学院</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="student" items="${stuList}" varStatus="status">
    <tr>
        <td>
                ${student.sNo}
        </td>
        <td>
                ${student.sName}
        </td>
        <td>
                ${student.sSex}
        </td>
        <td>
                ${student.sBir}
        </td>
        <td>
                ${student.sClass}
        </td>
        <td>
                ${student.majorName}
        </td>
        <td>
                ${student.facultyName}
        </td>
        <td>
            <a href="javascript:;" stuSno="${student.sNo}" stuMno="${student.majorNo}"
               stuFno="${student.facltyNo}" class="ViewStu label label-info">查看</a>
            <a href="javascript:;" stuSno="${student.sNo}" stuMno="${student.majorNo}"
               stuFno="${student.facltyNo}" class="ChangeStu label label-primary">更改</a>
            <a href="javascript:;" stuSno="${student.sNo}" class="DeleteStu label label-danger">删除</a>
        </td>
    </tr>
    </c:forEach>
    <tbody>
</table>


<%@include file="/jsp/common/footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/stulist.js"></script>