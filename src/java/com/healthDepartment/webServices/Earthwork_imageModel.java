/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.healthDepartment.webServices;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.ws.rs.core.Response;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;




/**
 *
 * @author Administrator
 */

public class Earthwork_imageModel {
    private Connection connection;
    private String message;
    private String msgBgColor;
    private final String COLOR_OK = "yellow";
    private final String COLOR_ERROR = "red";


 public  int isExits(String mobile_no)
    {
int count=0;
//        String query="SELECT count(key_person_id) FROM key_person as k,designation as d where k.designation_id=d.designation_id "
//                     + " and k.designation_id NOT IN(1,2,3) and k.mobile_no1='"+mobile_no+"' ";

               String query="SELECT count(key_person_id) FROM key_person as k,designation as d where k.designation_id=d.designation_id "
                     + " and k.mobile_no1='"+mobile_no+"' ";


        try{
           PreparedStatement ps=(PreparedStatement) connection.prepareStatement(query);
           ResultSet rset = ps.executeQuery();
          while (rset.next()) {
          count = rset.getInt(1);
           }
         }
        catch(Exception e)
        {
          System.out.println(e);
        }

   return   count;
 }
 public int insertReceiptRecord(int receipt_book_no, int receipt_revision_no, int page_no, double amount, int tp_id) {
        int rowsAffected = 0;
        String query = "INSERT INTO receipt_book (receipt_book_no, page_no, book_revision, amount)"
                + " VALUES(?, ?, ?, ?)";
        String insert_query = "INSERT INTO trafficpolice_receipt_map(traffic_police_id, receipt_book_id)"
                + "VALUES(?, ?)";
        try {
            //connection.setAutoCommit(false);
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, receipt_book_no);
            pstmt.setInt(2, page_no);
            pstmt.setInt(3, receipt_revision_no);
            pstmt.setDouble(4, amount);

            rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                int key = 0;
                if(rs.next())
                    key = rs.getInt(1);
                int receiptBookId = getReceiptBookId();
                PreparedStatement pstmt1 = connection.prepareStatement(insert_query);
                pstmt1.setInt(1,tp_id);
                //pstmt1.setInt(2, receiptBookId);
                pstmt1.setInt(2, key);
                rowsAffected = pstmt1.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Error: Receipt inserting: " + e);
        }        
        return rowsAffected;
    }

 public int getReceiptBookId() {
        int receipt_book_id = 0;
        try {
            String query = "SELECT MAX(receipt_book_id) as rb_id from receipt_book ";
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            if (rset.next())
                receipt_book_id = rset.getInt("rb_id");
        } catch (Exception e) {
            System.out.println("Error: in getReceiptBookId of ReceiptBookModel" + e);
        }
        return receipt_book_id;
    }
 
 public JSONArray getCityRecords()
        {
        JSONArray rowData = new JSONArray();
        String query = null;
        query = "select city_id,city_name,pin_code,std_code,district_id,city_description,division_id "
                +" from city c where c.active='Y'";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                JSONObject obj = new JSONObject();
                 obj.put("city_id",rset.getInt("city_id"));
                 obj.put("city_name",rset.getString("city_name"));
                 obj.put("pin_code",rset.getString("pin_code"));

                 obj.put("std_code",rset.getString("std_code"));
                 obj.put("district_id",rset.getString("district_id"));
                 obj.put("city_description",rset.getString("city_description"));
                 obj.put("division_id",rset.getString("division_id"));
                 rowData.add(obj);
           }
        } catch (Exception e) {
            System.out.println("Error inside show data of survey: " + e);
        }
        return rowData;
    }

 public String getCityLocationId(String latitude,String longitude)
        {
           String final_city_id=""; 
        JSONArray rowData = new JSONArray();
        String query = null;
        HashMap h = new HashMap();
        Double diff=0.0;
        int count=0;
        query = "select city_location_id,latitude,longitude\n" +
                "from city_location cl;";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                
                boolean b=false;
                int city_id = rset.getInt("city_location_id");
                String latitude1 = rset.getString("latitude");
                String longitude1 = rset.getString("longitude");
                if(Double.parseDouble(latitude1) > 0.0 && Double.parseDouble(longitude1) > 0.0)
                {
                    b=true;
                    count++;
//                diff=getDifference(latitude1,longitude1,latitude,longitude);
                  diff=distanceTo(latitude1,longitude1,latitude,longitude);
                
                }
                if(b){
                if(count==1){
                    h.put("city_id",city_id);
                    h.put("diff",diff);
                }if(count > 1){
                    Double d = Double.parseDouble(h.get("diff").toString());
                    if(diff < d){
                        h.clear();
                        h.put("city_id",city_id);
                        h.put("diff",diff);     
                    }   
                }  
                }
           }
            final_city_id = h.get("city_id").toString();
            
        } catch (Exception e) {
            System.out.println("Error inside show data of survey: " + e);
        }
        return final_city_id;
    }
 public double distanceTo(String latitude1,String longitude1,String latitude,String longitude) {
        Double db_latitude = Double.parseDouble(latitude1);
        Double db_longitude = Double.parseDouble(longitude1);
        
        Double user_latitude = Double.parseDouble(latitude);
        Double user_longitude = Double.parseDouble(longitude);
     
        double STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;
        double lat1 = Math.toRadians(db_latitude);
        double lon1 = Math.toRadians(db_longitude);
        double lat2 = Math.toRadians(user_latitude);
        double lon2 = Math.toRadians(user_longitude);

        // great circle distance in radians, using law of cosines formula
        double angle = Math.acos(Math.sin(lat1) * Math.sin(lat2)
                               + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));

        // each degree on a great circle of Earth is 60 nautical miles
        double nauticalMiles = 60 * Math.toDegrees(angle);
        double statuteMiles = STATUTE_MILES_PER_NAUTICAL_MILE * nauticalMiles;
        return statuteMiles;
    }
