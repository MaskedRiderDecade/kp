package com.jxw.icharity.vo.salary;

import com.jxw.icharity.vo.staff.StaffListVo;
import com.jxw.icharity.vo.staff.StaffVo;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class SalaryVo implements Serializable {
    private static final long serialVersionUID = 2627131627310366585L;

    private Integer id;

    private String create_time;

    private Integer amount;

    private StaffListVo staffListVo;
}
