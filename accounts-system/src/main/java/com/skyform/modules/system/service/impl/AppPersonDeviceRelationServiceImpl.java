package com.skyform.modules.system.service.impl;

import com.skyform.modules.system.domain.AppPersonDeviceRelation;
import com.skyform.utils.ValidationUtil;
import com.skyform.modules.system.repository.AppPersonDeviceRelationRepository;
import com.skyform.modules.system.service.AppPersonDeviceRelationService;
import com.skyform.modules.system.service.dto.AppPersonDeviceRelationDTO;
import com.skyform.modules.system.service.dto.AppPersonDeviceRelationQueryCriteria;
import com.skyform.modules.system.service.mapper.AppPersonDeviceRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
public class AppPersonDeviceRelationServiceImpl implements AppPersonDeviceRelationService {

    @Autowired
    private AppPersonDeviceRelationRepository appPersonDeviceRelationRepository;

    @Autowired
    private AppPersonDeviceRelationMapper appPersonDeviceRelationMapper;

    @Override
    public Object queryAll(AppPersonDeviceRelationQueryCriteria criteria, Pageable pageable){
        Page<AppPersonDeviceRelation> page = appPersonDeviceRelationRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(appPersonDeviceRelationMapper::toDto));
    }

    @Override
    public List<AppPersonDeviceRelationDTO> queryAll(AppPersonDeviceRelationQueryCriteria criteria){
        return appPersonDeviceRelationMapper.toDto(appPersonDeviceRelationRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public AppPersonDeviceRelationDTO findById(Long id) {
        Optional<AppPersonDeviceRelation> appPersonDeviceRelation = appPersonDeviceRelationRepository.findById(id);
        ValidationUtil.isNull(appPersonDeviceRelation,"AppPersonDeviceRelation","id",id);
        return appPersonDeviceRelationMapper.toDto(appPersonDeviceRelation.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AppPersonDeviceRelationDTO create(AppPersonDeviceRelation resources) {
        return appPersonDeviceRelationMapper.toDto(appPersonDeviceRelationRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(AppPersonDeviceRelation resources) {
        Optional<AppPersonDeviceRelation> optionalAppPersonDeviceRelation = appPersonDeviceRelationRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalAppPersonDeviceRelation,"AppPersonDeviceRelation","id",resources.getId());
        AppPersonDeviceRelation appPersonDeviceRelation = optionalAppPersonDeviceRelation.get();
        appPersonDeviceRelation.copy(resources);
        appPersonDeviceRelationRepository.save(appPersonDeviceRelation);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        appPersonDeviceRelationRepository.deleteById(id);
    }
}