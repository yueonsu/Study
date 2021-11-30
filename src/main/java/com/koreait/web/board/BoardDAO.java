package com.koreait.web.board;

import com.koreait.web.DbUtils;
import com.koreait.web.model.BoardPageVO;
import com.koreait.web.model.BoardVO;

import java.awt.image.DataBuffer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {

    public static List<BoardVO> selSearchList(String option, String search, BoardPageVO param) {
        List<BoardVO> list = new ArrayList<>();
        String[] arr = search.split(" ");

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT A.id, A.title, A.price, A.rdt, B.nm AS writerNm " +
                " FROM t_shop A" +
                " INNER JOIN s_user B" +
                " ON A.writer = B.iuser" +
                " WHERE ";
        for(int i=0; i<arr.length; i++) {
            sql += option + " LIKE '%" + arr[i] + "%'";
            if((arr.length - i) != 1) {
                sql+=" AND ";
            }
        }
        sql += " ORDER BY id DESC LIMIT ?, ?";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getIdx());
            ps.setInt(2, param.getRecordCnt());
            rs = ps.executeQuery();
            while(rs.next()) {
                BoardVO vo = new BoardVO();
                vo.setId(rs.getInt("id"));
                vo.setTitle(rs.getString("title"));
                vo.setPrice(rs.getInt("price"));
                vo.setRdt(rs.getString("rdt"));
                vo.setWriterNm(rs.getString("writerNm"));
                list.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return list;
    }

    public static int maxSearch(BoardPageVO param, String option, String search) {
        if("nm".equals(option)) {
            option = "B.nm";
        }
        String[] arr = search.split(" ");
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT CEIL(COUNT("+ option+ ") / ?) FROM t_shop A INNER JOIN s_user B ON A.writer = B.iuser WHERE ";
        for(int i=0; i<arr.length; i++) {
            if((arr.length-i) == 1) {
                sql += option + " LIKE '%" + arr[i] + "%'";
            } else {
                sql += option + " LIKE '%" + arr[i] + "%' AND ";
            }
        }
        System.out.println(sql);
        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getRecordCnt());
            rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return 0;
    }

    public static int nextBoard(BoardVO param) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT id FROM t_shop WHERE id < ? ORDER BY id DESC ";
        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getId());
            rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getInt("id");
            }
            return param.getId();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return 0;
    }

    public static int preBoard(BoardVO param) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT id FROM t_shop WHERE id > ? ";
        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getId());
            rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getInt("id");
            }
            return param.getId();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return 0;
    }

    public static int maxPage(BoardPageVO param) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT CEIL(COUNT(*) / ?) FROM t_shop ";
        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getRecordCnt());
            rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps ,rs);
        }
        return 0;
    }

    public static int insBoard(BoardVO param) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO t_shop (title, contents, category, price, writer) VALUES" +
                " (?, ?, ?, ?, ?) ";
        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, param.getTitle());
            ps.setString(2, param.getContents());
            ps.setString(3, param.getCategory());
            ps.setInt(4, param.getPrice());
            ps.setInt(5, param.getWriter());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }

    public static BoardVO selDetailList(BoardVO param) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT A.writer, A.id, A.title, A.contents, A.price, A.category, A.rdt, B.nm AS writerNm" +
                " FROM t_shop A" +
                " INNER JOIN s_user B" +
                " ON A.writer = B.iuser" +
                " WHERE id = ? ";
        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getId());
            rs = ps.executeQuery();
            if(rs.next()) {
                BoardVO vo = new BoardVO();
                vo.setWriter(rs.getInt("writer"));
                vo.setId(rs.getInt("id"));
                vo.setTitle(rs.getString("title"));
                vo.setContents(rs.getString("contents"));
                vo.setPrice(rs.getInt("price"));
                vo.setCategory(rs.getString("category"));
                vo.setWriterNm(rs.getString("writerNm"));
                vo.setRdt(rs.getString("rdt"));
                return vo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return null;
    }

    public static List<BoardVO> selBoardList(BoardPageVO param) {
        List<BoardVO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT A.id, A.title, A.rdt, A.price, B.nm AS writerNm" +
                " FROM t_shop A" +
                " INNER JOIN s_user B" +
                " ON A.writer = B.iuser" +
                " ORDER BY id DESC " +
                " LIMIT ?, ?";
        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getIdx());
            ps.setInt(2, param.getRecordCnt());
            rs = ps.executeQuery();
            while(rs.next()) {
                BoardVO vo = new BoardVO();
                vo.setId(rs.getInt("id"));
                vo.setTitle(rs.getString("title"));
                vo.setWriterNm(rs.getString("writerNm"));
                vo.setRdt(rs.getString("rdt"));
                vo.setPrice(rs.getInt("price"));
                list.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return list;
    }

    public static int updBoard(BoardVO param) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "UPDATE t_shop SET title = ?, contents = ?, category = ?, price = ? WHERE id = ? ";
        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, param.getTitle());
            ps.setString(2, param.getContents());
            ps.setString(3, param.getCategory());
            ps.setInt(4, param.getPrice());
            ps.setInt(5, param.getId());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }

    public static int delBoard(BoardVO param) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM t_shop WHERE id = ? AND writer = ? ";
        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getId());
            ps.setInt(2, param.getWriter());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }
}
