package com.skyform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skyform.domain.AlipayConfig;

public interface AlipayRepository extends JpaRepository<AlipayConfig,Long> {
}
