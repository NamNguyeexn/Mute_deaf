package com.business.services.impl;

import com.business.beans.Label;
import com.business.beans.Sample;
import com.business.repository.LabelRepo;
import com.business.repository.SampleLabeledRepo;
import com.business.services.LabelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LabelService implements LabelServiceImpl {
    @Autowired
    private LabelRepo labelRepo;
    @Autowired
    private SampleLabeledRepo sampleLabeledRepo;
    @Override
    public List<Label> getAll() {
        return labelRepo.findAll();
    }

    @Override
    public Optional<Label> getLabelBySampleId(int id) {
        int res = 0;
        for (var i : sampleLabeledRepo.findAll())
            if (i.getSampleId() == id) {
                Optional<Label> label = labelRepo.findById(i.getLabelId());
                res = label.get().getId();
            }
        return labelRepo.findById(res);
    }

    @Override
    public List<Label> getListOfLabelBySampleId(List<Sample> sample) {
//        List<Label> res = new ArrayList<>();
//        for (var i : sample) {
//            res.add(getLabelBySampleId(i.getId()).get());
//        }
        return null;
    }

    @Override
    public String getLabelById(int id) {
        return labelRepo.findById(id).get().getName();
    }

    @Override
    public Label getLabelByName(String name) {
        for (var b : labelRepo.findAll()) {
            if (b.getName().compareTo(name) == 0) {
                return b;
            }
        }
        return null;
    }
}
