package com.munna.model;

import javax.persistence.*;

/**
 * Created by monju on 1/24/2016.
 */


@Entity
@Table(name="genuinedata")
public class GenuineData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="age")
    private int age;
    @Column(name="gender")
    private String gender;
    @Column(name="tb")
    private double tb;
    @Column(name="db")
    private double db;
    @Column(name="alkphos")
    private double alkphos;
    @Column(name="sgpt")
    private double sgpt;
    @Column(name="sgot")
    private double sgot;
    @Column(name="tp")
    private double tp;
    @Column(name="alb")
    private double alb;

    @Column(name="ag")
    private double ag;
    @Column(name="disease")
    private String disease;

    public GenuineData(){

    }

    public double getAg() {
        return ag;
    }

    public void setAg(double ag) {
        this.ag = ag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getTb() {
        return tb;
    }

    public void setTb(double tb) {
        this.tb = tb;
    }

    public double getDb() {
        return db;
    }

    public void setDb(double db) {
        this.db = db;
    }

    public double getAlkphos() {
        return alkphos;
    }

    public void setAlkphos(double alkphos) {
        this.alkphos = alkphos;
    }

    public double getSgpt() {
        return sgpt;
    }

    public void setSgpt(double sgpt) {
        this.sgpt = sgpt;
    }

    public double getSgot() {
        return sgot;
    }

    public void setSgot(double sgot) {
        this.sgot = sgot;
    }

    public double getTp() {
        return tp;
    }

    public void setTp(double tp) {
        this.tp = tp;
    }

    public double getAlb() {
        return alb;
    }

    public void setAlb(double alb) {
        this.alb = alb;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }
}
