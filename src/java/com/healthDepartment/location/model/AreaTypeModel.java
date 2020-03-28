/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.healthDepartment.location.model;


import com.healthDepartment.location.tableClasses.AreaTypeBean;
import com.healthDepartment.util.KrutiDevToUnicodeConverter;
import com.healthDepartment.util.UnicodeToKrutiDevConverter;
import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;

/**
 *
 * @author JPSS
 */
public class AreaTypeModel {
    private Connection connection;
    private String driverClass;
    private String connectionString;
    private String db_username;
    private String db_password;
    private String message;
    private String msgBgColor;
    private final String COLOR_OK = "yellow";
    private final String COLOR_ERROR = "red";
UnicodeToKrutiDevConverter uk=new UnicodeToKrutiDevConverter();
KrutiDevToUnicodeConverter ku=new KrutiDevToUnicodeConverter();
    public void setConnection() {
        try {
            Class.forName(driverClass);
              connection = (Connection) DriverManager.getConnection(connectionString+"?useUnicode=true&characterEncoding=UTF-8&character_set_results=utf8", db_username, db_password);
           // connection = (Connection) DriverManager.getConnection(connectionString, db_username, db_password);
        } catch (Exception e) {
            System.out.println("AraeTYpeTypeModel setConnection() Error: " + e);
        }
    }

