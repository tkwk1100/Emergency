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
    public List<TCL_BasicsVO> selectBasicInfo(String tb_hpid);//외산센터 기본정보 가져오기
    public List<EmergencyVO>selectTCLBed();//외상센터 병상정보 가져오기
}
