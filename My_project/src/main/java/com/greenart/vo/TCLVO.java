package com.greenart.vo;

import lombok.Data;

@Data
public class TCLVO {
    private String hpid;//기관ID
    private String phpid;//기관ID(OLD)
    private String dutyEmcls;//응급의료기관분류
    private String dutyEmclsName;//응급의료기관분류명
    private String dutyAddr;//주소
    private String dutyName;//기관명
    private String dutyTel1;//대표전화1
    private String dutyTel3;//응급실전화
    private String wgs84Lon;//병원경도
    private String wgs84Lat;//병원위도
}









