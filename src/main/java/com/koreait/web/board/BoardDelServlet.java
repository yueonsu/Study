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

@WebServlet("/board/del")
public class BoardDelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int id = MyUtils.getParameterInt(req, "id");
        HttpSession session = req.getSession();
        UserVO user = (UserVO) session.getAttribute("loginUser");
        if(user == null) {
            req.setAttribute("err", "로그인 후 이용해주세요.");
            req.getRequestDispatcher("/board/detail").forward(req, res);
            return;
        }

        BoardVO param = new BoardVO();
        param.setWriter(user.getIuser());
        param.setId(id);
        int result = BoardDAO.delBoard(param);
        if(result == 0) {
            req.setAttribute("err", "로그인 사용자가 아닙니다.");
            req.getRequestDispatcher("/board/detail").forward(req, res);
            return;
        }
        res.sendRedirect("/board/list");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}
