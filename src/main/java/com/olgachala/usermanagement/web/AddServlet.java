package com.olgachala.usermanagement.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.olgachala.usermanagement.User;
import com.olgachala.usermanagement.db.DaoFactory;
import com.olgachala.usermanagement.db.DatabaseEx�eption;

public class AddServlet extends EditServlet {

    protected void processUser(User user) throws DatabaseEx�eption {
        DaoFactory.getInstance().getUserDao().create(user);
    }

    protected void showPage(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/add.jsp").forward(req, resp);
    }
}