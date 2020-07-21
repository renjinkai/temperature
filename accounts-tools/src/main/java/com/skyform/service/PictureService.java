package com.skyform.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.skyform.domain.Picture;
import com.skyform.service.dto.PictureQueryCriteria;

@CacheConfig(cacheNames = "picture")
public interface PictureService {

    /**
     * 查询图片
     * @param criteria
     * @param pageable
     * @return
     */
    @Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(PictureQueryCriteria criteria, Pageable pageable);

    /**
     * 上传图片
     * @param file
     * @param username
     * @return
     */
    @CacheEvict(allEntries = true)
    Picture upload(MultipartFile file, String username);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    Picture findById(Long id);

    /**
     * 删除图片
     * @param picture
     */
    @CacheEvict(allEntries = true)
    void delete(Picture picture);

    /**
     * 删除图片
     * @param ids
     */
    @CacheEvict(allEntries = true)
    void deleteAll(Long[] ids);
}
