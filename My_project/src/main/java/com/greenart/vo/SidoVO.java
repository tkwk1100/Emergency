package com.greenart.vo;

import lombok.Data;

@Data
public class SidoVO {
    private String region;// 시도
    private Integer ERbedinfo;//응급실(병상)
    private Integer AcuBedInfo;//입원실(병상)
}
