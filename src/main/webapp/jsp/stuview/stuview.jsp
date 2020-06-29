<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/stuview/common/header.jsp" %>
    <div class="center-block">
        <p><strong>学号：</strong><span>${stuSingle.sNo}</span></p>
        <p><strong>姓名：</strong><span> ${stuSingle.sName}</span></p>
        <p><strong>性别：</strong><span> ${stuSingle.sSex}</span></p>
        <p><strong>生日：</strong><span>${stuSingle.sBir}</span></p>
        <p><strong>班级：</strong><span> ${stuSingle.sClass}</span></p>
        <p><strong>专业：</strong><span>${stuSingle.majorName}</span></p>
        <p><strong>学院：</strong><span>${stuSingle.facultyName}</span></p>
    </div>
<%@include file="/jsp/common/footer.jsp" %>