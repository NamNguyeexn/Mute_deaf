package com.business.beans;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
@Table(name = "sample")
public class Sample {
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "validDate", nullable = false)
    private Date validDate;
    @Column(name = "link", nullable = false)
    private String link;
}
