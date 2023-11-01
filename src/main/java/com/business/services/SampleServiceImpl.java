package com.business.services;

import com.business.beans.Sample;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface SampleServiceImpl {
    List<Sample> getSampleInDate(Date d1, Date d2);
    List<Sample> getAll();
    Optional<Sample> getSampleById(int id);
    Sample saveSample(Sample sample);

    List<Sample> getListSampleByLabelId(int id);
    List<Sample> getListSampleByName(String name);
    void deleteSample(int id);
//    void addAllSample() throws IOException;
}
