package com.lpf.servlet.scores;

import com.alibaba.fastjson.JSONArray;
import com.lpf.pojo.*;
import com.lpf.service.course.CourseService;
import com.lpf.service.course.CourseServiceImpl;
import com.lpf.service.faculty.FacultyService;
import com.lpf.service.faculty.FacultyServiceImpl;
import com.lpf.service.major.MaService;
import com.lpf.service.major.MaServiceImpl;
import com.lpf.service.score.ScoreService;
import com.lpf.service.score.ScoreServiceImpl;
import com.lpf.service.student.StuService;
import com.lpf.service.student.StuServiceImpl;
import com.lpf.util.Constants;
import com.lpf.util.Page;
import com.microsoft.sqlserver.jdbc.StringUtils;
import org.apache.taglibs.standard.tag.common.fmt.MessageSupport;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoresServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method.equals("scorelist") && method != null) {
            this.getScoreInfo(req, resp);
        } else if (method.equals("upScoreInfo") && method != null) {
            this.upScoreInfo(req, resp);
        } else if (method.equals("beforechangeSco") && method != null) {
            this.beforeUpScore(req, resp);
        } else if (method.equals("delSco") && method != null) {
            this.delScoreInfo(req, resp);
        } else if (method.equals("addScoInfo") && method != null) {
            this.addScoreInfo(req, resp);
        } else if (method.equals("checkSnoCouno") && method != null) {
            this.checkSnoCouno(req, resp);
        } else if (method.equals("getSubInfo") && method != null) {
            this.getSubInfo(req, resp);
        }

    }

    //得到成绩信息
    public void getScoreInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String snoTemp = req.getParameter("scoresno");
        String scorecouTemp = req.getParameter("scorecou");
        String curPageTemp = req.getParameter("pageIndex");

        Integer sno = null;//输入框学号的初始默认数据
        int scorecou = 0;//选择课程的下拉框默认数据

        //获取每个页面数据个数
        int pageSize = Constants.PAGESIZE;
        int curPage = 1;

        ScoreService scoreService = new ScoreServiceImpl();
        CourseService courseService = new CourseServiceImpl();
        List<Score> scoreList = null;
        List<Course> courseList = null;

        if (snoTemp != null && !snoTemp.equals("") && !snoTemp.equals("null")) {
            sno = Integer.parseInt(snoTemp);
        }
        if (scorecouTemp != null && !scorecouTemp.equals("")) {
            scorecou = Integer.parseInt(scorecouTemp);
        }
        if (curPageTemp != null && !curPageTemp.equals("")) {
            curPage = Integer.parseInt(curPageTemp);
        }

        //获取数据总条数
        int totalCount = scoreService.getScoreListCount(sno, scorecou, curPage, pageSize);
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
        //获取成绩列表
        scoreList = scoreService.getScoreList(sno, scorecou, curPage, pageSize);
        req.setAttribute("scolist", scoreList);
        courseList = courseService.getCourseList();
        req.setAttribute("coList", courseList);

        req.setAttribute("querySno", snoTemp);
        req.setAttribute("queryCou", scorecou);

        req.getRequestDispatcher("/jsp/scorelist.jsp").forward(req, resp);
    }

    //更改成绩前查询
    public void beforeUpScore(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sno = req.getParameter("sno");
        String counum = req.getParameter("counum");

        ScoreService scoreService = new ScoreServiceImpl();
        CourseService courseService = new CourseServiceImpl();
        List<Score> scoreList = null;
        List<Course> courseList = null;

        scoreList = scoreService.getScoreList(Integer.parseInt(sno), Integer.parseInt(counum), 1, 10);
        courseList = courseService.getCourseList();

        Score score = new Score();
        for (Score sco : scoreList) {
            if (sco.getsNo() == Integer.parseInt(sno)) {
                score = sco;
                break;
            }
        }

        req.setAttribute("scoSingle", score);
        req.setAttribute("coList", courseList);
        req.getRequestDispatcher("/jsp/scorechange.jsp").forward(req, resp);
    }

    //更改学生成绩
    public void upScoreInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String changesno = req.getParameter("changesno");
        String changecouname = req.getParameter("changecouname");
        String changecounum = req.getParameter("changecounum");
        String changename = req.getParameter("changename");
        String changeSore = req.getParameter("changeSore");

        Score score = new Score();
        List<Course> courseList = null;
        score.setsNo(Integer.parseInt(changesno));
        score.setsName(changename);
        score.setCouNum(Integer.parseInt(changecounum));
        score.setScores(changeSore);
        score.setCouName(changecouname);

        ScoreService scoreService = new ScoreServiceImpl();
        CourseService courseService = new CourseServiceImpl();
        courseList = courseService.getCourseList();
        req.setAttribute("coList", courseList);

        if (scoreService.upSores(score)) {
            req.setAttribute(Constants.MESSAGE, "成绩更改成功");
            req.setAttribute("scoSingle", score);
        } else {
            req.setAttribute(Constants.MESSAGE, "成绩更改失败");
            req.setAttribute("scoSingle", score);
        }

        req.getRequestDispatcher("/jsp/scorechange.jsp").forward(req, resp);

    }

    //删除学生成绩
    public void delScoreInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sno = req.getParameter("sno");
        String counum = req.getParameter("counum");

        Map<String, String> message = new HashMap<String, String>();

        ScoreService scoreService = new ScoreServiceImpl();
        if (scoreService.delScores(Integer.parseInt(sno), Integer.parseInt(counum))) {
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

    //添加成绩信息
    public void addScoreInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String addscosno = req.getParameter("addscosno");
        String addscosname = req.getParameter("addscosname");
        String addscocname = req.getParameter("addscocname");
        String addscoscore = req.getParameter("addscoscore");

        Map<String, String> message = new HashMap<String, String>();

        System.out.println("addscosno>------>" + addscosno);
        System.out.println("addscosname>------>" + addscosname);
        System.out.println("addscocname>------>" + addscocname);
        System.out.println("addscoscore>------>" + addscoscore);
        Score score = new Score();
        score.setsNo(Integer.parseInt(addscosno));
        score.setsName(addscosname);
        score.setCouNum(Integer.parseInt(addscocname));
        score.setScores(addscoscore);

        CourseService courseService = new CourseServiceImpl();
        ScoreService scoreService = new ScoreServiceImpl();
        List<Course> courseList = null;

        courseList = courseService.getCourseList();
        req.setAttribute("coList", courseList);
        if (scoreService.addScores(score)) {
            message.put("result", "success");
        } else {
            message.put("result", "error");
            message.put("addscosno", addscosno);
            message.put("addscosname", addscosname);
            message.put("addscocname", addscocname);
            message.put("addscoscore", addscoscore);
        }

        try {
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            writer.write(JSONArray.toJSONString(message));
            writer.flush();
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //检查某一学生的某课程的成绩信息是否存在
    public void checkSnoCouno(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String snoTemp = req.getParameter("sno");
        String cnameTemp = req.getParameter("cname");

        Map<String, String> message = new HashMap<String, String>();
        int sno = 0;
        int cname = 0;
        try {
            if (snoTemp != null && !snoTemp.equals("")) {
                sno = Integer.parseInt(snoTemp);
            }
            if (cnameTemp != null && !cnameTemp.equals("")) {
                cname = Integer.parseInt(cnameTemp);
            }

            ScoreService scoreService = new ScoreServiceImpl();
            if (cname == 0) {
                message.put("result", "empty");
            } else if (scoreService.exam(sno, cname)) {
                message.put("result", "error");
            } else {
                message.put("result", "success");
            }

            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            writer.write(JSONArray.toJSONString(message));
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //以下学生操作，即学生能够操作的界面
    //登录成功后如果查询某一学科信息，则从session中获取用户学号从而完成查询
    public void getSubInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String scorecouTemp = req.getParameter("scorecou");
        Student stu = (Student) req.getSession().getAttribute(Constants.STU_SESSION);
        String curPageTemp = req.getParameter("pageIndex");

        Integer sno = null;
        //从session中获取登录的学号
        if (stu == null) {
            resp.sendRedirect("/error.jsp");
            return;
        } else {
            sno = stu.getsNo();
        }

        //选择课程的下拉框默认数据
        int scorecou = 0;

        //获取每个页面数据个数
        int pageSize = Constants.PAGESIZE;
        int curPage = 1;

        ScoreService scoreService = new ScoreServiceImpl();
        CourseService courseService = new CourseServiceImpl();
        List<Score> scoreList = null;
        List<Course> courseList = null;

        if (scorecouTemp != null && !scorecouTemp.equals("")) {
            scorecou = Integer.parseInt(scorecouTemp);
        }
        if (curPageTemp != null && !curPageTemp.equals("")) {
            curPage = Integer.parseInt(curPageTemp);
        }

        //获取数据总条数
        int totalCount = scoreService.getScoreListCount(sno, scorecou, curPage, pageSize);
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

        //获取成绩列表
        scoreList = scoreService.getScoreList(sno, scorecou, curPage, pageSize);//待定
        req.setAttribute("scolist", scoreList);
        courseList = courseService.getCourseList();
        req.setAttribute("coList", courseList);

        req.setAttribute("queryCou", scorecou);

        req.getRequestDispatcher("jsp/stuview/scorelist.jsp").forward(req, resp);
    }
}
