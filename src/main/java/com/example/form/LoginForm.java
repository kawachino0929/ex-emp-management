package com.example.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

/**
 * 
 * ログイン用のフォーム
 * @author 川内野孝樹
 */
public class LoginForm {

    /** メールアドレス */
    @Size(min = 1, max = 127, message = "1文字以上127文字以内で入力してください")
    @Email(message = "@を入力してください")
    private String mailAddress;
    /** パスワード */
    @Size(min = 1, max = 127, message = "1文字以上127文字以内で入力してください")
    private String password;
    
    @Override
    public String toString() {
        return "LoginForm [mailAddress=" + mailAddress + ", password=" + password + "]";
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
    
}
