package com.jxw.icharity.repository;

import com.jxw.icharity.domain.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ProjectRepo extends CrudRepository<Project,Integer> {

    List<Project>findAll();

    Optional<Project> findById(Integer id);

    Integer countByName(String name);

    List<Project> findByIdIn(List<Integer>ids);

    List<Project>findProjectByStartTimeBetween(Date startTime, Date endTime);

}
