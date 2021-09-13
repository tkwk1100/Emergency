package com.greenart.vo;



import lombok.Data;

@Data
public class EmergencyVO {
    private String hv1; //응급실 당직의 직통연락처
    private Integer hv2; //내과중환자실
    private Integer hv3; //외과중환자실
    private Integer hv4; //외과입원실(정형외과)
    private String hv5; //신경과입원실
    private Integer hv6; //신경외과중환자실
    private String hv7; //약물중환자
    private Integer hv8; //화상중환자
    private Integer hv9; //외상중환자
    private String hv10; //VENTI(소아) 
    private String hv11; //인큐베이터(보육기) 
    private String hv12; //소아당직의 직통연락처 
    private String dutyTel3; //응급실전화 
    private String hpid; //기관코드 
    private String hvamyn; //구급차가용여부 
    private String hvangioayn; //조영촬영기가용(가/부) 
    private Integer hvcc; //신경중환자 
    private Integer hvccc; //흉부중환자 
    private String hvctayn; //CT가용(가/부) 
    private Integer hvec; //응급실(병상) 
    private Integer hvgc; //입원실(병상)
    private Integer hvicc; //일반중환자 
    private String hvidate; //입력일시 
    private String hvmriayn; //MRI가용(가/부) 
    private Integer hvncc; //신생중환자 :
    private Integer hvoc; //수술실 
    private String hvventiayn; //인공호흡기가용(가/부) 
    private String dutyName; //기관명 
    private String phpid; //구기관코드 
    private String rnum; //일련번호 
    private String region;//시도
}
