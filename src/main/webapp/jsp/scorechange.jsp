<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/common/header.jsp" %>
<form class="form-horizontal" id="changescoreinfo" method="post" action="${pageContext.request.contextPath}/score.do">
    <input type="hidden" name="method" value="upScoreInfo"/>
    <div class="form-group">${message}</div>
    <div class="form-group">
        <label class="col-sm-1 control-label" for="changesno">当前学号</label>
        <div class="col-sm-5">
            <input class="form-control" type="text" name="changesno" id="changesno" value="${scoSingle.sNo }" readonly/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-1 control-label" for="changecouname">当前课程名</label>
        <div class="col-sm-5">
            <c:if test="${coList!=null}">
                <c:forEach var="course" items="${coList}">
                    <c:if test="${scoSingle.couNum==course.couNumber}">
                        <input class="form-control" type="text" name="changecouname" id="changecouname"
                               value="${course.couName}" readonly/>
                        <input type="hidden" name="changecounum" id="changecounum"
                               value="${scoSingle.couNum}"/>
                    </c:if>
                </c:forEach>
            </c:if>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-1 control-label" for="changename">学生姓名</label>
        <div class="col-sm-5">
            <input type="text" name="changename" id="changename" class="form-control" value="${scoSingle.sName}"
                   readonly>
        </div>
        <span class="help-block"></span>
    </div>
    <div class="form-group">
        <label class="col-sm-1 control-label" for="changeSore">分数</label>
        <div class="col-sm-5">
            <input type="text" name="changeSore" id="changeSore" class="form-control" value="${scoSingle.scores}">
            <span class="help-block"></span>
        </div>
    </div>
    <div class="col-sm-offset-1">
        <input class="btn btn-default" type="button" name="changesave" id="changesave" value="保存"/>
    </div>
</form>
<%@include file="/jsp/common/footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/scorechange.js"></script>


