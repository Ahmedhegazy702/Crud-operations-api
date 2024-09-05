package com.luv2code.dao;

import com.luv2code.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // No Need To Write Code
}
