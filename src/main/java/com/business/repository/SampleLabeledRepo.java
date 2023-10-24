package com.business.repository;

import com.business.beans.SampleLabeled;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleLabeledRepo extends JpaRepository<SampleLabeled, Integer> {
}
