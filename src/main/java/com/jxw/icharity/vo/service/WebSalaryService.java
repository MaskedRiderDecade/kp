package com.jxw.icharity.vo.service;

import com.jxw.icharity.domain.Salary;
import com.jxw.icharity.domain.Staff;
import com.jxw.icharity.exception.DBNotFoundException;
import com.jxw.icharity.form.SalaryForm;
import com.jxw.icharity.form.StaffForm;
import com.jxw.icharity.repository.StaffRepo;
import com.jxw.icharity.vo.salary.SalaryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class WebSalaryService {

    @Autowired
    private StaffRepo staffRepo;

    public Salary convertSalaryForm2Salary(SalaryForm form) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        return Salary.builder()
                .amount(form.getAmount())
                .createTime(sdf.parse(form.getCreate_time()))
                .staff(staffRepo.findById(form.getStaff_id()).orElseThrow(()->{throw new DBNotFoundException();
                }))
                .build();
    }

    public SalaryVo convertSalary2SalaryVo(Salary salary){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        return SalaryVo.builder()
                .create_time(sdf.format(salary.getCreateTime()))
                .amount(salary.getAmount())
                .build();
    }

}
