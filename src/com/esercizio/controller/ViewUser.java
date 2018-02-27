package com.esercizio.controller;

import com.esercizio.model.Skills;
import com.esercizio.model.Status;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by massimo_buonocore on 15/03/17.
 */
@WebServlet(name = "ViewUser", urlPatterns = {"/viewuser.do"})
public class ViewUser extends HttpServlet {



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");

        //-------Connection------------
        Connect sess = new Connect();
        Session sessione = sess.Connection();


        User us = null;
        List<Skills> skiq;
        List<Skills> skill = new ArrayList<>();
        List<String> stati = new ArrayList<>();

        if (id == null) {
            //----------Query----------------
            String SQL = "FROM Skills ";
            Query sql = sessione.createQuery(SQL);
            skiq = (List<Skills>) sql.list();


            String Hql = "from Status";
            Query hql = sessione.createQuery(Hql);
            List<Status> stat = (List<Status>) hql.list();

            //------ Salvo la lista nella variabile che poi verrà passata alla jSP------
            for (Status el : stat) {
                stati.add(el.getName());
            }

            //---------Gestione delle skill------------
//            for (Skills el2 : skiq) {
//                skill.add(el2);
//            }

        } else {
            String SQL = "FROM Skills ";
            Query sql = sessione.createQuery(SQL);
            skiq = (List<Skills>) sql.list();


            String Hql = "from Status";
            Query hql = sessione.createQuery(Hql);
            List<Status> stat = (List<Status>) hql.list();


            String Hql2 = "FROM User where id=" + id;
            Query hql2 = sessione.createQuery(Hql2);
            us = (User) hql2.list().get(0);

            //-------------For per gestire gli status --------
            String st = us.getStatus();
            for (Status el : stat) {
                if ((el.getName().compareTo(st)) != 0)
                    stati.add(el.getName());
            }

        //-------------For per gestire le skills ----------
            for (Skills el : skiq)
            {
                for (Skills temp: us.getSkills() )
                {
                    //----- add skill only different user skill-----
                    if (el.getDescription().compareTo(temp.getDescription()) == 0)
                        skill.add(el);
                }//end for utent skill
            }//end for skill

            skiq.removeAll(skill);


       }//end else id==null



        //--------Connect db---------
        sess.Commit(sessione);


        //---------Send jsp---------------
        request.setAttribute("ski", skiq);//param delle skills di un utente
        request.setAttribute("stat",stati);//param degli status di in utente
        request.setAttribute("us",us);//param dell'utente composto da skill e status

        RequestDispatcher in= request.getRequestDispatcher("insert.jsp");
        in.forward(request,response);


        sess.Close(sessione);//chiusura

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}