/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.healthDepartment.location.model;
import com.healthDepartment.location.tableClasses.ZoneNewBean;
import com.healthDepartment.util.KrutiDevToUnicodeConverter;
import com.healthDepartment.util.UnicodeToKrutiDevConverter;
import java.io.ByteArrayOutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
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
 * @author Administrator
 */
public class ZoneNewModel {
 private static Connection connection;
    private String driver,url,user,password;
    private static String message,messageBGColor;
    private KrutiDevToUnicodeConverter krutiToUnicode = new KrutiDevToUnicodeConverter();
    private UnicodeToKrutiDevConverter unicodeToKruti = new UnicodeToKrutiDevConverter();

     public byte[] generateMapReport(String jrxmlFilePath,List<ZoneNewBean> listAll) {
        byte[] reportInbytes = null;
         Connection c;
         HashMap mymap = new HashMap();
        try {

            JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(listAll);
            JasperReport compiledReport = JasperCompileManager.compileReport(jrxmlFilePath);
            reportInbytes = JasperRunManager.runReportToPdf(compiledReport, null , beanColDataSource );
        } catch (Exception e) {
            System.out.println("Error: in ZoneModel generateMapReport() JRException: " + e);
        }
        return reportInbytes;
    }
      public ByteArrayOutputStream generateZoneXlsRecordList(String jrxmlFilePath,List list) {
                ByteArrayOutputStream bytArray = new ByteArrayOutputStream();
              //  HashMap mymap = new HashMap();
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
    public List<ZoneNewBean> showAllData(String searchZone)
    {
        searchZone = krutiToUnicode.convert_to_unicode(searchZone);
        ArrayList<ZoneNewBean> list = new ArrayList<ZoneNewBean>();
        String query=  " SELECT z.zone_id,z.zone_name,z.description,z.zone_no "
         + "  FROM zone as z where "
       + " IF('"+searchZone +"'='',z.zone_name LIKE '%%',z.zone_name=?) order by zone_name" ;
      // + " IF('"+searchZone_no +"'='',z.zone_no LIKE '%%',z.zone_no=?) ";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
               pstmt.setString(1, searchZone);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                 ZoneNewBean zoneBean = new ZoneNewBean();
                zoneBean.setZoneId(rset.getInt(1));
                zoneBean.setZoneName(unicodeToKruti.Convert_to_Kritidev_010(rset.getString(2)));
                zoneBean.setZoneDescription(unicodeToKruti.Convert_to_Kritidev_010(rset.getString(3)));
                zoneBean.setZone_no(rset.getString(4));
                list.add(zoneBean);
            }
        } catch (Exception e) {
            System.out.println("Error in ShowAllData --- ZoneModel : " + e);
        }

