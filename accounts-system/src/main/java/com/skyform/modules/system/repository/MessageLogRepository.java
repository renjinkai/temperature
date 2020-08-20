package com.skyform.modules.system.repository;

import com.skyform.modules.system.domain.MessageLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
* @author renjk
* @date 2020-06-28
*/
public interface MessageLogRepository extends JpaRepository<MessageLog, Long>, JpaSpecificationExecutor {

    @Query(value = "SELECT COUNT(1) FROM message_log WHERE DATE_FORMAT(send_time,'%Y-%m-%d') = CURRENT_DATE AND phone = ?1",nativeQuery = true)
    int getCountCurrentDate(String phone);

    @Query(value = "SELECT COUNT(1) FROM message_log WHERE send_time >= DATE_SUB(NOW(),INTERVAL 1 MINUTE) AND phone = ?1",nativeQuery = true)
    int getCountOneMinute(String phone);
}
