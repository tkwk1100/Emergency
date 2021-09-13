package com.greenart.mapper;

import java.util.List;

import com.greenart.vo.EmergencyBasiVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmergencyBasiMapper {
    public void insertEmergencyBasiInfo(EmergencyBasiVO vo);
    public List<String> selectHospitalCodes();
}
