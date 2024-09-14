package com.infnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infnet.model.XgeneCheckHistory;

public interface XgeneRepository extends JpaRepository<XgeneCheckHistory, Long> {}
