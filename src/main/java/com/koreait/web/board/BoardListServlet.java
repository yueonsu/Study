package com.koreait.web.board;

import com.koreait.web.MyUtils;
import com.koreait.web.model.BoardPageVO;
import com.koreait.web.model.BoardVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/board/list")
public class BoardListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int recordCnt = 5;
        int page = MyUtils.getParameterInt(req, "page", 1);
        BoardPageVO param = new BoardPageVO();
        param.setRecordCnt(recordCnt);
        param.setPage(page);

        int maxPage = BoardDAO.maxPage(param);

        List<BoardVO> list = BoardDAO.selBoardList(param);

        req.setAttribute("listData", list);
        req.setAttribute("maxPage", maxPage);
        MyUtils.reqFoward(req, res, "/board/list");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}
