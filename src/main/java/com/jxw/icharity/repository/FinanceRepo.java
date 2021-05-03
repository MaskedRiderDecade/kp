package com.jxw.icharity.repository;

import com.jxw.icharity.domain.Finance;
import com.jxw.icharity.domain.Project;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface FinanceRepo extends CrudRepository<Finance,Integer> {
    List<Finance> findByProjectOrderByCreateTimeDesc(Project project);

    List<Finance>findFinancesByCreateTimeBetweenOrderByCreateTimeAsc(Date createTime, Date createTime2);
}
