package com.esercizio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table( name = "status",schema = "Prova")
public class Status {

    @Id
    @Column(name = "name")
    private String name;


    public Status(){
        //User constructor empty
    }

    public Status(String nm) {
        name=nm;

    }



    public String getName() {
        return name;
    }

    public void setName(String nm){
        this.name = nm;
    }




}

