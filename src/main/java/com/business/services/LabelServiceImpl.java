package com.business.services;

import com.business.beans.Label;
import com.business.beans.Sample;

import java.util.List;
import java.util.Optional;

public interface LabelServiceImpl {
    List<Label> getAll();
    Optional<Label> getLabelBySampleId(int id);

    List<Label> getListOfLabelBySampleId(List<Sample> sample);
    String getLabelById(int id);
    Label getLabelByName(String name);
}
