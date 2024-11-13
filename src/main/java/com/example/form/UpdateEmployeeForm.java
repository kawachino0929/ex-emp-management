package com.example.form;


/**
 * 管理者情報を変更するためのフォーム
 */
public class UpdateEmployeeForm {

    /** 従業員 ID */
    private String id;
    /** 扶養⼈数 */
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
