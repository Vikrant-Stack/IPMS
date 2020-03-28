package com.ipms.ePass.model;

import com.healthDepartment.util.KrutiDevToUnicodeConverter;
import com.healthDepartment.util.UnicodeToKrutiDevConverter;
import com.ipms.ePass.bean.ePassBean;
import java.io.ByteArrayOutputStream;
import java.sql.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;import net.sf.jasperreports.engine.JRExporterParameter;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;

public class ePassModel {

    private Connection connection;
    private String driver, url, user, password;
    private String message, messageBGColor;
    private KrutiDevToUnicodeConverter krutiToUnicode = new KrutiDevToUnicodeConverter();
    private UnicodeToKrutiDevConverter unicodeToKruti = new UnicodeToKrutiDevConverter();

    public byte[] generateMapReport(String jrxmlFilePath, List<ePassBean> listAll) {
        byte[] reportInbytes = null;
        try {

            JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(listAll);
            JasperReport compiledReport = JasperCompileManager.compileReport(jrxmlFilePath);
            reportInbytes = JasperRunManager.runReportToPdf(compiledReport, null, beanColDataSource);
        } catch (Exception e) {
            System.out.println("Error: in CityModel generateMapReport() JRException: " + e);
        }
        return reportInbytes;
    }

    public ByteArrayOutputStream generateCityXlsRecordList(String jrxmlFilePath, List list) {
        ByteArrayOutputStream bytArray = new ByteArrayOutputStream();
        //  HashMap mymap = new HashMap();
        try {
            JRBeanCollectionDataSource jrBean = new JRBeanCollectionDataSource(list);
            JasperReport compiledReport = JasperCompileManager.compileReport(jrxmlFilePath);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compiledReport, null, jrBean);
            JRXlsExporter exporter = new JRXlsExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, bytArray);
            exporter.exportReport();
        } catch (Exception e) {
            System.out.println("CityStatusModel generatReport() JRException: " + e);
        }
        return bytArray;
    }

    public List<ePassBean> showAllData(String cityName) {
        cityName = krutiToUnicode.convert_to_unicode(cityName);
        ArrayList<ePassBean> list = new ArrayList<ePassBean>();
        String query = "select city_name,pin_code,std_code,city_description from city where "
                + " if('" + cityName + "'='',city_name LIKE '%%',city_name='" + cityName + "')";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                ePassBean cityBean = new ePassBean();
//                cityBean.setCityName(unicodeToKruti.Convert_to_Kritidev_010(rset.getString("city_name")));
//                cityBean.setCityDescription(unicodeToKruti.Convert_to_Kritidev_010(rset.getString("city_description")));
//                cityBean.setPin_code(rset.getInt("pin_code"));
//                cityBean.setStd_code(rset.getInt("std_code"));
                list.add(cityBean);
            }
        } catch (Exception e) {
            System.out.println("Error in ShowAllData --- CityModel : " + e);
        }

        return list;
    }

    public List<String> getDivision(String q) {
        List<String> list = new ArrayList<String>();
        String query = " SELECT division_name FROM division GROUP BY division_name ORDER BY division_name ";
        try {
            ResultSet rset = connection.prepareStatement(query).executeQuery();
            int count = 0;
            q = q.trim();
            while (rset.next()) {    // move cursor from BOR to valid record.
                String division_type = unicodeToKruti.Convert_to_Kritidev_010(rset.getString("division_name"));
                if (division_type.toUpperCase().startsWith(q.toUpperCase())) {
                    list.add(division_type);
                    count++;
                }
            }
            if (count == 0) {
                list.add("No such city exists.......");
            }
        } catch (Exception e) {
            System.out.println("getCity ERROR inside CityModel - " + e);
        }
        return list;
    }

    public List<String> getCity(String q) {
        List<String> list = new ArrayList<String>();
        String query = " SELECT city_id, city_name FROM city GROUP BY city_name ORDER BY city_name ";
        try {
            ResultSet rset = connection.prepareStatement(query).executeQuery();
            int count = 0;
            q = q.trim();
            while (rset.next()) {    // move cursor from BOR to valid record.
                String city_type = unicodeToKruti.Convert_to_Kritidev_010(rset.getString("city_name"));
                if (city_type.toUpperCase().startsWith(q.toUpperCase())) {
                    list.add(city_type);
                    count++;
                }
            }
            if (count == 0) {
                list.add("No such city exists.......");
            }
        } catch (Exception e) {
            System.out.println("getCity ERROR inside CityModel - " + e);
        }
        return list;
    }

    public List<String> getOrgName(String q) {
        List<String> list = new ArrayList<String>();
        String query = " SELECT organisation_type_id,org_type_name FROM organisation_type where active='Y' "
                + " GROUP BY org_type_name ORDER BY org_type_name limit 2 ";
        try {
            ResultSet rset = connection.prepareStatement(query).executeQuery();
            int count = 0;

            while (rset.next()) {    // move cursor from BOR to valid record.
//                String tehsil_type = unicodeToKruti.Convert_to_Kritidev_010(rset.getString("tehsil_name"));
                String org_name = rset.getString("org_type_name");

                list.add(org_name);
                count++;

            }
            if (count == 0) {
                list.add("No such organisation exists.......");
            }
        } catch (Exception e) {
            System.out.println("getOrgName ERROR inside TehsilModel - " + e);
        }
        return list;
    }
