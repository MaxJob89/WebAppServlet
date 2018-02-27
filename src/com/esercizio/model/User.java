/**
 * Created by si2001 on 03/03/17.
 */

package com.esercizio.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@Entity
@Table( name = "user",schema = "Prova")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

@Column (name = "nome")
    private String nome;

@Column (name = "cognome")
    private String second;

@Column(name="data")
    private Date data;

@Column (name = "status")
    private String status;



    public User(String nm , String cg) {
        nome = nm;
        second = cg;

    }
    public User(int idn,String nm , String cg,String st) {
        nome = nm;
        second = cg;
        status=st;
        id=idn;


    }
    public User(String nm , String cg,String st) {
        nome = nm;
        second = cg;
        status=st;
    }

    public User(){}





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return nome;
    }
    public void setData(Date dt){ this.data=dt; }
    public Date getData (){ return data;}

    public void setStatus(String dt){ this.status=dt; }
    public String getStatus (){ return status;}


    public void setFirstname(String nm) {
        this.nome = nm;
    }

    public String getLastname() {
        return second;
    }

    public void setLastname(String cg){
        this.second = cg;
    }




    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JoinTable(name="userskills",
            joinColumns={@JoinColumn(name="iduser", referencedColumnName="id")},
               inverseJoinColumns={@JoinColumn(name="descriptionskills", referencedColumnName="description")})

        private List<Skills> ski=new ArrayList<Skills>();



    public List<Skills> getSkills() {
        return ski;
    }

    public void setSkills(List<Skills> ski) {
        this.ski = ski;
    }




}

