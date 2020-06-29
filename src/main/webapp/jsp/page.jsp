<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>page</title>
</head>
<body>
<nav style="text-align: center;">
    <ul class="pagination">
        <li><a href="javascript:void(0);" onclick="pageChange(document.forms[0],${param.curPage}-1)">《</a></li>
        <c:forEach var="i" begin="1" end="${param.totalPage}">
            <li id="${i}"><a href="javascript:void(0);" onclick="pageChange(document.forms[0],${i})">${i}</a></li>
        </c:forEach>
        <li><a href="javascript:void(0);" onclick="pageChange(document.forms[0],${param.curPage}+1)">》</a></li>
    </ul>
</nav>
</body>
<script type="application/javascript" src="${pageContext.request.contextPath }/js/page.js"></script>
</html>
