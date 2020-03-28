package com.ipms.ePass.controller;

import com.healthDepartment.location.controller.*;
import com.healthDepartment.location.model.CityModel;
import com.healthDepartment.location.tableClasses.CityBean;
import com.ipms.ePass.model.WorkTypeModel;
import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;

public class WorkTypeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException
    {
         int lowerLimit, noOfRowsTraversed, noOfRowsToDisplay = 15, noOfRowsInTable;
         
        System.out.println("Starting application");
        response.setContentType("text/html");
        ServletContext ctx = getServletContext();
        WorkTypeModel workTypeModel = new WorkTypeModel();

        workTypeModel.setDriver(ctx.getInitParameter("driverClass"));
        workTypeModel.setUrl(ctx.getInitParameter("connectionString"));
        workTypeModel.setUser(ctx.getInitParameter("db_user_name"));
        workTypeModel.setPassword(ctx.getInitParameter("db_user_password"));
        workTypeModel.setConnection();
        request.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "text/plain; charset=UTF-8");
        System.out.println(workTypeModel.getConnection());
        String task = request.getParameter("task");
         
        String searchCity= request.getParameter("searchCity");        

        try {
            //----- This is only for Vendor key Person JQuery
            String JQstring = request.getParameter("action1");  
            System.out.println("action string -"+JQstring);
            String q = request.getParameter("q");   // field own input           
            if (JQstring != null) {
                PrintWriter out = response.getWriter();
                List<String> list = null;
                if (JQstring.equals("getCity")) {
                     list = workTypeModel.getCity(q);
                }
                if (JQstring.equals("getOrgName")) {
                     list = workTypeModel.getOrgName(q);
                     System.out.println("listt  data -"+list);
                }
                Iterator<String> iter = list.iterator();
                while (iter.hasNext()) {
                    String data = iter.next();
                        out.println(data);
                }
                JSONObject gson = new JSONObject();
                     gson.put("list",list);
                   out.println(gson);
                workTypeModel.closeConnection();
                return;
            }
        } catch (Exception e) {
            System.out.println("\n Error --ClientPersonMapController get JQuery Parameters Part-" + e);
        } 
          
         if(searchCity==null)
             searchCity="";

         if(task==null)
            task="";
         else if(task.equals("generateMapReport"))//start from here
         {
            List listAll = null;
            String jrxmlFilePath;
            String search= request.getParameter("searchCity");            
            response.setContentType("application/pdf");
            ServletOutputStream servletOutputStream = response.getOutputStream();
            listAll=workTypeModel.showAllData(search);            
            jrxmlFilePath = ctx.getRealPath("/report/location/CityReport.jrxml");
            byte[] reportInbytes = workTypeModel.generateMapReport(jrxmlFilePath,listAll);
            response.setContentLength(reportInbytes.length);
            servletOutputStream.write(reportInbytes, 0, reportInbytes.length);
            servletOutputStream.flush();
            servletOutputStream.close();
            return;
         }else if(task.equals("generateMapXlsReport"))
         {     String jrxmlFilePath;
              List listAll=null;
               String search= request.getParameter("searchCity");
                       response.setContentType("application/vnd.ms-excel");
                       response.addHeader("Content-Disposition", "attachment; filename=city.xls");
                       ServletOutputStream servletOutputStream = response.getOutputStream();
                       jrxmlFilePath = ctx.getRealPath("/report/location/CityReport.jrxml");
                       listAll=workTypeModel.showAllData(search);
                       ByteArrayOutputStream reportInbytes =workTypeModel.generateCityXlsRecordList(jrxmlFilePath, listAll);
                       response.setContentLength(reportInbytes.size());
                       servletOutputStream.write(reportInbytes.toByteArray());
                       servletOutputStream.flush();
                       servletOutputStream.close();
                       return;
         }
         else if(task.equals("Search All Records"))
             searchCity="";        
          else if(task.equals("Save all records")||task.equals("Save As New")||task.equals("Save"))
          {
                int city_id=0;
                 try{
                     city_id = Integer.parseInt(request.getParameter("city_id").trim());
                 }catch(Exception ex){
                     city_id = 0;
                 }
              int pin_code=Integer.parseInt(request.getParameter("pin_code"));
              int std_code=Integer.parseInt(request.getParameter("std_code"));
             String cityDescription= request.getParameter("cityDescription");
               String cityName= request.getParameter("cityName");
                String tehsilName= request.getParameter("tehsil");
             CityBean b=new CityBean();
             b.setCityId(city_id);
             b.setCityName(cityName);
             b.setTehsilName(tehsilName);
             b.setCityDescription(cityDescription);
             b.setPin_code(pin_code);
             b.setStd_code(std_code);
              if(task.equals("Save all records"))
                workTypeModel.insertRecord(b);
              else if(task.equals("Save As New"))
              {
                 city_id=0;
                workTypeModel.insertRecord(b);
              }
             else if(task.equals("Save"))
              {   
             workTypeModel.insertRecord(b);
              }
          }else if(task.equals("Delete"))
             workTypeModel.deleteRecord(Integer.parseInt(request.getParameter("cityId")));
       
         noOfRowsInTable = workTypeModel.getTotalRowsInTable(searchCity);
        
            try {
                lowerLimit = Integer.parseInt(request.getParameter("lowerLimit"));
                noOfRowsTraversed = Integer.parseInt(request.getParameter("noOfRowsTraversed"));
            } catch (Exception e) {
                lowerLimit = noOfRowsTraversed = 0;
            }      
           
            if (task.equals("Next")); // lowerLimit already has value such that it shows forward records, so do nothing here.
            else if (task.equals("Previous")) {
                int temp = lowerLimit - noOfRowsToDisplay - noOfRowsTraversed;
                if (temp < 0) {
                    noOfRowsToDisplay = lowerLimit - noOfRowsTraversed;
                    lowerLimit = 0;
                } else {
                    lowerLimit = temp;
                }
            } else if (task.equals("First")) {
                lowerLimit = 0;
            } else if (task.equals("Last")) {
                lowerLimit = noOfRowsInTable - noOfRowsToDisplay;               
                if (lowerLimit < 0) {
                    lowerLimit = 0;
                }
            }

            if (task.equals("Save") || task.equals("Delete") || task.equals("Save AS New") || task.equals("Add All Records")) {
                lowerLimit = lowerLimit - noOfRowsTraversed;    // Here objective is to display the same view again, i.e. reset lowerLimit to its previous value.
              }                
         ArrayList<CityBean> list = workTypeModel.getAllRecords(lowerLimit,noOfRowsToDisplay,searchCity);
         lowerLimit = lowerLimit + list.size();
         noOfRowsTraversed = list.size();

         if ((lowerLimit - noOfRowsTraversed) == 0) {     // if this is the only data in the table or when viewing the data 1st time.
            request.setAttribute("showFirst", "false");
            request.setAttribute("showPrevious", "false");
            }
            if (lowerLimit == noOfRowsInTable) {             // if No further data (rows) in the table.
                request.setAttribute("showNext", "false");
                request.setAttribute("showLast", "false");
            }
            request.setAttribute("searchCity", searchCity);
            request.setAttribute("lowerLimit", lowerLimit);
            request.setAttribute("noOfRowsTraversed", noOfRowsTraversed);
            request.setAttribute("message", workTypeModel.getMessage());            
            request.setAttribute("messageBGColor", workTypeModel.getMessageBGColor());
            request.setAttribute("cityList", list);
            workTypeModel.closeConnection();

                request.getRequestDispatcher("workType").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException
    {   
        doGet(request, response);
    }
}
