package com.example.form;

import jakarta.validation.constraints.NotBlank;

/**
 * 管理者情報を変更するためのフォーム
 */
public class UpdateEmployeeForm {

    /** 従業員 ID */
    @NotBlank(message = "IDを入力してください")
    private String id;
    /** 扶養⼈数 */
    @NotBlank(message = "数字を入力してください")
    private String dependentsCount;
    
    @Override
    public String toString() {
        return "UpdateEmployeeForm [id=" + id + ", dependentsCount=" + dependentsCount + "]";
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getDependentsCount() {
        return dependentsCount;
    }
    public void setDependentsCount(String dependentsCount) {
        this.dependentsCount = dependentsCount;
    }
    
}
