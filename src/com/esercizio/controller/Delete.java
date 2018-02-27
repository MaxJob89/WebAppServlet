package com.esercizio.controller;

import com.esercizio.model.Skills;
import com.esercizio.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by massimo_buonocore on 09/03/17.
 */
@WebServlet(name = "Delete", urlPatterns = {"/remove.do"})
public class Delete extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       String id=request.getParameter("id");


        //-------Connection------------
        Connect sess= new Connect();
        Session sessione=sess.Connection();

        //----------Query----------------
        String SQL= "delete from User where id=:idn";
        Query sql = sessione.createQuery(SQL);
        sql.setString("idn", id);
        int msg= sql.executeUpdate();


        //--------Connect db---------
        sess.Commit(sessione);


        //---------Send jsp---------------
        request.setAttribute("msg", msg);


        RequestDispatcher in= request.getRequestDispatcher("esito.jsp");
        in.forward(request,response);


        sess.Close(sessione);//chiusura

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

