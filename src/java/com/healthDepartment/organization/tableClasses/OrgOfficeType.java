/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.healthDepartment.organization.tableClasses;

/**
 *
 * @author SoftTech
 */
public class OrgOfficeType {

    private int office_type_id;
    private String office_type;
    private String office_code;
    private String description;
   private  String  remark;
   private String active;
   private int revision_no;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public int getRevision_no() {
        return revision_no;
    }

    public void setRevision_no(int revision_no) {
        this.revision_no = revision_no;
    }
   
   
   
    public String getOffice_code() {
        return office_code;
    }

    public void setOffice_code(String office_code) {
        this.office_code = office_code;
    }
     
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOffice_type() {
        return office_type;
    }

    public void setOffice_type(String office_type) {
        this.office_type = office_type;
    }

    public int getOffice_type_id() {
        return office_type_id;
    }

    public void setOffice_type_id(int office_type_id) {
        this.office_type_id = office_type_id;
    }

    

}
