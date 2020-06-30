package com.lpf.servlet.student;

import com.alibaba.fastjson.JSONArray;
import com.lpf.dao.major.MajorDao;
import com.lpf.dao.major.MajorDaoImpl;
import com.lpf.pojo.Faculty;
import com.lpf.pojo.Major;
import com.lpf.pojo.Score;
import com.lpf.pojo.Student;
import com.lpf.service.faculty.FacultyService;
import com.lpf.service.faculty.FacultyServiceImpl;
import com.lpf.service.major.MaService;
import com.lpf.service.major.MaServiceImpl;
import com.lpf.service.student.StuService;
import com.lpf.service.student.StuServiceImpl;
import com.lpf.util.Constants;
import com.lpf.util.Page;
import com.microsoft.sqlserver.jdbc.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javafx.scene.input.KeyCode.CIRCUMFLEX;
import static javafx.scene.input.KeyCode.M;

public class StuServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method.equals("savepwd") && method != null) {
            this.updatePwd(req, resp);
        } else if (method.equals("pwdajax") && method != null) {
            this.checkPwd(req, resp);
        } else if (method.equals("scores") && method != null) {
            this.getScore(req, resp);
        } else if (method.equals("stuinfo") && method != null) {
            this.getStuInfo(req, resp);
        } else if (method.equals("changeStu") && method != null) {
            this.changeStuInfo(req, resp);
        } else if (method.equals("upStuInfo") && method != null) {
            this.upStuInfo(req, resp);
        } else if (method.equals("delStu") && method != null) {
            this.delStuInfo(req, resp);
        } else if (method.equals("addStuInfo") && method != null) {
            this.addStuInfo(req, resp);
        } else if (method.equals("examsno") && method != null) {
            this.examSno(req, resp);
        } else if (method.equals("getSigleStu") && method != null) {
            this.getSigleStu(req, resp);
        } else if (method.equals("stuGetInfo") && method != null) {
            this.stuGetInfo(req, resp);
        }
    }

    //修改密码
    public void updatePwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object attribute = req.getSession().getAttribute(Constants.STU_SESSION);
        String pwd = req.getParameter("newPwd");

        boolean flag = false;
        if (attribute != null && !StringUtils.isEmpty(pwd)) {
            StuService stuService = new StuServiceImpl();
            flag = stuService.chage(((Student) attribute).getsNo(), pwd);
            if (flag) {
                req.setAttribute(Constants.MESSAGE, "修改密码成功,请重新登录");
                //密码更改成功，移除session
                req.getSession().removeAttribute(Constants.STU_SESSION);
            } else {
                req.setAttribute(Constants.MESSAGE, "修改密码失败");
            }
        } else {
            req.setAttribute(Constants.MESSAGE, "新密码输入错误");
        }
        req.getRequestDispatcher(req.getContextPath() + "jsp/changepwd.jsp").forward(req, resp);
    }

    //验证旧密码
    public void checkPwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object attribute = req.getSession().getAttribute(Constants.STU_SESSION);
        String oldPwd = req.getParameter("oldPwd");

        Map<String, String> message = new HashMap<String, String>();
        if (attribute == null) {
            message.put("result", "sessionerror");
        } else if (StringUtils.isEmpty(oldPwd)) {
            message.put("result", "empty");
        } else {
            String sessionPwd = ((Student) attribute).getPassWord();
            if (oldPwd.equals(sessionPwd)) {
                message.put("result", "true");
            } else {
                message.put("result", "false");
            }
        }
        try {
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            writer.write(JSONArray.toJSONString(message));
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //获取用户成绩
    public void getScore(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object attribute = req.getSession().getAttribute(Constants.STU_SESSION);

        if (attribute != null) {
            StuService stuService = new StuServiceImpl();
            List<Score> scoreList = stuService.getScoreList(((Student) attribute).getsNo());
            req.setAttribute("scoreList", scoreList);
        }
        req.getRequestDispatcher(req.getContextPath() + "jsp/scorelist.jsp").forward(req, resp);
    }

    //获取学生信息
    public void getStuInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String snoTemp = req.getParameter("sno");
        String majorNoTemp = req.getParameter("queryMaNo");
        String queryFacultyNoTemp = req.getParameter("queryFacultyNo");
        String curPageTemp = req.getParameter("pageIndex");

        Integer sno = null;//输入框学号的初始默认数据
        int queryMaNo = 0;//选择专业的下拉框默认数据
        int queryFaNo = 0;//选择学院的下拉框默认数据

        //获取每个页面数据个数
        int pageSize = Constants.PAGESIZE;
        int curPage = 1;

        StuService stuService = new StuServiceImpl();
        MaService maService = new MaServiceImpl();
        FacultyService facultyService = new FacultyServiceImpl();
        List<Student> stuList = null;
        List<Major> majorList = null;
        List<Faculty> facultyList = null;

        if (snoTemp != null && !snoTemp.equals("") && !snoTemp.equals("null")) {
            sno = Integer.parseInt(snoTemp);
        }
        if (majorNoTemp != null && !majorNoTemp.equals("")) {
            queryMaNo = Integer.parseInt(majorNoTemp);
        }
        if (queryFacultyNoTemp != null && !queryFacultyNoTemp.equals("")) {
            queryFaNo = Integer.parseInt(queryFacultyNoTemp);
        }
        if (curPageTemp != null && !curPageTemp.equals("")) {
            curPage = Integer.parseInt(curPageTemp);
        }

        //获取数据总条数
        int totalCount = stuService.getStuListCount(sno, queryMaNo, queryFaNo);
        Page page = new Page();
        page.setCurPage(curPage);
        page.setPageSize(pageSize);
        page.setTotalCount(totalCount);
        page.setTotalPage();

        int totalPage = page.getTotalPage();
        if (curPage < 1) {
            curPage = 1;
        } else if (curPage > totalPage) {
            curPage = totalPage;
        }

        req.setAttribute("totalPage", totalPage);
        req.setAttribute("curPage", curPage);
        req.setAttribute("totalCount", totalCount);
        //获取用户列表
        stuList = stuService.getStuList(sno, queryMaNo, queryFaNo, curPage, pageSize);
        req.setAttribute("stuList", stuList);
        majorList = maService.getMajorList();
        req.setAttribute("maList", majorList);
        facultyList = facultyService.getFacultyList();
        req.setAttribute("faultyList", facultyList);

        req.setAttribute("querySno", snoTemp);
        req.setAttribute("queryMajor", queryMaNo);
        req.setAttribute("queryfaculty", queryFaNo);

        req.getRequestDispatcher("/jsp/stulist.jsp").forward(req, resp);
    }

    //修改用户信息前查询用户信息
    public void changeStuInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sno = req.getParameter("sno");
        String majorno = req.getParameter("majorno");
        String facultyno = req.getParameter("facultyno");


        MaService maService = new MaServiceImpl();
        FacultyService facultyService = new FacultyServiceImpl();
        StuService stuService = new StuServiceImpl();
        List<Student> stuList = null;
        List<Major> majorList = null;
        List<Faculty> facultyList = null;


        stuList = stuService.getStuList(Integer.parseInt(sno), Integer.parseInt(majorno), Integer.parseInt(facultyno), 1, 10);
        Student student = new Student();
        for (Student stu : stuList) {
            if (stu.getsNo() == Integer.parseInt(sno)) {
                student = stu;
                break;
            }
        }
        req.setAttribute("stuSingle", student);
        majorList = maService.getMajorList();
        req.setAttribute("maList", majorList);
        facultyList = facultyService.getFacultyList();
        req.setAttribute("faultyList", facultyList);

        req.getRequestDispatcher("/jsp/stuchange.jsp").forward(req, resp);
    }

    //修改用户信息
    public void upStuInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String changesno = req.getParameter("changesno");
        String changename = req.getParameter("changename");
        String changesex = req.getParameter("changesex");
        String changebir = req.getParameter("changebir");
        String changeclass = req.getParameter("changeclass");
        String changeMaNo = req.getParameter("changeMaNo");
        String changeFaNo = req.getParameter("changeFaNo");

        Student student = new Student();
        student.setsNo(Integer.parseInt(changesno));
        student.setsName(changename);
        student.setsSex(changesex);
        student.setsBir(changebir);
        student.setsClass(changeclass);
        student.setMajorNo(Integer.parseInt(changeMaNo));
        student.setFacltyNo(Integer.parseInt(changeFaNo));
        student.setMajorName(null);
        student.setFacultyName(null);

        MaService maService = new MaServiceImpl();
        FacultyService facultyService = new FacultyServiceImpl();
        StuService stuService = new StuServiceImpl();
        List<Major> majorList = null;
        List<Faculty> facultyList = null;

        majorList = maService.getMajorList();
        req.setAttribute("maList", majorList);
        facultyList = facultyService.getFacultyList();
        req.setAttribute("faultyList", facultyList);
        if (stuService.upStudent(student)) {
            req.setAttribute(Constants.MESSAGE, "用户信息更改成功");
            req.setAttribute("stuSingle", student);
        } else {
            req.setAttribute(Constants.MESSAGE, "用户信息更改失败");
            req.setAttribute("stuSingle", student);
        }

        req.getRequestDispatcher("/jsp/stuchange.jsp").forward(req, resp);
    }

    //删除用户
    public void delStuInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sno = req.getParameter("sno");

        Map<String, String> message = new HashMap<String, String>();

        StuService stuService = new StuServiceImpl();
        if (stuService.delStuInfo(Integer.parseInt(sno))) {
            message.put("result", "true");
        } else {
            message.put("result", "false");
        }

        try {
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            writer.write(JSONArray.toJSONString(message));
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //添加用户
    public void addStuInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String addsno = req.getParameter("addsno");
        String addname = req.getParameter("addname");
        String addsex = req.getParameter("addsex");
        String addbir = req.getParameter("addbir");
        String addclass = req.getParameter("addclass");
        String addMaNo = req.getParameter("addMaNo");
        String addFaNo = req.getParameter("addFaNo");
        String addpwd = req.getParameter("addpwd");

        Map<String, String> message = new HashMap<String, String>();
        Student student = new Student();
        student.setsNo(Integer.parseInt(addsno));
        student.setsName(addname);
        student.setsSex(addsex);
        student.setsBir(addbir);
        student.setsClass(addclass);
        student.setMajorNo(Integer.parseInt(addMaNo));
        student.setFacltyNo(Integer.parseInt(addFaNo));
        student.setMajorName(null);
        student.setFacultyName(null);
        student.setPassWord(addpwd);

        MaService maService = new MaServiceImpl();
        FacultyService facultyService = new FacultyServiceImpl();
        StuService stuService = new StuServiceImpl();
        List<Major> majorList = null;
        List<Faculty> facultyList = null;

        majorList = maService.getMajorList();
        req.setAttribute("maList", majorList);
        facultyList = facultyService.getFacultyList();
        req.setAttribute("faultyList", facultyList);
        if (stuService.addStuInfo(student)) {
            message.put("result", "success");
        } else {
            message.put("result", "error");
            message.put("sno", addsno);
            message.put("name",addname);
            message.put("sex", addsex);
            message.put("bir", addbir);
            message.put("class", addclass);
            message.put("mano", addMaNo);
            message.put("fano", addFaNo);
            message.put("pwd", addpwd);
        }

        //将json数据返回
        try {
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            writer.write(JSONArray.toJSONString(message));
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //验证添加学号是否重复
    public void examSno(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sno = req.getParameter("sno");

        Map<String, String> message = new HashMap<String, String>();
        if (StringUtils.isEmpty(sno)) {
            message.put("result", "empty");
        } else {
            StuService stuService = new StuServiceImpl();
            if (stuService.exam(Integer.parseInt(sno))) {
                message.put("result", "error");
            } else {
                message.put("result", "success");
            }
        }
        try {
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            writer.write(JSONArray.toJSONString(message));
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //根据学号得到student实例，然后返回名字与map判断
    public void getSigleStu(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sno = req.getParameter("sno");

        Map<String, String> message = new HashMap<String, String>();
        if (StringUtils.isEmpty(sno)) {
            message.put("result", "empty");
        } else {
            StuService stuService = new StuServiceImpl();
            Student sigStuInfo = stuService.getSigStuInfo(Integer.parseInt(sno));
            if (sigStuInfo != null) {
                message.put("result", "success");
                message.put("sname", sigStuInfo.getsName());
            } else {
                message.put("result", "error");
            }
        }
        try {
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            writer.write(JSONArray.toJSONString(message));
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //下面学生界面操作

    //获取登录的学生信息
    public void stuGetInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student stuSession = (Student) req.getSession().getAttribute(Constants.STU_SESSION);

        if (stuSession == null) {
            resp.sendRedirect("/error.jsp");
            return;
        }

        int sno = stuSession.getsNo();
        int majorno = stuSession.getMajorNo();
        int facultyno = stuSession.getFacltyNo();

        StuService stuService = new StuServiceImpl();
        List<Student> stuList = null;

        stuList = stuService.getStuList(sno, majorno, facultyno, 1, 10);
        Student student = new Student();
        for (Student stu : stuList) {
            if (stu.getsNo() == sno) {
                student = stu;
                break;
            }
        }
        req.setAttribute("stuSingle", student);
        req.getRequestDispatcher("/jsp/stuview/stuview.jsp").forward(req, resp);
    }
}
