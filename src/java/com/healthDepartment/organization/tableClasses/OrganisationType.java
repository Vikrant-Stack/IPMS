/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.healthDepartment.organization.tableClasses;

/**
 *
 * @author Soft_Tech
 */
public class OrganisationType {
    private int organisation_type_id;
    private String org_type_name;
    private String description;
    private String active;
    private int revision_no;
    private String remark;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrg_type_name() {
        return org_type_name;
    }

    public void setOrg_type_name(String org_type_name) {
        this.org_type_name = org_type_name;
    }

    public int getOrganisation_type_id() {
        return organisation_type_id;
    }

    public void setOrganisation_type_id(int organisation_type_id) {
        this.organisation_type_id = organisation_type_id;
    }
}
