<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/jsp/common/header.jsp" %>
<form class="form-horizontal" id="addscoinfo" method="post" action="${pageContext.request.contextPath}/score.do">
    <input type="hidden" name="method" value="addScoInfo"/>
    <div class="form-group">${message}</div>
    <div class="form-group">
        <label class="col-sm-1 control-label" for="addscosno">学号</label>
        <div class="col-sm-5">
            <input type="text" name="addscosno" id="addscosno" class="form-control" value="${score.sNo}">
            <span class="help-block"></span>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-1 control-label" for="addscosname">姓名</label>
        <div class="col-sm-5">
            <input type="text" name="addscosname" id="addscosname" class="form-control" value="${score.sName}" readonly>
            <span class="help-block"></span>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-1 control-label" for="addscocname">课程</label>
        <select name="addscocname" id="addscocname" class="selectpicker col-sm-5" data-live-search="true" data-size="5">
            <c:if test="${coList!=null}">
                <c:if test="${coList!=null}">
                    <option value="0">选择</option>
                    <c:forEach var="course" items="${coList}">
                        <option
                                <c:if test="${score.couNum==course.couNumber}">selected="selected"</c:if>
                                value="${course.couNumber}">${course.couName}
                        </option>
                    </c:forEach>
                </c:if>
            </c:if>
        </select>
        <span class="help-block col-sm-offset-1" id="addscocnameMsg"></span>
    </div>
    <div class="form-group">
        <label class="col-sm-1 control-label " for="addscoscore">分数</label>
        <div class="col-sm-5">
            <input type="text" name="addscoscore" id="addscoscore" class="form-control" value="${score.scores}" >
            <span class="help-block"></span>
        </div>
    </div>
    <div class="col-sm-offset-1">
        <input class="btn btn-default" type="button" name="addscosave" id="addscosave" value="保存"/>
    </div>
</form>
<%@include file="/jsp/common/footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/scoreadd.js"></script>