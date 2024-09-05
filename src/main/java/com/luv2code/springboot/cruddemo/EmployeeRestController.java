package com.luv2code.springboot.cruddemo;

import com.luv2code.dao.EmployeeRepository;
import com.luv2code.entity.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RestController
@RequestMapping("/api")

public class EmployeeRestController {

    private EmployeeRepository  employeeRepository;

  public EmployeeRestController(EmployeeRepository  employeeRepository) {
        this.employeeRepository = employeeRepository;

    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }
}
