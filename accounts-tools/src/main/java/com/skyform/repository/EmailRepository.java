package com.skyform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skyform.domain.EmailConfig;

public interface EmailRepository extends JpaRepository<EmailConfig,Long> {
}
