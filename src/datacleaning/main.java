package datacleaning;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;


public class main {
	public static void main(String[] args) throws FileNotFoundException  {
	// TODO Auto-generated method stub
	data d = new data();
	currentdate now = new currentdate();
	Logger log = Logger.getLogger(main.class.getName()); 
	String File = "C:\\Users\\varunkpk\\Desktop\\employee.csv";
    String csvFile = File;
    BufferedReader br = null;
    String line = "";
    String cvsSplitBy = ",";
    
    try {

        br = new BufferedReader(new FileReader(csvFile));
        String[] data = new String[1000];
        Scanner sc = new Scanner(File);
         
        log.info("Reading data from CSV File");
       // while(sc.hasNext()) {
      while ((line=br.readLine()) != null  ) {
          // data = sc.nextLine();
           data = line.split(cvsSplitBy,-1);
            boolean flag = true ;
           
            //Employee id
           if (d.isNumeric(data[0])) {
            	int i = Integer.parseInt(data[0]);
            	d.setEMPLOYEE_ID(i);
            	log.info("Assigned Valid Employee ID");
            }else {
            	int j = Integer.parseInt("0");
            	d.setEMPLOYEE_ID(j);
            	flag = false;
            	log.warn("Invalid Employee ID"+data[0]);
            }
           
            //First name 
            if (data[1].isEmpty()) {
                d.setFIRST_NAME("invalid "+data[1]);
                log.warn("First name is empty"+data[1]);

                flag = false;
            } else {
                d.setFIRST_NAME(data[1]);
                log.info("Assigned Valid First Name"+data[1]);
            }
            
            //Last Name
            if (data[2].isEmpty()) {
                d.setLAST_NAME("invalid "+data[2]); 
                flag = false;
                log.warn("Last  name is empty"+data[2]);
            } else {
                d.setLAST_NAME(data[2]);
                log.info("Assigned Valid Last Name"+data[1]);
            }
            
            
            String regex = "^(.+)@(.+)$";  
            Pattern pattern = Pattern.compile(regex);  
            Matcher matcher = pattern.matcher(data[3]);  
            if(matcher.matches())
            {
                d.setEMAIL(data[3]);
                log.info("Assigned with valid Email"+data[3]);
            }
            else
            {
            	if (data[3].isEmpty()) {
                    d.setEMAIL("invalid "+data[3]); 
                    log.warn("Email is empty"+data[3]);
                    flag = false;
                }else {
                	String s =data[3]+"@gmail.com";
                	d.setEMAIL(s);
                	log.info("Assigned with @gmail.com to the "+data[3]);
                }
                

            }
            
            //Phone number 
            if (data[4].matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}"))
            {
            d.setPHONE_NUMBER(data[4]);
            log.info("Valid Phone number "+data[4]);
            }
            else
            {
                d.setPHONE_NUMBER( "-->"+data[4]);
                log.warn("Invalid Phone number"+data[4]);
                flag = false;
            }
            
            
            //Hire date
            if (data[5].isEmpty()) {
            	d.setHIRE_DATE("invalid "+data[5]);
            	log.warn("Hire date is empty"+data[5]);
            	flag = false;
            }else if (d.validateJavaDate(data[5]))
             {
            		d.setHIRE_DATE(data[5]);
            		log.info("Valid Hire date "+data[5]);
            	}else {
            	d.setHIRE_DATE("invalid "+data[5]);
            	log.warn("Invalid Hire Date"+data[5]);
            	flag = false;
            }
            
            //COMMISSION_PCT
            if (data[8].isEmpty()) {
                d.setCOMMISSION_PCT("-");
                log.warn("Commission pct is empty "+data[8]);
                flag = false;
            } else {
                d.setCOMMISSION_PCT(data[8]);
                log.info("Valid PCT");
            }
            
            //Job id ;
            if (data[6].isEmpty()) {
                d.setJOB_ID("-"); 
                log.warn("Empty Job id"+data[6]);
                flag = false;
            } else {
                d.setJOB_ID(data[6]);
                log.info("valid job id"+data[6]);
            }
            
            //Salary 
            if (data[7].isEmpty()) {
            	
            	d.setSALARY("invalid"+data[7]);
            	log.warn("Empty salary "+data[7]);
            	flag = false;
            }else {
            	
            	d.setSALARY(data[7]);
            	log.info("Valid Salary "+data[7]);
            	
            }
            
            
            
            //Manager id
            
            if (d.isNumeric(data[9])) {
            	//int i = Integer.parseInt(data[9]);
            	d.setMANAGER_ID(data[9]);;
            	log.info("Valid manager id "+data[9]);
            }else {
            	//int j = Integer.parseInt("");
            	d.setMANAGER_ID("A1");
            	flag = false;
            	log.warn("Invalid Manager id"+data[9]);
            
            }
            
            //Department id 
            if (d.isNumeric(data[10])) {
            	//int i = Integer.parseInt(data[9]);
            	d.setDEPARTMENT_ID(data[10]);
            	log.info("Valid Department id "+data[10]);
            	
            }else {
            	//int j = Integer.parseInt(" ");
            	d.setDEPARTMENT_ID("-");
            	log.warn("Invalid Department id "+data[10]);
            	flag = false;
            
            }
            
           
          
            //Insert data in to the employee table
            if (flag) {
            	String queryy = "Insert into employee VALUES"+"(" + d.getEMPLOYEE_ID()+",'"+ d.getFIRST_NAME()
				+ "','"+ d.getLAST_NAME() +"','"+ d.getEMAIL() +"','"+d.getPHONE_NUMBER()+"','"+ d.getHIRE_DATE() +"','"+d.getJOB_ID()+"','"+d.getSALARY()+"','"+d.getCOMMISSION_PCT()+"','"+d.getMANAGER_ID()+"','"+d.getDEPARTMENT_ID()+"','"+now.curentdat()+"');";
            	
            	d.insertdatatoemployee(queryy);
            
            }
            //insert data in to the Employeefailed table
            else {
            	String queryy = "Insert into employeefailed VALUES"+"(" + d.getEMPLOYEE_ID()+",'"+ d.getFIRST_NAME()
				+ "','"+ d.getLAST_NAME() +"','"+ d.getEMAIL() +"','"+d.getPHONE_NUMBER()+"','"+ d.getHIRE_DATE() +"','"+d.getJOB_ID()+"','"+d.getSALARY()+"','"+d.getCOMMISSION_PCT()+"','"+d.getMANAGER_ID()+"','"+d.getDEPARTMENT_ID()+"','"+now.curentdat()+"');";
            	
            	d.insertdatatoemployeefailed(queryy);
            }
            
            
        }            
      

    } catch (FileNotFoundException e) {
    	log.error("File not found"+ File);
        e.printStackTrace();
    }
     catch (IOException e) {
        e.printStackTrace();
        log.error("IO Error");
    } 
    log.info("Success fully Inserted data in to the employee and Employeefailed table");
    d.Successreport();
    log.info("Generated Success Report");
    d.Failedreport();
    log.info("Generated Failed Report" );
    	
		}

	
		}
		  

