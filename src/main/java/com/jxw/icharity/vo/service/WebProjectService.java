package com.jxw.icharity.vo.service;

import com.jxw.icharity.domain.Project;
import com.jxw.icharity.domain.Staff;
import com.jxw.icharity.vo.ProjectVo;
import com.jxw.icharity.vo.StaffVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class WebProjectService {

    public ProjectVo convertProject2ProjectVo(Project project){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        ProjectVo vo= ProjectVo.builder()
                .id(project.getId())
                .name(project.getName())
                .region(project.getRegion())
                .amount(project.getAmount())
                .type(project.getType())
                .start_time(sdf.format(project.getStartTime()))
                .end_time(sdf.format(project.getEndTime()))
                .build();
        List<ProjectVo.StaffMemberVo>staffMemberVos=new ArrayList<>();
        for(Staff staff:project.getStaff()){
            ProjectVo.StaffMemberVo staffMemberVo= ProjectVo.StaffMemberVo.builder()
                    .id(staff.getId())
                    .name(staff.getName())
                    .entry_date(sdf.format(staff.getEntryDate()))
                    .salary(staff.getSalary())
                    .build();
            staffMemberVos.add(staffMemberVo);
        }
        vo.setRelated_staff(staffMemberVos);
        return vo;
    }
}
