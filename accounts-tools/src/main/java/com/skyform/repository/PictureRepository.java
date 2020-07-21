package com.skyform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.skyform.domain.Picture;

public interface PictureRepository extends JpaRepository<Picture,Long>, JpaSpecificationExecutor {
}
