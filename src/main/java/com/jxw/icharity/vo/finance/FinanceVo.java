package com.jxw.icharity.vo.finance;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
public class FinanceVo implements Serializable {
    private static final long serialVersionUID = 4844131738622515876L;

    private Integer amount;

    private String create_time;

    private Integer type;

    private Integer isPositive;

}
