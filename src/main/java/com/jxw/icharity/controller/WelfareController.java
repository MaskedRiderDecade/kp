package com.jxw.icharity.controller;

import com.jxw.icharity.domain.Welfare;
import com.jxw.icharity.exception.DBNotFoundException;
import com.jxw.icharity.form.WelfareForm;
import com.jxw.icharity.service.WelfareService;
import com.jxw.icharity.vo.ResponseVo;
import com.jxw.icharity.vo.service.WebWelfareService;
import com.jxw.icharity.vo.welfare.WelfareVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/welfare")
public class WelfareController {

    @Autowired
    private WelfareService welfareService;

    @Autowired
    private WebWelfareService webWelfareService;

    @PostMapping("/save")
    public ResponseVo save(@RequestBody WelfareForm welfareForm){
        return welfareService.save(webWelfareService.convertWelfareForm2Welfare(welfareForm));
    }

    @GetMapping("")
    public ResponseVo<List<WelfareVo>> getByProjectId(Integer project_id){
        List<Welfare>searchRes=welfareService.findByProjectId(project_id);
        List<WelfareVo>res=new ArrayList<>();
        if(searchRes!=null&&!searchRes.isEmpty()){
            for(Welfare welfare:searchRes){
                res.add(webWelfareService.convertWelfare2WelfareVo(welfare));
            }
        }
        return ResponseVo.success(res);
    }

}