        return list;
    }
    public List<String> getZone(String q) {
        List<String> list = new ArrayList<String>();
        String query = " SELECT zone_id, zone_name FROM zone GROUP BY zone_name ORDER BY zone_name ";
        try {
            ResultSet rset = connection.prepareStatement(query).executeQuery();
            int count = 0;
            q = q.trim();
            while (rset.next()) {    // move cursor from BOR to valid record.
                String zone_type = unicodeToKruti.Convert_to_Kritidev_010(rset.getString("zone_name"));
                if (zone_type.toUpperCase().startsWith(q.toUpperCase())) {
                    list.add(zone_type);
                    count++;
                }
            }
            if (count == 0) {
                list.add("No such Zone exists.......");
            }
        } catch (Exception e) {
            System.out.println("getZone ERROR inside ZoneModel - " + e);
        }
        return list;
    }
    public List<String> getCity(String q) {
        List<String> list = new ArrayList<String>();
        String query = " SELECT city_name FROM city GROUP BY city_name ORDER BY city_name ";
        try {
            ResultSet rset = connection.prepareStatement(query).executeQuery();
            int count = 0;

            while (rset.next()) {    // move cursor from BOR to valid record.
                String zone_type = (rset.getString("city_name"));
              
                    list.add(zone_type);
                    count++;
        
            }
            if (count == 0) {
                list.add("No such City exists.......");
            }
        } catch (Exception e) {
            System.out.println("getCity ERROR inside ZoneModel - " + e);
        }
        return list;
    }
    public List<String> getZoneName(String q,String cityName) {
        List<String> list = new ArrayList<String>();
        String query = " SELECT zone_name FROM zone where zone.city_id=(select country.city_id from country where country.city_name='"+cityName+"') GROUP BY zone_name ORDER BY  zone_name ";
        try {
            ResultSet rset = connection.prepareStatement(query).executeQuery();
            int count = 0;
            q = q.trim();
            while (rset.next()) {    // move cursor from BOR to valid record.
                String zone_type = rset.getString("zone_name");
                if (zone_type.toUpperCase().startsWith(q.toUpperCase())) {
                    list.add(zone_type);
                    count++;
                }
            }
            if (count == 0) {
                list.add("No such Zone exists.......");
            }
        } catch (Exception e) {
            System.out.println("getZoneName ERROR inside ZoneModel - " + e);
        }
        return list;
    }
      public void updateRecord(String countryName,String zoneId,String zoneName,String zoneDescription)
    {
        PreparedStatement presta=null;
        try
        {
            String query = "update zone set zone_name='"+zoneName.trim()+"',zone_description='"+zoneDescription.trim()+"',"
                    +"country_id=(select country_id from country where '"+countryName.trim()+"'=country.country_name)"
                    +"where zone_id="+Integer.parseInt(zoneId);
            presta = connection.prepareStatement(query);
            int i = presta.executeUpdate();
            if(i>0)
            {
                message=i+" Record updated successfully......";
                messageBGColor="yellow";
            }
            else
            {
                message="Record not updated successfully......";
                messageBGColor="red";
            }
        }catch(Exception e)
        {
            System.out.println("Error in updateRecord ---- ZoneModel : "+e);
        }
    }  
    public void deleteRecord(int zoneId)
    {
        PreparedStatement presta=null;
        try
        {
            presta = connection.prepareStatement("delete from zone where zone_id=?");
            presta.setInt(1, zoneId);
            int i = presta.executeUpdate();
            if(i>0)
            {
                message="Record deleted successfully......";
                messageBGColor="yellow";
            }
            else
            {
                message="Record not deleted successfully......";
                messageBGColor="red";
            }
        }catch(Exception e)
        {
            message="Record not deleted due to dependency";
            messageBGColor="red";
            System.out.println("Error in deleteRecord ---- ZoneModel - "+e);
        }
    }
    public ArrayList<ZoneNewBean> getAllRecords(int lowerLimit,int noOfRowsToDisplay,String searchZone)
    {
        ArrayList<ZoneNewBean> list = new ArrayList<ZoneNewBean>();
        searchZone = krutiToUnicode.convert_to_unicode(searchZone);
        String query = "SELECT z.zone_id,z.zone_name,z.description,z.zone_no,c.city_name FROM zone as z , city as c where z.city_id = c.city_id "
//        + "  IF('"+searchZone +"'='',z.zone_name LIKE '%%',z.zone_name=?)   "
        // + "  IF('"+searchZone_no +"'='',z.zone_no LIKE '%%',z.zone_no=?) order by z.zone_no  "
                +" limit "+ lowerLimit +","+ noOfRowsToDisplay ;

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
          //  pstmt.setString(1, searchZone);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                ZoneNewBean zoneBean = new ZoneNewBean();
                zoneBean.setZoneId(rset.getInt(1));
                zoneBean.setZoneName((rset.getString(2)));
                zoneBean.setZoneDescription((rset.getString(3)));
                zoneBean.setZone_no(rset.getString(4));
                zoneBean.setCityName(rset.getString(5));
                list.add(zoneBean);
            }
        } catch (Exception e) {
            System.out.println("Error in getAllRecrod in ZoneModel " + e);
        }
        return list;
    }


    public int getTotalRowsInTable(String searchZone)
    {
        searchZone = krutiToUnicode.convert_to_unicode(searchZone);
         String query = " SELECT Count(*) FROM zone as z where "
         + " IF('"+searchZone +"'='',z.zone_name LIKE '%%',z.zone_name=?) order by zone_name" ;
         // + " IF('"+searchZone_no +"'='',z.zone_no LIKE '%%',z.zone_no=?)" ;
        int noOfRows = 0;
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, searchZone);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            noOfRows = Integer.parseInt(rs.getString(1));
        } catch (Exception e) {
            System.out.println("Error inside getNoOfRows ZoneModel" + e);
        }
        System.out.println("No of Rows in Table for search is****....." + noOfRows);
        return noOfRows;
    }

    public static int getCityId(String city_name){
        int city_id=0;
        String query = "select city_id from city where city_name= '"+city_name+"' ";
        try {
            ResultSet rset = connection.prepareStatement(query).executeQuery();
            if (rset.next()) {
               city_id= rset.getInt("city_id");

            }
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex);
        }
        return city_id;
    }
    

