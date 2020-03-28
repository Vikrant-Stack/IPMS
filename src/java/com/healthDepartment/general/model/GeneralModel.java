/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.healthDepartment.general.model;

import com.healthDepartment.general.tableClasses.GenralBean;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.ColorSupported;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.PrinterName;
import javax.print.attribute.standard.Sides;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;

/**
 *
 * @author jpss
 */
public class GeneralModel {
    private Connection connection;
    static List<String> printerList ;
    public byte[] generateRecordList(String jrxmlFilePath, List list) {
        byte[] reportInbytes = null;
        HashMap mymap = new HashMap();
        try {
            JRBeanCollectionDataSource jrBean = new JRBeanCollectionDataSource(list);
            JasperReport compiledReport = JasperCompileManager.compileReport(jrxmlFilePath);
            reportInbytes = JasperRunManager.runReportToPdf(compiledReport, null, jrBean);
//            JasperReport compiledReport1 = JasperCompileManager.compileReport(jrxmlFilePath);
//            JasperPrint jasperPrint = JasperFillManager.fillReport(compiledReport1, null, jrBean);
//            String path = createAppropriateDirectories1("C:/ssadvt_repository/prepaid/RideReceipt");
//            path = path + "/receipt.pdf";
//            JRPdfExporter exporter = new JRPdfExporter();
//            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, path);
//            exporter.exportReport();
        } catch (Exception e) {
            System.out.println("GeneralModel generateRecordList() JRException: " + e);
        }
        return reportInbytes;
    }

    public ByteArrayOutputStream generateExcelList(String jrxmlFilePath, List list) {
        ByteArrayOutputStream reportInbytes = new ByteArrayOutputStream();
        HashMap mymap = new HashMap();
        try {
            JRBeanCollectionDataSource jrBean = new JRBeanCollectionDataSource(list);
            JasperReport compiledReport = JasperCompileManager.compileReport(jrxmlFilePath);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compiledReport, null, jrBean);
            JRXlsExporter exporter = new JRXlsExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, reportInbytes);
            exporter.exportReport();
            //print(jasperPrint);
        } catch (Exception e) {
            System.out.println("GeneralModel generateExcelList() JRException: " + e);
        }
        return reportInbytes;
    }

    public void SavePdf(String jrxmlFilePath, List list, String reportName) {
        ByteArrayOutputStream reportInbytes = new ByteArrayOutputStream();
        HashMap mymap = new HashMap();
        try {
            JRBeanCollectionDataSource jrBean = new JRBeanCollectionDataSource(list);
            JasperReport compiledReport = JasperCompileManager.compileReport(jrxmlFilePath);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compiledReport, null, jrBean);
            //JRXlsExporter exporter = new JRXlsExporter();
            String path = createAppropriateDirectories1("C:/ssadvt_repository/prepaid/temp_pdf");
            path = path + "/" +reportName;
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, path);
            exporter.exportReport();
        } catch (Exception e) {
            System.out.println("GeneralModel generateExcelList() JRException: " + e);
        }
        //return reportInbytes;
    }

    public ByteArrayOutputStream generateANDSavePdf(String jrxmlFilePath, List list) {
        ByteArrayOutputStream reportInbytes = new ByteArrayOutputStream();
        HashMap mymap = new HashMap();
        try {
            JRBeanCollectionDataSource jrBean = new JRBeanCollectionDataSource(list);
            JasperReport compiledReport = JasperCompileManager.compileReport(jrxmlFilePath);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compiledReport, null, jrBean);
            //JRXlsExporter exporter = new JRXlsExporter();
            String path = createAppropriateDirectories1("C:/ssadvt_repository/prepaid/RideReceipt");
            path = path + "/receipt.pdf";
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, path);
            exporter.exportReport();
            print(jasperPrint, path);
        } catch (Exception e) {
            System.out.println("GeneralModel generateExcelList() JRException: " + e);
        }
        return reportInbytes;
    }

    public void print(JasperPrint jsprPrint, String fileName) {
        String query = "SELECT printer_name, printer_path, no_of_copies FROM printer_detail";
        try{
            JasperPrint jasperPrint = jsprPrint;//getJasperPrint();
            ResultSet rs = connection.prepareStatement(query).executeQuery();
            String selectedPrinter = "";
            int no_of_copies = 0;
            if(rs.next()){
                selectedPrinter = rs.getString("printer_path");//"\\\\navitus2-pc\\hp officejet 5600 series";//AllPrinter.getDepartmentPrinter("Admin");
                no_of_copies = rs.getInt("no_of_copies");
            }
            //selectedPrinter = "\\\\navitus2-pc\\hp officejet 5600 series";
            PrinterJob printerJob = PrinterJob.getPrinterJob();
            PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
            PrintService selectedService = null;

            if(services.length != 0 || services != null)
            {
                for(PrintService service : services){
                    String existingPrinter = service.getName().toLowerCase();
                    System.out.println("Printer : " + existingPrinter);
                    if(existingPrinter.equals(selectedPrinter))
                    {
                        selectedService = service;
                        break;
                    }
                }
                if(selectedService != null)
                {
                    printerJob.setPrintService(selectedService);
                    
                    if(no_of_copies > 0)
                        printerJob.setCopies(no_of_copies);
                    System.out.println("Printing...");
                    boolean printSucceed = JasperPrintManager.printReport(jsprPrint, false);
                    //boolean printSucceed2 = JasperPrintManager.printReport(fileName, false);
//                    PrintReport pr = new PrintReport();
//                    pr.print(jasperPrint);

                }
            }
        }catch(Exception ex){
            System.out.println("GeneralModel print() JRException: " + ex);
        }
    }

    public static List<String> getPrinter() {
        List<String> list = new ArrayList<String>();
        try{
            PrinterJob printerJob = PrinterJob.getPrinterJob();
            PrintService[] serv = PrinterJob.lookupPrintServices();
            PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
            PrintService selectedService = null;
            if(services.length != 0 || services != null)
            {
                for(PrintService service : services){
                    String existingPrinter = service.getName().toLowerCase();
                    list.add(existingPrinter);
                }
            }
        }catch(Exception ex){
            System.out.println("GeneralModel getPrinter() JRException: " + ex);
        }
        return list;
    }