          public ByteArrayOutputStream generateAreaXlsRecordList(String jrxmlFilePath,List list) {
                ByteArrayOutputStream bytArray = new ByteArrayOutputStream();
                try {
                    JRBeanCollectionDataSource jrBean=new JRBeanCollectionDataSource(list);
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
   public byte[] generateMapReport(String jrxmlFilePath, List<AreaTypeBean> listAll) {
        byte[] reportInbytes = null;
         Connection c;
         //HashMap mymap = new HashMap();
        try {

            JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(listAll);
            JasperReport compiledReport = JasperCompileManager.compileReport(jrxmlFilePath);
            reportInbytes = JasperRunManager.runReportToPdf(compiledReport, null , beanColDataSource );
        } catch (Exception e) {
            System.out.println("Error: in WardTypeModel generateMapReport() JRException: " + e);
        }
        return reportInbytes;
    }

    public List<String> getZoneName(String q) {
        List<String> list = new ArrayList<String>();
        String query = " SELECT zone_name FROM zone GROUP BY zone_name ORDER BY zone_name ";
        try {
            ResultSet rset = connection.prepareStatement(query).executeQuery();
            int count = 0;
            q = q.trim();
            while (rset.next()) {    // move cursor from BOR to valid record.
                String zone_name = uk.Convert_to_Kritidev_010(rset.getString("zone_name"));
                if (zone_name.toUpperCase().startsWith(q.toUpperCase())) {
                    list.add(zone_name);
                    count++;
                }
            }
            if (count == 0) {
                list.add("No such Zone Type exists.");
            }
        } catch (Exception e) {
            System.out.println("getCityType ERROR inside WardTypeModel - " + e);
        }
        return list;
    }
    public List<String> getWardName(String q, String zone_name)
    {
        List<String> list = new ArrayList<String>();
         PreparedStatement pstmt;
          zone_name=ku.convert_to_unicode(zone_name);
        String query = " SELECT w.ward_name  FROM ward AS w, zone AS z "
               +  "WHERE   w.zone_id = z.zone_id "
                + "AND IF('" + zone_name + "'='', zone_name like '%%', zone_name ='" + zone_name + "') "
                 + "Group by ward_name ";
        try {
            pstmt = (PreparedStatement) connection.prepareStatement(query);
           // pstmt.setString(1, zone_name);
            ResultSet rset = pstmt.executeQuery();
            int count = 0;
            q = q.trim();
            while (rset.next()) {
                String ward_name =uk.Convert_to_Kritidev_010(rset.getString("ward_name"));
                if (ward_name.toUpperCase().startsWith(q.toUpperCase())) {
                    list.add(ward_name);
                    count++;
                }
            }
            if (count == 0) {
                list.add("No such Ward No exists.");
            }
        } catch (Exception e) {
            System.out.println("getWardType ERROR inside WardTypeModel - " + e);
        }
        return list;
    }
public List<String> getAreaName(String q) //, String ward_name, String zone_name
{
        List<String> list = new ArrayList<String>();

        String query =" SELECT a.area_name "
                + "FROM area AS a ,ward AS w, zone AS z "
               + "WHERE a.ward_id = w.ward_id "
               +  "AND w.zone_id = z.zone_id "
               //+  "AND IF('" + ward_name + "'='', ward_name like '%%', ward_name =?) "
              // +  "AND IF('" + zone_name + "'='', zone_name like '%%', zone_name =?) "
              +  "Group by area_name" ;
        try {
            java.sql.PreparedStatement pstmt = connection.prepareStatement(query);
           // pstmt.setString(1, ward_name);
           // pstmt.setString(2, zone_name);
            ResultSet rset = pstmt.executeQuery();
            int count = 0;
            q = q.trim();
            while (rset.next()) {    // move cursor from BOR to valid record.
                String areaName = rset.getString("area_name");
                if (areaName.toUpperCase().startsWith(q.toUpperCase())) {
                    list.add(areaName);
                    count++;
                }
            }
            if (count == 0) {
                list.add("No such Area Type exists.");
            }
        } catch (Exception e) {
            System.out.println("Error:AreaType1Model-" + e);
        }
        return list;
    }
 public int getWardId(String ward_name) {
        int ward_id = 0;
        ward_name=(ward_name);
        String query = " SELECT ward_id FROM ward WHERE ward_name ='"+ward_name+"'";
        try {
            java.sql.PreparedStatement pstmt = connection.prepareStatement(query);
           // pstmt.setString(1, ward_no);
            ResultSet rset = pstmt.executeQuery();
            rset.next();    // move cursor from BOR to valid record.
            ward_id = rset.getInt("ward_id");
        } catch (Exception e) {
            System.out.println("WardTypeModel getWardId() Error: " + e);
        }
        return ward_id;
    }
      public int getZoneId(String zone) {
          zone=ku.convert_to_unicode(zone);
        String query = " select zone_id from zone where zone_name='"+zone+"' ";
        int zone_id = 0;
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                zone_id=rs.getInt("zone_id");
            }
        } catch (Exception e) {
            System.out.println("Error inside getCircleId division type model" + e);
        }

        return zone_id;
    }
   public int getWard_rev_no(String ward_no) {
        int ward_rev_no = 0;
        String query = " SELECT ward_rev_no FROM ward WHERE ward_no =?  and active='Active'";
        try {
            java.sql.PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, ward_no);
            ResultSet rset = pstmt.executeQuery();
            rset.next();    // move cursor from BOR to valid record.
            ward_rev_no = rset.getInt("ward_rev_no");
        } catch (Exception e) {
            System.out.println("WardTypeModel getWardId() Error: " + e);
        }
        return ward_rev_no;
    }

 public int insertRecord(AreaTypeBean bean) {
        String query = " INSERT INTO area(area_name,area_no, remark, ward_id,revision_no,active) VALUES(?, ?, ?, ?,?,?) ";
        int rowsAffected = 0;
        try {
            java.sql.PreparedStatement pstmt = connection.prepareStatement(query);

            pstmt.setString(1, (bean.getArea_name()));
            pstmt.setString(2, bean.getArea_no());
            pstmt.setString(3, bean.getRemark());
            pstmt.setInt(5,bean.getRevision_no());
            pstmt.setString(6, "Y");
             int ward_id=getWardId(bean.getWard_name());
            pstmt.setInt(4, ward_id);
            rowsAffected = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("error in area TYpe Model Insert() method:" + e);
        }
        if (rowsAffected > 0) {
            message = "Record saved successfully.";
            msgBgColor = COLOR_OK;
        } else {
            message = "Cannot save the record, some error.";
            msgBgColor = COLOR_ERROR;
        }
        return rowsAffected;
    }

   public int getNoOfRows(String area_name,String ward,String zone) //, String ward_no, String city_name
   {
         area_name = ku.convert_to_unicode(area_name);
         ward = ku.convert_to_unicode(ward);
         zone = ku.convert_to_unicode(zone);
        int noOfRows = 0;
        try {
            String query = " SELECT COUNT(area_id) as area_id FROM area as a,ward as w,zone as z "
                          + " where a.ward_id=w.ward_id and w.zone_id=z.zone_id "
                            + " and IF('"+area_name +"'='',a.area_name LIKE '%%',a.area_name=?) "
                             + " and IF('"+ward +"'='',w.ward_name LIKE '%%',w.ward_name =?) "
                              + " and IF('"+zone +"'='',z.zone_name LIKE '%%',z.zone_name=?) "
                    + " ORDER BY a.area_name ";
            java.sql.PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, area_name);
            pst.setString(2, ward);
            pst.setString(3, zone);
            ResultSet rset = pst.executeQuery();
            rset.next();
            noOfRows = Integer.parseInt(rset.getString(1));
        } catch (Exception e) {
            System.out.println("Error in getRows() of AreaTypeModel...." + e);
        }
        return noOfRows;
    }

    public List<AreaTypeBean> showData(int lowerLimit, int noOfRowsToDisplay, String area_name,String ward,String zone)
    {     area_name = ku.convert_to_unicode(area_name);
          ward = ku.convert_to_unicode(ward);
         zone = ku.convert_to_unicode(zone);
        List<AreaTypeBean> list = new ArrayList<AreaTypeBean>();
        //PreparedStatement pstmt = null;

        String query = " SELECT a.area_id,z.zone_name,w.ward_name,a.area_name,a.area_no,a.remark FROM area as a,ward as w,zone as z "
                         + " where a.ward_id=w.ward_id and w.zone_id=z.zone_id "
                        + " and IF('"+area_name +"'='',a.area_name LIKE '%%',a.area_name=?)  "
                        + " and IF('"+ward +"'='',w.ward_name LIKE '%%',w.ward_name =?) "
                        + " and IF('"+zone +"'='',z.zone_name LIKE '%%',z.zone_name=?) order by zone_name,ward_name,area_no "
                         + " LIMIT " + lowerLimit + "," + noOfRowsToDisplay;
        try {
            java.sql.PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, area_name);
            pstmt.setString(2, ward);
            pstmt.setString(3, zone);
            ResultSet rset = pstmt.executeQuery();
            
            while (rset.next()) {
                AreaTypeBean bean = new AreaTypeBean();
                bean.setArea_id(rset.getInt("area_id"));
                bean.setArea_name(rset.getString("area_name"));
                 bean.setArea_no(rset.getString("area_no"));
                bean.setWard_name(rset.getString("ward_name"));
                bean.setZone_name(rset.getString("zone_name"));
                bean.setRemark(rset.getString("remark"));
                list.add(bean);
            }
        } catch (Exception e) {
            System.out.println("Error in ShowData() :AreaTypeModel" + e);
        }
        return list;
    }

 public List<AreaTypeBean>showAllData(String area_name,String ward,String zone) {
         area_name = ku.convert_to_unicode(area_name);
         ward = ku.convert_to_unicode(ward);
         zone = ku.convert_to_unicode(zone);
        List<AreaTypeBean> list = new ArrayList<AreaTypeBean>();
        String query = " select z.zone_name,w.ward_name,a.area_name,a.area_no,a.description FROM area as a,ward as w,zone as z "
                        + "    where a.ward_id=w.ward_id and w.zone_id=z.zone_id "
                        + " and IF('"+area_name +"'='',a.area_name LIKE '%%',a.area_name=?)  "
                        + " and IF('"+ward +"'='',w.ward_name LIKE '%%',w.ward_name =?) "
                        + " and IF('"+zone +"'='',z.zone_name LIKE '%%',z.zone_name=?) " ;
        try {
            java.sql.PreparedStatement pstmt = connection.prepareStatement(query);
          
            pstmt.setString(1, area_name);
            pstmt.setString(2, ward);
            pstmt.setString(3, zone);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                AreaTypeBean bean = new AreaTypeBean();
                bean.setArea_name(uk.Convert_to_Kritidev_010(rset.getString("area_name")));
                bean.setArea_no(rset.getString("area_no"));
                bean.setWard_name(uk.Convert_to_Kritidev_010(rset.getString("ward_name")));
                bean.setZone_name(uk.Convert_to_Kritidev_010(rset.getString("zone_name")));
                bean.setRemark(rset.getString("description"));
                list.add(bean);
            }
        } catch (Exception e) {
            System.out.println("Error in ShowData() :AreaTypeModel" + e);
        }
        return list;
    }
 public int updateRecord(AreaTypeBean areaTypeBean) {
        String query = " UPDATE area SET  area_name = ?, remark=?, ward_id=?, active=? WHERE area_id = ? ";
        int rowsAffected = 0;
        try {
            PreparedStatement pstmt = (PreparedStatement) connection.prepareStatement(query);

            pstmt.setString(1, areaTypeBean.getArea_name());
            pstmt.setString(2, areaTypeBean.getRemark());
            pstmt.setInt(3, areaTypeBean.getWard_id());
            pstmt.setString(4, areaTypeBean.getActive());
            pstmt.setInt(5, areaTypeBean.getArea_id());
            rowsAffected = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("AreaModel updateRecord() Error: " + e);
        }
        if (rowsAffected > 0) {
            message = "Record updated successfully......";
            msgBgColor = COLOR_OK;
        } else {
            message = "Cannot update the record, some error......";
            msgBgColor = COLOR_ERROR;
        }
        return rowsAffected;
    }
 public int deleteRecord(int area_id) {
        String query = " DELETE FROM area WHERE area_id = " + area_id;
        int rowsAffected = 0;
        try {
            rowsAffected = connection.prepareStatement(query).executeUpdate();
        } catch (Exception e) {
            System.out.println("AreaModel deleteRecord() Error: " + e);
        }
        if (rowsAffected > 0) {
            message = "Record deleted successfully......";
            msgBgColor = COLOR_OK;
        } else {
            message = "Cannot delete the record, some error.";
            msgBgColor = COLOR_ERROR;
        }
        return rowsAffected;
    }
 public void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println("Error inside closeConnection TrafficTypeModel:" + e);
        }
    }

    public String getMessage() {
        return message;
    }

    public String getMsgBgColor() {
        return msgBgColor;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public String getDb_password() {
        return db_password;
    }

    public void setDb_password(String db_password) {
        this.db_password = db_password;
    }

    public String getDb_username() {
        return db_username;
    }

    public void setDb_username(String db_username) {
        this.db_username = db_username;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }
}
