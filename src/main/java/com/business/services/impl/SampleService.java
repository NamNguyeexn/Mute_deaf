package com.business.services.impl;

import com.business.beans.Label;
import com.business.beans.Sample;
import com.business.repository.LabelRepo;
import com.business.repository.SampleRepo;
import com.business.services.LabelServiceImpl;
import com.business.services.SampleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SampleService implements SampleServiceImpl {
    @Autowired
    private SampleRepo sampleRepo;
    @Autowired
    private LabelRepo labelRepo;

    @Override
    public List<Sample> getSampleInDate(Date d1, Date d2) {
        if (d1 == null && d2 != null) return getSampleAfterDay(d1);
        else if (d2 == null && d1 == null) return getSampleBeforeDay(d2);
        else return getSampleInDay(d1, d2);
    }

    @Override
    public List<Sample> getAll() {
        return sampleRepo.findAll();
    }

    @Override
    public Optional<Sample> getSampleById(int id) {
        return sampleRepo.findById(id);
    }

    @Override
    public Sample saveSample(Sample sample) {
        return sampleRepo.save(sample);
    }

    @Override
    public List<Sample> getListSampleByLabelId(int id) {
        List<Sample> res = new ArrayList<>();
        Optional<Label> label = labelRepo.findById(id);
        for (var b : sampleRepo.findAll()) {
            if(label.get().getName().compareTo(b.getName()) == 0) {
                res.add(b);
            }
        }
        return res;
    }

    @Override
    public List<Sample> getListSampleByName(String name) {
        List<Sample> res = new ArrayList<>();
        for (var b : sampleRepo.findAll()) {
            if (b.getName().compareTo(name) == 0) {
                res.add(b);
            }
        }
        return res;
    }

    @Override
    public void deleteSample(int id) {
        sampleRepo.deleteById(id);
    }

    private List<Sample> getSampleBeforeDay(Date d2) {
        List<Sample> res = new ArrayList<>();
        for (var b : sampleRepo.findAll()) {
            if (b.getValidDate().compareTo(d2) < 0) {
                res.add(b);
            }
        }
        return res;
    }

    private List<Sample> getSampleAfterDay(Date d1) {
        List<Sample> res = new ArrayList<>();
        for (var b : sampleRepo.findAll()) {
            if (b.getValidDate().compareTo(d1) > 0) {
                res.add(b);
            }
        }
        return res;
    }

    private List<Sample> getSampleInDay(Date d1, Date d2) {
        List<Sample> res = new ArrayList<>();
        for (var b : sampleRepo.findAll()) {
            if (b.getValidDate().compareTo(d2) < 0 && b.getValidDate().compareTo(d1) > 0) {
                res.add(b);
            }
        }
        return res;
    }

}
