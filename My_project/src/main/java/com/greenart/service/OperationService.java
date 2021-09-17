package com.greenart.service;

import java.util.List;

import com.greenart.mapper.OperationMapper;
import com.greenart.vo.OperationVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationService {
    @Autowired
    OperationMapper mapper;
    public List<OperationVO>selectOperation(String o_region,String keyword){//선택지역 "안"에있는 병원 검색
        return mapper.selectOperation(o_region, keyword);
    }
    public List<OperationVO> selectCntOperation() {//지역별 중증질환자 수용가능병원 개수 통계
        return mapper.selectCntOperation();
    }
}
