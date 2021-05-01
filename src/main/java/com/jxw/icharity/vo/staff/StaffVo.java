package com.jxw.icharity.vo.staff;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class StaffVo implements Serializable {
    private static final long serialVersionUID = 1955017764968376891L;
    private Integer id;

    private String name;

    private String entry_date;

    private Integer salary;

    private List<ProjectMemberVo>related_projects;

    @Data
    @Builder
    public static class ProjectMemberVo implements Serializable{
        private static final long serialVersionUID = 4904895082125720441L;

        private Integer id;

        private String name;

        private Integer amount;

        private String region;

        private String start_time;

        private String end_time;

        private Integer type;
    }
}
