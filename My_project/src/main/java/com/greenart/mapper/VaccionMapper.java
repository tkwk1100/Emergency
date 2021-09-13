package com.greenart.mapper;

import com.greenart.vo.VaccionVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VaccionMapper {
    public void insertVaccion(VaccionVO vo);
}
