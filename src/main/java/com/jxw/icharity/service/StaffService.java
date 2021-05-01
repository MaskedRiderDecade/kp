package com.jxw.icharity.service;

import com.jxw.icharity.domain.Project;
import com.jxw.icharity.domain.Staff;
import com.jxw.icharity.form.StaffForm;
import com.jxw.icharity.repository.ProjectRepo;
import com.jxw.icharity.repository.StaffRepo;
import com.jxw.icharity.vo.ResponseVo;
import com.jxw.icharity.vo.StaffVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.jxw.icharity.constants.Constants.NOT_FOUND_MSG;

@Service
public class StaffService {

    @Autowired
    private StaffRepo staffRepo;

    @Autowired
    private ProjectRepo projectRepo;

    public Staff findById(Integer staffId){
        return staffRepo.findById(staffId).orElse(null);
    }

    public List<Staff>findAll(){
        return staffRepo.findAll();
    }

    public void save(Staff staff){
        staffRepo.save(staff);
    }

}
