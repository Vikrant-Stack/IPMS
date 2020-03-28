package com.healthDepartment.organization.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.healthDepartment.organization.tableClasses.OrganisationName;
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
 * @author Tarun
 */
public class OrganisationNameModel {

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
             System.out.println("QtOohDefaultsModel setConnection() Error: " + e);
        }
    }

    public int getNoOfRows() {
        int noOfRows = 0;
        try {
            ResultSet rset = connection.prepareStatement("SELECT COUNT(*) FROM organisation_name ").executeQuery();
            rset.next();
            noOfRows = Integer.parseInt(rset.getString(1));
        } catch (Exception e) {
            System.out.println("OrganisationNameModel getNoOfRows() Error: " + e);
        }
        return noOfRows;
    }

    public int getNoOfRows(String organisation_name) {
        int noOfRows = 0;
        organisation_name = krutiToUnicode.convert_to_unicode(organisation_name);
        try {
            String query = " select  COUNT(*) from organisation_name  where  organisation_name like ?  ";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, organisation_name + '%');
            ResultSet rset = pstmt.executeQuery();
            rset.next();
            noOfRows = Integer.parseInt(rset.getString(1));
        } catch (Exception e) {
            System.out.println("OrganisationNameModel getNoOfRows() Error: " + e);
        }
        return noOfRows;
    }

