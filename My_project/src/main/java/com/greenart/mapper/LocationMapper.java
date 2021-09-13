package com.greenart.mapper;

import java.util.List;

import com.greenart.vo.LocationVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LocationMapper {
    public void insertLocationInfo(LocationVO vo);
    public List<LocationVO> selectTarget();
    public List<LocationVO> selectLocationInfo();
    public List<LocationVO> selectRegionLocation(String region);
}
