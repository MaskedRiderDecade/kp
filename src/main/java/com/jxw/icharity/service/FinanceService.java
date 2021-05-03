package com.jxw.icharity.service;

import com.jxw.icharity.domain.Finance;
import com.jxw.icharity.domain.Project;
import com.jxw.icharity.enums.FinanceEnum;
import com.jxw.icharity.exception.DBNotFoundException;
import com.jxw.icharity.form.TimeRangeForm;
import com.jxw.icharity.repository.FinanceRepo;
import com.jxw.icharity.repository.ProjectRepo;
import com.jxw.icharity.util.TimeRangeDate;
import com.jxw.icharity.vo.ResponseVo;
import com.jxw.icharity.vo.finance.FinanceAnlysisVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        return financeRepo.findByProjectOrderByCreateTimeDesc(projectRepo.findById(project_id).<DBNotFoundException>orElseThrow(()->{throw new DBNotFoundException();
        }));
    }

    public List<Finance>findByTimeRange(TimeRangeForm form) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Assert.notNull(form.getStart_time(),"起始时间不可为空");
        Assert.notNull(form.getEnd_time(),"结束时间不可为空");
        TimeRangeDate timeRange= TimeRangeDate.builder()
                .startTime(sdf.parse(form.getStart_time()))
                .endTime(sdf.parse(form.getEnd_time()))
                .build();
        return financeRepo
                .findFinancesByCreateTimeBetweenOrderByCreateTimeAsc(
                        timeRange.getStartTime(),timeRange.getEndTime());


    }

}
