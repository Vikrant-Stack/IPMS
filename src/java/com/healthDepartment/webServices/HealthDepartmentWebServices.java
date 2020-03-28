/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthDepartment.webServices;

import com.healthDepartment.dbCon.DBConnection;
import com.healthDepartment.general.model.MapDetailClass;
//import com.healthDepartment.shift.model.ShiftLoginModel;
//import com.healthDepartment.shift.model.ShiftTimeModel;
//import com.healthDepartment.vehicle.model.VehicleWeightModel;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import Decoder.BASE64Decoder;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.QueryParam;
import static javax.ws.rs.core.HttpHeaders.USER_AGENT;
import net.sf.json.JSONException;

/**
 *
 * @author Administrator
 */
@Path("/apiServices")
public class HealthDepartmentWebServices {

    @Context
    ServletContext serveletContext;
    Connection connection = null;
    //private  String zone;
    // private  String ward;
    //private  String area;

//    @POST
//    @Path("/detail")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    //@Produces(MediaType.TEXT_PLAIN)
//    public JSONObject personDetail(String emp_code) throws Exception {
//        JSONObject obj = new JSONObject();
//        JSONArray arrayObj = new JSONArray();
//        Response res = null;
//        System.out.println("ShiftWebServices");
//        ShiftLoginModel slm = new ShiftLoginModel();
//        try {
//            slm.setConnection(DBConnection.getConnectionForUtf(serveletContext));
//        } catch (Exception ex) {
//            System.out.println("ERROR : in personDetail() in RideWebservices : " + ex);
//        }
//        JSONObject jsonObj = slm.getAreaDetails(emp_code);
//        String zone = jsonObj.getString("zone_name");
//        String ward = jsonObj.getString("ward_name");
//        String area = jsonObj.getString("area_name");
//        String designation = jsonObj.getString("designation");
//        System.out.println("Data Retrived : " + jsonObj);
//        arrayObj.add(jsonObj);
//        obj.put("Data", arrayObj);
//        arrayObj = slm.getBeneficiaryDetails(zone, ward, area);
//        System.out.println("Data Retrived : " + arrayObj);
//        obj.put("BeneficiaryData", arrayObj);
//        arrayObj = slm.getCityDetails(zone, ward, area);
//        System.out.println("Data Retrived : " + arrayObj);
//        obj.put("city_location", arrayObj);
//        arrayObj = slm.getReasonDetails();
//        System.out.println("Data Retrived : " + arrayObj);
//        obj.put("reason", arrayObj);
//        arrayObj = slm.getOccupationTypeDetails();
//        System.out.println("Data Retrived : " + arrayObj);
//        obj.put("occupation_type", arrayObj);
//        if (designation.equals("ड्राइवर")) {
//            arrayObj = slm.getVehicleKeyPersonDetails(emp_code);
//            System.out.println("Data Retrived : " + arrayObj);
//            obj.put("VehicleDetail", arrayObj);
//
//            arrayObj = slm.getvehicleDetails(zone, ward, area);
//            System.out.println("Data Retrived : " + arrayObj);
//            obj.put("AllVehicle", arrayObj);
//        }
//        res = Response.ok(obj, MediaType.APPLICATION_JSON).build();
//        slm.closeConnection();
//        return obj;
//    }
//
//    @POST
//    @Path("/insert")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public String InsertRecord(JSONObject jsonObj) throws Exception {
//        JSONObject obj = new JSONObject();
//        Response res = null;
//        String reply = "";
//        String beneficiary_id, reason_id, emp_code, status, date_time;
//        beneficiary_id = jsonObj.get("beneficiary_id").toString();
//        reason_id = jsonObj.get("reason").toString();
//        emp_code = jsonObj.get("emp_code").toString();
//        status = jsonObj.get("status").toString();
//        date_time = jsonObj.get("date").toString();
//        //mobile_no = jsonObj.get("mobile_no").toString();
//        System.out.println("insertRecord");
//        ShiftLoginModel slm = new ShiftLoginModel();
//        try {
//            slm.setConnection(DBConnection.getConnectionForUtf(serveletContext));
//        } catch (Exception ex) {
//            System.out.println("ERROR : in insertRecord() in ShiftWebservices : " + ex);
//        }
//        int result = slm.insertShiftRecord(beneficiary_id, reason_id, date_time, emp_code, status);
//        String id_attendence = slm.getskpmId(emp_code);
//        String skpm_id = id_attendence.split("_")[0];
//        String attendence = id_attendence.split("_")[1];
//        if (attendence.equals("N")) {
//            result = slm.updateAttendence(skpm_id);
//            JSONObject jsonObj1 = slm.getAreaDetails(emp_code);
//            String zone = jsonObj1.getString("zone_name");
//            String ward = jsonObj1.getString("ward_name");
//            String area = jsonObj1.getString("area_name");
//            slm.getBeneficiaryDetails1(zone, ward, area);
//        }
//        if (result > 0) {
//            System.out.println("Data Retrived : " + jsonObj + " : Saved...");
//            reply = "Successfully";
//        } else {
//            System.out.println("Data Retrived : " + jsonObj + " : Not Saved Some Error...");
//            reply = " Not Successfully";
//        }
//        slm.closeConnection();
//        return reply;
//    }
//
//    @POST
//    @Path("/shiftFinish")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public String shiftFinish(String emp_id) throws Exception {
//        ShiftLoginModel slm = new ShiftLoginModel();
//        try {
//            slm.setConnection(DBConnection.getConnectionForUtf(serveletContext));
//        } catch (Exception ex) {
//            System.out.println("ERROR : in insertRecord() in ShiftWebservices : " + ex);
//        }
//        JSONObject jsonObj = slm.getAreaDetails(emp_id);
//        String zone = jsonObj.getString("zone_name");
//        String ward = jsonObj.getString("ward_name");
//        String area = jsonObj.getString("area_name");
//        System.out.println("Data Retrived : " + jsonObj);
//        String id_attendence = slm.getskpmId(emp_id);
//        String skpm_id = id_attendence.split("_")[0];
//        slm.updateFinishTime(skpm_id);
//        slm.insertAllRemainingBeneficary(emp_id, zone, ward, area);
//        slm.closeConnection();
//        return "Success";
//    }
//
//    @POST
//    @Path("/insertKeyPerson")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public String InsertKeyPerson(JSONObject jsonObj) throws Exception {
//        JSONObject obj = new JSONObject();
//        Response res = null;
//        String reply = "";
//        String salutation, person_name, father_name, age, address_line1, address_line2, mobile_no1, email_id1, no_of_person, city_location, is_residencial, occupation_type, occupation_name, key_person_id;
//        String latitude = "";
//        String longitude = "";
//        salutation = jsonObj.get("salutation").toString();
//        person_name = jsonObj.get("person_name").toString();
//        father_name = jsonObj.get("father_name").toString();
//        age = jsonObj.get("age").toString();
//        address_line1 = jsonObj.get("address_line1").toString();
//        address_line2 = jsonObj.get("address_line2").toString();
//        mobile_no1 = jsonObj.get("mobile_no").toString();
//        email_id1 = jsonObj.get("email").toString();
//        no_of_person = jsonObj.get("no_of_person").toString();
//        city_location = jsonObj.get("location").toString();
//        is_residencial = jsonObj.get("is_residencial").toString();
//        occupation_type = jsonObj.get("occupationTypeId").toString();
//        occupation_name = jsonObj.get("occupation_name").toString();
//        key_person_id = jsonObj.get("key_person_id").toString();
//        if (Integer.parseInt(key_person_id) == 0) {
//            latitude = jsonObj.get("latitude").toString();
//            longitude = jsonObj.get("longitude").toString();
//        }
//
//        System.out.println("insertRecord");
//        ShiftLoginModel slm = new ShiftLoginModel();
//        try {
//            slm.setConnection(DBConnection.getConnectionForUtf(serveletContext));
//        } catch (Exception ex) {
//            System.out.println("ERROR : in insertRecord() in ShiftWebservices : " + ex);
//        }
//        int result = slm.insertKeyPerson(salutation, person_name, father_name, age, address_line1, address_line2, mobile_no1, email_id1, latitude, longitude, key_person_id, occupation_type, occupation_name, no_of_person, city_location, is_residencial);
//        if (result > 0) {
//            System.out.println("Data Retrived : " + jsonObj + " : Saved...");
//            reply = "Successfully";
//        } else {
//            System.out.println("Data Retrived : " + jsonObj + " : Not Saved Some Error...");
//            reply = " Not Successfully";
//        }
//        slm.closeConnection();
//        return reply;
//    }
//
////    @POST
////    @Path("/insertCordinate")
////    @Produces(MediaType.APPLICATION_JSON)
////    @Consumes(MediaType.APPLICATION_JSON)
////    public String InsertCordinate(JSONObject jsonObj) throws Exception {
////        JSONObject obj = new JSONObject();
////        Response res = null;
////        String reply = "";
////        int result = 0;
////        String latitude, longitude, imei_no, emp_code, mobile_no;
////        imei_no = jsonObj.get("deviceid").toString();
////        emp_code = jsonObj.get("empcode").toString();
////        latitude = jsonObj.get("latitude").toString();
////        longitude = jsonObj.get("longitude").toString();
////        if (jsonObj.get("phoneno") == null) {
////            mobile_no = "";
////        } else {
////            mobile_no = jsonObj.get("phoneno").toString();
////        }
////        System.out.println("insertRecord");
////        ShiftLoginModel slm = new ShiftLoginModel();
////        try {
////            slm.setConnection(DBConnection.getConnectionForUtf(serveletContext));
////        } catch (Exception ex) {
////            System.out.println("ERROR : in insertRecord() in ShiftWebservices : " + ex);
////        }
////        slm.insertCordinate(latitude, longitude, emp_code, imei_no, mobile_no);
////        System.out.println("record insert in cordinate table");
////        String id_attendence = slm.getskpmId(emp_code);
////        String skpm_id = id_attendence.split("_")[0];
////        String attendence = id_attendence.split("_")[1];
////
////        if (attendence.equals("N")) {
////            JSONObject Obj = slm.getAreaDetails(emp_code);
////            String zone = Obj.getString("zone_name");
////            String ward = Obj.getString("ward_name");
////            String area = Obj.getString("area_name");
////            List destination = slm.getLocationCordinates(zone, ward, area);
////            Iterator itr = destination.iterator();
////            while (itr.hasNext()) {
////                String data = (String) itr.next();
////                int distance = MapDetailClass.getDistance(latitude + "," + longitude, data);
////                if (distance < 30) {
////                    result = slm.updateAttendence(skpm_id);
////                    break;
////                }
////            }
////        }
////        if (result > 0) {
////            System.out.println("Data Retrived : " + jsonObj + " : Saved...");
////            res = Response.ok(jsonObj, MediaType.APPLICATION_JSON).build();
////        } else {
////        }
////        slm.closeConnection();
////
////        return reply;
////    }
//    @POST
//    @Path("/vehicleWeight")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public String insertVehicleWeightRecord(JSONObject jsonObj) throws Exception {
//
//        String reply = "";
//        VehicleWeightModel vwm = new VehicleWeightModel();
//        try {
//            vwm.setConnection(DBConnection.getConnectionForUtf(serveletContext));
//        } catch (Exception ex) {
//            System.out.println("ERROR : in insertRecord() in ShiftWebservices : " + ex);
//        }
//        String vehicle_code, weight;
//        vehicle_code = jsonObj.get("vehicle_code").toString();
//        weight = jsonObj.get("weight").toString();
//        int vehicle_id = vwm.getVehicleId(vehicle_code);
//        int affected = 0;
//
//        System.out.println("Receiving data...");
//        String path = "C:\\ssadvt_repository\\HealthDepartment\\vechileWeightImage";
//        vwm.makeDirectory(path);
//        FileOutputStream outputStream = null;
//        byte[] fileAsBytes = null;
//        String file = "";
//        org.json.JSONArray jsonArray = null;
//        int size = 0;
//        org.json.JSONObject jsn = new org.json.JSONObject(jsonObj.toString());
//        if (jsonObj.get("images") != null) {
//            jsonArray = jsn.getJSONArray("images");//new JSONArray(json.get("images") == null ? "" : json.get("images").toString());
//            size = jsonArray.length();
//        }
//        org.json.JSONObject jsonObject = null;
//        for (int i = 0; i < size; i++) {
//            try {
//                System.out.println(" Image Uploading.....");
//                jsonObject = jsonArray.getJSONObject(i);
//                fileAsBytes = new BASE64Decoder().decodeBuffer(jsonObject.getString("byte_arr"));
//                String fileName = jsonObject.getString("imgname");
//                file = path + "\\" + fileName;
//                outputStream = new FileOutputStream(file);
//                outputStream.write(fileAsBytes);
//                outputStream.close();
//            } catch (Exception ex) {
//                System.out.println("ERROR : in saveComplainReportFiles() in ComplainAppWebservices : " + ex);
//            }
//        }
//        int result = vwm.saveVehicleWeightRecord_webService(vehicle_id, weight, file);
//        if (result > 0) {
//            System.out.println("Data Retrived : " + jsonObj + " : Saved...");
//            reply = "Successfully";
//        } else {
//            System.out.println("Data Retrived : " + jsonObj + " : Not Saved Some Error...");
//            reply = " Not Successfully";
//        }
//        return reply;
//    }
//
//    @POST
//    @Path("/insertVehicle")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public String InsertVehicleRecord(JSONObject jsonObj) throws Exception {
//        JSONObject obj = new JSONObject();
//        Response res = null;
//        String reply = "";
//        String beneficiary_id, vehicle_code, emp_code, latitude, longitude, date_time, status;
//        beneficiary_id = jsonObj.get("beneficiary_id").toString();
//        emp_code = jsonObj.get("emp_code").toString();
//        latitude = jsonObj.get("latitude").toString();
//        date_time = jsonObj.get("date_time").toString();
//        longitude = jsonObj.get("longitude").toString();
//        status = jsonObj.get("weight").toString();
//        System.out.println("insertRecord");
//        ShiftLoginModel slm = new ShiftLoginModel();
//        try {
//            slm.setConnection(DBConnection.getConnectionForUtf(serveletContext));
//        } catch (Exception ex) {
//            System.out.println("ERROR : in insertRecord() in ShiftWebservices : " + ex);
//        }
//        int result = slm.insertVehicle(emp_code, beneficiary_id, latitude, longitude, date_time, status);
//
//        if (result > 0) {
//            System.out.println("Data Retrived : " + jsonObj + " : Saved...");
//            reply = "Successfully";
//        } else {
//            System.out.println("Data Retrived : " + jsonObj + " : Not Saved Some Error...");
//            reply = " Not Successfully";
//        }
//        slm.closeConnection();
//        return reply;
//    }
//
//    @POST
//    @Path("/insertVehicle1")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public String InsertVehicleRecord1(JSONObject jsonObj) throws Exception {
//        int result = 0;
//        String reply = "";
//        String beneficiary_id, emp_code, latitude, longitude, date_time, status;
//        org.json.JSONObject jsn = new org.json.JSONObject(jsonObj.toString());
//        org.json.JSONArray jsonArray = jsn.getJSONArray("Nfc_Driver");
//        int size = jsonArray.length();
//
//        ShiftLoginModel slm = new ShiftLoginModel();
//        try {
//            slm.setConnection(DBConnection.getConnectionForUtf(serveletContext));
//        } catch (Exception ex) {
//            System.out.println("ERROR : in insertRecord() in ShiftWebservices : " + ex);
//        }
//        for (int i = 0; i < size; i++) {
//            beneficiary_id = jsonObj.get("beneficiary_id").toString();
//            emp_code = jsonObj.get("emp_code").toString();
//            latitude = jsonObj.get("latitude").toString();
//            date_time = jsonObj.get("date").toString();
//            longitude = jsonObj.get("longitude").toString();
//            status = jsonObj.get("weight").toString();
//            System.out.println("insertRecord");
//            result = slm.insertVehicle(emp_code, beneficiary_id, latitude, longitude, date_time, status);
//        }
//        if (result > 0) {
//            System.out.println("Data Retrived : " + jsonObj + " : Saved...");
//            reply = "Successfully";
//        } else {
//            System.out.println("Data Retrived : " + jsonObj + " : Not Saved Some Error...");
//            reply = " Not Successfully";
//        }
//        slm.closeConnection();
//        return reply;
//    }
//
//    @POST
//    @Path("/vehicle_Attendance")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public String vehicleAttendence(JSONObject jsonObj) throws Exception {
//        int result = 0;
//        String reply = "";
//        String vehicle_code, emp_code;
//        vehicle_code = jsonObj.get("beneficiary_id").toString();
//        emp_code = jsonObj.get("emp_code").toString();
//        System.out.println("insertRecord");
//        ShiftLoginModel slm = new ShiftLoginModel();
//        try {
//            slm.setConnection(DBConnection.getConnectionForUtf(serveletContext));
//        } catch (Exception ex) {
//            System.out.println("ERROR : in insertRecord() in ShiftWebservices : " + ex);
//        }
//        String id_attendence = slm.getskpmId(emp_code);
//        String skpm_id = id_attendence.split("_")[0];
//        String attendence = id_attendence.split("_")[1];
//
//        String skpm_idFromVehicleCode = slm.getSkpmIdFromVehicleCode(vehicle_code);
//        if (skpm_id.equals(skpm_idFromVehicleCode)) {
//            if (attendence.equals("N")) {
//                result = slm.updateAttendence(skpm_id);
//                result = slm.updateVehicleVerify(skpm_id);
//            }
//        }
//        if (result > 0) {
//            System.out.println("Data Retrived : " + jsonObj + " : Saved...");
//            reply = "Successfully";
//        } else {
//            System.out.println("Data Retrived : " + jsonObj + " : Not Saved Some Error...");
//            reply = " Not Successfully";
//        }
//        slm.closeConnection();
//        return reply;
//    }
//
//    @POST
//    @Path("/update_vehicle")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public String vehicleUpdate(JSONObject jsonObj) throws Exception {
//        int result = 0;
//        String reply = "";
//        String vehicle_code;
//        vehicle_code = jsonObj.get("beneficiary_id").toString();
//
//        System.out.println("insertRecord");
//        ShiftLoginModel slm = new ShiftLoginModel();
//        try {
//            slm.setConnection(DBConnection.getConnectionForUtf(serveletContext));
//        } catch (Exception ex) {
//            System.out.println("ERROR : in insertRecord() in ShiftWebservices : " + ex);
//        }
//
//        result = slm.InsertErrorLog(vehicle_code);
//        if (result > 0) {
//            System.out.println("Data Retrived : " + jsonObj + " : Saved...");
//            reply = "Successfully";
//        } else {
//            System.out.println("Data Retrived : " + jsonObj + " : Not Saved Some Error...");
//            reply = " Not Successfully";
//        }
//        slm.closeConnection();
//        return reply;
//    }
//
//    @POST
//    @Path("/fingerPrint")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public String FingerPrint(JSONObject jsonObj) throws Exception {
//        int result = 0;
//        String reply = "";
//        String vehicle_code;
//        vehicle_code = jsonObj.get("beneficiary_id").toString();
//
//        System.out.println("insertRecord");
//        ShiftLoginModel slm = new ShiftLoginModel();
//        try {
//            slm.setConnection(DBConnection.getConnectionForUtf(serveletContext));
//        } catch (Exception ex) {
//            System.out.println("ERROR : in insertRecord() in ShiftWebservices : " + ex);
//        }
//
//        result = slm.InsertErrorLog(vehicle_code);
//        if (result > 0) {
//            System.out.println("Data Retrived : " + jsonObj + " : Saved...");
//            reply = "Successfully";
//        } else {
//            System.out.println("Data Retrived : " + jsonObj + " : Not Saved Some Error...");
//            reply = " Not Successfully";
//        }
//        slm.closeConnection();
//        return reply;
//    }
    
    
    
