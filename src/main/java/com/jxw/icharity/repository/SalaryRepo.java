package com.jxw.icharity.repository;

import com.jxw.icharity.domain.Salary;
import com.jxw.icharity.domain.Staff;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SalaryRepo extends CrudRepository<Salary, Integer> {

    List<Salary>findByStaffOrderByCreateTimeAsc(Staff staff);

    List<Salary>findAllByIdGreaterThanEqualOrderByCreateTimeAsc(Integer id);
}
