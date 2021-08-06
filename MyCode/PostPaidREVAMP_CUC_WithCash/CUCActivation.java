package PostPaidREVAMP_CUC_WithCash;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;


	import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.*;

	import javax.swing.*;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

public class CUCActivation {
	/*static String ip;
	static String ser_username;
	static String ser_password;
	static String finalstr;
	private static int y;
	static String a;
	static String trackFile;
	static String statusFile;
	static String EAI_path=CucMain.EAIpre;
	static String Acti_path=CucMain.Actipre;
	static int cellId=CucMain.cellId;
	static String oreder_id;
	static int conditionCounter=0;
	
	static CommonUtilitiesWriter report=new CommonUtilitiesWriter();
	static String runlog=CucMain.runlog;
	static String reportfile=CucMain.reportfile;*/
	static String oreder_id;
	static String ip;
	static String ser_username;
	static String ser_password;
	static String finalstr;
	private static int y;
	static String a;
	static String trackFile;
	static String statusFile;
	
	//static String ip;
	static String File;
	static String EAIpre;
	static int conditionCounter = 0;
	static String runlog;
	static String reportfile;
	static String SIM;
	static String MSISDN;
	static CommonUtilitiesWriter report;
	static String orderNumber;
	static String Actipre;
	
	public CUCActivation(String runlog,String reportfile,String datapre,String Actipre,String EAIpre,CommonUtilitiesWriter report)
	{
		CUCActivation.runlog=runlog;
		CUCActivation.reportfile=reportfile;
		CUCActivation.Actipre=Actipre;
		CUCActivation.report=report;
		CUCActivation.EAIpre=EAIpre;
		CUCActivation.report=report;
		
	}
	
