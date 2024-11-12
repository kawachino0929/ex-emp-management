package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Administrator;

@Repository
public class AdministratorRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs, i) -> {
        Administrator administrator = new Administrator();
        administrator.setId(rs.getInt("id"));
        administrator.setName(rs.getString("name"));
        administrator.setMailAddress(rs.getString("mailAdderss"));
        administrator.setPassword(rs.getString("password"));
        return administrator;
    };

    /** 管理者情報を挿⼊ */
    public void insert(Administrator administrator) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);

        if (administrator.getId() == null) {
            String insertSql = "INSERT INTO administrators(id, name, mailAddress, password) VALUES(:id, :name, :mailAddress, :password)";;
            template.update(insertSql, param);
        }
    }

    /** メールアドレスとパスワードから管理者情報を取得する(1件も存在しない場合は null を返す※) */
    public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
        String sql = "SELECT id, name, mailAddress, password FROM administrators WHERE mailAddress = :mailAddress AND password = :password;";
        SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress).addValue("password", password);
        List<Administrator> administratorList = template.query(sql, param, ADMINISTRATOR_ROW_MAPPER);
        if (administratorList.size() == 0) {
            return null;
        }
        return administratorList.get(0);
    }
}