
package com.ipms.ePass.bean;


public class ePassBean {
    private String ePassId;
    private String workCode,personId,locationId;
    private String validFrom,validTo,remark,qrCode,keyPersonId;

    /**
     * @return the ePassId
     */
    public String getePassId() {
        return ePassId;
    }

    /**
     * @param ePassId the ePassId to set
     */
    public void setePassId(String ePassId) {
        this.ePassId = ePassId;
    }

    /**
     * @return the workCode
     */
    public String getWorkCode() {
        return workCode;
    }

    /**
     * @param workCode the workCode to set
     */
    public void setWorkCode(String workCode) {
        this.workCode = workCode;
    }

    /**
     * @return the personId
     */
    public String getPersonId() {
        return personId;
    }

    /**
     * @param personId the personId to set
     */
    public void setPersonId(String personId) {
        this.personId = personId;
    }

    /**
     * @return the locationId
     */
    public String getLocationId() {
        return locationId;
    }

    /**
     * @param locationId the locationId to set
     */
    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    /**
     * @return the validFrom
     */
    public String getValidFrom() {
        return validFrom;
    }

    /**
     * @param validFrom the validFrom to set
     */
    public void setValidFrom(String validFrom) {
        this.validFrom = validFrom;
    }

    /**
     * @return the validTo
     */
    public String getValidTo() {
        return validTo;
    }

    /**
     * @param validTo the validTo to set
     */
    public void setValidTo(String validTo) {
        this.validTo = validTo;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return the qrCode
     */
    public String getQrCode() {
        return qrCode;
    }

    /**
     * @param qrCode the qrCode to set
     */
    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    /**
     * @return the keyPersonId
     */
    public String getKeyPersonId() {
        return keyPersonId;
    }

    /**
     * @param keyPersonId the keyPersonId to set
     */
    public void setKeyPersonId(String keyPersonId) {
        this.keyPersonId = keyPersonId;
    }

    
}
