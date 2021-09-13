package com.greenart.mapper;

import com.greenart.vo.TCL_LocationVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TCL_LocationMapper {
    public void insertTCL_LocationInfo(TCL_LocationVO vo);
}
