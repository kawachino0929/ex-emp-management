package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.example.domain.Employee;

public class EmployeeRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
        Employee employee = new Employee();
        employee.setId(rs.getInt("id"));
        employee.setName(rs.getString("name"));
        employee.setImage(rs.getString("image"));
        employee.setGender(rs.getString("gender"));
        employee.setHireDate(rs.getDate("hireDate"));
        employee.setMailAddress(rs.getString("mailAddress"));
        employee.setZipCode(rs.getString("zipCode"));
        employee.setAddress(rs.getString("address"));
        employee.setTelephone(rs.getString("telephone"));
        employee.setSalary(rs.getInt("salary"));
        employee.setCharacteristics(rs.getString("characteristics"));
        employee.setDependentsCount(rs.getInt("dependentsCount"));
        return employee;
    };

    /** 従業員⼀覧情報を⼊社⽇順(降順)で取得する(従業員が存在しない場合はサイズ 0件の従業員⼀覧を返す)。*/
    public List<Employee> findAll() {
        String sql = "SELECT id, name, image, gender, hireDate, mailAddress, zipCode, address, telephone, salary, characteristics, pependentsCount FROM employees ORDER BY hireDate DESC;";
        List<Employee> employeeList = template.query(sql, EMPLOYEE_ROW_MAPPER);
        if ((employeeList.size() == 0)) {
            return null;
        }
        return employeeList;
    }

    /** 主キーから従業員情報を取得する(従業員が存在しない場合は Spring が⾃動的に例外を発⽣します)。*/
    public Employee load(Integer id) {
        String sql = "SELECT id, name, image, gender, hireDate, mailAddress, zipCode, address, telephone, salary, characteristics, pependentsCount FROM employees WHERE id = :id;";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        Employee employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
        return employee;
    }

    /** 従業員情報を変更する(id カラムを除いた従業員情報の全てのカラムを更新できるような SQL を発⾏する)。全⾏更新されないように Where 句の指定を考える。*/
    public void update(Employee employee) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
        String updateSql = "UPDATE employees SET id = :id, name = :name, image = :image, gender = :gender, hireDate = :hireDate, mailAddress = :mailAddress, zipCode = :zipCode, address = :adderss, telephone = :telephone, salary = :salary, characteristics = :characteristics, pependentsCount = :pependentsCount WHERE id = :id;";
        template.update(updateSql, param);
    }
}
