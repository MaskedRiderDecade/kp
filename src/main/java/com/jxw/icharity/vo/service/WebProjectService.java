package com.jxw.icharity.vo.service;

import com.jxw.icharity.domain.Project;
import com.jxw.icharity.domain.Staff;
import com.jxw.icharity.service.dto.ProjectAnalysisDto;
import com.jxw.icharity.vo.project.ProjectAnalysisVo;
import com.jxw.icharity.vo.project.ProjectListVo;
import com.jxw.icharity.vo.project.ProjectVo;
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

    public ProjectListVo convertProject2ProjectListVo(Project project){
        return ProjectListVo.builder()
                .id(project.getId())
                .name(project.getName())
                .build();
    }

    public ProjectAnalysisVo convertProjectAnalysisDto2Vo(ProjectAnalysisDto dto){
        return ProjectAnalysisVo.builder()
                .projectListVo(convertProject2ProjectListVo(dto.getProject()))
                .welfareVos(new ArrayList<>())
                .build();
    }
}
