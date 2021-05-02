package com.jxw.icharity.service;

import com.jxw.icharity.domain.Staff;
import com.jxw.icharity.exception.DBNotFoundException;
import com.jxw.icharity.repository.ProjectRepo;
import com.jxw.icharity.repository.StaffRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class StaffService {

    @Autowired
    private StaffRepo staffRepo;

    @Autowired
    private ProjectRepo projectRepo;

    public Staff findById(Integer staffId){
        return staffRepo.findById(staffId).orElseThrow(()->{throw new DBNotFoundException();
        });
    }

    public List<Staff>findAll(){
        return staffRepo.findAll();
    }

    public void save(Staff staff){
        validStaff(staff);
        staffRepo.save(staff);
    }

    public void validStaff(Staff staff){
        Assert.notNull(staff.getName(),"姓名不能为空");
        Assert.notNull(staff.getEntryDate(),"入职时间不能为空");
        Assert.notNull(staff.getSalary(),"薪水不能为空");
    }

}
