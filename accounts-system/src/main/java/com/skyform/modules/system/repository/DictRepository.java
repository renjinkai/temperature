package com.skyform.modules.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.skyform.modules.system.domain.Dict;

public interface DictRepository extends JpaRepository<Dict, Long>, JpaSpecificationExecutor {
}