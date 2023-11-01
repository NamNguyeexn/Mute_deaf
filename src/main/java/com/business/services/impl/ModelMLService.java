package com.business.services.impl;

import com.business.beans.ModelML;
import com.business.repository.ModelMLRepo;
import com.business.services.ModelMLServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class ModelMLService implements ModelMLServiceImpl {
    @Autowired
    private ModelMLRepo modelMLRepo;
    @Override
    public List<ModelML> getAll() {
        List<ModelML> res = new ArrayList<>();
        for (var b : modelMLRepo.findAll()) {
            res.add(b);
        }
        return res;
    }

    @Override
    public List<ModelML> addModelML() {
        ModelML m1 = new ModelML(
                1,
                "epoch0",
                "D:\\Tai_lieu_ki_1_nam_4\\PT_HTTM\\Mute_deaf_python\\yolov5\\runs\\train\\exp27\\weights\\epoch0.pt",
                new Date(2023, 11, 1),
                "all",
                (float) 0.04079,
                (float) 0.500467,
                (float) 0.98094,
                (float) 0.020821,
                false
        );
        ModelML m2 = new ModelML(
                2,
                "last",
                "D:\\Tai_lieu_ki_1_nam_4\\PT_HTTM\\Mute_deaf_python\\yolov5\\runs\\train\\exp27\\weights\\last.pt",
                new Date(2023, 11, 1),
                "all",
                (float) 0.84853,
                (float) 0.895355,
                (float) 0.8698,
                (float) 0.92726,
                true
        );
        ModelML m3 = new ModelML(
                3,
                "best",
                "D:\\Tai_lieu_ki_1_nam_4\\PT_HTTM\\Mute_deaf_python\\yolov5\\runs\\train\\exp27\\weights\\best.pt",
                new Date(2023, 11, 1),
                "all",
                (float) 0.84853,
                (float) 0.895355,
                (float) 0.8698,
                (float) 0.92726,
                false
        );
        List<ModelML> res = new ArrayList<>();
        modelMLRepo.save(m1);
        modelMLRepo.save(m2);
        modelMLRepo.save(m3);
        res.add(m1);
        res.add(m2);
        res.add(m3);
        return res;
    }
}
