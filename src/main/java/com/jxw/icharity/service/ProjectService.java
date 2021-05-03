package com.jxw.icharity.service;

import com.jxw.icharity.domain.Project;
import com.jxw.icharity.domain.Staff;
import com.jxw.icharity.domain.Welfare;
import com.jxw.icharity.exception.DBNotFoundException;
import com.jxw.icharity.form.ProjectForm;
import com.jxw.icharity.form.TimeRangeForm;
import com.jxw.icharity.repository.ProjectRepo;
import com.jxw.icharity.repository.StaffRepo;
import com.jxw.icharity.repository.WelfareRepo;
import com.jxw.icharity.service.dto.ProjectAnalysisDto;
import com.jxw.icharity.util.TimeRangeDate;
import com.jxw.icharity.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.jxw.icharity.constants.Constants.NOT_FOUND_MSG;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private StaffRepo staffRepo;

    @Autowired
    private WelfareRepo welfareRepo;

    public List<Project> findAll(){
        return projectRepo.findAll();
    }

    public Project findProject(Integer id){

        return projectRepo.findById(id).<DBNotFoundException>orElseThrow(()->{throw new DBNotFoundException();
        });
    }

    public List<ProjectAnalysisDto> analysis(TimeRangeForm form) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Assert.notNull(form.getStart_time(),"起始时间不可为空");
        Assert.notNull(form.getEnd_time(),"结束时间不可为空");
        TimeRangeDate timeRange= TimeRangeDate.builder()
                .startTime(sdf.parse(form.getStart_time()))
                .endTime(sdf.parse(form.getEnd_time()))
                .build();
        List<ProjectAnalysisDto>projectAnalysisDtos=new ArrayList<>();
        List<Project>projects=projectRepo.findProjectByStartTimeBetween(timeRange.getStartTime(),timeRange.getEndTime());
        Map<Integer,Integer> projectIndexMap=new HashMap<>();
        List<Welfare>welfares=welfareRepo.findByProjectIn(projects);
        for(int i=0;i<projects.size();i++){
            projectAnalysisDtos.add(ProjectAnalysisDto.builder()
            .project(projects.get(i))
            .welfares(new ArrayList<>())
            .build());
            projectIndexMap.put(projects.get(i).getId(),i);
        }
        for(Welfare welfare:welfares){
            Integer index=projectIndexMap.get(welfare.getProject().getId());
            List<Welfare>welfareTarget=projectAnalysisDtos.get(index).getWelfares();
            welfareTarget.add(welfare);
        }
        return projectAnalysisDtos;

    }

    public ResponseVo saveProject(ProjectForm projectForm) throws ParseException {
        //先查询是否存在该名称的项目
        if(projectRepo.countByName(projectForm.getName())>0){
            return ResponseVo.error("已经存在该名称的项目");
        }
        //添加关联的staff
        List<Staff>staffSearchResult=null;
        if(projectForm.getStaff_ids()!=null&&!projectForm.getStaff_ids().isEmpty()){
            staffSearchResult=staffRepo.findByIdIn(projectForm.getStaff_ids());
        }
        Project project=new Project();
        BeanUtils.copyProperties(projectForm,project);

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        project.setStartTime(sdf.parse(projectForm.getStart_time()));
        project.setEndTime(sdf.parse(projectForm.getEnd_time()));
        if(staffSearchResult!=null&&!staffSearchResult.isEmpty()) {
            Set<Staff> staffs = new HashSet<>(staffSearchResult);
            project.setStaff(staffs);
        }
        validProject(project);
        projectRepo.save(project);
        return ResponseVo.success();
    }

    private void validProject(Project project){
        Assert.notNull(project.getName(),"项目名称不能为空");
        Assert.notNull(project.getAmount(),"项目金额不能为空");
        Assert.notNull(project.getRegion(),"项目地区不能为空");
        Assert.notNull(project.getType(),"项目类型不能为空");
        Assert.notNull(project.getStartTime(),"项目开始时间不能为空");
        Assert.notNull(project.getEndTime(),"项目结束时间不能为空");

    }

}
