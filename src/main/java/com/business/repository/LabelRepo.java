package com.business.repository;

import com.business.beans.Label;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepo extends JpaRepository<Label, Integer> {
}
