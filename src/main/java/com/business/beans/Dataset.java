package com.business.beans;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "dataset")
public class Dataset {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "trainCollectionLink", nullable = false)
    private String trainCollectionLink;
    @Column(name = "numbLabel", nullable = false)
    private int numbLabel;
    @Column(name = "labelId", nullable = false)
    private String labelId;
}
