package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    /** 従業員情報を全件取得する */
    public List<Employee> showList() {
        List<Employee> employeeList = repository.findAll();
        return employeeList;
    }

}
