package com.koreait.web.user;

import com.koreait.web.MyUtils;
import com.koreait.web.model.UserVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/user/login")
public class UserLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        MyUtils.reqFoward(req, res, "/user/login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String uid = req.getParameter("uid");
        String upw = req.getParameter("upw");

        UserVO vo = new UserVO();
        vo.setUid(uid);
        vo.setUpw(upw);

        ArrayList<Object> answer = new ArrayList<>();


        int result = UserDAO.login(vo);
        if(result == 1) {
            vo.setUpw(null);
            HttpSession session = req.getSession();
            session.setAttribute("loginUser", vo); // uid, nm, iuser
            res.sendRedirect("/board/list");
            return;
        }

        String err = null;
        switch(result) {
            case 2:
                err = "아이디가 존재하지 않습니다.";
                break;
            case 3:
                err = "비밀번호가 일치하지 않습니다.";
                break;
            case 0:
                err = "로그인 실패";
                break;
        }
        req.setAttribute("err", err);
        doGet(req, res);
    }
}
