package com.skyform.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;

import com.skyform.domain.EmailConfig;
import com.skyform.domain.vo.EmailVo;

@CacheConfig(cacheNames = "email")
public interface EmailService {

    /**
     * 更新邮件配置
     * @param emailConfig
     * @param old
     * @return
     */
    @CachePut(key = "'1'")
    EmailConfig update(EmailConfig emailConfig, EmailConfig old);

    /**
     * 查询配置
     * @return
     */
    @Cacheable(key = "'1'")
    EmailConfig find();

    /**
     * 发送邮件
     * @param emailVo
     * @param emailConfig
     * @throws Exception
     */
    @Async
    void send(EmailVo emailVo, EmailConfig emailConfig) throws Exception;
}
