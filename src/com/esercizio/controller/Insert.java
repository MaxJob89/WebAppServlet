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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by si2001 on 02/03/17.
 */
@WebServlet(name = "Insert", urlPatterns = {"/insert.do"})
public class Insert extends HttpServlet {



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User utent= new User(request.getParameter("nome"),request.getParameter("cognome"),request.getParameter("statusFilter"));

        String idn= request.getParameter("idn");

        //-----------Convert string in data format----------
        String dt=request.getParameter("data");

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try
        {
             date = dateFormat.parse(dt);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        utent.setData(date);

        //------------  Prelevo le Skill e setto l'utente --------------
        String[] sk = request.getParameterValues("skadd");

        //-------Connection------------
        Connect sess= new Connect();
        Session sessione=sess.Connection();

        if (sk!=null)
        {
            List<Skills> listSkill = ManegeSkill(sess,sk);
            utent.setSkills(listSkill);
        } //end if sk

        int msg=0;

        if(idn.compareTo("")!=0 ){

            utent.setId(Integer.parseInt(idn));
            sessione.update(utent);
            msg=1;
        }
        else
        {
            msg=(int) sessione.save(utent);

        }



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


    public List<Skills> ManegeSkill(Connect sessi,String[] lsk)
    {
        List<Skills> skill=new ArrayList<>();
        List<Skills> skiq;

        Session sessione=sessi.Connection();


        //----------Query----------------
        String SQL = "FROM Skills ";
        Query sql = sessione.createQuery(SQL);
        skiq = (List<Skills>) sql.list();

        //--------Connect db---------
        sessi.Commit(sessione);


    //---------- Get description skill---------
        for (String idt: lsk ){

            for(Skills skt : skiq)
            {
                if(idt.compareTo(skt.getDescription()) ==0)
                    skill.add(skt);
            }
        }

        return  skill;
    }

}

