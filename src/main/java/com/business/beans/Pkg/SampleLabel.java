package com.business.beans.Pkg;

import com.business.beans.Label;

public class SampleLabel {
    private int sampleId;
    private String link;
    private int labelId;
    public SampleLabel(){};

    public SampleLabel(int sampleId, String link, int labelId) {
        this.sampleId = sampleId;
        this.link = link;
        this.labelId = labelId;
    }

    @Override
    public String toString() {
        return "SampleLabel{" +
                "sampleId=" + sampleId +
                ", link='" + link + '\'' +
                ", labelId=" + labelId +
                '}';
    }

    public int getSampleId() {
        return sampleId;
    }

    public void setSampleId(int sampleId) {
        this.sampleId = sampleId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getLabelId() {
        return labelId;
    }

    public void setLabelId(int labelId) {
        this.labelId = labelId;
    }
}
