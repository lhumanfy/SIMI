<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/common/header.jsp" %>
<div>
    <form class="form-inline" method="post" action="${pageContext.request.contextPath}/score.do">
        <input type="hidden" name="method" value="scorelist">
        <div class="form-group">
            <label for="scoresno">学号</label>
            <input type="text" name="scoresno" id="scoresno" autocomplete="off" value="${querySno}"
                   class="form-control">
        </div>
        <div class="form-group">
            <label for="scorecou">课程</label>
            <select name="scorecou" id="scorecou" class="selectpicker" data-live-search="true" data-size="5">
                <c:if test="${coList!=null}">
                    <option value="0">选择</option>
                    <c:forEach var="course" items="${coList}">
                        <option
                                <c:if test="${queryCou==course.couNumber}">selected="selected"</c:if>
                                value="${course.couNumber}">${course.couName}
                        </option>
                    </c:forEach>
                </c:if>
            </select>
        </div>
        <input type="hidden" name="pageIndex" value="1">
        <input type="submit" id="searchSco" value="查询" class="btn btn-default">
        <input type="button" id="goScoAdd" value="添加成绩" class="btn btn-default">
    </form>
</div>
<table class="table table-striped">
    <thead>
    <tr>
        <th>学号</th>
        <th>姓名</th>
        <th>课程号</th>
        <th>课程名</th>
        <th>分数</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="score" items="${scolist}" varStatus="status">
    <tr>
        <td>
                ${score.sNo}
        </td>
        <td>
                ${score.sName}
        </td>
        <td>
                ${score.couNum}
        </td>
        <td>
                ${score.couName}
        </td>
        <td>
                ${score.scores}
        </td>
        <td>
            <a href="javascript:;" stuSno="${score.sNo}" scoCouNum="${score.couNum}"
               class="ViewSco label label-info">查看</a>
            <a href="javascript:;" stuSno="${score.sNo}" scoCouNum="${score.couNum}"
               class="ChangeSco label label-primary">更改</a>
            <a href="javascript:;" stuSno="${score.sNo}" scoCouNum="${score.couNum}"
               class="DeleteSco label label-danger">删除</a>
        </td>
    </tr>
    </c:forEach>
    <tbody>
</table>
<c:import url="page.jsp">
    <c:param name="totalCount" value="${totalCount}"/>
    <c:param name="curPage" value="${curPage}"/>
    <c:param name="totalPage" value="${totalPage}"/>
</c:import>

<%@include file="/jsp/common/footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/scorelist.js"></script>