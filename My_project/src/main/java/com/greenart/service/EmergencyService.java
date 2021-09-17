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
    public List<EmergencyVO> selectEmergencyRegionInfo(String region, String keyword) {//선택지역 "안"에있는 병원 검색
        return mapper.selectEmergencyRegionInfo(region, keyword);
    }
    public List<HospitalVO> selectHospital_info(String hpid) {//병원 상세내용
        return mapper.selectHospital_info(hpid);
    }
    public List<String> selectRegionCodes() {
        return mapper.selectRegionCodes();
    }
    public List<SidoVO> selectEmergencyRegion() {//지역별 응급실 통계 자료
        return mapper.selectEmergencyRegion();
    }
    public List<LocationVO> selectlocation(String hospital) {
        return mapper.selectlocation(hospital);
    }
}
