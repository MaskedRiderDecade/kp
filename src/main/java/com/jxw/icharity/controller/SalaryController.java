package com.jxw.icharity.controller;

import com.jxw.icharity.domain.Salary;
import com.jxw.icharity.form.SalaryForm;
import com.jxw.icharity.service.SalaryService;
import com.jxw.icharity.vo.ResponseVo;
import com.jxw.icharity.vo.salary.SalaryVo;
import com.jxw.icharity.vo.service.WebSalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/salary")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @Autowired
    private WebSalaryService webSalaryService;

    @PostMapping("/save")
    public ResponseVo save(@RequestBody SalaryForm salaryForm) throws ParseException {
        return salaryService.save(webSalaryService.convertSalaryForm2Salary(salaryForm));
    }

    @GetMapping("")
    public ResponseVo<List<SalaryVo>>listStaffSalary(Integer staff_id){
        List<Salary>searchRes=salaryService.listStaffSalary(staff_id);
        List<SalaryVo>res=new ArrayList<>();
        if(searchRes!=null&&!searchRes.isEmpty()){
            for(Salary salary:searchRes){
                res.add(webSalaryService.convertSalary2SalaryVo(salary));
            }
        }
        return ResponseVo.success(res);
    }

}
