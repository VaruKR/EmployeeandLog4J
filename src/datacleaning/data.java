package datacleaning;

import java.io.FileWriter;
import java.lang.System.Logger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.ParseException;
//import com.mysql.cj.xdevapi.Statement;



public class data {
	
	private int EMPLOYEE_ID;
	private String FIRST_NAME;
	
	private String LAST_NAME;
	private String EMAIL;
	private String PHONE_NUMBER;
	private String HIRE_DATE;
	private String JOB_ID;
	private String SALARY;
	private String COMMISSION_PCT;
	private String MANAGER_ID;
	private String  DEPARTMENT_ID;
	
	
	 
    
	public int getEMPLOYEE_ID() {
		return EMPLOYEE_ID;
	}
	public void setEMPLOYEE_ID(int data) {
		EMPLOYEE_ID = data;
	}
	public String getFIRST_NAME() {
		return FIRST_NAME;
	}
	public void setFIRST_NAME(String fIRST_NAME) {
		FIRST_NAME = fIRST_NAME;
	}
	public String getLAST_NAME() {
		return LAST_NAME;
	}
	public void setLAST_NAME(String lAST_NAME) {
		LAST_NAME = lAST_NAME;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	public String getPHONE_NUMBER() {
		return PHONE_NUMBER;
	}
	public void setPHONE_NUMBER(String pHONE_NUMBER) {
		PHONE_NUMBER = pHONE_NUMBER;
	}
	public String getHIRE_DATE() {
		return HIRE_DATE;
	}
	public void setHIRE_DATE(String data) {
		HIRE_DATE = data;
	}
	public String getJOB_ID() {
		return JOB_ID;
	}
	public void setJOB_ID(String jOB_ID) {
		JOB_ID = jOB_ID;
	}
	public String getSALARY() {
		return SALARY;
	}
	public void setSALARY(String data) {
		SALARY = data;
	}
	public String getCOMMISSION_PCT() {
		return COMMISSION_PCT;
	}
	public void setCOMMISSION_PCT(String cOMMISSION_PCT) {
		COMMISSION_PCT = cOMMISSION_PCT;
	}
	public String getMANAGER_ID() {
		return MANAGER_ID;
	}
	public void setMANAGER_ID(String data) {
		MANAGER_ID = data;
	}
	public String getDEPARTMENT_ID() {
		return DEPARTMENT_ID;
	}
	public void setDEPARTMENT_ID(String data) {
		DEPARTMENT_ID = data;
	}
	
	//Tp validate the numeric value
	public boolean isNumeric(String str){
        return str != null && str.matches("[0-9.]+");
	}
	
	//For date format validation
	public  boolean validateJavaDate(String strDate)
	   {
		
		    /*
		     * Set preferred date format,
		     * For example MM-dd-yyyy, MM.dd.yyyy,dd.MM.yyyy etc.*/
		    SimpleDateFormat sdfrmt = new SimpleDateFormat("dd-MM-yyyy");
		    sdfrmt.setLenient(false);
		    /* Create Date object
		     * parse the string into date 
	             */
		    try
		    {
		        java.util.Date javaDate = sdfrmt.parse(strDate);  
		    }
		    /* Date format is invalid */
		    catch (ParseException e)
		    {    
		        return false;
		    }
		    /* Return true if date format is valid */
		    return true;
		}
	   
	public static void insertdatatoemployee(String queryy)
	{
		try
		{
			String query = queryy;
			try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root","Kenchi@1122"))
				{
				
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.executeUpdate();
				
				conn.close();
				}
			catch(SQLException e)
			{
			System.out.println("Connection failed!");
			e.printStackTrace();
			}
			
		}
		catch(Exception e)
		{
		System.out.println("Connection failed!");
		e.printStackTrace();
		}
	}
	
	public static void insertdatatoemployeefailed(String queryy)
	{
		try
		{
			String query = queryy;
			try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root","Kenchi@1122"))
				{
				
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.executeUpdate();
				conn.close();
				}
			catch(SQLException e)
			{
			System.out.println("Connection failed!");
			e.printStackTrace();
			}
			
		}
		catch(Exception e)
		{
		System.out.println("Connection failed!");
		e.printStackTrace();
		}
	}
	public String currentdate() {
		 String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
		return mydate; 
	}
	
	
	public void Successreport() {
		try {
			String file ="C:\\Users\\varunkpk\\Desktop\\employee.csv";
	FileWriter fw = new FileWriter(file);
    Class.forName("com.mysql.jdbc.Driver").newInstance();
  Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee","root","Kenchi@1122");
  String query  = "SELECT e.EMPLOYEE_ID,e.LAST_NAME,e.EMAIL,ej.JOB_Description,em.MANAGER_NAME,ed.department_desc\r\n"
  		+ "		        FROM employee e\r\n"
  		+ "	         	INNER JOIN employee_job As ej\r\n"
  		+ "	         	ON e.JOB_ID = ej.JOB_ID\r\n"
  		+ "		       INNER JOIN employee_dept ed\r\n"
  		+ "		        ON e.DEPARTMENT_ID=ed.department_id\r\n"
  		+ "                INNER JOIN employee_manager em\r\n"
  		+ "                ON e.MANAGER_ID=em.MANAGER_ID";
  
  java.sql.Statement stmt = conn.createStatement();
  ResultSet rs = stmt.executeQuery(query);
  fw.append("EMPLOYEE_ID,NAME,EMAIL,JOB_DESCRIPTION,MANAGER_NAME,DEPARTMENT_DESCRIPTION");  
  fw.write('\n');
  while(rs.next())
  {
	   
  fw.write(rs.getString(1));
  fw.write(',');
  fw.write(rs.getString(2));
  fw.write(',');
  fw.write(rs.getString(3));
  fw.write(',');
  fw.write(rs.getString(4));
  fw.write(',');
  fw.write(rs.getString(5));
  fw.write(',');
  fw.write(rs.getString(6));
  fw.write('\n');
  }
  fw.flush();
  fw.close();
  conn.close();
  System.out.println("Success Report generated");
  } catch (Exception e) {
  e.printStackTrace();
  }
  
	}	

public void Failedreport() {
	try {
		String file ="C:\\Users\\varunkpk\\Desktop\\employee.csv";
FileWriter fw = new FileWriter(file);
Class.forName("com.mysql.jdbc.Driver").newInstance();
Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee","root","Kenchi@1122");
String query  = "SELECT e.EMPLOYEE_ID,e.LAST_NAME,e.EMAIL,ej.JOB_Description,em.MANAGER_NAME,ed.department_desc\r\n"
		+ "		        FROM employeefailed e\r\n"
		+ "	         	INNER JOIN employee_job As ej\r\n"
		+ "	         	ON e.JOB_ID = ej.JOB_ID\r\n"
		+ "		       INNER JOIN employee_dept ed\r\n"
		+ "		        ON e.DEPARTMENT_ID=ed.department_id\r\n"
		+ "                INNER JOIN employee_manager em\r\n"
		+ "                ON e.MANAGER_ID=em.MANAGER_ID";

java.sql.Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery(query);
fw.append("EMPLOYEE_ID,NAME,EMAIL,JOB_DESCRIPTION,MANAGER_NAME,DEPARTMENT_DESCRIPTION");  
fw.write('\n');
while(rs.next())
{
   
fw.write(rs.getString(1));
fw.write(',');
fw.write(rs.getString(2));
fw.write(',');
fw.write(rs.getString(3));
fw.write(',');
fw.write(rs.getString(4));
fw.write(',');
fw.write(rs.getString(5));
fw.write(',');
fw.write(rs.getString(6));
fw.write('\n');
}
fw.flush();
fw.close();
conn.close();
System.out.println("Failed Report generated");

} catch (Exception e) {
e.printStackTrace();
}
}

}



		
	





	

	





