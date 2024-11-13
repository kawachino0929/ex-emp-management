package com.example.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * 
 * 管理者用のフォーム
 * @author 川内野孝樹
 */
public class InsertAdministratorForm {

    /** 名前 */
    @NotBlank(message = "名前を入力してください")
    private String name;
    /** メールアドレス */
    @Size(min = 1, max = 127, message = "1文字以上127文字以内で入力してください")
    @Email(message = "@を入力してください")
    private String mailAddress;
    /** パスワード */
    @Size(min = 1, max = 127, message = "1文字以上127文字以内で入力してください")
    private String password;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMailAddress() {
        return mailAddress;
    }
    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "InsertAdministratorForm [name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
                + "]";
    }
    
}
