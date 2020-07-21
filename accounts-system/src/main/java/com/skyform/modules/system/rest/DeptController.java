package com.skyform.modules.system.rest;

import com.skyform.modules.system.service.dto.CodeDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.skyform.aop.log.Log;
import com.skyform.config.DataScope;
import com.skyform.exception.BadRequestException;
import com.skyform.modules.system.domain.Dept;
import com.skyform.modules.system.service.DeptService;
import com.skyform.modules.system.service.dto.DeptDTO;
import com.skyform.modules.system.service.dto.DeptQueryCriteria;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @Autowired
    private DataScope dataScope;

    private static final String ENTITY_NAME = "dept";

    @Log("查询部门")
    @ApiOperation(value = "查询部门")
    @GetMapping(value = "/deptPageable")
    @PreAuthorize("hasAnyRole('ADMIN','USER_ALL','USER_SELECT','DEPT_ALL','DEPT_SELECT')")
    public ResponseEntity getDepts(DeptQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(deptService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("按名称查询子部门")
    @ApiOperation(value = "按名称查询子部门")
    @GetMapping(value = "/findSubDeptByName")
    @PreAuthorize("hasAnyRole('ADMIN','USER_ALL','USER_SELECT','DEPT_ALL','DEPT_SELECT')")
    public ResponseEntity findSubDeptByName(Long deptId, Pageable pageable){
        return new ResponseEntity(deptService.findSubDeptById(deptId,pageable),HttpStatus.OK);
    }

    @Log("查询部门")
    @GetMapping(value = "/dept")
    @PreAuthorize("hasAnyRole('ADMIN','USER_ALL','USER_SELECT','DEPT_ALL','DEPT_SELECT')")
    public ResponseEntity getDepts(DeptQueryCriteria criteria){
        // 数据权限
        criteria.setIds(dataScope.getDeptIds());
        List<DeptDTO> deptDTOS = deptService.queryAll(criteria);
        return new ResponseEntity(deptService.buildTree(deptDTOS),HttpStatus.OK);
    }

    @Log("新增部门")
    @PostMapping(value = "/dept")
    @PreAuthorize("hasAnyRole('ADMIN','DEPT_ALL','DEPT_CREATE')")
    public ResponseEntity create(@Validated @RequestBody Dept resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(deptService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改部门")
    @PutMapping(value = "/dept")
    @PreAuthorize("hasAnyRole('ADMIN','DEPT_ALL','DEPT_EDIT')")
    public ResponseEntity update(@Validated(Dept.Update.class) @RequestBody Dept resources){
        deptService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除部门")
    @DeleteMapping(value = "/dept/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DEPT_ALL','DEPT_DELETE')")
    public ResponseEntity delete(@PathVariable Long id){
        deptService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Log("通过验证码关联部门")
    @PostMapping(value = "/dept/bindByCode")
    @PreAuthorize("hasAnyRole('ADMIN','DEPT_ALL','DEPT_BIND')")
    public ResponseEntity bindByCode(@RequestBody CodeDTO dto){
        Map<String, String> map = new HashMap<String, String>();
        try {
            deptService.bindByCode(dto.getDeptId(), dto.getCodeList());
            map.put("message", "已通过校验码关联到机构部门");
            return new ResponseEntity(map,HttpStatus.OK);
        }catch (BadRequestException e){
            map.put("message", e.getMessage());
            return new ResponseEntity(map,HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
}