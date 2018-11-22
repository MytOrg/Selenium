/*
 *  The code is for Demo purpose
 */

package webShop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Utility.ExcelUtils;


public class webShop {
	
@Test

public void testgooglrsearch(){

System.setProperty("webdriver.gecko.driver","D:\\@Exlore\\Softwares\\geckodriver.exe");	
FirefoxDriver driver = new FirefoxDriver(); 
driver.get("http://google.in"); 

String Expectedtitle = "Google";
//it will fetch the actual title 
String Actualtitle = driver.getTitle();
System.out.println("Before Assetion " + Expectedtitle + Actualtitle);
//it will compare actual title and expected title
Assert.assertEquals(Actualtitle, Expectedtitle);
//print out the result
System.out.println("After Assertion " + Expectedtitle + Actualtitle + " Title matched ");
   
}


@Test
public void testPageTitle() {
	
	System.setProperty("webdriver.gecko.driver","D:\\@Exlore\\Softwares\\geckodriver.exe");	
	FirefoxDriver driver = new FirefoxDriver(); 
	driver.get("http://google.in"); 

	String Expectedtitle = "Wrong";
	//it will fetch the actual title 
	String Actualtitle = driver.getTitle();
	System.out.println("Before Assetion " + Expectedtitle + Actualtitle);
	//it will compare actual title and expected title
	Assert.assertEquals(Actualtitle, Expectedtitle);
	//print out the result
	System.out.println("After Assertion " + Expectedtitle + Actualtitle + " Title matched ");
}

}
