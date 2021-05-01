package com.jxw.icharity.controller;

import com.jxw.icharity.domain.Staff;
import com.jxw.icharity.form.StaffForm;
import com.jxw.icharity.service.StaffService;
import com.jxw.icharity.vo.ResponseVo;
import com.jxw.icharity.vo.StaffVo;
import com.jxw.icharity.vo.service.WebStaffService;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static com.jxw.icharity.constants.Constants.NOT_FOUND_MSG;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @Autowired
    private WebStaffService webStaffService;

    @PostMapping("/save")
    public ResponseVo save(@RequestBody StaffForm staffForm) throws ParseException {
        staffService.save(webStaffService.convertStaffForm2Staff(staffForm));
        return ResponseVo.success();
    }

    @GetMapping("/list")
    public ResponseVo<List<StaffVo>>list(){
        List<StaffVo>res=new ArrayList<>();
        List<Staff>searchRes=staffService.findAll();
        if(searchRes!=null&&!searchRes.isEmpty()){
            for(Staff staff:searchRes){
                res.add(webStaffService.convertStaff2StaffVo(staff));
            }
        }
        return ResponseVo.success(res);
    }

    @GetMapping("")
    public ResponseVo<StaffVo>getStaff(Integer staff_id){
        Assert.notNull(staff_id,"staffId不能为空");
        Staff staff=staffService.findById(staff_id);
        if(staff==null){
            return ResponseVo.error(NOT_FOUND_MSG);
        }
        return ResponseVo.success(webStaffService.convertStaff2StaffVo(staff));
    }

}
