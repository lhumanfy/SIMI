<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/common/header.jsp" %>
<div class="center-block">
    <p><strong>学号：</strong><span>${soSingle.sNo}</span></p>
    <p><strong>姓名：</strong><span> ${soSingle.sName}</span></p>
    <p><strong>课程号：</strong><span>${soSingle.couNum}</span></p>
    <p><strong>课程名：</strong>
        <c:if test="${coList!=null}">
            <c:forEach var="course" items="${coList}">
                <c:if test="${soSingle.couNum==course.couNumber}">
                    <span>${course.couName}</span>
                </c:if>
            </c:forEach>
        </c:if>
    </p>
    <p><strong>分数：</strong><span> ${soSingle.scores}</span></p>
</div>
<%@include file="/jsp/common/footer.jsp" %>