//    public List<OrganisationName> showData(int lowerLimit, int noOfRowsToDisplay, String org_name) {
//        List<OrganisationName> list = new ArrayList<OrganisationName>();
//        org_name = (org_name);
//        String query;
//        PreparedStatement pstmt = null;
//        // Use DESC or ASC for descending or ascending order respectively of fetched data.
//        try {
//            if (org_name == null || org_name.isEmpty()) {
//                query = "SELECT * FROM organisation_name  ORDER BY organisation_name "
//                        + " LIMIT " + lowerLimit + ", " + noOfRowsToDisplay;
//                pstmt = connection.prepareStatement(query);
//            } else {
//                query = "SELECT * FROM organisation_name where  organisation_name like ? ORDER BY organisation_name "
//                        + " LIMIT " + lowerLimit + ", " + noOfRowsToDisplay;
//                pstmt = connection.prepareStatement(query);
//                pstmt.setString(1, org_name + '%');
//            }
//            ResultSet rset = pstmt.executeQuery();
//            while (rset.next()) {
//                OrganisationName orgName = new OrganisationName();
//                orgName.setOrganisation_id(rset.getInt("organisation_id"));
//                orgName.setOrganisation_sub_type_id(rset.getString("organisation_name"));
//                orgName.setOrganisation_name((rset.getString("organisation_name")));
//                orgName.setDescription((rset.getString("description")));
//                list.add(orgName);
//            }
//        } catch (Exception e) {
//            System.out.println("OrganisationNameModel showData() Error: " + e);
//        }
//        return list;
//    }

     public List<OrganisationName> showData(int lowerLimit, int noOfRowsToDisplay, String searchOrgType, String searchOrgSubType) {
        List<OrganisationName> list = new ArrayList<OrganisationName>();
        searchOrgType = (searchOrgType);
        searchOrgSubType =(searchOrgSubType);
        // Use DESC or ASC for descending or ascending order respectively of fetched data.
        String query =     "Select o.organisation_id,os.organisation_sub_type_id,ot.organisation_type_id,o.organisation_name,o.description,os.organisation_sub_type_name,ot.org_type_name from organisation_name o,organisation_sub_type os,organisation_type ot where o.organisation_sub_type_id=os.organisation_sub_type_id and ot.organisation_type_id=os.organisation_type_id and ot.active='Y' and o.active='Y' and os.active='Y'"
               
                + " LIMIT " + lowerLimit + ", " + noOfRowsToDisplay;
        try {
            ResultSet rset = connection.prepareStatement(query).executeQuery();
            while (rset.next()) {
                OrganisationName organisationSubType = new OrganisationName();
                organisationSubType.setOrganisation_id(rset.getInt("organisation_id"));
                organisationSubType.setOrganisation_type_id(rset.getInt("organisation_type_id"));
                 organisationSubType.setOrganisation_sub_type_id((rset.getInt("organisation_sub_type_id")));
                    organisationSubType.setOrganisation_type((rset.getString("org_type_name")));
                organisationSubType.setOrganisation_name((rset.getString("organisation_name")));
                organisationSubType.setOrganisation_sub_type_name((rset.getString("organisation_sub_type_name")));
                organisationSubType.setDescription(rset.getString("description"));
                list.add(organisationSubType);
            }
        } catch (Exception e) {
            System.out.println("Error: OrganisationSubTypeModel-" + e);
        }
        return list;
    }

    
    
    
    
    
    
    public int insertRecord(OrganisationName organisation_name) {
        String query = "INSERT INTO organisation_name(organisation_name,organisation_sub_type_id,description,revision_no,active,remark) VALUES(?,?,?,?,?,?) ";
        int rowsAffected = 0;
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
//            pstmt.setInt(1,(organisation_name.getOrganisation_id()));
            pstmt.setString(1, (organisation_name.getOrganisation_name()));
            pstmt.setInt(2,(organisation_name.getOrganisation_sub_type_id()));
            pstmt.setString(3, (organisation_name.getDescription()));
             pstmt.setInt(4,organisation_name.getRevision_no());
            pstmt.setString(5,"Y");
            pstmt.setString(6,"OK");
            rowsAffected = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("OrganisationNameModel insertRecord() Error: " + e);
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

    public int updateRecord(OrganisationName organisation_name,int organisation_id) {
      int revision=OrganisationNameModel.getRevisionno(organisation_name,organisation_id);
         int updateRowsAffected = 0;
           boolean status=false;
        String query1 = "SELECT max(revision_no) revision_no FROM organisation_name WHERE organisation_id = "+organisation_id+"  && active=? ";
        String query2 = "UPDATE organisation_name SET active=? WHERE organisation_id=? and revision_no=?";
        String query3 = "INSERT INTO organisation_name(organisation_id,organisation_name,organisation_sub_type_id,description,revision_no,active,remark) VALUES(?,?,?,?,?,?,?) ";
        int rowsAffected = 0;
        try {
            PreparedStatement pstmt = connection.prepareStatement(query1);
//           pstmt.setInt(1,organisation_type_id);
           pstmt.setString(1, "Y");
           
           
           ResultSet rs = pstmt.executeQuery();
                if(rs.next()){
                PreparedStatement pstm = connection.prepareStatement(query2);
               
                 pstm.setString(1,"n");
               
                 pstm.setInt(2,organisation_id);
                 pstm.setInt(3,revision);
                  updateRowsAffected = pstm.executeUpdate();
             if(updateRowsAffected >= 1){
                   revision = rs.getInt("revision_no")+1;
                    PreparedStatement psmt = (PreparedStatement) connection.prepareStatement(query3);
                     psmt.setInt(1,(organisation_name.getOrganisation_id()));
            psmt.setString(2, (organisation_name.getOrganisation_name()));
            psmt.setInt(3,(organisation_name.getOrganisation_sub_type_id()));
            psmt.setString(4, (organisation_name.getDescription()));
             psmt.setInt(5,revision);
            psmt.setString(6,"Y");
            psmt.setString(7,"OK");
                    rowsAffected = psmt.executeUpdate();
                   if(rowsAffected > 0)
                   status=true;
                else 
                  status=false;
             }
                 
                 
                }
        } catch (Exception e) {
            System.out.println("OrganisationNameModel updateRecord() Error: " + e);
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

     public static int getRevisionno(OrganisationName organisationName,int organisation_id) {
        int revision=0;
        try {

            String query = " SELECT max(revision_no) as revision_no FROM organisation_name WHERE organisation_id ="+organisation_id+"  && active='Y';";

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
    
    
    public int deleteRecord(int organisation_id) {
        String query = "DELETE FROM organisation_name WHERE organisation_id = " + organisation_id;
        int rowsAffected = 0;
        try {
            rowsAffected = connection.prepareStatement(query).executeUpdate();
        } catch (Exception e) {
            System.out.println("OrganisationNameModel deleteRecord() Error: " + e);
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

    
     public int getOrganisationID(String org_name) {
        org_name = (org_name);
        String query = "SELECT organisation_id FROM organisation_name WHERE organisation_name = ?";
        int id = 0;
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, org_name);
            ResultSet rset = pstmt.executeQuery();
            rset.next();    // move cursor from BOR to valid record.
            id = rset.getInt("organisation_id");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return id;
    }
    
    
    
     public int getOrganisationTypeID(String org_type_name) {
        org_type_name = (org_type_name);
        String query = "SELECT organisation_type_id FROM organisation_type WHERE org_type_name = ?";
        int id = 0;
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, org_type_name);
            ResultSet rset = pstmt.executeQuery();
            rset.next();    // move cursor from BOR to valid record.
            id = rset.getInt("organisation_type_id");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return id;
    }
    
    public int getOrganisationSubTypeID(String org_sub_type_name) {
        org_sub_type_name = (org_sub_type_name);
        String query = "SELECT organisation_sub_type_id FROM organisation_sub_type WHERE organisation_sub_type_name = ?";
        int id = 0;
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, org_sub_type_name);
            ResultSet rset = pstmt.executeQuery();
            rset.next();    // move cursor from BOR to valid record.
            id = rset.getInt("organisation_sub_type_id");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return id;
    }
    
    
    
    public List<String> getOrganisationTypeName(String q) {
        List<String> list = new ArrayList<String>();
        String query = "SELECT org_type_name FROM organisation_type ORDER BY org_type_name";
        try {
            ResultSet rset = connection.prepareStatement(query).executeQuery();
            int count = 0;
         
            while (rset.next()) {    // move cursor from BOR to valid record.
                String orgTypeName = (rset.getString("org_type_name"));
               
                    list.add(orgTypeName);
                    count++;
             
            }
        } catch (Exception e) {
            System.out.println("Error:OrganisationNameModel--getOrganationNameList()-- " + e);
        }
        return list;
    }
    
    
     public List<String> getOrganisationSubTypeName(String q,String organisation_type) {
        List<String> list = new ArrayList<String>();
        String query = "SELECT organisation_sub_type_name FROM organisation_sub_type ORDER BY organisation_sub_type_name";
        try {
            ResultSet rset = connection.prepareStatement(query).executeQuery();
            int count = 0;
            q = q.trim();
            while (rset.next()) {    // move cursor from BOR to valid record.
                String orgTypeName = (rset.getString("organisation_sub_type_name"));
                if (orgTypeName.toUpperCase().startsWith(q.toUpperCase())) {
                    list.add(orgTypeName);
                    count++;
                }
            }
        } catch (Exception e) {
            System.out.println("Error:OrganisationNameModel--getOrganationNameList()-- " + e);
        }
        return list;
    }
    
      public List<String> getOrganisationName(String q) {
        List<String> list = new ArrayList<String>();
        String query = "SELECT organisation_name FROM organisation_name ORDER BY organisation_name ";
        try {
            ResultSet rset = connection.prepareStatement(query).executeQuery();
            int count = 0;
           
            while (rset.next()) {    // move cursor from BOR to valid record.
                String orgTypeName = (rset.getString("organisation_name"));
             
                    list.add(orgTypeName);
                    count++;
              
            }
        } catch (Exception e) {
            System.out.println("Error:OrganisationNameModel--getOrganationNameList()-- " + e);
        }
        return list;
    }
    
    public List<String> getOrganisationName(String q,String organisation_type,String organisation_sub_type) {
        List<String> list = new ArrayList<String>();
        String query = "SELECT organisation_name FROM organisation_name ORDER BY organisation_name ";
        try {
            ResultSet rset = connection.prepareStatement(query).executeQuery();
            int count = 0;
            q = q.trim();
            while (rset.next()) {    // move cursor from BOR to valid record.
                String orgTypeName = unicodeToKruti.Convert_to_Kritidev_010(rset.getString("organisation_name"));
                if (orgTypeName.toUpperCase().startsWith(q.toUpperCase())) {
                    list.add(orgTypeName);
                    count++;
                }
            }
        } catch (Exception e) {
            System.out.println("Error:OrganisationNameModel--getOrganationNameList()-- " + e);
        }
        return list;
    }

    public byte[] generateSiteList(String jrxmlFilePath) {
        byte[] reportInbytes = null;
        HashMap mymap = new HashMap();
        try {
            JasperReport compiledReport = JasperCompileManager.compileReport(jrxmlFilePath);
            reportInbytes = JasperRunManager.runReportToPdf(compiledReport, mymap, connection);
        } catch (Exception e) {
            System.out.println("Error: in OrganisationNameModel generatReport() JRException: " + e);
        }
        return reportInbytes;
    }
    public ByteArrayOutputStream generateOrginisationXlsRecordList(String jrxmlFilePath) {
                ByteArrayOutputStream bytArray = new ByteArrayOutputStream();
              //  HashMap mymap = new HashMap();
                try {
                   // JRBeanCollectionDataSource jrBean=new JRBeanCollectionDataSource(list);
                    JasperReport compiledReport = JasperCompileManager.compileReport(jrxmlFilePath);
                    JasperPrint jasperPrint = JasperFillManager.fillReport(compiledReport, null, connection);
                    JRXlsExporter exporter = new JRXlsExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                    exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, bytArray);
                    exporter.exportReport();
                } catch (Exception e) {
                    System.out.println("OrginisationStatusModel generateOrgnisitionXlsRecordList() JRException: " + e);
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
            System.out.println("OrganisationNameModel closeConnection() Error: " + e);
        }
    }
}
