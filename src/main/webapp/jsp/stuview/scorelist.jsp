<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/stuview/common/header.jsp" %>
<div>
    <form class="form-inline" method="post" action="${pageContext.request.contextPath}/score.do">
        <input type="hidden" name="method" value="getSubInfo">
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
    </tr>
    </c:forEach>
    <tbody>
</table>
<c:import url="/jsp/page.jsp">
    <c:param name="totalCount" value="${totalCount}"/>
    <c:param name="curPage" value="${curPage}"/>
    <c:param name="totalPage" value="${totalPage}"/>
</c:import>

<%@include file="/jsp/common/footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/scorelist.js"></script>