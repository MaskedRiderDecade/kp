package com.jxw.icharity.repository;

import com.jxw.icharity.domain.Staff;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface StaffRepo extends CrudRepository<Staff,Integer> {

    Optional<Staff> findById(Integer id);

    List<Staff>findByIdIn(List<Integer>ids);

    List<Staff>findAll();

}
