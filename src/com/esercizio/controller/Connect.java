package com.esercizio.controller;

import com.esercizio.model.Skills;

import com.esercizio.model.Status;
import com.esercizio.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * Created by massimo_buonocore on 16/03/17.
 */
public class Connect {

    public void Connect(){}

    public Session Connection(){

        Configuration conf = new Configuration().configure();
        conf.addAnnotatedClass(User.class);
        conf.addAnnotatedClass(Skills.class);
        conf.addAnnotatedClass(Status.class);

        SessionFactory sess= conf.buildSessionFactory();

        Session sessione = sess.openSession();
        sessione.beginTransaction();
        return sessione;
    }

    public void Commit(Session sessione){
        //--------Connect db---------
        sessione.getTransaction().commit();
    }

    public void Close(Session sessione){
        //--------Close connect---------
        sessione.getTransaction().commit();
        sessione.close();
     }


}
