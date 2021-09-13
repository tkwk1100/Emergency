package com.greenart.vo;

import lombok.Data;

@Data
public class TCL_BasicsVO {
    private String tb_dgidIdName;//진료과목
    private String tb_hpid;//기관ID
    private String tb_dutyName;//기관명
    private String tb_dutyAddr;//주소
    private String tb_dutyTel1;//대표전화1
    private String tb_dutyTel3;//응급실전화
    private String tb_MKioskTy25;//응급실(Emergency gate keeper) 
    private String tb_MKioskTy1;//뇌출혈수술
    private String tb_MKioskTy2;//뇌경색의재관류
    private String tb_MKioskTy3;//심근경색의재관류
    private String tb_MKioskTy4;//복부손상의수술
    private String tb_MKioskTy5;//사지접합의수술
    private String tb_MKioskTy6;//응급내시경
    private String tb_MKioskTy7;//응급투석
    private String tb_MKioskTy8;//조산산모
    private String tb_MKioskTy9;//정신질환자
    private String tb_MKioskTy10;//신생아
    private String tb_MKioskTy11;//중증화상
    private String dutyDivName;//병원분류명 //종합병원
    private String dutyEmclsName;//응급의료기관분류명 //권역||지역 응급의료센터
}



























