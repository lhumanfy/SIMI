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
        <input type="button" id="goScoAdd"  data-toggle="modal" data-target="#add_scoinfo"
               value="添加成绩" class="btn btn-default">
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
            <input type="button" class="btn btn-info btn-xs" data-toggle="modal" data-target="#stuinfo"
                    onclick="getData('${score.sNo}','${score.couNum}','${score.couName}','${score.sName}','${score.scores}')" value="查看"/>
            <input type="button" class="ChangeSco btn btn-xs btn-primary" onclick="window.location.href=('javascript:;')"
                   stuSno="${score.sNo}" scoCouNum="${score.couNum}" value="更改">
            <input type="button" class="DeleteSco btn btn-xs btn-danger" onclick="window.location.href=('javascript:;')"
                   stuSno="${score.sNo}" scoCouNum="${score.couNum}" value="删除">
        </td>
    </tr>
    </c:forEach>
    <tbody>
</table>

<%--查看信息静态框--%>
<div class="modal fade" id="stuinfo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">成绩信息</h4>
            </div>
            <div class="modal-body">
                <form id="scoreinfo">
                    <div class="form-group">
                        <label class="control-label" for="sno">学号</label>
                        <input class="form-control" type="text" name="changesno" id="sno" value="" readonly/>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="couname">课程名</label>
                        <input class="form-control" type="text" name="couname" id="couname" value="" readonly/>
                        <input type="hidden" name="counum" id="counum" value=""/>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="name">学生姓名</label>
                            <input type="text" name="changename" id="name" class="form-control" value="" readonly>
                        <span class="help-block"></span>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="Score">分数</label>
                        <input type="text" name="changeSore" id="Score" class="form-control" value="" readonly>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<%--添加成绩信息静态框--%>
<div class="modal fade" id="add_scoinfo" tabindex="-1" role="dialog" aria-labelledby="add_scoinfo_title">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="add_scoinfo_title">添加成绩</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label class="control-label" for="addscosno">学号</label>
                    <input type="text" name="addscosno" id="addscosno" class="form-control" value="${score.sNo}">
                    <span class="help-block"></span>
                </div>
                <div class="form-group">
                    <label class="control-label" for="addscosname">姓名</label>
                    <input type="text" name="addscosname" id="addscosname" class="form-control" value="${score.sName}" readonly>
                    <span class="help-block"></span>
                </div>
                <div class="form-group">
                    <label class="control-label" for="addscocname">课程</label>
                    <select name="addscocname" id="addscocname" class="selectpicker" data-live-search="true" data-size="5">
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
                    <span class="help-block" id="addscocnameMsg"></span>
                </div>
                <div class="form-group">
                    <label class="control-label" for="addscoscore">分数</label>
                    <input type="text" name="addscoscore" id="addscoscore" class="form-control" value="${score.scores}" >
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <input class="btn btn-default" type="button" name="addscosave" id="addscosave" onclick="saveScoreInfo()" value="保存"/>
            </div>
        </div>
    </div>
</div>

<c:import url="page.jsp">
    <c:param name="totalCount" value="${totalCount}"/>
    <c:param name="curPage" value="${curPage}"/>
    <c:param name="totalPage" value="${totalPage}"/>
</c:import>

<%@include file="/jsp/common/footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/scorelist.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/scoreadd.js"></script>