//    public void insertNewRecord(String[] zone_id,String[] zoneName,String[] zoneDescription,String[] zone_no)
//    {
//       int rowAffected=0;
//       int rowNotAffected=0;
//       message="";
//       for(int i=0;i<zone_id.length;i++)
//       {
//            if(zone_id[i].equals("1"))
//            {
//                PreparedStatement presta;
//                try
//                {
//                 
//                    zoneName[i] = zoneName[i];
//                    zoneDescription[i] = zoneDescription[i];
//                   // String query1 = "select zone from zone where zone_name='"+zoneName[i].trim()+"'";
//                    String query2 = "insert into zone(zone_name,description,zone_no,remark) "
//                            +"values('"+zoneName[i].trim()+"','"+zoneDescription[i].trim()+"','"+zone_no[i].trim()+"')";
////                    presta = connection.prepareStatement(query1);
////                    ResultSet result = presta.executeQuery();
////                    if(result.next())
////                        rowNotAffected++;
////                    else
////                    {
//                     PreparedStatement pstmt = connection.prepareStatement(query2);
//
//                        rowAffected = pstmt.executeUpdate();
////                        presta = connection.prepareStatement(query2);
////                        presta.executeUpdate();
////                        rowAffected++;
//                    //}
//                }catch(Exception e)
//                {
//                    System.out.println("Error in addAllRecords inside ZoneModel---- "+e);
//                }
//
//            }
//       }
//         if(rowAffected > 0)
//         {
//           message=" Record inserted... ";
//           messageBGColor="yellow";
//         }
//        if(rowAffected == 0)
//        {
//            message = message+"("+rowAffected+" : Record not inserted some error!)";
//            messageBGColor="red";
//        }
//    }


      public static void insertNewRecord(ZoneNewBean zone)
      {
   String query = "INSERT INTO zone(zone_name,description,zone_no, city_id,revision_no,active ) VALUES(?,?,?,?,?,?)";
        int rowsAffected = 0;
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
//            pstmt.setString(1, krutiToUnicode.convert_to_unicode(designation.getDesignation()));
//            pstmt.setString(2, krutiToUnicode.convert_to_unicode(designation.getDescription()));
            pstmt.setString(1, zone.getZoneName());
            pstmt.setString(2, zone.getZoneDescription());
            pstmt.setString(3, zone.getZone_no());
            pstmt.setInt(4, zone.getCityId());
            pstmt.setInt(5, zone.getRevision_no());
            pstmt.setString(6,"Y");
            rowsAffected = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Model Error: " + e);
        }
        if (rowsAffected > 0) {
            message = "Record saved successfully.";

        } else {
            message = "Cannot save the record, some error.";

        }
        
    }

    

    public void closeConnection()
    {
        try
        {
            connection.close();
        }catch(Exception e)
        {
            System.out.println("ZoneModel closeConnection: "+e);
        }
    }
    public void setConnection()
    {
     try
     {
        Class.forName(driver);
        connection = DriverManager.getConnection(url + "?useUnicode=true&characterEncoding=UTF-8&character_set_results=utf8",user,password);
     }catch(Exception e)
     {
        System.out.println("ZoneModel setConnection error: "+e);
     }
    }
    public Connection getConnection()
    {
        return connection;
    }
    public void setDriver(String driver)
    {
        this.driver = driver;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }
    public void setUser(String user)
    {
        this.user = user;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public String getDriver()
    {
        return driver;
    }

    public String getUrl()
    {
        return url;
    }
    public String getUser()
    {
        return user;
    }
    public String getPassword()
    {
        return password;
    }
    public String getMessage()
    {
        return message;
    }
    public String getMessageBGColor()
    {
        return messageBGColor;
    }

}