    // to get connected with mqtt server
    
    @POST
    @Path("/testingdata")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String  getLatLonJSONArray(@QueryParam("DevId") String DevId){
        String str="my string";
        String getlatlon=null;
        //String device_Id=device_id;
        //System.out.println("device iiiddd- -"+device_Id);
    //SocketAddress socketaddress = new InetSocketAddress("120.138.10.146", 8060);
    SocketAddress socketaddress = new InetSocketAddress("192.168.43.180", 8060);
      JSONObject json = new JSONObject();
      JSONArray jsonArray = new JSONArray();
      String result="";
//    SocketAddress socketaddress = new InetSocketAddress("120.138.10.197", 8090);
    Socket socket = new Socket();
    int timeout =60000;
    try{
    socket.connect(socketaddress, timeout);
     //result = "$$$$,50, , , , ,####";
     result = "$$$$,59,D_1,06,104,12,3/1/0 0:4:33,$GNGGA,044336.00,2837.58916,N,07722.66413,E,2,12,0.50,210.1,M,-40.2,M,,0000,A1,11858,1,98,1174,0000,00,####";
    DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
    outputStream.writeChars(result);
    //outputStream.close();
        System.out.println("result ---"+result);
    DataInputStream inputStream = new DataInputStream(socket.getInputStream());
        System.out.println("input streammm --"+inputStream);
    byte[] bytes = new byte[25]; 
    //inputStream.read(bytes);
    inputStream.read(bytes);
    getlatlon = new String(bytes);
        System.out.println("get alt llon"+getlatlon);
//    String latitude = getlatlon.split(",")[0];
//    String arr1[] = latitude.split("\\.");
//    String beforePoint = arr1[0];
//    String firsthalf = beforePoint.substring(0, 2);
//    String secondhalf = beforePoint.substring(2, 4);
//    String afterPoint = arr1[1];
//    String finalSubString = (secondhalf+afterPoint);
//    Long value1 = (Long.parseLong(finalSubString))/60;
//    String afterMultiply =  Long.toString(value1);
//    String finalString = firsthalf+"."+afterMultiply;
//
//    String longitude = getlatlon.split(",")[1];
//    String arr2[] = longitude.split("\\.");
//    String beforePoint2 = arr2[0];
//    String firsthalf2 = beforePoint2.substring(0, 3);
//    String secondhalf2 = beforePoint2.substring(3, 5);
//    String afterPoint2 = arr2[1];
//    String finalSubString2 = (secondhalf2+afterPoint2);
//     Long value2 = (Long.parseLong(finalSubString2))/60;
//     String afterMultiply2 =  Long.toString(value2);
//     String finalString2 = firsthalf2+"."+afterMultiply2;
//    org.json.simple.JSONObject json1 = new org.json.simple.JSONObject();
//    json1.put("latitude", finalString);
//    json1.put("longitude", finalString2);
//    jsonArray.add(json1);

    }
    catch(SocketTimeoutException exception){
    System.out.println("Socket Timeout Exception ::: " + exception);
    }
    catch(IOException exception){
    System.out.println("Unable to connect Exception ::: " + exception);
    }
//    json.put("data", jsonArray);
   // json.put("cordinateLength", jsonArray.size());
    //return result;
    return getlatlon;
    }
    
    
    
    
    @POST
    @Path("/mqttdata")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String Mqtt_data(JSONObject jsonObj) throws Exception {
        int result = 0;
        String reply = "";
        String vehicle_code;
        
         int size = jsonObj.size();
         System.out.println("size -"+size+" json obj --"+jsonObj+" json obj string -"+jsonObj.toString());
         System.out.println("data array "+jsonObj.toString());  
         System.out.println("Command ---"+jsonObj.getString("Command"));
         System.out.println("json data ---"+jsonObj.getString("Device_id"));
        String device_id = ""; 
           try{
       String connectivity = jsonObj.getString("Connectivity");
       
     
        
           if(connectivity.equals("0"))
           {
        String fuel_level =  jsonObj.getString("fuel_level");
        
        String fuel_temperature = jsonObj.get("fuel_temperature").toString();
      //  String date_time = jsonObj.get("date_time").toString();
        String lat = jsonObj.get("lat").toString();
        String longg = jsonObj.get("long").toString();
        String accuracy = jsonObj.get("accuracy").toString();
        String voltage = jsonObj.get("voltage").toString();
        String engine_status = jsonObj.get("engine_status").toString();
        String door_status = jsonObj.get("door_status").toString();
        String unused_input = jsonObj.get("unused_input").toString();
        String fuel_intensity = jsonObj.get("fuel_intensity").toString();
        String water_level = jsonObj.get("water_level").toString();
        String water_temperature = jsonObj.get("water_temperature").toString();
        String water_intensity = jsonObj.get("water_intensity").toString();
        String software_version = jsonObj.get("software_version").toString();
       // String connectivity = jsonObj.get("connectivity").toString();
        String speed = jsonObj.get("speed").toString();
        String service = jsonObj.get("service").toString();
        String crc = jsonObj.get("crc").toString();
        
         result++;
           }
           
        else
           {
            result=0;
           }
       
           }
           catch(Exception e)
                {
                System.out.println("Exception"+e);
                
                }
      
  

//        System.out.println("insertRecord");
//        ShiftLoginModel slm = new ShiftLoginModel();
//        try {
//            slm.setConnection(DBConnection.getConnectionForUtf(serveletContext));
//        } catch (Exception ex) {
//            System.out.println("ERROR : in insertRecord() in ShiftWebservices : " + ex);
//        }

     //   result=slm.InsertErrorLog(vehicle_code);
   
        if (result > 0) {
            System.out.println("Data Retrived : " + jsonObj + " : Saved...");
            reply = "Successfully";
        } else {
            System.out.println("Data Retrived : " + jsonObj + " : Not Saved Some Error...");
            reply = " Not Successfully";
        }
      //  slm.closeConnection();
        return reply;
    }
    