//    public double getDifference(String latitude1,String longitude1,String latitude,String longitude){
//        Double db_latitude = Double.parseDouble(latitude1);
//        Double db_longitude = Double.parseDouble(longitude1);
//        Double user_latitude = Double.parseDouble(latitude);
//        Double user_longitude = Double.parseDouble(longitude);
//        Double lat_diff=db_latitude-user_latitude;
//        Double long_diff=db_longitude-user_longitude;
//        Double diff=Math.abs((lat_diff+long_diff)/2.0);
//        return diff;
//    }
 
 
 public List DetailList(String registrationNo)throws Exception{
        org.json.JSONObject obj = new org.json.JSONObject();
        org.json.JSONObject obj1 = new org.json.JSONObject();
         JSONArray rowData = new JSONArray();
        Response res = null;
        
//        String details = "RegisterationNumber=" +RegisterationNumber+ "RegisterationDate=" +RegisterationDate+ 
        Earthwork_imageModel vehicleDriverWebServiceModel = new Earthwork_imageModel();
        try{
              String path="oltp.mptransport.org/DataForPoliceDept/api/data/GetByRegistrationNo?appType=RC&RegistrationNumber=" + registrationNo + "&SecurityKey=POLICE$RC2";
//            JSONObject jsonObject = new JSONObject();
//          jsonObject.put("appType", "RC");
//          jsonObject.put("RegistrationNumber", "MP20N6269");
//          jsonObject.put("SecurityKey", "POLICE$RC2");                 
         URL url = new URL("http://"+path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
      connection.setRequestProperty("Content-Type", "application/json");
      connection.setRequestMethod("GET");
     // OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
      //DataOutputStream out = new DataOutputStream(connection.getOutputStream());
    //  out.write(jsonObject.toString());
      //out.close();
      
      // start for reading json data          
                 BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
                 String decodeString = reader.readLine();
                // JSONParser parser = new JSONParser();
              //  JSONObject jobj1 = (JSONObject) parser.parse(decodeString);
                 org.json.JSONArray jsonArr = new org.json.JSONArray(decodeString);
            
                 for (int i = 0; i < jsonArr.length(); i++)
                 {
                  //JSONObject jsonObj = jsonArr.getJSONObject(i);
                     obj = jsonArr.getJSONObject(i);
                     //String RegNo = obj.getString("RegisterationNumber");
                    // String RegDate = obj.getString("RegisterationDate");
                     String OwnerName = obj.getString("OwnerName");
                    // String IssueDate = obj.getString("IssueDate");
                     // String OwnerRelative = obj.getString("OwnerRelativeName");
                     String PermanentAddress = obj.getString("PermanentAddress");
                      String TemporaryAddress = obj.getString("TemporaryAddress");
                    
                    // String Fathername=obj.getString("FatherName");
                  
//                     obj1.put("OwnerName",latitude);
                       rowData.add(obj);
//                   System.out.println(latitude);
                 }
        }catch(Exception ex){
            System.out.println("ERROR : in vehicleDetail() in RideWebservices : " + ex);
        }
       return rowData;
       
    }
 
 
 
 
 
 
 
 
 public JSONArray getZone_newRecords()
        {
        JSONArray rowData = new JSONArray();
        String query = null;
        query = "select zone_new_id,zone,city_id,description,zone_no "
                +" from zone_new zn ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                JSONObject obj = new JSONObject();
                 obj.put("zone_new_id",rset.getInt("zone_new_id"));
                 obj.put("zone",rset.getString("zone"));
                 obj.put("city_id",rset.getString("city_id"));

                 obj.put("description",rset.getString("description"));
                 obj.put("zone_no",rset.getString("zone_no"));
                 
                 rowData.add(obj);
           }
        } catch (Exception e) {
            System.out.println("Error inside show data of survey: " + e);
        }
        return rowData;
    }//getWardRecords

 public JSONArray getWardRecords()
        {
        JSONArray rowData = new JSONArray();
        String query = null;
        query = "select ward_id,ward_name,description,zone_new_id,ward_no "
               +" from ward  ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                JSONObject obj = new JSONObject();
                 obj.put("ward_id",rset.getInt("ward_id"));
                 obj.put("ward_name",rset.getString("ward_name"));
                 obj.put("description",rset.getString("description"));

                 obj.put("zone_new_id",rset.getString("zone_new_id"));
                 obj.put("ward_no",rset.getString("ward_no"));

                 rowData.add(obj);
           }
        } catch (Exception e) {
            System.out.println("Error inside show data of survey: " + e);
        }
        return rowData;
    }//getAreaRecords

 public JSONArray getAreaRecords()
        {
        JSONArray rowData = new JSONArray();
        String query = null;
        query = "select area_id,area_name,description,ward_id,area_no "
                +" from area a ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                JSONObject obj = new JSONObject();
                 obj.put("area_id",rset.getInt("area_id"));
                 obj.put("area_name",rset.getString("area_name"));
                 obj.put("description",rset.getString("description"));

                 obj.put("ward_id",rset.getString("ward_id"));
                 obj.put("area_no",rset.getString("area_no"));

                 rowData.add(obj);
           }
        } catch (Exception e) {
            System.out.println("Error inside show data of survey: " + e);
        }
        return rowData;
    }//getCity_locationRecords

 public JSONArray getCity_locationRecords()
        {
        JSONArray rowData = new JSONArray();
        String query = null;
        query = "select city_location_id,location,zone_new_id,location_no,latitude,longitude,area_id "
                +" from city_location cl ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                JSONObject obj = new JSONObject();
                 obj.put("city_location_id",rset.getInt("city_location_id"));
                 obj.put("location",rset.getString("location"));
                 obj.put("zone_new_id",rset.getString("zone_new_id"));

                 obj.put("location_no",rset.getString("location_no"));
                 obj.put("latitude",rset.getString("latitude"));
                 obj.put("longitude",rset.getString("longitude"));
                 obj.put("area_id",rset.getString("area_id"));

                 rowData.add(obj);
           }
        } catch (Exception e) {
            System.out.println("Error inside show data of survey: " + e);
        }
        return rowData;
    }


 
 public int insertImageCount(int general_image_detail_id,String imagePath,String[] fileNameArray,int size){
//        DateFormat dateFormat = new SimpleDateFormat("dd.MMMMM.yyyy/ hh:mm:ss aaa");
//        Date date = new Date();
//        String current_date = dateFormat.format(date);
        int rowsAffected = 0;
        String imageQuery = "insert into image_count(general_image_detail_id,image_name,image_path) values(?,?,?);";
        try{
            for(int i=0;i<size;i++){
                String imagePath1 = imagePath.replaceAll("/","\\\\");
            PreparedStatement pstmt = connection.prepareStatement(imageQuery);
            pstmt.setInt(1,general_image_detail_id);
            pstmt.setString(2,fileNameArray[i]);
            pstmt.setString(3,imagePath1);

            rowsAffected = pstmt.executeUpdate();

            pstmt.close();
            }
        } catch (Exception e) {
            System.out.println("Error:keypersonModel--insertRecord-- " + e);
        }
        return rowsAffected;
    }

 public int insertTempVehicleImage(int general_image_detail_id,String imagePath,String[] fileNameArray,int size,String city_location_id,String latitude,String longitude){
//        DateFormat dateFormat = new SimpleDateFormat("dd.MMMMM.yyyy/ hh:mm:ss aaa");
//        Date date = new Date();
//        String current_date = dateFormat.format(date);
        int rowsAffected = 0;
        String imageQuery = "insert into temp_vehicle_image(general_image_details_id,image_path,image_name,city_location_id,lattitude,longitude,date,time) values(?,?,?,?,?,?,?,?)";
        try{
            for(int i=0;i<size;i++){
                String date="";
                String data2 = "";
                String lat ="";
                String logn ="";
                String finalDate="";
                String finalTime="";
                String imagePath1 = imagePath.replaceAll("/","\\\\");
            PreparedStatement pstmt = connection.prepareStatement(imageQuery);
            pstmt.setInt(1,general_image_detail_id);
            pstmt.setString(2,imagePath1);
            pstmt.setString(3,fileNameArray[i]);
            try{
            String total_image_Path = fileNameArray[i]+"";
            String totalImagePathArray[] =  total_image_Path.split("_");
            String data1 = totalImagePathArray[1];//lat,long,date
             data2 = totalImagePathArray[2];//time
            String splitData[] = data1.split(",");
             lat = splitData[0];
             logn = splitData[1];
             date = splitData[2];
             
             
             String rawDate = date;
             String year = rawDate.substring(0,4);
             String month = rawDate.substring(4,6);
             String date1 = rawDate.substring(6); 
             finalDate = date1+"-"+month+"-"+year;
             
             String rawTime = data2;
             String hours = rawTime.substring(0,2);
             String minutes = rawTime.substring(2,4);
             finalTime = hours+":"+minutes;
             
             
             
            }catch(Exception e){
                System.out.println(e);
            }
            
            pstmt.setString(4,city_location_id);
            pstmt.setString(5,lat);
            pstmt.setString(6,logn);
            pstmt.setString(7,finalDate);
            pstmt.setString(8,finalTime);

            rowsAffected = pstmt.executeUpdate();

            pstmt.close();
            }
        } catch (Exception e) {
            System.out.println("Error:keypersonModel--insertRecord-- " + e);
        }
        return rowsAffected;
    }

