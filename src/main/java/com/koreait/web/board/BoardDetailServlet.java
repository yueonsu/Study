package com.koreait.web.board;

import com.koreait.web.MyUtils;
import com.koreait.web.model.BoardVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/detail")
public class BoardDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int id = MyUtils.getParameterInt(req, "id");
        BoardVO param = new BoardVO();
        param.setId(id);
        BoardVO list = BoardDAO.selDetailList(param);

        req.setAttribute("pre", BoardDAO.preBoard(param));
        req.setAttribute("next", BoardDAO.nextBoard(param));
        req.setAttribute("detailData", list);
        MyUtils.reqFoward(req, res, "/board/detail");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}
