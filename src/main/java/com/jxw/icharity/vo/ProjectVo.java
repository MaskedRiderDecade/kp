package com.jxw.icharity.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class ProjectVo implements Serializable {
    private static final long serialVersionUID = -7721805001703493301L;

    private Integer id;

    private String name;

    private Integer amount;

    private String region;

    private String start_time;

    private String end_time;

    private Integer type;

    private List<StaffMemberVo>related_staff;

    @Data
    @Builder
    public static class StaffMemberVo implements Serializable{
        private static final long serialVersionUID = -8570085958356731079L;

        private Integer id;

        private String name;

        private String entry_date;

        private Integer salary;
    }
}