public String getDestinationPath(String image_uploaded_for){
     String destination_path = "";
     String query = " SELECT destination_path FROM image_destination id, image_uploaded_for  iuf "
             + " WHERE id.image_uploaded_for_id = iuf.image_uploaded_for_id "
             + " AND iuf.image_uploaded_for = '" + image_uploaded_for + "' ";//traffic_police
     try{
         ResultSet rs = connection.prepareStatement(query).executeQuery();
         if(rs.next())
             destination_path = rs.getString("destination_path");
     }catch(Exception ex){
         System.out.println("ERROR: in getTrafficPoliceId in TraffiPoliceSearchModel : " + ex);
     }
     return destination_path;
 }


 public  int insertVehicleDetails(String auto_no,String vehicle_owner_name)
    {
int count=0;
int vehicle_detail_id =0;
        String query=" insert into vehicle_detail(vehicle_no,owner_name) values(?,?) ";

        try{
           PreparedStatement ps=(PreparedStatement) connection.prepareStatement(query);
           ps.setString(1, auto_no);
           ps.setString(2, vehicle_owner_name);

            count = ps.executeUpdate();
          if(count > 0) {
          System.out.println("Record inserted successfully");
          ResultSet rs = ps.getGeneratedKeys();
          if(rs.next()){
             vehicle_detail_id = rs.getInt(1);
          }

           }
           else{
               System.out.println("Record not inserted");
           }
         }
        catch(Exception e)
        {
          System.out.println(e);
        }

   return   vehicle_detail_id;
 }

 public  int insertLicenseDetail(String driver_license_no,String vehicle_driver_age)
    {
int count=0;
int license_detail_id=0;
        String query=" insert into license_detail(license_no,owner_age) values(?,?) ";

        try{
           PreparedStatement ps=(PreparedStatement) connection.prepareStatement(query);
           ps.setString(1, driver_license_no);
           ps.setString(2, vehicle_driver_age);

            count = ps.executeUpdate();
          if(count > 0) {
          System.out.println("Record inserted successfully");
          ResultSet rs = ps.getGeneratedKeys();
          if(rs.next()){
             license_detail_id = rs.getInt(1);
          }
           }
           else{
               System.out.println("Record not inserted");
           }
         }
        catch(Exception e)
        {
          System.out.println(e);
        }
          return   license_detail_id;
       }
          public  int vehicleLicenseMapping(int vehicle_detail_id,int license_detail_id)
         {
           int count=0;   
        String query="insert into vehicle_license_map(vehicle_detail_id,license_detail_id) values(?,?)";
        try{
           PreparedStatement ps=(PreparedStatement) connection.prepareStatement(query);
           ps.setInt(1, vehicle_detail_id);
           ps.setInt(2, license_detail_id);
            count = ps.executeUpdate();
          if(count > 0) {
          System.out.println("Record inserted successfully");
//          ResultSet rs = ps.getGeneratedKeys();
//          if(rs.next()){
//             license_detail_id = rs.getInt(1);
          //}
           }
           else{
               System.out.println("Record not inserted");
           }
         }
        catch(Exception e)
        {
          System.out.println(e);
        }
          return   count;
 }

          public  int getCityLocationId(String mobile_no)
         {
              int  city_location_id=0;
           int count=0;
           int key_person_id=0;
           int designation_id=0;
        String query=" select key_person_id,designation_id "
                     +" from key_person kp "
                     +" where kp.mobile_no1=? ";
        try{
           PreparedStatement ps=(PreparedStatement) connection.prepareStatement(query);
           ps.setString(1, mobile_no);
            ResultSet rs = ps.executeQuery();
          while(rs.next()) {
              key_person_id = rs.getInt("key_person_id");
              designation_id = rs.getInt("designation_id");
           }


            String query1 = "select cl.city_location_id "
                         +" from shift_key_person_map skpm,shift_designation_location_map sdlm, "
                         +" designation_location_type dlt,city_location cl,key_person kp "
                         +" where cl.city_location_id = dlt.city_location_id "
                         +" and dlt.designation_location_type_id = sdlm.designation_location_type_id "
                         +" and sdlm.map_id1 = skpm.map_id1 "
                         +" and sdlm.map_id2 = skpm.map_id2 "
                         +" and skpm.key_person_id = kp.key_person_id "
                         +" and kp.key_person_id=?";

            PreparedStatement ps1=(PreparedStatement) connection.prepareStatement(query1);
            ps1.setInt(1, key_person_id);
            ResultSet rs1 = ps1.executeQuery();
            while(rs1.next()) {
             city_location_id = rs1.getInt("city_location_id");

           }
         }
        catch(Exception e)
        {
          System.out.println(e);
        }
          return  city_location_id;
 }
          
          
           public  String getNewOffenceCode(String offenceCode,String vehicle_type,String vehicle_type1)
         {
           int count=0;   
           String offence_type="";
           String newOffenceCode="";
        String query="select offence_type\n" +
                     "from offence_type oft\n" +
                     "where offence_code='"+offenceCode+"'";
        
//        String query1="select offence_code\n" +
//                      "from offence_type oft,commercial_type ct\n" +
//                      "where oft.commercial_type_id = ct.commercial_type_id\n" +
//                      "and ct.commercial_type=?\n" +
//                      "and oft.offence_type=?";
        
        String query2="select offence_code\n" +
                      "from offence_type oft,commercial_type ct,vehicle_type vt\n" +
                      "where oft.commercial_type_id = ct.commercial_type_id\n" +
                      "and oft.vehicle_type_id=vt.vehicle_type_id\n" +
                      "and ct.commercial_type=? \n" +
                      "and oft.offence_type=? \n" +
                      "and vt.vehicle_type=?";
        try{
           PreparedStatement ps=(PreparedStatement) connection.prepareStatement(query);
           
            ResultSet rs = ps.executeQuery();
          if(rs.next()) {
              offence_type = rs.getString("offence_type");

           }
          
          if(offence_type.length()>0){
          PreparedStatement ps1=(PreparedStatement) connection.prepareStatement(query2);
              
              ps1.setString(1,vehicle_type);
              ps1.setString(2,offence_type);
              ps1.setString(3,vehicle_type1);
              ResultSet rs1 = ps1.executeQuery();
              if(rs1.next())
              {
                   newOffenceCode = rs1.getString("offence_code");
                  
              }
          }
          if(newOffenceCode.equals("")){
              String query3="select offence_code\n" +
                      "from offence_type oft,commercial_type ct,vehicle_type vt\n" +
                      "where oft.commercial_type_id = ct.commercial_type_id\n" +
                      "and oft.vehicle_type_id=vt.vehicle_type_id\n" +
                      "and ct.commercial_type=? \n" +
                      "and oft.offence_type=? \n" +
                      "and vt.vehicle_type=?";
              
              PreparedStatement ps1=(PreparedStatement) connection.prepareStatement(query3);
              vehicle_type1="All";
              ps1.setString(1,vehicle_type);
              ps1.setString(2,offence_type);
              ps1.setString(3,vehicle_type1);
              ResultSet rs1 = ps1.executeQuery();
              if(rs1.next())
              {
                   newOffenceCode = rs1.getString("offence_code");
                  
              }
              
              newOffenceCode=offenceCode;
          }
         }
        catch(Exception e)
        {
          System.out.println(e);
        }
          return   newOffenceCode;
 }
           
           
           public String  checksecondPenaltyAmount(String vehicle_no,String offence_code){
   
     JSONObject obj = new JSONObject();
        JSONArray arrayObj = new JSONArray();
        String data="";
        int traffic_offence_map_id = 0;
        String second_offence_penalty ="";
        
        List list = new ArrayList();

          String query="select traffic_police_id\n" +
                       "from traffic_police tp\n" +
                       "where tp.vehicle_no='"+vehicle_no+"'";
          
          String query1 = "select traffic_offence_map_id\n" +
                          "from traffic_offence_map t\n" +
                          "where traffic_police_id = ?\n" +
                          "and offence_type_id='"+offence_code+"'";
          
          String query2 = "select second_offence_penalty\n" +
                          "from offence_type oft\n" +
                          "where oft.offence_code = ?\n" +
                          "and oft.have_second_offence='YES'\n" +
                          "and oft.active='Y'";
          
        try{
            ResultSet rs = connection.prepareStatement(query).executeQuery();
            while(rs.next()){
                String traffic_police_id = rs.getString("traffic_police_id");
                list.add(traffic_police_id);
                
                }
            int count = list.size();
            if(count > 0){
                for(int i=0;i<count;i++){ 
                    PreparedStatement pst1 = connection.prepareStatement(query1);
                    pst1.setString(1,list.get(i).toString());
                    
                    ResultSet rs1 = pst1.executeQuery();
                    if(rs1.next()){
                        traffic_offence_map_id = rs1.getInt("traffic_offence_map_id");
                    }          
                }
            }
            if(traffic_offence_map_id > 0){
                
                PreparedStatement pst2 = connection.prepareStatement(query2);
                    pst2.setString(1,offence_code);
                    
                    ResultSet rs2 = pst2.executeQuery();
                    if(rs2.next()){
                        second_offence_penalty = rs2.getString("second_offence_penalty");
                    }
                
            }
            
        }catch(Exception e){
            System.out.println(e);
        }

       return second_offence_penalty;
    }
           
           
           public  String getNormalPenaltyAmount(String offenceCode)
         {
           int count=0;   
           String penalty_amount="";
           String newOffenceCode="";
        String query="select penalty_amount\n" +
                     "from offence_type oft\n" +
                     "where oft.offence_code ='"+offenceCode+"'";
        
        try{
           PreparedStatement ps=(PreparedStatement) connection.prepareStatement(query);
           
            ResultSet rs = ps.executeQuery();
          if(rs.next()) {
              penalty_amount = rs.getString("penalty_amount");

           }
         }
        catch(Exception e)
        {
          System.out.println(e);
        }
          return   penalty_amount;
 }
           
           
           
         public  String checkForSecondOffence(String auto_no,String offence_code)
         {
           List list = new ArrayList();
           String status="";
           int traffic_offence_map_id=0;
           String have_second_offence="";
           
           String query0="select have_second_offence\n" +
                         "from offence_type oft\n" +
                         "where offence_code='"+offence_code+"'";
           
           
        String query="select traffic_police_id\n" +
                       "from traffic_police tp\n" +
                       "where tp.vehicle_no='"+auto_no+"'";
          
          String query1 = "select traffic_offence_map_id\n" +
                          "from traffic_offence_map t\n" +
                          "where traffic_police_id = ?\n" +
                          "and offence_type_id='"+offence_code+"'";
          
          
          try{
              ResultSet rs = connection.prepareStatement(query0).executeQuery();
            while(rs.next()){
                 have_second_offence = rs.getString("have_second_offence");
                
                
                }
              
          }catch(Exception e){
              System.out.println(e);
          }
          
          if(have_second_offence.equals("YES")){
        
        try{
           ResultSet rs = connection.prepareStatement(query).executeQuery();
            while(rs.next()){
                String traffic_police_id = rs.getString("traffic_police_id");
                list.add(traffic_police_id);
                
                }
            int count = list.size();
            if(count > 0){
                for(int i=0;i<count;i++){ 
                    PreparedStatement pst1 = connection.prepareStatement(query1);
                    pst1.setString(1,list.get(i).toString());
                    
                    ResultSet rs1 = pst1.executeQuery();
                    if(rs1.next()){
                        traffic_offence_map_id = rs1.getInt("traffic_offence_map_id");
                    }          
                }
            }
            if(traffic_offence_map_id>0){
                status="YES";
            }else{
                status="NO";
            }
         }
        catch(Exception e)
        {
          System.out.println(e);
        }
          return   status;
          }else{
              
              status="NO";
              return status;
          }
 }
           public  int getVehicleTYpeId(String vehicle_type1)
         {
           int vehicle_type_id=0;    
           String newOffenceCode="";
        String query="select vehicle_type_id\n" +
                     "from vehicle_type vt\n" +
                     "where vehicle_type='"+vehicle_type1+"'";
        try{
           PreparedStatement ps=(PreparedStatement) connection.prepareStatement(query);  
            ResultSet rs = ps.executeQuery();
          if(rs.next()) {
              vehicle_type_id = rs.getInt("vehicle_type_id");
           }
         }
        catch(Exception e)
        {
          System.out.println(e);
        }
          return   vehicle_type_id;
 }
           
           

