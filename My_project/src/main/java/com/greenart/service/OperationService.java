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
    public List<OperationVO>selectOperation(String o_region,String keyword){
        return mapper.selectOperation(o_region, keyword);
    }
    public List<OperationVO> selectCntOperation() {
        return mapper.selectCntOperation();
    }
}
