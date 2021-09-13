package com.greenart.mapper;

import java.util.List;

import com.greenart.vo.OperationVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperationMapper {
    public void insertOperation(OperationVO vo);
    public void updataOperation(OperationVO vo);
    public List<OperationVO>selectOperation(String o_region, String keyword);
    public List<OperationVO> selectCntOperation();
}
