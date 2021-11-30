package com.koreait.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyUtils {
    public static void reqFoward(HttpServletRequest req, HttpServletResponse res, String path) throws ServletException, IOException {
        String jsp = "/WEB-INF/jsp" + path + ".jsp";
        req.getRequestDispatcher(jsp).forward(req, res);
    }

    public static int getParameterInt(HttpServletRequest req, String str) {
        return getParameterInt(req, str, 0);
    }

    public static int getParameterInt(HttpServletRequest req, String str, int def) {
        try {
            return Integer.parseInt(req.getParameter(str));
        } catch (Exception e) {
            return def;
        }
    }
}
