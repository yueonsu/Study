package com.koreait.web.user;

import com.koreait.web.DbUtils;
import com.koreait.web.model.UserVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    public static int login(UserVO param) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT upw, nm, iuser FROM s_user WHERE uid = ? ";
        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, param.getUid());
            rs = ps.executeQuery();
            if(rs.next()) {
                param.setNm(rs.getString("nm"));
                param.setIuser(rs.getInt("iuser"));
                String upw = rs.getString("upw");
                return upw.equals(param.getUpw()) ? 1 : 3;
            }
            return 2;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return 0;
    }

    public static int join(UserVO param) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO s_user (uid, upw, nm, gender) VALUES (?, ?, ?, ?) ";
        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, param.getUid());
            ps.setString(2, param.getUpw());
            ps.setString(3, param.getNm());
            ps.setInt(4, param.getGender());
            return ps.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }
}
