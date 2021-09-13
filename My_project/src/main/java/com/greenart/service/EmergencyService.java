package com.greenart.service;

import java.util.List;

import com.greenart.mapper.EmergencyMapper;
import com.greenart.vo.EmergencyVO;
import com.greenart.vo.HospitalVO;
import com.greenart.vo.LocationVO;
import com.greenart.vo.SidoVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmergencyService {
    @Autowired
    EmergencyMapper mapper;
    // public List<EmergencyVO> selectEmergencyInfo() {
    //     return mapper.selectEmergencyInfo();
    // }
    public List<EmergencyVO> selectEmergencyRegionInfo(String region, String keyword) {
        return mapper.selectEmergencyRegionInfo(region, keyword);
    }
    // 병원 "만" 검색
    // public List<EmergencyVO>selectSearch_hospital(String dutyName) {
    //     return mapper.selectSearch_hospital(dutyName);
    // }
    public List<HospitalVO> selectHospital_info(String hpid) {
        return mapper.selectHospital_info(hpid);
    }
    public List<String> selectRegionCodes() {
        return mapper.selectRegionCodes();
    }
    public List<SidoVO> selectEmergencyRegion() {
        return mapper.selectEmergencyRegion();
    }
    public List<LocationVO> selectlocation(String hospital) {
        return mapper.selectlocation(hospital);
    }
}
