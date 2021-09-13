package com.greenart.service;

import java.util.List;

import com.greenart.mapper.LocationMapper;
import com.greenart.vo.LocationVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
    @Autowired LocationMapper mapper;

    public List<LocationVO> selectTarget() {
        return mapper.selectTarget();
    }
    public List<LocationVO> getLocation(){
        return mapper.selectLocationInfo();
    }
    public List<LocationVO> getRegionLocationInfo(String region) {
    return mapper.selectRegionLocation(region);
    }
}
