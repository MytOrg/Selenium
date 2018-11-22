/*
 *  The code is for Demo purpose hey
 */

package webShop;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Utility.ExcelUtils;


public class webShop {
	
@Test
public void testgooglrsearch() throws Exception{

System.setProperty("webdriver.gecko.driver","D:\\@Exlore\\Softwares\\geckodriver.exe");	
FirefoxDriver driver = new FirefoxDriver(); 
driver.get("http://google.in"); 
String Expectedtitle = "Google";
//it will fetch the actual title 
String Actualtitle = driver.getTitle();
System.out.println("Before Assetion " + Expectedtitle + Actualtitle);
//it will compare actual title and expected title

Thread.sleep(2000);
webShop.takeScreenshot();
Assert.assertEquals(Actualtitle, Expectedtitle);
//print out the result
System.out.println("After Assertion " + Expectedtitle + Actualtitle + " Title matched ");
driver.quit();
driver.close();
   
}

@Test
public void testPageTitle() throws Exception {
	
	System.setProperty("webdriver.gecko.driver","D:\\@Exlore\\Softwares\\geckodriver.exe");	
	FirefoxDriver driver = new FirefoxDriver(); 
	driver.get("http://google.in"); 
	String Expectedtitle = "Wrong";
	//it will fetch the actual title 
	String Actualtitle = driver.getTitle();
	System.out.println("Before Assetion " + Expectedtitle + Actualtitle);
	//it will compare actual title and expected title
	Thread.sleep(2000);
	webShop.takeScreenshot();
	Assert.assertEquals(Actualtitle, Expectedtitle);
	//print out the result
	System.out.println("After Assertion " + Expectedtitle + Actualtitle + " Title matched ");
	driver.quit();
	driver.close();	
}


public static void takeScreenshot() throws Exception {
String timeStamp;
File screenShotName;


FirefoxDriver driver = new FirefoxDriver(); 
//WebDriver augmentedDriver = new Augmenter().augment(driver);
//File scrFile = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);

File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//The below method will save the screen shot in d drive with name "screenshot.png"
timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()); 
screenShotName = new File("D:\\Telia\\Testing\\Selenium\\screenshots\\"+timeStamp+".png");
FileUtils.copyFile(scrFile, screenShotName);
 
String filePath = screenShotName.toString();
String path = "<img src=\"file://" + filePath + "\" alt=\"\"/>";
Reporter.log(path);
 
}

}
