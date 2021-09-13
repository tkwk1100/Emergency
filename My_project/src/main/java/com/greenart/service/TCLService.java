package com.greenart.service;

import java.util.List;

import com.greenart.mapper.TCLMapper;
import com.greenart.vo.EmergencyVO;
import com.greenart.vo.TCLPositionVO;
import com.greenart.vo.TCLVO;
import com.greenart.vo.TCL_BasicsVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TCLService {
    @Autowired
    TCLMapper mapper;
    public List<TCLPositionVO> selectTCLPosition() {
        return mapper.selectTCLPosition();
    }
    public List<TCLVO> selectTCLAll() {
        return mapper.selectTCLAll();
    }
    public List<String> selectTCLCodes() {
        return mapper.selectTCLCodes();
    }
    public List<TCL_BasicsVO> selectBasicInfo(String tb_hpid) {
        return mapper.selectBasicInfo(tb_hpid);
    }
    public List<EmergencyVO>selectTCLBed() {
        return mapper.selectTCLBed();
    }
}
