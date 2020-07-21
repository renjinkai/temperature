package com.skyform.modules.system.service.impl;

import com.skyform.exception.BadRequestException;
import com.skyform.exception.EntityExistException;
import com.skyform.exception.EntityNotFoundException;
import com.skyform.modules.monitor.service.RedisService;
import com.skyform.modules.system.domain.Job;
import com.skyform.modules.system.domain.Role;
import com.skyform.modules.system.domain.User;
import com.skyform.modules.system.repository.UserRepository;
import com.skyform.modules.system.service.DeptService;
import com.skyform.modules.system.service.JobService;
import com.skyform.modules.system.service.RoleService;
import com.skyform.modules.system.service.UserService;
import com.skyform.modules.system.service.dto.DeptDTO;
import com.skyform.modules.system.service.dto.DeptQueryCriteria;
import com.skyform.modules.system.service.dto.UserDTO;
import com.skyform.modules.system.service.dto.UserQueryCriteria;
import com.skyform.modules.system.service.mapper.DeptMapper;
import com.skyform.modules.system.service.mapper.UserMapper;
import com.skyform.utils.PageUtil;
import com.skyform.utils.QueryHelp;
import com.skyform.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private JobService jobService;

    @Override
    public Object queryAll(UserQueryCriteria criteria, Pageable pageable) {
        Page<User> page = userRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(userMapper::toDto));
    }

    @Override
    public User findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    @Override
    public List<User> findByDeptId(Long deptId) {
        return userRepository.findByDeptId(deptId);
    }

    @Override
    public void updateCname(String phone, String cname) {
        userRepository.updateCname(phone,cname);
    }

    @Override
    public UserDTO findById(long id) {
        Optional<User> user = userRepository.findById(id);
        ValidationUtil.isNull(user,"User","id",id);
        return userMapper.toDto(user.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDTO create(User resources) {

        if(userRepository.findByUsername(resources.getUsername())!=null){
            throw new EntityExistException(User.class,"username",resources.getUsername());
        }

        if(resources.getEmail()==null){
            resources.setEmail(resources.getUsername()+"@"+resources.getUsername()+".com");
        }

        if(userRepository.findByEmail(resources.getEmail())!=null){
            throw new EntityExistException(User.class,"email",resources.getEmail());
        }

        if(resources.getEnabled() == null) {
            resources.setEnabled(false);
        }

        if(resources.getRoles() == null) {
            Role role = roleService.findByName("APP用户");
            if(role == null){
                throw new BadRequestException("APP用户角色不存在");
            }
            Set set = new HashSet();
            set.add(role);
            resources.setRoles(set);
        }

        if(resources.getDept() == null) {
            DeptQueryCriteria criteria = new DeptQueryCriteria();
            criteria.setName("默认部门");
            List<DeptDTO> list = deptService.queryAll(criteria);
            if(list.size() == 0){
                throw new BadRequestException("默认部门不存在");
            }
            resources.setDept(deptMapper.toEntity(list.get(0)));
        }

        if(resources.getJob() == null) {
            Job job = jobService.findJobByName("APP用户");
            if(job == null){
                throw new BadRequestException("APP用户岗位不存在");
            }
            resources.setJob(job);
        }

        // 默认密码 123456，此密码是加密后的字符
        if(resources.getPassword() != null) {
            resources.setPassword(resources.getPassword());
        }else{
            resources.setPassword("e10adc3949ba59abbe56e057f20f883e");
        }
        resources.setAvatar("https://i.loli.net/2019/04/04/5ca5b971e1548.jpeg");
        resources.setCreateTime(new Timestamp(new Date().getTime()));
        return userMapper.toDto(userRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(User resources) {
        Optional<User> userOptional = userRepository.findById(resources.getId());
        ValidationUtil.isNull(userOptional,"User","id",resources.getId());

        User user = userOptional.get();

        User user1 = userRepository.findByUsername(user.getUsername());
        User user2 = userRepository.findByEmail(user.getEmail());

        if(user1 !=null&&!user.getId().equals(user1.getId())){
            throw new EntityExistException(User.class,"username",resources.getUsername());
        }

        if(user2!=null&&!user.getId().equals(user2.getId())){
            throw new EntityExistException(User.class,"email",resources.getEmail());
        }

        // 如果用户的角色改变了，需要手动清理下缓存
        if (!resources.getRoles().equals(user.getRoles())) {
            String key = "role::loadPermissionByUser:" + user.getUsername();
            redisService.delete(key);
            key = "role::findByUsers_Id:" + user.getId();
            redisService.delete(key);
        }

        user.setUsername(resources.getUsername());
        user.setEmail(resources.getEmail());
        user.setEnabled(resources.getEnabled());
        user.setRoles(resources.getRoles());
        user.setDept(resources.getDept());
        user.setJob(resources.getJob());
        user.setPhone(resources.getPhone());
        user.setCname(resources.getCname());
        userRepository.save(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO findByName(String userName) {
        User user = null;
        if(ValidationUtil.isEmail(userName)){
            user = userRepository.findByEmail(userName);
        } else {
            user = userRepository.findByUsername(userName);
        }
        if (user == null) {
            throw new EntityNotFoundException(User.class, "name", userName);
        } else {
            return userMapper.toDto(user);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePass(String username, String pass) {
        userRepository.updatePass(username,pass,new Date());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateAvatar(String username, String url) {
        userRepository.updateAvatar(username,url);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateEmail(String username, String email) {
        userRepository.updateEmail(username,email);
    }
}
