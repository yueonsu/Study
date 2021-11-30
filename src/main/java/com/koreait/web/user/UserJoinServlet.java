package com.koreait.web.user;

import com.koreait.web.MyUtils;
import com.koreait.web.model.UserVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/join")
public class UserJoinServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        MyUtils.reqFoward(req, res, "/user/join");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String uid = req.getParameter("uid");
        String upw = req.getParameter("upw");
        String nm = req.getParameter("nm");
        int gender = MyUtils.getParameterInt(req, "gender");

        if(uid.trim().equals("") || upw.trim().equals("") || nm.trim().equals("")) {
            req.setAttribute("err", "회원가입에 실패하였습니다.");
            doGet(req, res);
            return;
        }

        UserVO param = new UserVO();
        param.setUpw(upw);
        param.setUid(uid);
        param.setNm(nm);
        param.setGender(gender);

        int result = UserDAO.join(param);
        switch (result) {
            case 1:
                res.sendRedirect("/user/login");
                break;
            default:
                req.setAttribute("err", "회원가입에 실패하였습니다.");
                doGet(req, res);
                break;
        }
    }
}
