package com.jxw.icharity.form;

import com.jxw.icharity.domain.Staff;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class ProjectForm {

    private String name;

    private Integer amount;

    private String region;

    private String start_time;

    private String end_time;

    private Integer type;

    private List<Integer> staff_ids;
}
