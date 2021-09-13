package com.greenart.service;

import java.util.List;

import com.greenart.mapper.EmergencyBasiMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmergencyBasiService {
    @Autowired 
    EmergencyBasiMapper mapper;
    public List<String> selectHospitalCodes(){
        return mapper.selectHospitalCodes();
    }
}
