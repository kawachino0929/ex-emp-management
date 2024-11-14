package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Administrator;
import com.example.repository.AdministratorRepository;

/**
 * 管理者用のサービスクラス
 * @author 川内野孝樹
 */
@Service
@Transactional
public class AdministratorService {

    /**
     * 
     * 管理者用のリポジトリのメソッドを使えるようにする
     */
    @Autowired
    private AdministratorRepository repository;

    /**
     * 
     * administratorRepositoryのinsert()メソッドで管理者情報を挿⼊する。
     * @param Administrator フォームで受け取った情報を格納
     */
    public void insert(Administrator administrator) {
        repository.insert(administrator);
    }

     /** 
      * 
      * administratorRepositoryのfindByMailAddressAndPassword()メソッドで管理者情報を閲覧する。
      * @param mailAddress,password フォームで受け取ったメールアドレスとパスワードを格納
      * @return administratorを返す
      */
    public Administrator login(String mailAddress, String password) {
        Administrator administrator =  repository.findByMailAddressAndPassword(mailAddress, password);
        return administrator;
    }

}
