package com.business.repository;

import com.business.beans.Dataset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatasetRepo extends JpaRepository<Dataset, Integer> {
}
