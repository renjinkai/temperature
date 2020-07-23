package com.skyform.modules.system.rest;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.skyform.aop.log.Log;
import com.skyform.config.DataScope;
import com.skyform.modules.system.domain.Student;
import com.skyform.modules.system.service.DeptService;
import com.skyform.modules.system.service.StudentService;
import com.skyform.modules.system.service.dto.DeptDTO;
import com.skyform.modules.system.service.dto.DeptQueryCriteria;
import com.skyform.modules.system.service.dto.StudentDTO;
import com.skyform.modules.system.service.dto.StudentQueryCriteria;
import com.skyform.modules.system.service.mapper.StudentMapper;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
* @author renjk
* @date 2020-05-29
*/
@Api(tags = "Student管理")
@RestController
@RequestMapping("api")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private DeptService deptService;

    @Autowired
    private DataScope dataScope;

    @Log("查询Student")
    @ApiOperation(value = "查询Student")
    @GetMapping(value = "/student")
    @PreAuthorize("hasAnyRole('ADMIN','STUDENT_ALL','STUDENT_SELECT')")
    public ResponseEntity getStudents(StudentQueryCriteria criteria, Pageable pageable){
        // 数据权限
        criteria.setDeptIds(dataScope.getDeptIds());
        return new ResponseEntity(studentService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增Student")
    @ApiOperation(value = "新增Student")
    @PostMapping(value = "/student")
    @PreAuthorize("hasAnyRole('ADMIN','STUDENT_ALL','STUDENT_CREATE')")
    public ResponseEntity create(@Validated @RequestBody Student resources){
        return new ResponseEntity(studentService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Student")
    @ApiOperation(value = "修改Student")
    @PutMapping(value = "/student")
    @PreAuthorize("hasAnyRole('ADMIN','STUDENT_ALL','STUDENT_EDIT')")
    public ResponseEntity update(@Validated @RequestBody Student resources){
        studentService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("批量修改Student")
    @ApiOperation(value = "批量修改Student")
    @PutMapping(value = "/studentBatch")
    @PreAuthorize("hasAnyRole('ADMIN','STUDENT_ALL','STUDENT_EDIT')")
    public ResponseEntity updateBatch(@Validated @RequestBody List<Student> resources){
        studentService.updateBatch(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Student")
    @ApiOperation(value = "删除Student")
    @DeleteMapping(value = "/student/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','STUDENT_ALL','STUDENT_DELETE')")
    public ResponseEntity delete(@PathVariable Long id){
        studentService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Log("导出Student")
    @ApiOperation(value = "导出Student")
    @PostMapping(value = "/export/student")
    @PreAuthorize("hasAnyRole('ADMIN','STUDENT_ALL','STUDENT_EXPORT')")
    public ResponseEntity exportStudent(StudentQueryCriteria criteria, HttpServletResponse response) throws Exception{
        // 数据权限
        criteria.setDeptIds(dataScope.getDeptIds());
        List<StudentDTO> list = studentService.queryAllExcel(criteria);
        ExportParams exportParams = new ExportParams("学生信息详情", "学生信息");
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams,StudentDTO.class, list);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "Attachment;Filename="+ "学生信息详情" + ".xls");
        OutputStream out = response.getOutputStream();
        workbook.write(out);
        out.close();
        out.flush();
        return new ResponseEntity(studentService.queryAll(criteria),HttpStatus.OK);
    }

    @Log("下载Student导入模板")
    @ApiOperation(value = "下载Student导入模板")
    @PostMapping(value = "/download/student")
    @PreAuthorize("hasAnyRole('ADMIN','STUDENT_ALL','STUDENT_DOWNLOAD')")
    public ResponseEntity downloadStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource=resourceLoader.getResource("classpath:template/学生信息导入模板.xls");
        InputStream inputStream = resource.getInputStream();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/force-download;charset=utf-8");
        OutputStream out = response.getOutputStream();
        response.setHeader("Content-Disposition", "Attachment;Filename="+ "学生信息导入模板" + ".xls");
        int b = 0;
        byte[] buffer = new byte[1000000];
        while (b != -1) {
            b = inputStream.read(buffer);
            if(b!=-1) out.write(buffer, 0, b);
        }
        inputStream.close();
        out.close();
        out.flush();
        headers.add("message", "学生信息导入模板下载完成");
        return new ResponseEntity(headers,HttpStatus.OK);
    }

    @Log("导入Student")
    @ApiOperation(value = "导入Student")
    @PostMapping(value = "/import/student")
    @PreAuthorize("hasAnyRole('ADMIN','STUDENT_ALL','STUDENT_IMPORT')")
    public ResponseEntity importStudent(MultipartFile file) throws Exception{
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(1);
        ExcelImportResult<StudentDTO> result = ExcelImportUtil.importExcelMore(file.getInputStream(), StudentDTO.class, importParams);
        List<StudentDTO> list = result.getList();
        if(list.size()>0 && list!=null) {
            for(StudentDTO dto : list) {
                if(dto.getIdCard() != null && !"".equals(dto.getIdCard())) {
                    // 存学校id
                    // 存班级id
                }
            }
            headers.add("message", "导入成功，录入了" + list.size() + "条数据");
            return new ResponseEntity(headers,HttpStatus.OK);
        }else {
            headers.add("message", "未查询到录入数据");
            return new ResponseEntity(headers,HttpStatus.NO_CONTENT);
        }
    }
}
