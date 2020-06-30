<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/common/header.jsp" %>

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
        <input type="hidden" name="pageIndex" value="1">
        <input type="submit" id="searchBtn" value="查询" class="btn btn-default">
        <input type="button" id="goStuAdd" data-toggle="modal" data-target="#add_stuinfo"
               class="btn btn-default" value="添加新用户">
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
            <input type="button" class="btn btn-info btn-xs" data-toggle="modal" data-target="#show_stuinfo"
                   onclick="getStuData('${student.sNo}','${student.sName}','${student.sSex}','${student.sBir}','${student.sClass}',
                           '${student.majorName}','${student.facultyName}')" value="查看"/>
            <input type="button" class="ChangeStu btn btn-xs btn-primary"
                   onclick="window.location.href=('javascript:;')"
                   stuSno="${student.sNo}" stuMno="${student.majorNo}" stuFno="${student.facltyNo}"
                   value="更改">
            <input type="button" class="DeleteStu btn btn-xs btn-danger" onclick="window.location.href=('javascript:;')"
                   stuSno="${student.sNo}" value="删除">
        </td>
    </tr>
    </c:forEach>
    <tbody>
</table>

<%--显示学生信息静态框--%>
<div class="modal fade" id="show_stuinfo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">学生信息</h4>
            </div>
            <div class="modal-body">
                <form id="scoreinfo">
                    <div class="form-group">
                        <label class="control-label" for="show_sno">学号</label>
                        <input class="form-control" type="text" name="changesno" id="show_sno" value="" readonly/>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="show_sname">姓名</label>
                        <input class="form-control" type="text" name="couname" id="show_sname" value="" readonly/>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="show_sex">性别</label>
                        <input type="text" name="changename" id="show_sex" class="form-control" value="" readonly>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="show_sbir">生日</label>
                        <input type="text" name="changeSore" id="show_sbir" class="form-control" value="" readonly>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="show_sclass">班级</label>
                        <input type="text" name="changeSore" id="show_sclass" class="form-control" value="" readonly>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="show_smaj">专业</label>
                        <input type="text" name="changeSore" id="show_smaj" class="form-control" value="" readonly>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="show_sfac">学院</label>
                        <input type="text" name="changeSore" id="show_sfac" class="form-control" value="" readonly>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<%--添加新的信息静态库--%>
<div class="modal fade" id="add_stuinfo" tabindex="-1" role="dialog" aria-labelledby="add_stuinfo_title">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="add_stuinfo_title">添加学生信息</h4>
            </div>
            <div class="modal-body">
                <form id="addstuinfo">
                    <div class="form-group">
                        <label class="control-label" for="addsno">学号</label>
                        <input type="text" name="addsno" id="addsno" class="form-control" value="">
                        <span class="help-block"></span>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="addname">姓名</label>
                        <input type="text" name="addname" id="addname" class="form-control" value="">
                        <span class="help-block"></span>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="addsex">性别</label>&nbsp;&nbsp;&nbsp;
                        <select class="selectpicker" name="addsex" id="addsex">
                            <option value="男" selected="selected">男</option>
                            <option value="女">女</option>
                        </select>
                        <span class="help-block"></span>
                    </div>
                    <div class="form-group">
                        <label class="control-label " for="addbir">生日</label>
                        <input type="text" name="addbir" id="addbir" class="form-control" value="" readonly>
                        <span class="help-block"></span>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="addclass">班级</label>
                        <input type="text" name="addclass" id="addclass" class="form-control" value="">
                        <span class="help-block"></span>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="addMaNo">专业</label>&nbsp;&nbsp;&nbsp;
                        <select name="addMaNo" id="addMaNo" class="selectpicker" data-live-search="true" data-size="5">
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
                        <label class="control-label" for="addFaNo">学院</label>&nbsp;&nbsp;&nbsp;
                        <select name="addFaNo" id="addFaNo" class="selectpicker" data-live-search="true" data-size="5">
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
                        <label class="control-label" for="addpwd">密码</label>
                        <input type="text" name="addpwd" id="addpwd" class="form-control" value="">
                        <span class="help-block"></span>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <input class="btn btn-default" type="button" name="addsave" id="addsave" onclick="saveStuInfo()"
                       value="保存"/>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/stulist.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/stuadd.js"></script>