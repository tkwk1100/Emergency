package com.greenart.vo;

import lombok.Data;

@Data
public class HospitalVO {
    private String hv1;//응급실 당직의 직통연락처
    private String hv2;//내과중환자실
    private String hv3;//외과중환자실
    private String hv4;//외과입원실(정형외과)
    private String hv5;//신경과입원실
    private String hv6;//신경외과중환자실
    private String hv7;//약물중환자
    private String hv8;//화상중환자
    private String hv9;//외상중환자
    private String hv10;//VENTI(소아) 
    private String hv11;//인큐베이터(보육기) 
    private String hv12;//소아당직의 직통연락처 
    private String dutyTel3;//응급실전화 
    private String hpid;//기관코드 
    private String hvamyn;//구급차가용여부 
    private String hvangioayn;//조영촬영기가용(가/부) 
    private String hvcc;//신경중환자 
    private String hvccc;//흉부중환자 
    private String hvctayn;//CT가용(가/부) 
    private String hvec;//응급실(병상) 
    private String hvgc;//입원실(병상) 
    private String hvicc;//일반중환자 
    private String hvidate;//입력일시 
    private String hvmriayn;//MRI가용(가/부) 
    private String hvncc;//신생중환자 :
    private String hvoc;//수술실 
    private String hvventiayn;//인공호흡기가용(가/부) 
    private String dutyName;//기관명 
    private String phpid;//구기관코드 
    private String rnum;//일련번호 
    private String region;//시도

    //병원 정보
    private String ea_dutyAddr;//주소
    private String dutyDiv;//병원분류
    private String dutyDivName;//병원분류명
    private String dutyFax;//팩스번호
    private String dutyTel1;//대표전화1
    private String MKioskTy25;//(Emergency gate keeper)
    private String MKioskTy1;//뇌출혈수술
    private String MKioskTy2;//뇌경색의재관류
    private String MKioskTy3;//심근경색의재관류
    private String MKioskTy4;//복부손상의수술
    private String MKioskTy5;//사지접합의수술
    private String MKioskTy6;//응급내시경
    private String MKioskTy7;//응급투석
    private String MKioskTy8;//조산산모
    private String MKioskTy9;//정신질환자
    private String MKioskTy10;//신생아
    private String MKioskTy11;//중증화상
    private String dgidIdName;//진료과목
    private String ea_dutyEmclsName;//응급의료기관분류명
    private Double latitude;    //위도
    private Double longitude;   //경도
}   
