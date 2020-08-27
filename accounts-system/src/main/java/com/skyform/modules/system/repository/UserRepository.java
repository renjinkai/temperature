package com.skyform.modules.system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.skyform.modules.system.domain.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor {

    /**
     * findByUsername
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * findByEmail
     * @param email
     * @return
     */
    User findByEmail(String email);

    User findByPhone(String phone);

    /**
     * 修改密码
     * @param username
     * @param pass
     */
    @Modifying
    @Query(value = "update user set password = ?2 , last_password_reset_time = ?3 where username = ?1",nativeQuery = true)
    void updatePass(String username, String pass, Date lastPasswordResetTime);

    /**
     * 修改头像
     * @param username
     * @param url
     */
    @Modifying
    @Query(value = "update user set avatar = ?2 where username = ?1",nativeQuery = true)
    void updateAvatar(String username, String url);

    /**
     * 修改邮箱
     * @param username
     * @param email
     */
    @Modifying
    @Query(value = "update user set email = ?2 where username = ?1",nativeQuery = true)
    void updateEmail(String username, String email);

    @Query(value = "SELECT * FROM `user` WHERE dept_id = ?1",nativeQuery = true)
    List<User> findByDeptId(Long deptId);

    @Modifying
    @Transactional
    @Query(value = "update user set cname = ?2 where phone = ?1",nativeQuery = true)
    void updateCname(String phone, String cname);

    @Query(value = "SELECT dept_id FROM `user` WHERE username = ?1",nativeQuery = true)
    long findDeptIdByUsername(String username);

    @Query(value = "SELECT * FROM `user` WHERE id IN (SELECT user_id FROM app_group_person_relation WHERE group_id = ?1)",nativeQuery = true)
    List<User> getAppGroupUsers(Long groupId);
}
