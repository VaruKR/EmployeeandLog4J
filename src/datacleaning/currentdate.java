package datacleaning;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
public  class currentdate {
	 private String s;
     
	 public String curentdat() {
      Date date = new Date();
      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
      String str = formatter.format(date);
      return str;
	 }
      
}
   