//    public static List<String> getPrinter() {
//        List<String> list = new ArrayList<String>();
//        POSPrinterControl113 printer = (jpos.POSPrinterControl113) new POSPrinter();
//CashDrawerControl113 drawer = (CashDrawerControl113) new CashDrawer();
//try {
//        printer.open("POSPrinter");
//        printer.claim(100);
//
//        printer.setDeviceEnabled(true);
//    } catch (Exception e) {
//        System.err.println("Printer deactivated " + e.getMessage());
////        printerdisabled = true;
////        drawerdisabled  = true;
////        return;
//    }
//    try {
//        drawer.open("CashDrawer");
//        drawer.claim(100);
//        drawer.setDeviceEnabled(true);
//    } catch (Exception e) {
//        System.out.println("Cashdrawer deactivated: " + e.getMessage());
////        drawerdisabled = true;
//        //return;
//    }
//        return list;
//    }

//    public static List<String> getPrinter2() {
//        List<String> list = new ArrayList<String>();
//        try{
//            PrinterJob printerJob = PrinterJob.getPrinterJob();
//            PrintService[] services = PrinterJob.lookupPrintServices();
//            //PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
//            PrintService selectedService = null;
//            if(services.length != 0 || services != null)
//            {
//                for(PrintService service : services){
//                    String existingPrinter = service.getName().toLowerCase();
//                    list.add(existingPrinter);
//                }
//            }
//        }catch(Exception ex){
//            System.out.println("GeneralModel getPrinter() JRException: " + ex);
//        }
//        return list;
//    }
//    public static List<String> getPrinter() {
//        List<String> list = new ArrayList<String>();
//        DocFlavor psFlavor = 
//           new DocFlavor("application/postscript", "java.io.OutputStream");
//        StreamPrinter2D[] streamPrinter[] = PrinterServiceLookup.lookupStreamPrinters(psFlavor);
//        if(streamPrinter != null && streamPrinter.length > 0) {
//           StreamPrinter2D sprinter = streamPrinter[0];
//           try {
//              FileOutputStream fos = new FileOutputStream("out.ps");
//              PrinterJob pjob =
//                 PrinterJob.getPrinterJob(sprinter, fos);
//              pjob.setPageable(this);
//              pjob.print();
//              fos.close();
//           } catch (IOException e) {
//           } catch (PrinterException e) {
//           }
//        }
//        return list;
//    }

