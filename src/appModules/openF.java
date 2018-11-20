package appModules;

import org.openqa.selenium.firefox.FirefoxDriver;
import Utility.ExcelUtils;

public class openF {
	
	 public static void openBrowser() throws Exception {
		  System.out.println("This is Function Test");
		  System.setProperty("webdriver.gecko.driver","D:\\@Exlore\\Softwares\\geckodriver.exe");	
		  String x = ExcelUtils.getCellData(2, 4);
		  System.out.println(x);
		  System.out.println("Hi This is My first Test");	
		  FirefoxDriver driver = new FirefoxDriver();  	
		 // driver.get("http://web-test.callme.dk/");
		 // driver.get(ExcelUtils.getCellData(2, 4));
		  
		  
	  }

}
