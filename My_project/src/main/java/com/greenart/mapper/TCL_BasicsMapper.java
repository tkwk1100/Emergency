package com.greenart.mapper;

import com.greenart.vo.TCL_BasicsVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TCL_BasicsMapper {
    public void insertTCL_Basics(TCL_BasicsVO vo);
}
