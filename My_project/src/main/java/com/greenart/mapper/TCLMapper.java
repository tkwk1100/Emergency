package com.greenart.mapper;

import java.util.List;

import com.greenart.vo.EmergencyVO;
import com.greenart.vo.TCLPositionVO;
import com.greenart.vo.TCLVO;
import com.greenart.vo.TCL_BasicsVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TCLMapper {
    public void insertTCLinfo (TCLVO vo);
    public List<TCLPositionVO> selectTCLPosition();
    public List<TCLVO> selectTCLAll();
    public List<String> selectTCLCodes();
    public List<TCL_BasicsVO> selectBasicInfo(String tb_hpid);
    public List<EmergencyVO>selectTCLBed();
}
