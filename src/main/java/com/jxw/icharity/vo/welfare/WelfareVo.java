package com.jxw.icharity.vo.welfare;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WelfareVo {

    private Integer edu_amount;

    private Integer med_amount;

    private Integer employ_amount;

    private Integer house_amount;

    private Integer traffic_amount;

    private String ctime;

}
