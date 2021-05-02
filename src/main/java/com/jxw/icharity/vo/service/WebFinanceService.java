package com.jxw.icharity.vo.service;

import com.jxw.icharity.domain.Finance;
import com.jxw.icharity.exception.DBNotFoundException;
import com.jxw.icharity.form.FinanceForm;
import com.jxw.icharity.repository.ProjectRepo;
import com.jxw.icharity.vo.finance.FinanceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class WebFinanceService {

    @Autowired
    private ProjectRepo projectRepo;

    public Finance convertFinanceForm2Finance(FinanceForm form) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        return Finance.builder()
                .amount(form.getAmount())
                .createTime(sdf.parse(form.getCreate_time()))
                .project(projectRepo.findById(form.getProject_id())
                        .orElseThrow(()->{throw new DBNotFoundException();}))
                .type(form.getType())
                .isPositive(form.getAmount()>=0?1:0)
                .build();
    }

    public FinanceVo convertFinance2FinanceVo(Finance finance){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        return FinanceVo.builder()
                .amount(finance.getAmount())
                .type(finance.getType())
                .create_time(sdf.format(finance.getCreateTime()))
                .isPositive(finance.getIsPositive())
                .build();
    }

}
