package com.esercizio.controller;


import com.esercizio.model.User;

import org.hibernate.Session;

import org.hibernate.query.Query;


import javax.jws.soap.SOAPBinding;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by si2001 on 02/03/17.
 */
@WebServlet(name = "Servlet", urlPatterns = {"/"})
public class Servlet extends HttpServlet {



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String filtro= request.getParameter("filtro");
        String statusFilter=request.getParameter("statusFilter");


        //-------Connection------------
        Connect sess= new Connect();
        Session sessione=sess.Connection();

        List<User> result= new ArrayList<User>();

        if (filtro==null && statusFilter==null)
        {
            //----------Query----------------
            String SQL= "FROM User";
            Query sql = sessione.createQuery(SQL);
             result = (List<User>) sql.list();
        }
        else if(filtro!=null && statusFilter.compareTo("All")==0)
        {
            //----------Query----------------
            String SQL= "FROM User where nome like :filtro or cognome like :filtro ";
            Query sql = sessione.createQuery(SQL);
            sql.setString("filtro", "%"+filtro+"%");
            result = (List<User>) sql.list();
        }
        else if(filtro!=null && statusFilter.compareTo("All")!=0)
        {
            //----------Query----------------
            String SQL= "FROM User where (nome like :filtro or cognome like :filtro) and status=:stfiltro";
            Query sql = sessione.createQuery(SQL);
            sql.setString("filtro", "%"+filtro+"%");
            sql.setString("stfiltro", statusFilter);
            result = (List<User>) sql.list();


        }
        else if(filtro==null && statusFilter.compareTo("All")!=0)
        {
            //----------Query----------------
            String SQL= "FROM User where status=:stfiltro";
            Query sql = sessione.createQuery(SQL);
            sql.setString("stfiltro", statusFilter);
            result = (List<User>) sql.list();
        }


        //----------Query----------------


        String Hql="from Status";
        Query hql=sessione.createQuery(Hql);
        List<String> stat =(List<String>) hql.list();

        //--------Connect db---------
        sess.Commit(sessione);




        //---------Send jsp---------------
        request.setAttribute("result", result);
        request.setAttribute("stat",stat);


        RequestDispatcher in= request.getRequestDispatcher("utenti.jsp");
        in.forward(request,response);

        sess.Close(sessione);//chiusura

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