//////////////////////////////////////////////////////////////////////////////////////////////////////////////
          public  int insertTrafficPoliceRecords(String mobile_no,String officier_mobile_no,String auto_no,
                  String driver_license_no,String date_time,String offender_age,
                  String vehicle_driver_gender,int general_image_detail_id,String latitude,
                  String longitude,String vehicle_type,String vehicle_type1,String city_location_id, org.json.JSONArray jsonArray,String challan_type,String offender_address,String Permanentaddress,String OwnerName,String OwnerRelative)
         {
             ///////////////////////////////////////////////////////////////////////////////////////////////////
//             Set set = new HashSet();
//             List list = new ArrayList();
//             String new_vihicle_type[] = vehicle_type1.split(",");
//             for(int i=0;i<new_vihicle_type.length;i++){
//                 String s[] = new_vihicle_type[i].split("_");
//                 set.add(s[1]);
//             }
//             Iterator itr = set.iterator();
//             while(itr.hasNext()){
//             list.add(itr.next()); 
//                     }
//             Collections.sort(list);
//             if(list.get(0) == "All"){
//                 
//             }
             ///////////////////////////////////////////////////////////////////////////////////////////////////////////
             int vehicle_type_id = getVehicleTYpeId(vehicle_type1);
             List offenceList = new ArrayList();
             double totalDeposit = 0.0;
             try{
             int length =jsonArray.length();
             for(int i=0;i<length;i++){
       
                 String offence_code = getNewOffenceCode(jsonArray.get(i)+"",vehicle_type,vehicle_type1);
                 offenceList.add(offence_code);
                 
             }
             
             //calculate deposit amount
//             double totalDeposit = 0.0;
             for(int i=0;i<offenceList.size();i++){
                 try{
             String depositAmount = checksecondPenaltyAmount(auto_no,offenceList.get(i)+"");
             
             if(depositAmount.length()>0){
                 totalDeposit = totalDeposit+Double.parseDouble(depositAmount);  
             }
             else{
                 String penalty_amount=getNormalPenaltyAmount(offenceList.get(i)+"");
                 if(penalty_amount.equals("")){
                     penalty_amount="0000";
                 }
                 totalDeposit = totalDeposit + Double.parseDouble(penalty_amount);
                 
             }
                 }
                 catch(Exception e){  
                  System.out.println(e);
                 }
             
             }
             }//try end
             catch(Exception e){
                 System.out.println(e);
             }
             
             
             
             
             
             
           int count=0;
             int id=getKeyPersonId(officier_mobile_no);
           int traffic_police_id=0;
           int rowAffected=0;
        String query="  insert into traffic_police(vehicle_no,offender_license_no, "
                + " offence_date,city_location_id,offender_age,offender_mobile_no,gender, "
                + " is_from_mobile,lattitude,longitude,general_image_details_id,deposited_amount,offender_name,vehicle_type_id,key_person_id,offender_address,father_name) "
                +" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";

        String mapQuery = "insert into traffic_offence_map(traffic_police_id,offence_type_id,is_second_offence) values(?,?,?)";
        try{
           PreparedStatement ps=(PreparedStatement) connection.prepareStatement(query);

            ps.setString(1,auto_no );
             ps.setString(2, driver_license_no);
             
             ps.setString(3,date_time);
//              ps.setInt(4,getCityLocationId(officier_mobile_no));
              ps.setInt(4,Integer.parseInt(city_location_id));
              ps.setString(5,offender_age);
              ps.setString(6,mobile_no);

              ps.setString(7,vehicle_driver_gender);
              ps.setString(8,"Y");
               ps.setString(9,latitude);
                ps.setString(10,longitude);
                ps.setInt(11,general_image_detail_id);
                ps.setString(12,totalDeposit+"");
                ps.setString(13,OwnerName);
                ps.setInt(14,vehicle_type_id);
            
               ps.setInt(15,id);
                   ps.setString(16,Permanentaddress);
                   ps.setString(17,OwnerRelative);
                
              //  ps.setString(18,challan_type);
                
 
            count = ps.executeUpdate();
             if(count > 0) {
             System.out.println("Record inserted successfully");
             ResultSet rs = ps.getGeneratedKeys();
             if(rs.next()){
                 traffic_police_id = rs.getInt(1);
                 }

                  int length =offenceList.size();
                   for(int i = 0; i< length ; i++){
                       ///////////////////////check for second offence
                       String status = checkForSecondOffence(auto_no,offenceList.get(i)+"");
                       int offence_type_id = Integer.parseInt(offenceList.get(i)+"");
                       try{
                       ps=(PreparedStatement) connection.prepareStatement(mapQuery);
                       ps.setInt(1, traffic_police_id);
                       ps.setInt(2, offence_type_id);
                       if(status.equals("YES")){
                           ps.setString(3,status );
                       }else{
                           ps.setString(3,status );
                       }
                       rowAffected = ps.executeUpdate();
                       }catch(Exception e){
                           System.out.println(e);
                       }


               }
                   
                   
                   
                   
                   ///////////////////////////send sms/////////////////
                   if(rowAffected > 0){
                   //sendOTP(tubeWellSurveyBean,offenceDateTime,mobile_no,total_offence_code,traffic_police_id);
                   sendOTP(auto_no,date_time,traffic_police_id,totalDeposit,mobile_no,offenceList);

                   }
           }
           else{
               System.out.println("Record not inserted");
           }
         }
        catch(Exception e)
        {
          System.out.println(e);
        }
          return   traffic_police_id;
 }
          
          public void sendOTP(String auto_no,String date_time,int traffic_police_id,Double total_deposit,String mobile_no,List offence_list) throws Exception {
        String otp = "";
        
        //String offence_code_array[] = total_offence_code.split(" ");
        
        String getActQuery = "select act_origin,act\n" +
                             "from offence_type oft,act_origin ao\n" +
                             "where oft.act_origin_id =ao.act_origin_id\n" +
                             "and offence_code = ?";
        
        String act_string = "";
        for(int i=0;i < offence_list.size();i++){
            PreparedStatement pstmt2 = connection.prepareStatement(getActQuery);
            
            pstmt2.setString(1, offence_list.get(i).toString());
            ResultSet rs = pstmt2.executeQuery();
            while(rs.next()){
                String act_origin = rs.getString("act_origin");
                String act = rs.getString("act");
                
                act_string=act_string+act_origin+"  "+act+",";
            }
            
        }
        
        System.out.println("UserAppWebServices...");

               String ofnc_date = date_time;
                if(ofnc_date != null && !ofnc_date.isEmpty()){
                //String[] ofnc_date_array  = ofnc_date.split("-");
                //ofnc_date = ofnc_date_array[2] + "-" +  ofnc_date_array[1] + "-" +  ofnc_date_array[0];
                
                String[] ofnc_date_array  = ofnc_date.split(" ");
                String date=ofnc_date_array[0];
                String time="";
                try{
                time=ofnc_date_array[1];
                }catch(Exception e){
                    System.out.println(e);
                }
                String[] setDate =date.split("-"); 
                String myDate_format = setDate[2]+"-"+setDate[1]+"-"+setDate[0];
                ofnc_date=myDate_format+" "+time;
                  
                }
               
          sendSmsToAssignedFor1(mobile_no, "Challan_No. = "+traffic_police_id+"\nYour vehicle (vehicle no. "+auto_no+") is caught for  Traffic rule violetion under\n" +
                                "  Act "+act_string+" at "+ofnc_date+". \n"+
                                "  You have to pay penalty \n" +
                                " amount Rs "+total_deposit+" at Thana Yatayat,"+
                                " City Center, Gwalior, Madhya Pradesh.");
        System.out.println("Data Retrived : " + mobile_no);
        //stm.closeConnection();
    }
    
    public String sendSmsToAssignedFor1(String numberStr1, String messageStr1) {
       String result = "";
       try {
           String host_url = "http://login.smsgatewayhub.com/api/mt/SendSMS?";//"http://api.mVaayoo.com/mvaayooapi/MessageCompose?";
           String tempMessage = messageStr1;
           String sender_id = java.net.URLEncoder.encode("TEST SMS", "UTF-8");         // e.g. "TEST+SMS"
           System.out.println("messageStr1 is = " + messageStr1);
           messageStr1 = java.net.URLEncoder.encode(messageStr1, "UTF-8");
           String queryString = "APIKey=WIOg7OdIzkmYTrqTsw262w&senderid=JPSOFT&channel=2&DCS=8&flashsms=0&number=" + numberStr1 + "&text=" + messageStr1 + "&route=";
           String url = host_url + queryString;
           result = callURL1(url);
           System.out.println("SMS URL: " + url);
       } catch (Exception e) {
           result = e.toString();
           System.out.println("SMSModel sendSMS() Error: " + e);
       }
       return result;
   }
    
    private String callURL1(String strURL) {
       String status = "";
       try {
           java.net.URL obj = new java.net.URL(strURL);
           HttpURLConnection httpReq = (HttpURLConnection) obj.openConnection();
           httpReq.setDoOutput(true);
           httpReq.setInstanceFollowRedirects(true);
           httpReq.setRequestMethod("GET");
           status = httpReq.getResponseMessage();
       } catch (MalformedURLException me) {
           status = me.toString();
       } catch (IOException ioe) {
           status = ioe.toString();
       } catch (Exception e) {
           status = e.toString();
       }
       return status;
   }




  public  int insertVehicleDriverRecord(String auto_no,String mobile_no,String latitude,String longitude,String imei_no,String officier_mobile_no,String date_time, String e_chalan,org.json.JSONArray jsonArray) {
      int rowAffected = 0;
       int vehicle_driver_id=0;
        String query="insert into vehicle_driver (auto_no,mobile_no,date_time,latitude,longitude,created_by,imei_no, e_chalan) values(?,?,?,?,?,?,?, ?)";
        String mapQuery = "INSERT INTO vehicle_offence_map (vehicle_driver_id,offence_type_id) VALUES(?,?)";
        try{
           PreparedStatement ps=(PreparedStatement) connection.prepareStatement(query);
           ps.setString(1, auto_no);
           ps.setString(2, mobile_no);
           ps.setString(3, date_time);
           ps.setDouble(4, Double.parseDouble(latitude));
           ps.setDouble(5,  Double.parseDouble(longitude));
           int id=getKeyPersonId(officier_mobile_no);
           ps.setInt(6, id);
           ps.setString(7, imei_no);
           ps.setString(8, e_chalan);
           rowAffected = ps.executeUpdate();
           if(rowAffected > 0){
              ResultSet rst = ps.getGeneratedKeys();
               if(rst.next())
                   vehicle_driver_id = rst.getInt(1);
               if(e_chalan.equals("Y")){
                  int length =jsonArray.length();
                   for(int i = 0; i< length ; i++){
                       int offence_type_id = jsonArray.getInt(i);
                       ps=(PreparedStatement) connection.prepareStatement(mapQuery);
                       ps.setInt(1, vehicle_driver_id);
                       ps.setInt(2, offence_type_id);
                       rowAffected = ps.executeUpdate();
                   }

               }

           }
        }
        catch(Exception e) {
          System.out.println(e);
        }
   return   vehicle_driver_id;
 }
  public  int getKeyPersonId(String mobile_no)
    {

       int rowsAffected=0;

        String query="select key_person_id from key_person where mobile_no1='"+mobile_no+"' ";
        try{
           PreparedStatement ps=(PreparedStatement) connection.prepareStatement(query);
           ResultSet rs= ps.executeQuery();
           while(rs.next())
           {
            rowsAffected=rs.getInt("key_person_id");
           }
        }
        catch(Exception e)
        {
          System.out.println(e);
        }

   return   rowsAffected;
 }

    public int insertImageRecord(String image_name, int kp_id){
        DateFormat dateFormat = new SimpleDateFormat("dd.MMMMM.yyyy/ hh:mm:ss aaa");
        Date date = new Date();
        String current_date = dateFormat.format(date);
        int rowsAffected = 0;
        int general_image_detail_id=0;
        String imageQuery = "INSERT INTO general_image_details (image_name, image_destination_id, date_time, description,key_person_id) "
                    + " VALUES(?, ?, ?, ?,?)";
        try{
            PreparedStatement pstmt = connection.prepareStatement(imageQuery);
            pstmt.setString(1, image_name);
            pstmt.setInt(2, getimage_destination_id("e_challan"));
            pstmt.setString(3, current_date);
            pstmt.setString(4, "this image is form mini_app");
            pstmt.setInt(5, kp_id);
            rowsAffected = pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                general_image_detail_id=rs.getInt(1);
            }
            pstmt.close();
        } catch (Exception e) {
            System.out.println("Error:keypersonModel--insertRecord-- " + e);
        }
        return general_image_detail_id;
    }


    public int insertTempVehicleImageRecords(){
        DateFormat dateFormat = new SimpleDateFormat("dd.MMMMM.yyyy/ hh:mm:ss aaa");
        Date date = new Date();
        String current_date = dateFormat.format(date);
        int rowsAffected = 0;
        int general_image_detail_id=0;
        String imageQuery = "INSERT INTO general_image_details (image_name, image_destination_id, date_time, description) "
                    + " VALUES(?, ?, ?, ?)";
        try{
            PreparedStatement pstmt = connection.prepareStatement(imageQuery);
            pstmt.setString(1, " ");
            pstmt.setInt(2, getimage_destination_id("temp_vehicle"));
            pstmt.setString(3, current_date);
            pstmt.setString(4, "this image is for temp_vehicle");
           // pstmt.setInt(5, kp_id);
            rowsAffected = pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                general_image_detail_id=rs.getInt(1);
            }
            pstmt.close();
        } catch (Exception e) {
            System.out.println("Error:keypersonModel--insertRecord-- " + e);
        }
        return general_image_detail_id;
    }



    public JSONArray getOffence(){
        JSONArray jsonArray = new JSONArray();
        DateFormat dateFormat = new SimpleDateFormat("dd.MMMMM.yyyy/ hh:mm:ss aaa");
        Date date = new Date();
        String current_date = dateFormat.format(date);
        int rowsAffected = 0;
//        String Query = "SELECT ot.offence_type_id, count(ot.offence_type_id) offence, offence_code,act, offence_type, penalty_amount "
//                + " FROM traffic_offence_map tom, offence_type ot "
//                + " WHERE ot.offence_type_id=tom.offence_type_id "
//                + " GROUP BY act ORDER BY offence DESC LIMIT 0, 10";
//        String Query = "select offence_type_id,offence_code,offence_type,act,penalty_amount,priority "
//                       +" from offence_type oft "
//                       +" where active='Y' "
//                       +" group by offence_type order by offence_type";

//             String Query = "select offence_type_id,offence_code,offence_type,act,penalty_amount,priority,vehicle_type\n" +
//                            "from offence_type oft,vehicle_type vt\n" +
//                            "where oft.vehicle_type_id=vt.vehicle_type_id\n" +
//                            "and oft.active='Y'\n" +
//                            "group by offence_type order by offence_type";

              String Query = "select offence_type_id,offence_code,offence_type,act,commercial_type,penalty_amount,priority,have_second_offence,second_offence_penalty,to_date,from_date,vehicle_type\n" +
                            "from offence_type oft,vehicle_type vt,commercial_type ct\n" +
                            "where oft.vehicle_type_id=vt.vehicle_type_id\n" +
                            "and oft.active='Y'\n" +
                            "order by offence_type";



        try{
            ResultSet rs = connection.prepareStatement(Query).executeQuery();
            while(rs.next()){
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("offence_type_id", rs.getInt("offence_type_id"));
                jsonObj.put("offence_code", rs.getString("offence_code"));
                jsonObj.put("act", rs.getString("act"));
                jsonObj.put("offence_type", rs.getString("offence_type"));
                jsonObj.put("penalty_amount", rs.getDouble("penalty_amount"));
                jsonObj.put("priority", rs.getString("priority"));
                jsonObj.put("vehicle_type", rs.getString("vehicle_type"));
                jsonObj.put("have_second_offence", rs.getString("have_second_offence"));
                jsonObj.put("second_offence_penalty", rs.getString("second_offence_penalty"));
                jsonObj.put("to_date", rs.getString("to_date"));
                jsonObj.put("from_date", rs.getString("from_date"));
                jsonObj.put("commercial_type", rs.getString("commercial_type"));
                
                jsonArray.add(jsonObj);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error:keypersonModel--insertRecord-- " + e);
        }

        return jsonArray;
    }
    
    public JSONArray vehicleType(){
        JSONArray jsonArray = new JSONArray();
        DateFormat dateFormat = new SimpleDateFormat("dd.MMMMM.yyyy/ hh:mm:ss aaa");
        Date date = new Date();
        String current_date = dateFormat.format(date);
        int rowsAffected = 0;

        String Query = "select vehicle_type_id,vehicle_type\n" +
                       "from vehicle_type vt\n" +
                       "group by vehicle_type order by vehicle_type";
        try{
            ResultSet rs = connection.prepareStatement(Query).executeQuery();
            while(rs.next()){
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("vehicle_type_id", rs.getInt("vehicle_type_id"));
                jsonObj.put("vehicle_type", rs.getString("vehicle_type"));
                
                jsonArray.add(jsonObj);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error:getting vehicle type-- " + e);
        }
        return jsonArray;
    }
    public JSONArray getPreviousOffenceRecords(String vehicle_no){
        JSONArray jsonArray = new JSONArray();
        DateFormat dateFormat = new SimpleDateFormat("dd.MMMMM.yyyy/ hh:mm:ss aaa");
        Date date = new Date();
        String current_date = dateFormat.format(date);
        int rowsAffected = 0;

        String Query = "select oft.offence_type,oft.offence_code,penalty_amount,tp.create_date "
                      +" from offence_type oft,traffic_police tp,traffic_offence_map tom "
                      +" where tp.traffic_police_id = tom.traffic_police_id "
                      +" and tom.offence_type_id = oft.offence_type_id "
                      +" and tp.vehicle_no='"+vehicle_no+"'";


        try{
            ResultSet rs = connection.prepareStatement(Query).executeQuery();
            while(rs.next()){
                JSONObject jsonObj = new JSONObject();
                
                jsonObj.put("offence_code", rs.getString("offence_code"));
                 jsonObj.put("penalty_amount", rs.getDouble("penalty_amount"));
                jsonObj.put("offence_type", rs.getString("offence_type"));
                  jsonObj.put("create_date", rs.getString("create_date"));
                jsonArray.add(jsonObj);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error:keypersonModel--insertRecord-- " + e);
        }
        return jsonArray;
    }

         public int getimage_destination_id(String image_uploaded_for) {
        String query;
        int image_destination_id = 0;
        query = " SELECT image_destination_id, destination_path from image_destination AS id , image_uploaded_for As i "
                + " WHERE id.image_uploaded_for_id=i.image_uploaded_for_id AND i.image_uploaded_for= ? ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, image_uploaded_for);
            ResultSet rset = pstmt.executeQuery();
            if (rset.next()) {
                String destination_path = rset.getString("destination_path");
                System.out.println(destination_path);
                rset.getInt("image_destination_id");
                //System.out.println("image_destination_id = " + rset.getInt("image_destination_id"));
                image_destination_id = rset.getInt("image_destination_id");
                System.out.println(image_destination_id);
            }

        } catch (Exception ex) {
            System.out.println("Error: UploadBillImageModel-getimage_destination_id-" + ex);
        }
        return image_destination_id;
    }

 public String sendSmsToAssignedFor(String numberStr1, String messageStr1) {
       String result = "";
       try {
           String host_url = "http://login.smsgatewayhub.com/api/mt/SendSMS?";//"http://api.mVaayoo.com/mvaayooapi/MessageCompose?";
//           String tempMessage = messageStr1;
//           String sender_id = java.net.URLEncoder.encode("TEST SMS", "UTF-8");         // e.g. "TEST+SMS"
           System.out.println("messageStr1 is = " + messageStr1);
           messageStr1 = java.net.URLEncoder.encode(messageStr1, "UTF-8");
           String queryString = "APIKey=WIOg7OdIzkmYTrqTsw262w&senderid=JPSOFT&channel=2&DCS=8&flashsms=0&number=" + numberStr1 + "&text=" + messageStr1 + "&route=";
           String url = host_url + queryString;
           result = callURL(url);
           System.out.println("SMS URL: " + url);
       } catch (Exception e) {
           result = e.toString();
           System.out.println("SMSModel sendSMS() Error: " + e);
       }
       return result;
   }

   private String callURL(String strURL) {
       String status = "";
       try {
           java.net.URL obj = new java.net.URL(strURL);
           HttpURLConnection httpReq = (HttpURLConnection) obj.openConnection();
           httpReq.setDoOutput(true);
           httpReq.setInstanceFollowRedirects(true);
           httpReq.setRequestMethod("GET");
           status = httpReq.getResponseMessage();
       } catch (MalformedURLException me) {
           status = me.toString();
       } catch (IOException ioe) {
           status = ioe.toString();
       } catch (Exception e) {
           status = e.toString();
       }
       return status;
   }

   public static String random(int size) {
        StringBuilder generatedToken = new StringBuilder();
        try {
            SecureRandom number = SecureRandom.getInstance("SHA1PRNG");
            // Generate 20 integers 0..20
            for (int i = 0; i < size; i++) {
                generatedToken.append(number.nextInt(9));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return generatedToken.toString();
    }


    public boolean makeDirectory(String dirPathName) {
        boolean result = false;
        File directory = new File(dirPathName);
        if (!directory.exists()) {
            result = directory.mkdirs();
        }
        return result;
    }


    
    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMsgBgColor() {
        return msgBgColor;
    }

    public void setMsgBgColor(String msgBgColor) {
        this.msgBgColor = msgBgColor;
    }
    public void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println("CorrespondencePriorityModel closeConnection() Error: " + e);
        }
    }
}
