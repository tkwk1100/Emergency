package com.greenart.vo;

import lombok.Data;

@Data
public class EmergencyBasiVO {
    private String dutyName;//기관명
    private String hpid;//기관ID
    private String MKioskTy25;//응급실
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
    private double latitude;//위도
    private double longitude;//경도
    private String dutyAddr;//주소
}
