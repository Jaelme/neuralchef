package com.devportfolio.neuralchef.repository;

import com.devportfolio.neuralchef.model.NutritionalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutritionalInfoRepository extends JpaRepository<NutritionalInfo, Long> {
}
