package Utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class constant {
	
	public static final String baseurl = "https://www.google.com";
	 //public static final String baseurl = "https://web.pre.callme.dk";
	//public static final String baseurl = "https://web-test.callme.dk/"; // https://web.pre.callme.dk/
	public static final String Path_TestData = "D:\\Telia\\Testing\\Selenium\\src\\TestData\\";
    public static final String File_TestData = "Data.xlsx";
    public static final String expectedTitle = "Callme - NOT FOR COMMERCIAL USE";
    public static final String FirefoxP      = "D:\\@Exlore\\Softwares\\geckodriver.exe";
 
    
	public static WebDriver driver =  null;
	public static WebElement element = null;
	public static String Xidentificationby = null;
	public static String Xevent = null;
	
	 public static ExtentReports extent;
	 public static ExtentTest logger;
	
}