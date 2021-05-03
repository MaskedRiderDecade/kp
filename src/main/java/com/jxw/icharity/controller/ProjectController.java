package com.jxw.icharity.controller;

import com.jxw.icharity.domain.Project;
import com.jxw.icharity.domain.Welfare;
import com.jxw.icharity.form.ProjectForm;
import com.jxw.icharity.form.TimeRangeForm;
import com.jxw.icharity.service.ProjectService;
import com.jxw.icharity.service.dto.ProjectAnalysisDto;
import com.jxw.icharity.vo.project.ProjectAnalysisVo;
import com.jxw.icharity.vo.project.ProjectListVo;
import com.jxw.icharity.vo.project.ProjectVo;
import com.jxw.icharity.vo.ResponseVo;
import com.jxw.icharity.vo.service.WebProjectService;
import com.jxw.icharity.vo.service.WebWelfareService;
import com.jxw.icharity.vo.welfare.WelfareVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static com.jxw.icharity.constants.Constants.NOT_FOUND_MSG;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private WebProjectService webProjectService;

    @Autowired
    private WebWelfareService webWelfareService;

    @GetMapping("")
    public ResponseVo<ProjectVo>getProject(Integer project_id){
        Assert.notNull(project_id,"projectId不能为空");
        Project project=projectService.findProject(project_id);
        return ResponseVo.success(webProjectService.convertProject2ProjectVo(project));
    }

    @GetMapping("/list")
    public ResponseVo<List<ProjectListVo>> getAllProjects(){
        List<Project>searchRes=projectService.findAll();
        List<ProjectListVo>res=new ArrayList<>();
        if(searchRes!=null&&!searchRes.isEmpty()){
            for(Project project:searchRes){
                res.add(webProjectService.convertProject2ProjectListVo(project));
            }
        }
        return ResponseVo.success(res);
    }

    @PostMapping("/save")
    public ResponseVo save(@RequestBody ProjectForm projectForm) throws ParseException {
        return projectService.saveProject(projectForm);
    }

    @PostMapping("/analysis")
    public ResponseVo<List<ProjectAnalysisVo>> analysis(@RequestBody TimeRangeForm form) throws ParseException {
        List<ProjectAnalysisDto>searchRes=projectService.analysis(form);
        List<ProjectAnalysisVo>res=new ArrayList<>();
        if(searchRes!=null&&!searchRes.isEmpty()){
            for(ProjectAnalysisDto dto:searchRes){
                ProjectAnalysisVo vo=webProjectService.convertProjectAnalysisDto2Vo(dto);
                List<WelfareVo>welfareVos=vo.getWelfareVos();
                for(Welfare welfare:dto.getWelfares()){
                    welfareVos.add(webWelfareService.convertWelfare2WelfareVo(welfare));
                }
                res.add(vo);
            }
        }
        return ResponseVo.success(res);
    }

}
