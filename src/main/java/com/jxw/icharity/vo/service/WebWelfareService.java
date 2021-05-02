package com.jxw.icharity.vo.service;

import com.jxw.icharity.domain.Welfare;
import com.jxw.icharity.exception.DBNotFoundException;
import com.jxw.icharity.form.WelfareForm;
import com.jxw.icharity.repository.ProjectRepo;
import com.jxw.icharity.vo.welfare.WelfareVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
public class WebWelfareService {

    @Autowired
    private ProjectRepo projectRepo;

    public Welfare convertWelfareForm2Welfare(WelfareForm welfareForm){
        return Welfare.builder()
                .eduAmount(welfareForm.getEdu_amount())
                .medAmount(welfareForm.getMed_amount())
                .houseAmount(welfareForm.getHouse_amount())
                .employAmount(welfareForm.getEmploy_amount())
                .trafficAmount(welfareForm.getTraffic_amount())
                .project(projectRepo.findById(welfareForm.getProject_id()).orElseThrow(()->{throw new DBNotFoundException();
                }))
                .build();
    }

    public WelfareVo convertWelfare2WelfareVo(Welfare welfare){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        return WelfareVo.builder()
                .edu_amount(welfare.getEduAmount())
                .employ_amount(welfare.getEmployAmount())
                .house_amount(welfare.getHouseAmount())
                .med_amount(welfare.getMedAmount())
                .traffic_amount(welfare.getTrafficAmount())
                .ctime(sdf.format(welfare.getCtime()))
                .build();
    }

}
