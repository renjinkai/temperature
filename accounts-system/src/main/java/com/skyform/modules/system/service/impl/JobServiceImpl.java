package com.skyform.modules.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.skyform.modules.system.domain.Job;
import com.skyform.modules.system.repository.DeptRepository;
import com.skyform.modules.system.repository.JobRepository;
import com.skyform.modules.system.service.JobService;
import com.skyform.modules.system.service.dto.JobDTO;
import com.skyform.modules.system.service.dto.JobQueryCriteria;
import com.skyform.modules.system.service.mapper.JobMapper;
import com.skyform.utils.PageUtil;
import com.skyform.utils.QueryHelp;
import com.skyform.utils.ValidationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private DeptRepository deptRepository;

    @Override
    public Object queryAll(JobQueryCriteria criteria, Pageable pageable) {
        Page<Job> page = jobRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        List<JobDTO> jobs = new ArrayList<>();
        for (Job job : page.getContent()) {
            jobs.add(jobMapper.toDto(job,deptRepository.findNameById(job.getDept().getPid())));
        }
        return PageUtil.toPage(jobs,page.getTotalElements());
    }

    @Override
    public Job findJobByName(String name) {
        return jobRepository.findJobByName(name);
    }

    @Override
    public JobDTO findById(Long id) {
        Optional<Job> job = jobRepository.findById(id);
        ValidationUtil.isNull(job,"Job","id",id);
        return jobMapper.toDto(job.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JobDTO create(Job resources) {
        return jobMapper.toDto(jobRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Job resources) {
        Optional<Job> optionalJob = jobRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalJob,"Job","id",resources.getId());

        Job job = optionalJob.get();
        resources.setId(job.getId());
        jobRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        jobRepository.deleteById(id);
    }
}