//    public static List<String> getPrinter2() {
//        List<String> list = new ArrayList<String>();
//        Doc psDoc = new PrintDoc("file.ps");
//        // Gets the format of the document
//        DocFlavor psFlavor = psDoc.getDocFlavor();
//        // Creates a new attribute set
//        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
//        // Prints 5 copies
//        aset.add(new Copies(5));
//        // Specifies the size of the medium
//        aset.add(MediaSize.A4);
//        // Prints two-sided
//        aset.add(Sides.DUPLEX);
//        // Staples the pages
//        aset.add( FinishingsBinding STAPLE);
//        /* Locates printers that can print the specified document format
//         * with the specified attribute set
//         */
//        PrintService[] services =
//        PrintServiceLookup.lookupPrintServices(psFlavor, aset);
//        if (services != null && services.length > 0) {
//           DocPrintJob job = services[0].createPrintJob();
//           try {
//              job.print(psDoc, aset);
//           }catch (PrintException pe) {
//        }
//        }
//        return list;
//    }
    public static List<String> getPrinter2() {
        List<String> list = new ArrayList<String>();
        printerList = list;
     PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);



	        System.out.println("Printer Services found:");
                printerList.add("Printer Services found:");
	        printService(services);



	        // Look up the default print service

	        PrintService service = PrintServiceLookup.lookupDefaultPrintService();



	        if (service!=null) {

	            System.out.println("Default Printer Service found:");
                    printerList.add("Default Printer Service found:");
	            System.out.println("t" + service);
                    printerList.add(service.getName());

	        }



	        // find services that support a particular input format (e.g. JPEG)

	        services = PrintServiceLookup.lookupPrintServices(DocFlavor.INPUT_STREAM.JPEG, null);

	        System.out.println("Printer Services with JPEG support:");
                printerList.add("Printer Services with JPEG support:");

	        printService(services);



	        // find printer service by name

	        AttributeSet aset = new HashAttributeSet();

	        aset.add(new PrinterName("Microsoft XPS Document Writer", null));

	        services = PrintServiceLookup.lookupPrintServices(null, aset);



	        System.out.println("Printer Service Microsoft XPS Document Writer:");
                printerList.add("Printer Service Microsoft XPS Document Writer:");

	        printService(services);


	        // find services that support a set of print job capabilities (e.g. color)

	        aset = new HashAttributeSet();

	        aset.add(ColorSupported.SUPPORTED);

	        services = PrintServiceLookup.lookupPrintServices(null, aset);



	        System.out.println("Printer Services with color support:");
                printerList.add("Printer Services with color support:");
	        printService(services);

                return printerList;

	    }



	    private static void printService(PrintService[] services) {

	        if (services!=null && services.length>0) {

	            for (int i = 0; i < services.length; i++) {

	                System.out.println("t" + services[i]);
                        printerList.add(services[i].getName());

	            }

	        }

	    }



    public static void refreshSystemPrinterList() {

    Class[] classes = PrintServiceLookup.class.getDeclaredClasses();

    for (int i = 0; i < classes.length; i++) {

        if ("javax.print.PrintServiceLookup$Services".equals(classes[i].getName())) {

            sun.awt.AppContext.getAppContext().remove(classes[i]);
            break;
        }
    }
}



