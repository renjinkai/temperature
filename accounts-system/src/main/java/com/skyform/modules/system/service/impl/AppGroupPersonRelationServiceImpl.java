package com.skyform.modules.system.service.impl;

import com.skyform.modules.system.domain.AppGroupPersonRelation;
import com.skyform.utils.ValidationUtil;
import com.skyform.modules.system.repository.AppGroupPersonRelationRepository;
import com.skyform.modules.system.service.AppGroupPersonRelationService;
import com.skyform.modules.system.service.dto.AppGroupPersonRelationDTO;
import com.skyform.modules.system.service.dto.AppGroupPersonRelationQueryCriteria;
import com.skyform.modules.system.service.mapper.AppGroupPersonRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.skyform.utils.PageUtil;
import com.skyform.utils.QueryHelp;

/**
* @author renjk
* @date 2020-06-19
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class AppGroupPersonRelationServiceImpl implements AppGroupPersonRelationService {

    @Autowired
    private AppGroupPersonRelationRepository appGroupPersonRelationRepository;

    @Autowired
    private AppGroupPersonRelationMapper appGroupPersonRelationMapper;

    @Override
    public Object queryAll(AppGroupPersonRelationQueryCriteria criteria, Pageable pageable){
        Page<AppGroupPersonRelation> page = appGroupPersonRelationRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(appGroupPersonRelationMapper::toDto));
    }

    @Override
    public Object queryAll(AppGroupPersonRelationQueryCriteria criteria){
        return appGroupPersonRelationMapper.toDto(appGroupPersonRelationRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public AppGroupPersonRelationDTO findById(Long id) {
        Optional<AppGroupPersonRelation> appGroupPersonRelation = appGroupPersonRelationRepository.findById(id);
        ValidationUtil.isNull(appGroupPersonRelation,"AppGroupPersonRelation","id",id);
        return appGroupPersonRelationMapper.toDto(appGroupPersonRelation.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AppGroupPersonRelationDTO create(AppGroupPersonRelation resources) {
        return appGroupPersonRelationMapper.toDto(appGroupPersonRelationRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(AppGroupPersonRelation resources) {
        Optional<AppGroupPersonRelation> optionalAppGroupPersonRelation = appGroupPersonRelationRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalAppGroupPersonRelation,"AppGroupPersonRelation","id",resources.getId());
        AppGroupPersonRelation appGroupPersonRelation = optionalAppGroupPersonRelation.get();
        appGroupPersonRelation.copy(resources);
        appGroupPersonRelationRepository.save(appGroupPersonRelation);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        appGroupPersonRelationRepository.deleteById(id);
    }
}