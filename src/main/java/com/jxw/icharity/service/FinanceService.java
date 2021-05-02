package com.jxw.icharity.service;

import com.jxw.icharity.domain.Finance;
import com.jxw.icharity.domain.Project;
import com.jxw.icharity.enums.FinanceEnum;
import com.jxw.icharity.exception.DBNotFoundException;
import com.jxw.icharity.repository.FinanceRepo;
import com.jxw.icharity.repository.ProjectRepo;
import com.jxw.icharity.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class FinanceService {

    @Autowired
    private FinanceRepo financeRepo;

    @Autowired
    private ProjectRepo projectRepo;

    public ResponseVo save(Finance finance){
        validFinance(finance);
        financeRepo.save(finance);
        return ResponseVo.success();
    }

    private void validFinance(Finance finance){
        Assert.notNull(finance.getAmount(),"流水金额不能为空");
        Assert.notNull(finance.getCreateTime(),"流水日期不能为空");
        Assert.notNull(finance.getIsPositive(),"流水正负类型不能为空");
        Assert.notNull(finance.getType(),"项目类型不能为空");
        FinanceEnum.searchFinance(finance.getType());
    }

    public List<Finance> findByProjectId(Integer project_id){
        Assert.notNull(project_id,"项目id不能为空");
        return financeRepo.findByProject(projectRepo.findById(project_id).orElseThrow(()->{throw new DBNotFoundException();
        }));
    }

}
