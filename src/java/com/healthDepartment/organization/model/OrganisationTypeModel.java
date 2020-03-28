/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthDepartment.organization.model;

import com.healthDepartment.organization.tableClasses.OrganisationType;
import com.healthDepartment.util.KrutiDevToUnicodeConverter;
import com.healthDepartment.util.UnicodeToKrutiDevConverter;
import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
 * @author Soft_Tech
 */
public class OrganisationTypeModel {

    private static Connection connection;
   
    private String message;
    private String msgBgColor;
    private final String COLOR_OK = "lightyellow";
    private final String COLOR_ERROR = "red";
    private KrutiDevToUnicodeConverter krutiToUnicode = new KrutiDevToUnicodeConverter();
    private UnicodeToKrutiDevConverter unicodeToKruti = new UnicodeToKrutiDevConverter();


    public void setConnection(Connection con) {
        try {

            connection = con;
        } catch (Exception e) {
             System.out.println("OrganisationTypeModel setConnection() Error: " + e);
        }
    }

    public int getNoOfRows(String searchOrgType) {
        int noOfRows = 0;
        searchOrgType = krutiToUnicode.convert_to_unicode(searchOrgType);
        try {
            String query = " SELECT Count(*) "
                    + " FROM organisation_type "
                    + " WHERE IF('" + searchOrgType + "' = '', org_type_name LIKE '%%',org_type_name =?) "
                    + " ORDER BY org_type_name ";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, searchOrgType);
            ResultSet rset = pst.executeQuery();
            rset.next();
            noOfRows = Integer.parseInt(rset.getString(1));
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return noOfRows;
    }
    public List<OrganisationType> showAllData(String searchOrgType )
    {     List<OrganisationType> list = new ArrayList<OrganisationType>();
          searchOrgType = krutiToUnicode.convert_to_unicode(searchOrgType);
        String query = " SELECT organisation_type_id, org_type_name, description "
                + " FROM organisation_type "
                 + " WHERE IF('" + searchOrgType + "' = '', org_type_name LIKE '%%',org_type_name=?) ";              
                 try {
            PreparedStatement pstmt = connection.prepareStatement(query);
              pstmt.setString(1, searchOrgType);
            ResultSet rset = pstmt.executeQuery();          
            while (rset.next()) {
                OrganisationType organisationType = new OrganisationType();
                organisationType.setOrganisation_type_id(rset.getInt("organisation_type_id"));
                organisationType.setOrg_type_name(rset.getString("org_type_name"));
                organisationType.setDescription(rset.getString("description"));
                list.add(organisationType);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return list;
    }
    public List<OrganisationType> showData(int lowerLimit, int noOfRowsToDisplay, String searchOrgType) {
        List<OrganisationType> list = new ArrayList<OrganisationType>();
        searchOrgType = krutiToUnicode.convert_to_unicode(searchOrgType);
        // Use DESC or ASC for descending or ascending order respectively of fetched data.
        String query = " SELECT organisation_type_id, org_type_name, description "
                + " FROM organisation_type "
                + " WHERE IF('" + searchOrgType + "' = '', org_type_name LIKE '%%',org_type_name =?) "
                + " ORDER BY org_type_name "
                + " LIMIT " + lowerLimit + ", " + noOfRowsToDisplay;
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, searchOrgType);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                OrganisationType organisationType = new OrganisationType();
                organisationType.setOrganisation_type_id(rset.getInt("organisation_type_id"));
                organisationType.setOrg_type_name(unicodeToKruti.Convert_to_Kritidev_010(rset.getString("org_type_name")));
                organisationType.setDescription(unicodeToKruti.Convert_to_Kritidev_010(rset.getString("description")));
                list.add(organisationType);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return list;
    }

    public int insertRecord(OrganisationType organisationType) {
        String query = "INSERT INTO organisation_type(org_type_name,description,revision_no,active,remark) VALUES(?,?,?,?,?)";
        int rowsAffected = 0;
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, (organisationType.getOrg_type_name()));
            pstmt.setString(2, (organisationType.getDescription()));
              pstmt.setInt(3,organisationType.getRevision_no());
            pstmt.setString(4,"Y");
            pstmt.setString(5,"OK");
          
            
            rowsAffected = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e);
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

