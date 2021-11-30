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

@WebServlet("/board/mod")
public class BoardModServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int id = MyUtils.getParameterInt(req, "id");
        BoardVO param = new BoardVO();
        param.setId(id);
        BoardVO list = BoardDAO.selDetailList(param);

        HttpSession session = req.getSession();
        UserVO user = (UserVO) session.getAttribute("loginUser");

        if(user == null || user.getIuser()!=list.getWriter()) {
            res.sendRedirect("/user/login");
            return;
        }

        if(req.getAttribute("modData") == null) {
            req.setAttribute("modData", list);
        }
        MyUtils.reqFoward(req, res, "/board/mod");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String title = req.getParameter("title");
        String contents = req.getParameter("contents");
        String category = req.getParameter("category");
        int price = MyUtils.getParameterInt(req, "price");
        int id = MyUtils.getParameterInt(req, "id");

        BoardVO param = new BoardVO();
        param.setTitle(title);
        param.setContents(contents);
        param.setCategory(category);
        param.setPrice(price);
        param.setId(id);

        int result = BoardDAO.updBoard(param);

        switch (result) {
            case 1:
                res.sendRedirect("/board/detail?id=" + id);
                break;
            default:
                req.setAttribute("err", "글 수정에 실패했습니다.");
                req.setAttribute("modData", param);
                doGet(req, res);
                break;
        }
    }
}
