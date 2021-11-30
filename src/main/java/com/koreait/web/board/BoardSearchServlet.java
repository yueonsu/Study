package com.koreait.web.board;

import com.koreait.web.MyUtils;
import com.koreait.web.model.BoardPageVO;
import com.koreait.web.model.BoardVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/board/search")
public class BoardSearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String option = req.getParameter("option");
        int page = MyUtils.getParameterInt(req, "page", 1);
        String search = req.getParameter("search");

        BoardPageVO param = new BoardPageVO();
        param.setRecordCnt(10);
        param.setPage(page);

        int maxPage = BoardDAO.maxSearch(param, option, search);
        List<BoardVO> list = BoardDAO.selSearchList(option, search, param);

        if(search.trim().equals("") || list.isEmpty()) {
            req.setAttribute("err", "검색결과가 없습니다.");
        }

        req.setAttribute("listData", list);
        req.setAttribute("maxPage", maxPage);
        req.setAttribute("option", option);
        req.setAttribute("search", search);

        MyUtils.reqFoward(req, res, "/board/search");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}
