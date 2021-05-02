package com.jxw.icharity.repository;

import com.jxw.icharity.domain.Project;
import com.jxw.icharity.domain.Welfare;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WelfareRepo extends CrudRepository<Welfare,Integer> {

    List<Welfare>findByProjectOrderByCtimeDesc(Project project);

}
