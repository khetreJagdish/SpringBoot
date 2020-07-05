package com.fulcrum.employeeDao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fulcrum.employee.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> 
{

}
