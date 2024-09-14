package com.infnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infnet.model.MutantModel;

@Repository
public interface MutantRepository extends JpaRepository<MutantModel, Long> {}