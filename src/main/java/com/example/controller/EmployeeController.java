package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Employee;
import com.example.service.EmployeeService;

/**
 * 
 * 従業員用コントローラー
 * 従業員一覧や従業員を閲覧
 * 
 * @author 川内野孝樹
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

  /**
   * 
   * 従業員用のサービスクラスのメソッドが使えるようにする
   */
  @Autowired
  private EmployeeService employeeService;

  /**
   * 
   * 従業員一覧を出力
   * 
   * @return 従業員一覧画面へ遷移
   * @param Model リクエストスコープに格納できるようにする
   */
  @GetMapping("/showList")
  public String showList(Model model) {
    List<Employee> employeeList = employeeService.showList();
    model.addAttribute("employeeList", employeeList);
    return "employee/list";
  }

  /**
   * 
   * 従業員を出力
   * 
   * @return 従業員詳細画面へ遷移
   * @param id                 見たい従業員IDを選択
   * @param Model              リクエストスコープに格納できるようにする
   * @param UpdateEmployeeForm フォームオブジェクトをリクエストスコープに⾃動的に格納
   */
  @GetMapping("/showDetail")
  public String showDetail(String id, Model model, UpdateEmployeeForm form) {
    Employee employee = employeeService.showDetail(Integer.parseInt(id));
    model.addAttribute("employee", employee);
    return "employee/detail";
  }

  /**
   * 
   * 従業員情報を更新
   * 
   * @return /employee/showListへリダイレクト
   * @param UpdateEmployeeForm フォームオブジェクトをリクエストスコープに⾃動的に格納
   */
  @PostMapping("/update")
  public String update(UpdateEmployeeForm form) {
    Employee employee = employeeService.showDetail(Integer.parseInt(form.getId()));
    employee.setDependentsCount(Integer.parseInt(form.getDependentsCount()));
    employeeService.update(employee);
    return "redirect:/employee/showList"; 
  }
}
