package com.munna.model;

import javax.persistence.*;

/**
 * Created by monju on 1/24/2016.
 */

@Entity
@Table(name="normalizeddata")
public class NormalizedData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    public Long id;
    @Column(name="age")
    public String age;
    @Column(name="gender")
    public String gender;
    @Column(name="tb")
    public String tb;
    @Column(name="db")
    public String db;
    @Column(name="alkphos")
    public String alkphos;
    @Column(name="sgpt")
    public String sgpt;
    @Column(name="sgot")
    public String sgot;
    @Column(name="tp")
    public String tp;
    @Column(name="alb")
    public String alb;
    @Column(name="ag")
    public String ag;
    @Column(name="disease")
    public String disease;

    public NormalizedData(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTb() {
        return tb;
    }

    public void setTb(String tb) {
        this.tb = tb;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getAlkphos() {
        return alkphos;
    }

    public void setAlkphos(String alkphos) {
        this.alkphos = alkphos;
    }

    public String getSgpt() {
        return sgpt;
    }

    public void setSgpt(String sgpt) {
        this.sgpt = sgpt;
    }

    public String getSgot() {
        return sgot;
    }

    public void setSgot(String sgot) {
        this.sgot = sgot;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    public String getAlb() {
        return alb;
    }

    public void setAlb(String alb) {
        this.alb = alb;
    }

    public String getAg() {
        return ag;
    }

    public void setAg(String ag) {
        this.ag = ag;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }
}
