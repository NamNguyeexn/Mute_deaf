package com.business.beans;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name = "modelml")
public class ModelML {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "link", nullable = false)
    private String link;
    @Column(name = "trainDay", nullable = false)
    private Date trainDay;
    @Column(name = "trainCollection", nullable = false)
    private String trainCollection;
    @Column(name = "f1", nullable = false)
    private float f1;
    @Column(name = "acc", nullable = false)
    private float acc;
    @Column(name = "pre", nullable = false)
    private float pre;
    @Column(name = "re", nullable = false)
    private float re;
    @Column(name = "isActive", nullable = false)
    private Boolean isActive;

    public ModelML() {
    }
    public ModelML(int id, String name, String link, Date trainDay, String trainCollection, float f1, float acc, float pre, float re, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.trainDay = trainDay;
        this.trainCollection = trainCollection;
        this.f1 = f1;
        this.acc = acc;
        this.pre = pre;
        this.re = re;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getTrainDay() {
        return trainDay;
    }

    public void setTrainDay(Date trainDay) {
        this.trainDay = trainDay;
    }

    public String getTrainCollection() {
        return trainCollection;
    }

    public void setTrainCollection(String trainCollection) {
        this.trainCollection = trainCollection;
    }

    public float getF1() {
        return f1;
    }

    public void setF1(float f1) {
        this.f1 = f1;
    }

    public float getAcc() {
        return acc;
    }

    public void setAcc(float acc) {
        this.acc = acc;
    }

    public float getPre() {
        return pre;
    }

    public void setPre(float pre) {
        this.pre = pre;
    }

    public float getRe() {
        return re;
    }

    public void setRe(float re) {
        this.re = re;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getStatus() {
        if(isActive) return "Default";
        else return "";
    }

    public String showF1(){
        return String.valueOf(f1*100) + "%";
    }
    public String showAcc(){
        return String.valueOf(acc*100) + "%";
    }
    public String showPre(){
        return String.valueOf(pre*100) + "%";
    }
    public String showRe(){
        return String.valueOf(re*100) + "%";
    }
}
