package com.business.beans;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "samplelabeled")
public class SampleLabeled {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "labelId", nullable = false)
    private int labelId;
    @Column(name = "sampleId", nullable = false)
    private int sampleId;
    @Column(name = "modelId", nullable = false)
    private int modelId;
    @Column(name = "datasetId", nullable = false)
    private int datasetId;
}
