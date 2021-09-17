package com.greenart.mapper;

import java.util.List;

import com.greenart.vo.EmergencyVO;
import com.greenart.vo.HospitalVO;
import com.greenart.vo.LocationVO;
import com.greenart.vo.SidoVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmergencyMapper {
    public void insertEmergencyInfo(EmergencyVO vo);

    public void updataEmergencyInfo(EmergencyVO vo);

    public List<EmergencyVO> selectEmergencyRegionInfo(String region, String keyword);// 선택지역 "안"에있는 병원 검색

    public List<HospitalVO> selectHospital_info(String hpid);//병원 상세내용

    public List<String> selectRegionCodes();

    public List<SidoVO> selectEmergencyRegion();//지역별 응급실 통계 자료
    
    public List<LocationVO> selectlocation(String hospital);
    
} 