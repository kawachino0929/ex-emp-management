package com.example.form;

/**
 * 
 * ログイン用のフォーム
 * @author 川内野孝樹
 */
public class LoginForm {

    /** メールアドレス */
    private String mailAddress;
    /** パスワード */
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
