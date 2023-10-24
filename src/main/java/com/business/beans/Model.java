package com.business.beans;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
@Table(name = "model")
public class Model {
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
}
