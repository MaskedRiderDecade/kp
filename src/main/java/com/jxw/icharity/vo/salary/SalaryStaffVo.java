package com.jxw.icharity.vo.salary;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
public class SalaryStaffVo implements Serializable {
    private static final long serialVersionUID = 8706452647571445710L;

    private Integer id;

    private String create_time;

    private Integer amount;
}
