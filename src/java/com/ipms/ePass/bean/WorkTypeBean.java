
package com.ipms.ePass.bean;

import com.healthDepartment.location.tableClasses.*;

public class WorkTypeBean {
    private int cityId;
    private String cityName,cityDescription,tehsilName;
    int pin_code,std_code;

    public int getPin_code() {
        return pin_code;
    }

    public void setPin_code(int pin_code) {
        this.pin_code = pin_code;
    }

    public String getTehsilName() {
        return tehsilName;
    }

    public void setTehsilName(String tehsilName) {
        this.tehsilName = tehsilName;
    }

    public int getStd_code() {
        return std_code;
    }

    public void setStd_code(int std_code) {
        this.std_code = std_code;
    }

 
    public void setCityId(int cityId)
    {
        this.cityId = cityId;
    }
     public void setCityName(String cityName)
    {
        this.cityName = cityName;
    }
      public void setCityDescription(String cityDescription)
    {
        this.cityDescription = cityDescription;
    }
    public int getCityId()
    {
        return cityId;
    }
     public String getCityName()
    {
        return cityName;
    }
      public String getCityDescription()
    {
        return cityDescription;
    }
}
