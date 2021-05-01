package com.jxw.icharity.vo.service;

import com.jxw.icharity.domain.Project;
import com.jxw.icharity.domain.Staff;
import com.jxw.icharity.form.StaffForm;
import com.jxw.icharity.vo.StaffVo;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class WebStaffService {

    public Staff convertStaffForm2Staff(StaffForm staffForm) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        return Staff.builder()
                .name(staffForm.getName())
                .entryDate(sdf.parse(staffForm.getEntry_date()))
                .salary(staffForm.getSalary())
                .build();


    }

    public StaffVo convertStaff2StaffVo(Staff staff){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        StaffVo vo= StaffVo.builder()
                .id(staff.getId())
                .name(staff.getName())
                .salary(staff.getSalary())
                .entry_date(sdf.format(staff.getEntryDate()))
                .build();
        List<StaffVo.ProjectMemberVo>projectMemberVos=new ArrayList<>();
        for(Project project:staff.getProjects()){
            StaffVo.ProjectMemberVo projectMemberVo= StaffVo.ProjectMemberVo.builder()
                    .id(project.getId())
                    .name(project.getName())
                    .amount(project.getAmount())
                    .region(project.getRegion())
                    .type(project.getType())
                    .start_time(sdf.format(project.getStartTime()))
                    .end_time(sdf.format(project.getEndTime()))
                    .build();
            projectMemberVos.add(projectMemberVo);
        }
        vo.setRelated_projects(projectMemberVos);
        return vo;
    }

}
