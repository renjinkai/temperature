package com.skyform.modules.system.service.impl;

import com.skyform.modules.system.domain.Temperature;
import com.skyform.modules.system.repository.TemperatureRepository;
import com.skyform.modules.system.service.TemperatureService;
import com.skyform.modules.system.service.dto.TemperatureDTO;
import com.skyform.modules.system.service.dto.TemperatureQueryCriteria;
import com.skyform.modules.system.service.mapper.TemperatureMapper;
import com.skyform.modules.system.service.mybatis_mapper.TemperatureMybatisMapper;
import com.skyform.utils.PageUtil;
import com.skyform.utils.QueryHelp;
import com.skyform.utils.ValidationUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
* @author renjk
* @date 2020-05-29
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TemperatureServiceImpl implements TemperatureService {

    @Autowired
    private TemperatureRepository temperatureRepository;

    @Autowired
    private TemperatureMapper temperatureMapper;

    @Autowired
    private TemperatureMybatisMapper temperatureMybatisMapper;

    @Override
    public Object queryAll(TemperatureQueryCriteria criteria, Pageable pageable){
        Page<Temperature> page = temperatureRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(temperatureMapper::toDto));
    }

    @Override
    public Object queryAll(TemperatureQueryCriteria criteria){
        return temperatureMapper.toDto(temperatureRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public TemperatureDTO findById(Long id) {
        Optional<Temperature> temperature = temperatureRepository.findById(id);
        ValidationUtil.isNull(temperature,"Temperature","id",id);
        return temperatureMapper.toDto(temperature.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TemperatureDTO create(Temperature resources) {
        return temperatureMapper.toDto(temperatureRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Temperature resources) {
        Optional<Temperature> optionalTemperature = temperatureRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalTemperature,"Temperature","id",resources.getId());
        Temperature temperature = optionalTemperature.get();
        temperature.copy(resources);
        temperatureRepository.save(temperature);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        temperatureRepository.deleteById(id);
    }

    @Override
    public List<TemperatureDTO> query(@Param("criteria") TemperatureQueryCriteria criteria) {
        return temperatureMybatisMapper.query(criteria);
    }

    @Override
    public void deleteByDeviceId(String deviceId) {
        temperatureRepository.deleteByDeviceId(deviceId);
    }
}