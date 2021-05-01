package com.jxw.icharity.service;

import com.jxw.icharity.domain.Project;
import com.jxw.icharity.domain.Staff;
import com.jxw.icharity.form.ProjectForm;
import com.jxw.icharity.repository.ProjectRepo;
import com.jxw.icharity.repository.StaffRepo;
import com.jxw.icharity.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.jxw.icharity.constants.Constants.NOT_FOUND_MSG;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private StaffRepo staffRepo;

    public List<Project> findAll(){
        return projectRepo.findAll();
    }

    public Project findProject(Integer id){

        return projectRepo.findById(id).orElse(null);

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
        projectRepo.save(project);
        return ResponseVo.success();
    }

}
