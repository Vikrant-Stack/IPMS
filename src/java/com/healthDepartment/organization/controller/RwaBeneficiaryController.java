/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.healthDepartment.organization.controller;

import com.healthDepartment.dbCon.DBConnection;
import com.healthDepartment.organization.model.RwaBeneficiaryModel;
import com.healthDepartment.organization.tableClasses.RwaBeneficiaryBean;
import com.healthDepartment.util.UniqueIDGenerator;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrator
 */
public class RwaBeneficiaryController extends HttpServlet {
   
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        ServletContext ctx = getServletContext();
        String task=request.getParameter("task");
          int lowerLimit=0, noOfRowsTraversed=0, noOfRowsToDisplay =5, noOfRowsInTable = 0;
         if(task==null)
            task="";
        RwaBeneficiaryModel rbm=new RwaBeneficiaryModel();
          try{
            rbm.setConnection((Connection) DBConnection.getConnectionForUtf(ctx));}
            catch(Exception e)
           {
           System.out.print(e);
             }
        try {
             String emp_code="",person_name="";
             String JQstring = request.getParameter("action1");
             String q = request.getParameter("q");
            if (JQstring != null) {
                 PrintWriter out = response.getWriter();
                List<String> list = null;
                if (JQstring.equals("getRwaName"))
                {
                    list = rbm.getRwaName(q);
                }
                if (JQstring.equals("getPersonName"))
                {
                   if (request.getParameter("action2") != null && !request.getParameter("action2").isEmpty())
                     emp_code = request.getParameter("action2");

                    list = rbm.getPersonName(q,emp_code);
                }
                else if(JQstring.equals("getEmp_code"))
                {
                  if (request.getParameter("action2") != null && !request.getParameter("action2").isEmpty())
                    person_name = request.getParameter("action2");
                  list = rbm.getEmp_code(q,person_name);
                }
                   Iterator<String> iter = list.iterator();
                while (iter.hasNext()) {
                    String data = iter.next();
                        out.println(data);
                }

                return;
            }
        }
        catch(Exception e)
         {
            System.out.println(e);
         }
        try {
               if(task.equals("Delete"))
                rbm.deleteRecord(request.getParameter("rwa_beneficiary_mapping_id"));
                int rwa_beneficiary_mapping_id=0;
                try
                {
          rwa_beneficiary_mapping_id = Integer.parseInt(request.getParameter("rwa_beneficiary_mapping_id").trim());
                    }
          catch(Exception e)
                {
               rwa_beneficiary_mapping_id=0;
                }
         if(task.equals("save")|| task.equals("Save AS New"))
              {
            if(task.equals("Save AS New"))
                   rwa_beneficiary_mapping_id = 0;
          String rwa_name=request.getParameter("rwa_name");
          int emp_code=Integer.parseInt(request.getParameter("emp_code"));
          RwaBeneficiaryBean rb=new RwaBeneficiaryBean();
          rb.setRwa_beneficiary_mapping_id(rwa_beneficiary_mapping_id);
          rb.setEmp_code(emp_code);
          rb.setRwa_name(rwa_name);
          rbm.insertRecord(rb);
            }

String buttonAction = request.getParameter("buttonAction");
             if(buttonAction == null)
                 buttonAction = "none";
              try {
        lowerLimit = Integer.parseInt(request.getParameter("lowerLimit"));
        noOfRowsTraversed = Integer.parseInt(request.getParameter("noOfRowsTraversed"));
            }catch (Exception e) {
                lowerLimit = noOfRowsTraversed = 0;
            }

     noOfRowsInTable = rbm.getNoOfRows();

    if (buttonAction.equals("Next"));
      else if (buttonAction.equals("Previous")) {
                int temp = lowerLimit - noOfRowsToDisplay - noOfRowsTraversed;
                if (temp < 0) {
                    noOfRowsToDisplay = lowerLimit - noOfRowsTraversed;
                    lowerLimit = 0;
                } else {
                    lowerLimit = temp;
                }
            } else if (buttonAction.equals("First")) {
                lowerLimit = 0;
            } else if (buttonAction.equals("Last")) {
                lowerLimit = noOfRowsInTable - noOfRowsToDisplay;
                if (lowerLimit < 0) {
                    lowerLimit = 0;
                }
            }
     if (task.equals("save") || task.equals("Save AS New") ||  task.equals("Delete")) {
            lowerLimit = lowerLimit - noOfRowsTraversed;
        }
     else if (task.equals("Show All Records")) {

        }
         List<RwaBeneficiaryBean>  list=  rbm.showData(lowerLimit, noOfRowsToDisplay);
          lowerLimit = lowerLimit + list.size();
            noOfRowsTraversed = list.size();
            if ((lowerLimit - noOfRowsTraversed) == 0) {
                request.setAttribute("showFirst", "false");
                request.setAttribute("showPrevious", "false");
            }
            if (lowerLimit == noOfRowsInTable) {
                request.setAttribute("showNext", "false");
                request.setAttribute("showLast", "false");
            }
         request.setAttribute("list", list);
         request.setAttribute("lowerLimit",lowerLimit);
         request.setAttribute("noOfRowsTraversed",noOfRowsTraversed);
        request.setAttribute("IDGenerator", new UniqueIDGenerator());
        request.setAttribute("message", rbm.getMessage());
             request.getRequestDispatcher("rwaBeneficiary").forward(request, response);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
