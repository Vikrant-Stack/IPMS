package com.ipms.ePass.controller;

import com.ipms.ePass.bean.ePassBean;
import com.ipms.ePass.model.ePassModel;
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

public class ePassController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException
    {
         int lowerLimit, noOfRowsTraversed, noOfRowsToDisplay = 15, noOfRowsInTable;
         
        System.out.println("Starting application");
        response.setContentType("text/html");
        ServletContext ctx = getServletContext();
        ePassModel ePassModel = new ePassModel();

        ePassModel.setDriver(ctx.getInitParameter("driverClass"));
        ePassModel.setUrl(ctx.getInitParameter("connectionString"));
        ePassModel.setUser(ctx.getInitParameter("db_user_name"));
        ePassModel.setPassword(ctx.getInitParameter("db_user_password"));
        ePassModel.setConnection();
        request.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "text/plain; charset=UTF-8");
        System.out.println(ePassModel.getConnection());
        String task = request.getParameter("task");
//         System.out.println("taskk --"+task);
//         if(task.equals("Save AS New")){
//             System.out.println("come heerreerer "); 
//         }
        String searchEPass= request.getParameter("searchEPass");        

        try {
            //----- This is only for Vendor key Person JQuery
            String JQstring = request.getParameter("action1");  
            System.out.println("action string -"+JQstring);
            String q = request.getParameter("q");   // field own input           
            if (JQstring != null) {
                PrintWriter out = response.getWriter();
                List<String> list = null;
                if (JQstring.equals("getCity")) {
                     list = ePassModel.getCity(q);
                }
                if (JQstring.equals("getOrgName")) {
                     list = ePassModel.getOrgName(q);
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
                ePassModel.closeConnection();
                return;
            }
        } catch (Exception e) {
            System.out.println("\n Error --ClientPersonMapController get JQuery Parameters Part-" + e);
        } 
          
         if(searchEPass==null)
             searchEPass="";

         if(task==null)
            task="";
         else if(task.equals("generateMapReport"))//start from here
         {
            List listAll = null;
            String jrxmlFilePath;
            String search= request.getParameter("searchEPass");            
            response.setContentType("application/pdf");
            ServletOutputStream servletOutputStream = response.getOutputStream();
            listAll=ePassModel.showAllData(search);            
            jrxmlFilePath = ctx.getRealPath("/report/location/CityReport.jrxml");
            byte[] reportInbytes = ePassModel.generateMapReport(jrxmlFilePath,listAll);
            response.setContentLength(reportInbytes.length);
            servletOutputStream.write(reportInbytes, 0, reportInbytes.length);
            servletOutputStream.flush();
            servletOutputStream.close();
            return;
         }else if(task.equals("generateMapXlsReport"))
         {     String jrxmlFilePath;
              List listAll=null;
               String search= request.getParameter("searchEPass");
                       response.setContentType("application/vnd.ms-excel");
                       response.addHeader("Content-Disposition", "attachment; filename=city.xls");
                       ServletOutputStream servletOutputStream = response.getOutputStream();
                       jrxmlFilePath = ctx.getRealPath("/report/location/CityReport.jrxml");
                       listAll=ePassModel.showAllData(search);
                       ByteArrayOutputStream reportInbytes =ePassModel.generateCityXlsRecordList(jrxmlFilePath, listAll);
                       response.setContentLength(reportInbytes.size());
                       servletOutputStream.write(reportInbytes.toByteArray());
                       servletOutputStream.flush();
                       servletOutputStream.close();
                       return;
         }

         else if(task.equals("Search All Records"))
             searchEPass="";        
          else if(task.equals("Save all records")||task.equals("Save AS New")||task.equals("Save"))
          {
                String ePass_id="";
                 try{
                     ePass_id = request.getParameter("ePass_id").trim();
                 }catch(Exception ex){
                     ePass_id = null;
                 }
              String work_code=request.getParameter("work_code");
              String person_id=request.getParameter("person_id");
             String location= request.getParameter("location_id");
               String valid_from= request.getParameter("valid_from");
                String valid_to= request.getParameter("valid_to");
                String remark= request.getParameter("remark");
                
             ePassBean b=new ePassBean();
             b.setePassId(ePass_id);
             b.setWorkCode(work_code);
             b.setPersonId(person_id);
             b.setLocationId(location);
             b.setValidTo(valid_to);
             b.setValidFrom(valid_from);
             b.setRemark(remark);
             

              if(task.equals("Save all records"))
                ePassModel.insertRecord(b);
              else if(task.equals("Save AS New"))
              {
                 ePass_id="";
                ePassModel.insertRecord(b);
              } 
             else if(task.equals("Save"))
              {   
             ePassModel.insertRecord(b);
              }
          }else if(task.equals("Delete"))
             ePassModel.deleteRecord(Integer.parseInt(request.getParameter("cityId")));
       
         noOfRowsInTable = ePassModel.getTotalRowsInTable(searchEPass);
        
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
         ArrayList<ePassBean> list = ePassModel.getAllRecords(lowerLimit,noOfRowsToDisplay,searchEPass);
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
            request.setAttribute("searchEPass", searchEPass);
            request.setAttribute("lowerLimit", lowerLimit);
            request.setAttribute("noOfRowsTraversed", noOfRowsTraversed);
            request.setAttribute("message", ePassModel.getMessage());            
            request.setAttribute("messageBGColor", ePassModel.getMessageBGColor());
            request.setAttribute("ePassList", list);
            ePassModel.closeConnection();

                request.getRequestDispatcher("ePassView").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException
    {   
        doGet(request, response);
    }
}
