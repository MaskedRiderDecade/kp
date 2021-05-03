package com.jxw.icharity.vo.service;

import com.jxw.icharity.domain.Salary;
import com.jxw.icharity.domain.Staff;
import com.jxw.icharity.exception.DBNotFoundException;
import com.jxw.icharity.form.SalaryForm;
import com.jxw.icharity.repository.StaffRepo;
import com.jxw.icharity.vo.salary.SalaryStaffVo;
import com.jxw.icharity.vo.salary.SalaryVo;
import com.jxw.icharity.vo.staff.StaffListVo;
import com.jxw.icharity.vo.staff.StaffVo;
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
                .staff(staffRepo.findById(form.getStaff_id()).<DBNotFoundException>orElseThrow(()->{throw new DBNotFoundException();
                }))
                .build();
    }

    public SalaryStaffVo convertSalary2SalaryStaffVo(Salary salary){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        return SalaryStaffVo.builder()
                .id(salary.getId())
                .create_time(sdf.format(salary.getCreateTime()))
                .amount(salary.getAmount())
                .build();
    }

    public SalaryVo convertSalary2SalaryVo(Salary salary, StaffListVo staffListVo){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        return SalaryVo.builder()
                .id(salary.getId())
                .create_time(sdf.format(salary.getCreateTime()))
                .amount(salary.getAmount())
                .staffListVo(staffListVo)
                .build();
    }

}
