package com.jxw.icharity.service;

import com.jxw.icharity.domain.Welfare;
import com.jxw.icharity.exception.DBNotFoundException;
import com.jxw.icharity.repository.ProjectRepo;
import com.jxw.icharity.repository.WelfareRepo;
import com.jxw.icharity.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class WelfareService {

    @Autowired
    private WelfareRepo welfareRepo;

    @Autowired
    private ProjectRepo projectRepo;

    public ResponseVo save(Welfare welfare){
        validWelfare(welfare);
        welfareRepo.save(welfare);
        return ResponseVo.success();
    }

    public List<Welfare>findByProjectId(Integer project_id)throws DBNotFoundException{
        Assert.notNull(project_id);
        return welfareRepo.findByProjectOrderByCtimeDesc(projectRepo.findById(project_id).<DBNotFoundException>orElseThrow(()->{throw new DBNotFoundException();
        }));
    }



    private void validWelfare(Welfare welfare){
        Assert.notNull(welfare.getEduAmount(),"教育经费不可为空");
        Assert.notNull(welfare.getEmployAmount(),"就业经费不可为空");
        Assert.notNull(welfare.getHouseAmount(),"住房经费不可为空");
        Assert.notNull(welfare.getMedAmount(),"医疗经费不可为空");
        Assert.notNull(welfare.getTrafficAmount(),"交通经费不可为空");
    }

}
