package com.business.repository;

import com.business.beans.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleRepo extends JpaRepository<Sample, Integer> {
}
