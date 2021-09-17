package com.greenart.mapper;

import java.util.List;

import com.greenart.vo.OperationVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperationMapper {
    public void insertOperation(OperationVO vo);
    public void updataOperation(OperationVO vo);
    public List<OperationVO>selectOperation(String o_region, String keyword);//선택지역 "안"에있는 병원 검색
    public List<OperationVO> selectCntOperation();//지역별 중증질환자 수용가능병원 개수 통계
}
