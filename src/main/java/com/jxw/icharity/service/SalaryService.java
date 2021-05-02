package com.jxw.icharity.service;

import com.jxw.icharity.domain.Salary;
import com.jxw.icharity.exception.DBNotFoundException;
import com.jxw.icharity.repository.SalaryRepo;
import com.jxw.icharity.repository.StaffRepo;
import com.jxw.icharity.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class SalaryService {

    @Autowired
    private SalaryRepo salaryRepo;

    @Autowired
    private StaffRepo staffRepo;

    public ResponseVo save(Salary salary){
        validSalary(salary);
        salaryRepo.save(salary);
        return ResponseVo.success();
    }

    private void validSalary(Salary salary){
        Assert.notNull(salary.getAmount(),"金额不能为空");
        Assert.notNull(salary.getCreateTime(),"创建时间不能为空");
    }

    public List<Salary>listStaffSalary(Integer staff_id){
        Assert.notNull(staff_id,"员工id不能为空");
        return salaryRepo.findByStaffOrderByCreateTimeDesc(staffRepo.findById(staff_id).orElseThrow(()->{throw new DBNotFoundException();
        }));
    }

}
