package com.greenart.mapper;

import com.greenart.vo.EmergencyAgencyVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmergencyAgencyMapper {
    public void insertEmergencyAgency(EmergencyAgencyVO vo);
}
