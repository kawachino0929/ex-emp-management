package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;

/**
 * 
 * 雇用者用のサービスクラス
 * @author 川内野孝樹
 */
@Service
@Transactional
public class EmployeeService {

    /**
     * 
     * 雇用者用のリポジトリのメソッドを使えるようにする
     */
    @Autowired
    private EmployeeRepository repository;

   /**
    * 
    * 従業員情報を全件取得する
    * @return employeeListを返す
    */
    public List<Employee> showList() {
        List<Employee> employeeList = repository.findAll();
        return employeeList;
    }

    /**
     * 
     * 従業員情報を取得する
     * @param id 閲覧したい従業員IDを取得
     * @return employeeを返す
     */
    public Employee showDetail(Integer id) {
        Employee employee = repository.load(id);
        return employee;
    }

    /**
     * 
     * 従業員情報を更新する
     * @param Employee 閲覧したい従業員情報を取得
     */
    public void update(Employee employee) {
        repository.update(employee);
    }

}
