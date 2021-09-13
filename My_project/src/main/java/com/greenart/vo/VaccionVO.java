package com.greenart.vo;

import lombok.Data;

@Data
public class VaccionVO {
    private String v_dywk;//기준일자 요일
    private String v_endTm;//기준일자 진료종료시간
    private String v_hldyYn;//기준일자 휴무일여부
    private String v_lunchSttTm;//기준일자 점심시작시간
    private String v_lunchEndTm;//기준일자 점심종료시간 
    private String v_orgTlno;//기관전화번호
    private String v_orgZipaddr; //기관주소
    private String v_orgcd;//기관코드
    private String v_orgnm;//기관명
    private String v_slrYmd;//기준일자(현재날짜)
    private String v_sttTm;//기준일자 진료시작시간
}
