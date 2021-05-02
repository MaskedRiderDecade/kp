package com.jxw.icharity.controller;

import com.jxw.icharity.domain.Finance;
import com.jxw.icharity.form.FinanceForm;
import com.jxw.icharity.service.FinanceService;
import com.jxw.icharity.vo.finance.FinanceVo;
import com.jxw.icharity.vo.ResponseVo;
import com.jxw.icharity.vo.service.WebFinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/finance")
public class FinanceController {

    @Autowired
    private FinanceService financeService;

    @Autowired
    private WebFinanceService webFinanceService;

    @PostMapping("/save")
    public ResponseVo save(@RequestBody FinanceForm financeForm) throws ParseException {
        return financeService.save(webFinanceService.convertFinanceForm2Finance(financeForm));
    }

    @GetMapping("")
    public ResponseVo<List<FinanceVo>> get(Integer project_id){
        List<Finance> searchRes=financeService.findByProjectId(project_id);
        List<FinanceVo>res=new ArrayList<>();
        if(searchRes!=null&&!searchRes.isEmpty()){
            for(Finance finance:searchRes){
                res.add(webFinanceService.convertFinance2FinanceVo(finance));
            }
        }
        return ResponseVo.success(res);
    }

}