    // end
    
    
    
    

//    @POST
//    @Path("/mqttdata")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response getMqttData(JSONObject jsonObj) throws IOException {
//        System.out.println("web servie called");
//        System.out.println("data in web service -" + jsonObj);
//        String result = "Product created : ";
//
//        //
//        URL url = null;
//        try {
//            url = new URL("http://120.138.10.146:8060/");
//            //url = new URL("http://192.168.1.107:8060/api/apiServices/mqttdata");
//        } catch (MalformedURLException ex) {
//            Logger.getLogger(HealthDepartmentWebServices.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        HttpURLConnection con = (HttpURLConnection) url.openConnection();
//        con.setDoOutput(true);
//        con.setRequestMethod("GET");
//        //con.setRequestProperty("Content-Type", "application/json; utf-8");
//        con.setRequestProperty("User-Agent", USER_AGENT);
//        int responseCode = con.getResponseCode();
//        System.out.println("GET Response Code :: " + responseCode);
//        if (responseCode == HttpURLConnection.HTTP_OK) { // success
//            BufferedReader in = new BufferedReader(new InputStreamReader(
//                    con.getInputStream()));
//            String inputLine;
//            StringBuffer response = new StringBuffer();
//
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//            in.close();
//
//            // print result
//            System.out.println(response.toString());
//        } else {
//            System.out.println("GET request not worked");
//        }
//        //
//
//        return Response.status(201).entity(result).build();
//    }
//
    @POST
    @Path("/testingdata_2")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getMqttDataa(String str) {
        String str1 = "my string";
        //String device=DevId;
        return str1;

    }
    
    
    @POST
    @Path("/getQRCode")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String  getQRCode(@QueryParam("DevId") String DevId){
        String str="my string";
        String getlatlon=str;
        //String device_Id=device_id;
        //System.out.println("device iiiddd- -"+device_Id);
    return getlatlon;
    }

//     @POST
//    @Path("/FleetImage")
//    @Produces(MediaType.APPLICATION_JSON)//http://192.168.1.15:8084/trafficSignals_new/api/service/hello
//    @Consumes(MediaType.APPLICATION_JSON)
//    public String testForFleet(JSONObject inputJsonObj) throws JSONException, IOException {
//    System.out.println("hello FleetImage method... ");
//
//    String no_of_litre = inputJsonObj.get("no_of_litre").toString();
//   
//    OutputStream out = null;
//     int general_image_detail_id=0;
//      List<File> fileList = new ArrayList<File>();      
//        try{                  
//        String destination_path = "";                
//                String imagePath="";
//                String fileName="";                        
//                String getBackEncodedString = inputJsonObj.get("byte_arr").toString();
//                byte[] imageAsBytes = new BASE64Decoder().decodeBuffer(getBackEncodedString);
//                fileName = ( inputJsonObj.get("imgname").toString());                                        
//                if (fileName.isEmpty()) {
//                    fileName = "out.jpg";
//                }              
//                         
//               // destination_path = tcpServerWebServiceModel.getDestinationPath("DetailList");                    
//               // fileNameArray[i-1] = fileName;
//               // imagePath=destination_path+ "/" +task;                      
//               //  tcpServerWebServiceModel.makeDirectory(imagePath);
//                String file = "c://" + "ssadvt_repository/fleetManagement/"+ fileName;
//            //     String file =  imagePath + "/"+ fileName;
//                fileList.add(new File(file));
//                out = new FileOutputStream(file);
//                out.write(imageAsBytes);
//                out.close();        
//                                         
//        }
//          catch (Exception ex) {
//            System.out.println("ERROR  : " + ex);
//              }      
//   
//    return "success";
//    }
//    
}
