package com.infnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infnet.model.HistoryModel;

@Repository
public interface HistoryRepository extends JpaRepository<HistoryModel, Long> {}