//    private static JasperPrint getJasperPrint() {
//            return JasperPrinterCreator.getJasperprint();
//    }


    public java.sql.Date convertToSqlDate(String date) throws ParseException {
        java.sql.Date finalDate = null;
        String strD = date;
        String[] str1 = strD.split("-");
        strD = str1[1] + "/" + str1[0] + "/" + str1[2]; // Format: mm/dd/yy
        finalDate = new java.sql.Date(DateFormat.getDateInstance(DateFormat.SHORT, new Locale("en", "US")).parse(strD).getTime());
        return finalDate;
    }

    public String sendSmsToAssignedFor(String numberStr1, String messageStr1) {
        String result = "";
        try {
            //String query = "SELECT si.user_name, si.user_password, si.sender_id FROM sms_info si ";
//            PreparedStatement pstmt = connection.prepareStatement(query);
//            ResultSet rset = pstmt.executeQuery();
//            rset.next();
            String host_url = "http://api.mVaayoo.com/mvaayooapi/MessageCompose?";
//            String user_name = rset.getString("user_name");        // e.g. "jpss1277@gmail.com"
//            String user_password = rset.getString("user_password"); // e.g. "8826887606"
//            String sender_id = java.net.URLEncoder.encode(rset.getString("sender_id"), "UTF-8");         // e.g. "TEST+SMS"
            messageStr1 = calstr(messageStr1);
            //String hex = calstr(messageStr1);
            String sender_id = java.net.URLEncoder.encode("TEST SMS", "UTF-8");         // e.g. "TEST+SMS"
            //String msg = String.format("%x", new BigInteger(1, messageStr1.getBytes()));
            System.out.println("messageStr1 is =" + messageStr1);
            messageStr1 = java.net.URLEncoder.encode(messageStr1, "UTF-8");

            String queryString = "user=" + "jpss1277@gmail.com" + ":" + "8826887606" + "&senderID=" + sender_id + "&receipientno=" + numberStr1 + "&msgtype=4&dcs=8&ishex=1&msgtxt=" + messageStr1;
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
    
    public String dhex(int str) {
        //Integer a = new Integer(str);
        return Integer.toString(str, 16).toUpperCase();
        //return (str+0).toString().toUpperCase();
    }

   public String calstr (String str){// convert unicode to hexadecimal
	int haut = 0;
	//int n = 0;
	String CPstring = "";
	for (int i = 0; i < str.length(); i++)
	{
		int b = str.charAt(i); 
		if (b < 0 || b > 0xFFFF) {
			CPstring += "Error " + dhex(b) + "!";
		}
		if (haut != 0) {
			if (0xDC00 <= b && b <= 0xDFFF) {
				CPstring += dhex(0x10000 + ((haut - 0xD800) << 10) + (b - 0xDC00)) + ' ';
				haut = 0;
				continue;
				}
			else {
				CPstring += "!erreur " + dhex(haut) + "!";
				haut = 0;
				}
			}
		if (0xD800 <= b && b <= 0xDBFF) {
			haut = b;
			}
		else {
			CPstring += dhex(b) + ' ';
			}
	}
	CPstring = CPstring.substring(0, CPstring.length()-1);
	
	String hex = convertCP2HexNCR(CPstring);
        return hex;
	//var eu = convertHex2EU(hex);
	
//	document.uni.name2.value = convertCP2DecNCR(CPstring);
//	document.uni.name3.value = convertCP2HexNCR(CPstring);
//	document.uni.name4.value = convertCP2UTF8(CPstring);
//	document.uni.name5.value = eu;
}

public String convertCP2HexNCR (String argstr)
{
  String outputString = "";
  argstr = argstr.replace("\\s+", "");
  if (argstr.length() == 0) { return ""; }
  argstr = argstr.replace("\\s+/g", " ");
  String[] listArray = argstr.split(" ");
  for ( int i = 0; i < listArray.length; i++ )
  {
    int n = Integer.parseInt(listArray[i], 16);
//    if(n == 32) // for space
//        outputString += "00" + dhex(n);// + ';';
    if((n == 32 || n >= 65 && n <= 90) || (n >= 97 && n <= 122) || (n >= 48 && n <= 57) || n == 45 || n == 46 || n == 58)
        outputString += "00" + dhex(n);// + ';';
//    else if(n >= 48 && n <= 57)// for numbers
//        outputString += "00" + dhex(n);// + ';';
//    else if(n == 45 || n == 46 || n == 58)// for . - : 
//        outputString += "00" + dhex(n);// + ';';
    else
        outputString += "0" + dhex(n);// + ';';
  }
  return( outputString );
}

    public String createAppropriateDirectories1(String destination_path) {
        // destination_path e.g. "C:/ssadvt_repository/SSADVT/MPWZ/PAYMENT REQUISITIONS/Aug-2014"
        String destPath[] = destination_path.split("/");
        String path = "";
        int length = destPath.length;
        for(int i = 0; i < length; i++)
        {
            if(path.isEmpty())
                path = destPath[i];
            else
                path = path + "/" + destPath[i];
            makeDirectory(path);
        }
        return path;
    }
public List getNodeDetail(){
        List list = new ArrayList();
        String query =  " select node_name "
                       +" from node1 n,node_detail nd "
                       +" where nd.node_id=n.node_id ";
        try{
            ResultSet rs = connection.prepareStatement(query).executeQuery();
            while(rs.next()){
                GenralBean general = new GenralBean();
              general.setNode_name(rs.getString("node_name"));
                list.add(general);
            }


        }catch(Exception e){
            System.out.println(e);
        }

        return list;
    }
public String  getLatLang(String node_name){
       String data="";
       String query =  " select head_latitude,head_longitude,tail_lattitude,tail_longitude "
                       +" from node_detail nd,node1 n "
                       +" where nd.node_id=n.node_id "
                       +" and n.node_name='"+node_name+"'";
       try{
           ResultSet rs = connection.prepareStatement(query).executeQuery();
           while(rs.next()){
              String head_latitude=rs.getString("head_latitude");
              String head_longitude=rs.getString("head_longitude");
              String tail_lattitude=rs.getString("tail_lattitude");
              String tail_longitude=rs.getString("tail_longitude");
              data=head_latitude+","+head_longitude+","+tail_lattitude+","+tail_longitude;
           }


       }catch(Exception e){
           System.out.println(e);
       }

      return data;
   }
    public boolean makeDirectory(String dirPathName) {
        boolean result = false;
        File directory = new File(dirPathName);
        if (!directory.exists()) {
            result = directory.mkdir();
        }
        return result;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println("CorrespondencePriorityModel closeConnection() Error: " + e);
        }
    }
}
