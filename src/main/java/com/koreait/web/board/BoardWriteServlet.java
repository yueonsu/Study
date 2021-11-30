package com.koreait.web.board;

import com.koreait.web.MyUtils;
import com.koreait.web.model.BoardVO;
import com.koreait.web.model.UserVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/board/write")
public class BoardWriteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session.getAttribute("loginUser") == null) {
            res.sendRedirect("/user/login");
            return;
        }

        MyUtils.reqFoward(req, res, "/board/write");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserVO user = (UserVO) session.getAttribute("loginUser");
        String title = req.getParameter("title");
        String contents = req.getParameter("contents");
        String category = req.getParameter("category");
        int price = MyUtils.getParameterInt(req, "price");

        BoardVO param = new BoardVO();
        param.setTitle(title);
        param.setContents(contents);
        param.setCategory(category);
        param.setPrice(price);
        param.setWriter(user.getIuser());

        int result = BoardDAO.insBoard(param);
        switch (result) {
            case 1:
                res.sendRedirect("/board/list");
                break;
            default:
                req.setAttribute("err", "글 등록에 실패하였습니다.");
                req.setAttribute("data", param);
                doGet(req, res);
        }
    }
}
