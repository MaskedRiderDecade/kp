package com.jxw.icharity.vo.service;

import com.jxw.icharity.domain.Finance;
import com.jxw.icharity.exception.DBNotFoundException;
import com.jxw.icharity.form.FinanceForm;
import com.jxw.icharity.repository.ProjectRepo;
import com.jxw.icharity.vo.finance.FinanceAnlysisVo;
import com.jxw.icharity.vo.finance.FinanceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
                        .<DBNotFoundException>orElseThrow(()->{throw new DBNotFoundException();}))
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

    public FinanceAnlysisVo convertFinances2FinanceAnlysisVo(List<Finance> finances){
        List<FinanceAnlysisVo.FinanceTypeSumDto>sumDtos=new ArrayList<>();
        List<FinanceVo>vos=new ArrayList<>();
        for(int i=1;i<9;i++){
            FinanceAnlysisVo.FinanceTypeSumDto dto= FinanceAnlysisVo.FinanceTypeSumDto.builder()
                    .type(i)
                    .sum(0)
                    .build();
            sumDtos.add(dto);
        }
        for(int i=0;i<finances.size();i++){
            Finance finance=finances.get(i);
            vos.add(convertFinance2FinanceVo(finance));
            //获取当前finance的类型type
            Integer type=finance.getType();
            Assert.isTrue(type>0,"类型参数不正确");
            //获取对应type的dto
            FinanceAnlysisVo.FinanceTypeSumDto dto=sumDtos.get(type-1);
            //sum加上当前finance的amount
            dto.setSum(dto.getSum()+finance.getAmount());
        }
        return FinanceAnlysisVo.builder()
                .financeVos(vos)
                .sumDtos(sumDtos)
                .build();

    }

}