//    public List<String> getCityName(String q,String distName) {
//        List<String> list = new ArrayList<String>();       
//
//        String query = "select city_name FROM city where city.district_id=(select district.district_id from district where district_name='"+distName+"') GROUP BY city_name ORDER BY city_name";
//        try {
//            ResultSet rset = connection.prepareStatement(query).executeQuery();
//            int count = 0;
//            q = q.trim();
//            while (rset.next()) {    // move cursor from BOR to valid record.
//                String city_type = rset.getString("city_name");
//                if (city_type.toUpperCase().startsWith(q.toUpperCase())) {
//                    list.add(city_type);
//                    count++;
//                }
//            }
//            if (count == 0) {
//                list.add("No such city exists.......");
//            }
//        } catch (Exception e) {
//            System.out.println("getCityName ERROR inside CityModel - " + e);
//        }
//        return list;
//    }
//    public List<String> getDistrict(String q,String diviName) {
//        List<String> list = new ArrayList<String>();
//        diviName = krutiToUnicode.convert_to_unicode(diviName);
//        String query = " SELECT district_name FROM district where district.division_id=(select division.division_id from division where division.division_name='"+diviName+"') GROUP BY district_name ORDER BY district_name ";
//        try {
//            ResultSet rset = connection.prepareStatement(query).executeQuery();
//            int count = 0;
//            q = q.trim();
//            while (rset.next()) {    // move cursor from BOR to valid record.
//                String district_type = unicodeToKruti.Convert_to_Kritidev_010(rset.getString("district_name"));
//                if (district_type.toUpperCase().startsWith(q.toUpperCase())) {
//                    list.add(district_type);
//                    count++;
//                }
//            }
//            if (count == 0) {
//                list.add("No such District exists.......");
//            }
//        } catch (Exception e) {
//            System.out.println("getDistrict ERROR inside CityModel - " + e);
//        }
//        return list;
//    }

    public void deleteRecord(int cityId) {
        PreparedStatement presta = null;
        try {
            presta = connection.prepareStatement("delete from city where city_id=?");
            presta.setInt(1, cityId);
            int i = presta.executeUpdate();
            if (i > 0) {
                message = "Record deleted successfully......";
                messageBGColor = "yellow";
            } else {
                message = "Record not deleted successfully......";
                messageBGColor = "red";
            }
        } catch (Exception e) {
            System.out.println("Error in deleting recordl ---- CityModel : " + e);
        }
    }

    public String getTehsilNameFromId(int id) {
        String name = null;

        String query = "SELECT tehsil_name FROM tehsil where tehsil_id=? and active='Y'";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                name = rset.getString(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(WorkTypeModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return name;
    }

    public ArrayList<ePassBean> getAllRecords(int lowerLimit, int noOfRowsToDisplay, String searchEPass) {
        searchEPass = krutiToUnicode.convert_to_unicode(searchEPass);
        ArrayList<ePassBean> list = new ArrayList<ePassBean>();
//        String query = "SELECT city_id,city_name,pin_code,std_code,city_description,tehsil_id FROM city "
//                + " WHERE IF('" + searchEPass + "'='',city_name LIKE '%%',city_name=?) and active='Y' "
//                + " order by city_name limit " + lowerLimit + "," + noOfRowsToDisplay;
        String query="select e_pass_id,loc_id,valid_from,valid_to,work_type_id,key_person_id,qr_code,remark from e_pass "
                + " order by e_pass_id limit "+lowerLimit+","+noOfRowsToDisplay;

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            //pstmt.setString(1, searchEPass); 
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                ePassBean ePassBean = new ePassBean();
                ePassBean.setePassId(rset.getString(1));
                ePassBean.setLocationId(rset.getString(2));
                ePassBean.setValidFrom(rset.getString(3));
                ePassBean.setValidTo(rset.getString(4));
                ePassBean.setWorkCode(unicodeToKruti.Convert_to_Kritidev_010(rset.getString(5)));
                //String tehsil = getTehsilNameFromId(rset.getInt(6));
                ePassBean.setKeyPersonId(rset.getString(6));
                ePassBean.setQrCode(rset.getString(7));
                ePassBean.setRemark(rset.getString(8));
                
                list.add(ePassBean);
            }
        } catch (Exception e) {
            System.out.println("Error in getAllRecrod -- ePassModel : " + e);
        } 
        return list;
    }

    public int getTotalRowsInTable(String searchEPass) {
        searchEPass = krutiToUnicode.convert_to_unicode(searchEPass);
        String query = " SELECT Count(*) "
                + " FROM city "
                + " WHERE IF('" + searchEPass + "' = '', city_name LIKE '%%',city_name =?) "
                + " ORDER BY city_name ";
        int noOfRows = 0;
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, searchEPass);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            noOfRows = Integer.parseInt(rs.getString(1));
        } catch (Exception e) {
            System.out.println("Error inside getNoOfRows CityModel" + e);
        }
        System.out.println("No of Rows in Table for search is****....." + noOfRows);
        return noOfRows;
    }

    public int getTehsilIdFromName(String name) {
        int id = 0;

        String query = "SELECT tehsil_id FROM tehsil where tehsil_name=? and active='Y'";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, name);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                id = rset.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(WorkTypeModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id;
    }

    public void insertRecord(ePassBean bean) {
        int rowAffected = 0;
        try {

            // for QR code
            String qrCode = "", personId = "", epassId = "", location = "", validF = "", validT = "";
            PreparedStatement psmt1 = null;
            ResultSet rs = null;

            String qry = " select max(e_pass_id) as id from e_pass ";
            psmt1=connection.prepareStatement(qry);
            rs = psmt1.executeQuery();
            while (rs.next()) {
                epassId = rs.getString("id");
            }
            // KP_personId_EP_epassId_loc_LocationId_validfrom_validTo
            String str1[] = bean.getValidFrom().split("-");
            String str2[] = bean.getValidTo().split("-");
//            System.out.println("date 000"+str[0]);
//            System.out.println("date 111"+str[1]);
//            System.out.println("date 222"+str[2]);
            String[] newDateFrom=str1[2].split(" ");
            String[] newDateTo=str2[2].split(" ");
            //System.out.println("new date --"+newDate[0]);
            qrCode="KP"+bean.getPersonId()+"_EP"+epassId+"_loc"+bean.getLocationId()+"_"+newDateFrom[0]+"_"+newDateTo[0];
            //System.out.println("qr code -"+qrCode);
            

            String query = " insert into e_pass(loc_id,valid_from,valid_to,work_type_id,created_at,created_by,remark,"
                    + " QR_code,key_person_id) values(?,?,?,?,Now(),?,?,?,?) ";
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setString(1, bean.getLocationId());
            ps.setString(2, bean.getValidFrom());
            ps.setString(3, bean.getValidTo()); 
            ps.setString(4, bean.getWorkCode());
            ps.setString(5, "Vikrant");
            ps.setString(6, bean.getRemark());
            ps.setString(7, qrCode);
            ps.setString(8, bean.getPersonId());

            rowAffected = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error in insertRecord in ePassModel : " + e);
        }
        if (rowAffected > 0) {
            message = rowAffected + " Record inserted successfully";
            messageBGColor = "yellow";
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println("CityModel closeConnection: " + e);
        }
    }

    public void setConnection() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("CityModel setConnection error: " + e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriver() {
        return driver;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageBGColor() {
        return messageBGColor;
    }

}
