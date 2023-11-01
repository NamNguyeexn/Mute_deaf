package com.business.services;

import com.business.beans.ModelML;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ModelMLServiceImpl {
    List<ModelML> getAll();
    List<ModelML> addModelML();
}