    public  int updateRecord(OrganisationType organisationType,int organisation_type_id) {
        int revision=OrganisationTypeModel.getRevisionno(organisationType,organisation_type_id);
         int updateRowsAffected = 0;
           boolean status=false;
        String query1 = "SELECT max(revision_no) revision_no FROM organisation_type WHERE organisation_type_id = "+organisation_type_id+"  && active=? ";
        String query2 = "UPDATE organisation_type SET active=? WHERE organisation_type_id=? and revision_no=?";
        String query3 = "INSERT INTO organisation_type(organisation_type_id,org_type_name,description,revision_no,active,remark) VALUES(?,?,?,?,?,?)";
        int rowsAffected = 0;
        try {
            PreparedStatement pstmt = connection.prepareStatement(query1);
//           pstmt.setInt(1,organisation_type_id);
           pstmt.setString(1, "Y");
           
           
           ResultSet rs = pstmt.executeQuery();
                if(rs.next()){
                PreparedStatement pstm = connection.prepareStatement(query2);
               
                 pstm.setString(1,"n");
               
                 pstm.setInt(2,organisation_type_id);
                 pstm.setInt(3,revision);
                  updateRowsAffected = pstm.executeUpdate();
             if(updateRowsAffected >= 1){
                   revision = rs.getInt("revision_no")+1;
                    PreparedStatement psmt = (PreparedStatement) connection.prepareStatement(query3);
                     psmt.setInt(1,(organisation_type_id));
                    psmt.setString(2,(organisationType.getOrg_type_name()));
                    psmt.setString(3, (organisationType.getDescription()));
                    psmt.setInt(4,revision);
                    psmt.setString(5,"Y");
                    psmt.setString(6,"OK");
                    rowsAffected = psmt.executeUpdate();
                   if(rowsAffected > 0)
                   status=true;
                else 
                  status=false;
             }
                 
                 
                }
            
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        if (rowsAffected > 0) {
            message = "Record updated successfully.";
            msgBgColor = COLOR_OK;
        } else {
            message = "Cannot update the record, some error.";
            msgBgColor = COLOR_ERROR;
        }
        return rowsAffected;
    }

    public int deleteRecord(int organisation_type_id) {
        String query = "DELETE FROM organisation_type WHERE organisation_type_id=" + organisation_type_id;
        int rowsAffected = 0;
        try {
            rowsAffected = connection.prepareStatement(query).executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        if (rowsAffected > 0) {
            message = "Record deleted successfully.";
            msgBgColor = COLOR_OK;
        } else {
            message = "Cannot delete the record, some error.";
            msgBgColor = COLOR_ERROR;
        }
        return rowsAffected;
    }
public static int getRevisionno(OrganisationType bean,int organisation_type_id) {
        int revision=0;
        try {

            String query = " SELECT max(revision_no) as revision_no FROM organisation_type WHERE organisation_type_id ="+organisation_type_id+"  && active='Y';";

            PreparedStatement pstmt = (PreparedStatement) connection.prepareStatement(query);
           
            
           
            ResultSet rset = pstmt.executeQuery();

            while (rset.next())
            {
                revision = rset.getInt("revision_no");

            }
        } catch (Exception e) {
        }
        return revision;
    }

    public List<String> getOrgType(String q) {
        List<String> list = new ArrayList<String>();
        String query = " SELECT organisation_type_id, org_type_name FROM organisation_type o ORDER BY org_type_name ";
        try {
            ResultSet rset = connection.prepareStatement(query).executeQuery();
            int count = 0;
          
            while (rset.next()) {    // move cursor from BOR to valid record.
                String org_type_name = (rset.getString("org_type_name"));
            
                    list.add(org_type_name);
                    count++;
            
            }
            if (count == 0) {
                list.add("No such Organisation Type exists.");
            }
        } catch (Exception e) {
            System.out.println("getOrgType ERROR - " + e);
        }
        return list;
    }
 public byte[] generateSiteList(String jrxmlFilePath,List list) {
        byte[] reportInbytes = null;
        HashMap mymap = new HashMap();
        try {           
            JRBeanCollectionDataSource jrBean=new JRBeanCollectionDataSource(list);
            JasperReport compiledReport = JasperCompileManager.compileReport(jrxmlFilePath);
            reportInbytes = JasperRunManager.runReportToPdf(compiledReport, null, jrBean);
        } catch (Exception e) {
            System.out.println("Error: in OrganisationTypeModel generateSiteList() JRException: " + e);
        }
        return reportInbytes;
    }
    public  ByteArrayOutputStream generateOrginisationXlsRecordList(String jrxmlFilePath,List list) {
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
                    System.out.println("OrginisationTypeStatusModel generateOrgnisitionXlsRecordList() JRException: " + e);
                }
                return bytArray;
            }

    public String getMessage() {
        return message;
    }

    public String getMsgBgColor() {
        return msgBgColor;
    }

  

    public void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println("OrganisationTypeModel closeConnection() Error: " + e);
        }
    }
}