	public int CorporateActi (String MSISDN) throws ClassNotFoundException, SQLException, InterruptedException,IOException,FileNotFoundException
	{
		report.writeToFile(reportfile, System.lineSeparator()+"EAI DB Check"+System.lineSeparator()+"------------");
		conditionCounter = 0;
		report.writeToFile(runlog, "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+System.lineSeparator()+"\t\tEAI DB Check"+System.lineSeparator()+"++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		String temp = "";
		statusFile =EAIpre.concat("\\STATUS.xls");
		trackFile =EAIpre.concat("\\TRACK.xls");
		int i=1;
		while (i<15)
		{
			
			report.writeTorun(runlog,"Iteration" +i+ "of 15");
			
			status_write(MSISDN);
			status_read();
			track_write();
			track_read();
			if(y==4)
			{
				
				report.writeToFile(reportfile,"Activation status is - Pending - " +y+ "&& Activation is in ||" +a);
				report.writeTorun(runlog,"Activation status is - Pending - " +y);
				report.writeTorun(runlog,"Activation is  in || " +a);
			}
			else if(y==3)
			{
				report.writeToFile(reportfile,"Activation status is - Rejected - " +y+ "&& Activation is in ||" +a);
				report.writeTorun(runlog,"Activation status is - Rejected - " +y);
				report.writeTorun(runlog,"Activation is in || " +a);
				break;
			}


			else if (y==5)
			{
				report.writeToFile(reportfile,"Activation status is - Failed - " +y+ "&& Activation is in ||" +a);
				report.writeTorun(runlog,"Activation status is - Failed - " +y);
				report.writeTorun(runlog,"Activation is in || " +a);
				break;
			}
			else if(y==6)
			{
				/*report.writeToFile(reportfile,"Activation status is - Success - " +y+ "&& Activation is in ||" +a);
				report.writeTorun(runlog,"Activation status is - Success - " +y);
				report.writeTorun(runlog,"Activation  is in || " +a);*/
				
				
				
				report.writeTorun(runlog, "Status is : " +y);
				report.writeTorun(runlog, "Activation Success || "+a);
				report.writeTorun(runlog, "SR ID is "+finalstr);
				report.writeToFile(reportfile, "Status is : " +y);
				report.writeToFile(reportfile, "Activation Success || "+a);
				report.writeToFile(reportfile, "SR ID is "+finalstr);
				
				FileInputStream fis = new FileInputStream("C:\\Automated_Execution\\Input\\TestData_CUCPostPaidRevampWithCash.xls");
				HSSFWorkbook workbook = new HSSFWorkbook(fis);
				HSSFSheet sheet = workbook.getSheet("Log_Cheeck");
				sheet.getRow(4).getCell(2).setCellValue(finalstr);
				fis.close();
				
				
				FileOutputStream fos = new FileOutputStream("C:\\Automated_Execution\\Input\\TestData_CUCPostPaidRevampWithCash.xls");
				workbook.write(fos);
				fos.close();
				
				/*report.writeToFile(reportfile, "#######################################"+System.lineSeparator()+"\t\tLog Check"+System.lineSeparator()+"###############################################");
				report.writeTorun(runlog, "#######################################"+System.lineSeparator()+"\t\tLog Check"+System.lineSeparator()+"###############################################");
				
					
				CUCLogCheck log=new CUCLogCheck();
				log.execute();
				System.out.println("Calling Unix Log Check Module!");
				report.writeToFile(reportfile, "#######################################"+System.lineSeparator()+"\t\tLog check comepleted"+System.lineSeparator()+"###############################################");
				report.writeTorun(runlog, "#######################################"+System.lineSeparator()+"\t\tLog check comepleted"+System.lineSeparator()+"###############################################");
				

				report.writeToFile(reportfile, "#######################################"+System.lineSeparator()+"\t\tPost activation validation"+System.lineSeparator()+"###############################################");
				report.writeTorun(runlog, "#######################################"+System.lineSeparator()+"\t\tPost activation validation"+System.lineSeparator()+"###############################################");
				
				CUC_Post_ValidationMain post=new CUC_Post_ValidationMain();
				post.postMain();
			
				System.out.println("Calling Post activation validation module!");
			
				report.writeToFile(reportfile, "#######################################"+System.lineSeparator()+"\t\tPost activation validation completed"+System.lineSeparator()+"###############################################");
				report.writeTorun(runlog, "#######################################"+System.lineSeparator()+"\t\tPost activation validation completed"+System.lineSeparator()+"###############################################");
				*/
				
				break;
			}
			
			else 
			
			{
				
				System.out.println("Status is : " +y);
				System.out.println("Activation Request currently in " +a);
				report.writeToFile(runlog, "Status is : " +y);
				report.writeToFile(runlog, "Activation currently in " +a);
				if(i==15){
					report.writeToFile(reportfile, "Status is : " +y);
					report.writeToFile(reportfile, "Activation currently in " +a);
				}
			}
			if(i==1)
			{
				System.out.println("Check EAI");
				temp = a;
				i++;
			}
			else if(temp.equalsIgnoreCase(a))
			{
				i++;
			}
			else
			{
				temp = a;
				i=1;
			}
			
			report.writeTorun(runlog,"Next iteration is in progress...");
			System.out.println("Next iteration is in progress...");
			Thread.sleep(60000);
			report.writeToFile(runlog, "Activation is in progress. Kindly wait for a minute to check where the request resides at that moment..."+System.lineSeparator());
			System.out.println("\nActivation is in progress. Kindly wait for a minute to check where the request resides at that moment...");
			Thread.sleep(60000);
		} // while end
		return y;
	} // Corporate end
	
	public static void status_write(String MSISDN) throws IOException,FileNotFoundException
	{
		try {
			
			
			//Test data reading
			
			
			//System.out.println("Status query started");

			/*File file_data=new File (Actipre.concat("\\Order.xls"));
			//File file_data=new File ("C:\\Automated_Execution\\Input\\DB_VALIDATION.xls");
			FileInputStream inputstrm= new FileInputStream(file_data);
			HSSFWorkbook testdata=new HSSFWorkbook(inputstrm); 
			HSSFSheet worksheet = testdata.getSheet("Sheet1");
			HSSFRow ro = worksheet.getRow(1);
			 oreder_id = ro.getCell(0).getStringCellValue();
			//System.out.println(oreder_id);
			
			//testdata.close();
			
			inputstrm.close();*/

			//Connection -DB
			
			/*File file_dat=new File ("C:\\Automated_Execution\\Input\\TestData_CUCPostPaidRevampWithCash.xls");
			FileInputStream input= new FileInputStream(file_dat);
			HSSFWorkbook testdat=new HSSFWorkbook(input); 
		    HSSFSheet worksh = testdat.getSheet("Server_Information");
		    HSSFRow ro1 = worksh.getRow(7);
		    ip=ro1.getCell(2).getStringCellValue();
		    HSSFRow ro2 = worksh.getRow(8);
		    ser_username=ro2.getCell(2).getStringCellValue();
		    HSSFRow ro3 = worksh.getRow(9);
		    ser_password=ro3.getCell(2).getStringCellValue();
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con =DriverManager.getConnection(ip,ser_username,ser_password);
			con.setAutoCommit(false);
			Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			System.out.println("DB Connection established");
			report.writeTorun(runlog,"DB Connection established");
*/
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@10.14.11.160:1521:eaitest","PGW","pgw");
			con.setAutoCommit(false);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
						
			
			/*File file_data=new File ("C:\\Automated_Execution\\Input\T.xls");
			FileInputStream inputstrm= new FileInputStream(file_data);
			
			HSSFWorkbook testdata=new HSSFWorkbook(inputstrm); 
		    HSSFSheet worksheet = testdata.getSheet("CUC_PREP");
		    HSSFRow ro = worksheet.getRow(1);
			String a1 = ro.getCell(0).getStringCellValue();
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@10.14.11.160:1521:eaitest","PGW","pgw");
			con.setAutoCommit(false);
			Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			*/
			
			String b = ("select SR_ID,STATUS,Time_recieved from SR_SERVICEREQUEST_TBL D where D.LINE_NUMBER='").concat(MSISDN).concat("' ORDER BY TIME_RECIEVED DESC");
			ResultSet rs=stmt.executeQuery(b);
			System.out.println("Status query executed");
			HSSFWorkbook workbook = new HSSFWorkbook();
		    HSSFSheet sheet = workbook.createSheet("DB_VALIDATION_SR_ID");
		    HSSFRow rowhead = sheet.createRow((short) 0);
		    rowhead.createCell(0).setCellValue("SR_ID");
		    rowhead.createCell(1).setCellValue("TIME_RECIVED");
		    rowhead.createCell(2).setCellValue("STATUS");
		   
		    int i = 1;
		    while (rs.next()){
		        HSSFRow row = sheet.createRow((short) i);
		        row.createCell( 0).setCellValue(rs.getString(1));
		        row.createCell(1).setCellValue(rs.getString(2));
		        row.createCell(2).setCellValue(rs.getString(3));
		        i++;
		    }
		    
		    FileOutputStream fileOut = new FileOutputStream(statusFile,false);
			workbook.write(fileOut);
			fileOut.close();
			report.writeTorun(runlog,"Status file has been created successfully");
			//System.out.println("Status File has been created successfully ");
	    
		}
		catch (ClassNotFoundException e1)
		{
	       e1.printStackTrace();
	    }
		catch (SQLException e1)
		{
	        e1.printStackTrace();
	    }
		catch (FileNotFoundException e1)
		{
	        e1.printStackTrace();
	    }
		catch (IOException e1) 
		{
	        e1.printStackTrace();
	    }
				
		}
	
	public static void status_read() throws IOException,FileNotFoundException
	{
		File file=new File(statusFile);
		FileInputStream fileInputStream = new FileInputStream(file);
		HSSFWorkbook workbook1 = new HSSFWorkbook(fileInputStream);
	    HSSFSheet worksheet = workbook1.getSheet("DB_VALIDATION_SR_ID");
		HSSFRow row1 = worksheet.getRow(1);
		HSSFCell cellA1 = row1.getCell(2);
		HSSFCell cellA2 = row1.getCell(0);
		String a1Val = cellA1.getStringCellValue();
		String a1Va2 = cellA2.getStringCellValue();
		String str =a1Va2;
        int len = str.length();
        finalstr = str.substring(0, len);
        y = Integer.parseInt(a1Val);
	}

	public static void track_write() throws IOException,FileNotFoundException
	{
		try 
	{
			/*File file_dat=new File ("C:\\Automated_Execution\\Input\\TestData_CUCPostPaidRevampWithCash.xls");
			FileInputStream input= new FileInputStream(file_dat);
			HSSFWorkbook testdat=new HSSFWorkbook(input); 
		    HSSFSheet work = testdat.getSheet("Server_Information");
		    HSSFRow ro1 = work.getRow(7);
		    ip=ro1.getCell(2).getStringCellValue();
		    HSSFRow ro2 = work.getRow(8);
		    ser_username=ro2.getCell(2).getStringCellValue();
		    HSSFRow ro3 = work.getRow(9);
		    ser_password=ro3.getCell(2).getStringCellValue();
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con =DriverManager.getConnection(ip,ser_username,ser_password);
			con.setAutoCommit(false);
			Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			*/
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@10.14.11.160:1521:eaitest","PGW","pgw");
		con.setAutoCommit(false);
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
		System.out.println("DB Connection established");
		String a = ("select a.id,b.name,a.sr_id,a.time_stamp,a.message from sr_aa_TRACKING_POINT_tbl A, SR_TRACKING_POINT_TBL B  WHERE A.SR_ID ='").concat(finalstr).concat("' AND A.TRACKING_POINT = B.CODE   order by TIME_STAMP desc");
		System.out.println("Track query executed");
		ResultSet rs=stmt.executeQuery(a);
		HSSFWorkbook workbook = new HSSFWorkbook();
	    HSSFSheet sheet = workbook.createSheet("DB_VALIDATION_TRACK");
	    HSSFRow rowhead = sheet.createRow((short) 0);
	    rowhead.createCell(0).setCellValue("ID");
	    rowhead.createCell(1).setCellValue("NAME");
	    rowhead.createCell(2).setCellValue("SR_ID");
	    rowhead.createCell(3).setCellValue("TIME_STAMP");
	    rowhead.createCell(4).setCellValue("MESSAGE");
	    
	    int j = 1;
	    while (rs.next()){
	        HSSFRow row = sheet.createRow((short) j);
	        row.createCell(0).setCellValue(rs.getString(1));
	        row.createCell(1).setCellValue(rs.getString(2));
	        row.createCell(2).setCellValue(rs.getString(3));
	        row.createCell(3).setCellValue(rs.getString(4));
	        row.createCell(4).setCellValue(rs.getString(5));
	        j++;
	    }
	    FileOutputStream fileOut1 = new FileOutputStream(trackFile,false);
		workbook.write(fileOut1);
		//workbook.close();
		fileOut1.close();
		System.out.println("Track File has been created successfully ");
		report.writeTorun(runlog,"Track File has been created successfully");
		} 
		catch (ClassNotFoundException e1)
		{
	       e1.printStackTrace();
	    }
		catch (SQLException e1)
		{
	        e1.printStackTrace();
	    }
		catch (FileNotFoundException e1)
		{
	        e1.printStackTrace();
	    }
		catch (IOException e1) 
		{
	        e1.printStackTrace();
	    }
		}
	
	public static void track_read() throws IOException
	{
		File file1=new File(trackFile);
		FileInputStream fileInputStream1 = new FileInputStream(file1);
		HSSFWorkbook workbook2 = new HSSFWorkbook(fileInputStream1);
	    HSSFSheet worksheet2 = workbook2.getSheet("DB_VALIDATION_TRACK");
		HSSFRow row2 = worksheet2.getRow(1);
		HSSFCell cllA1 = row2.getCell( 1);
		String a2Val = cllA1.getStringCellValue();
		a=a2Val;
		fileInputStream1.close();
	}
}
