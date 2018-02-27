/**
 * Created by si2001 on 03/03/17.
 */

package com.esercizio.model;

import com.esercizio.controller.Connect;
import org.hibernate.Session;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;
import org.hibernate.query.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table( name = "skills",schema = "Prova")
public class Skills {


    @Id
    @Column(name = "description")
    private String description;


    public Skills(){
        //User constructor empty
    }

    public Skills(String desc) {
        description=desc;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String desc){
        this.description = desc;
    }




}

