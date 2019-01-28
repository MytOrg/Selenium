
package webShop;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Utility.constant;

public class Category {


	@BeforeTest

	public void setBaseURL() throws Exception {
		
		
		System.setProperty("webdriver.gecko.driver", Utility.constant.FirefoxP);
		Utility.constant.driver =  new FirefoxDriver();

	}
	
	@BeforeMethod
	
	public void setupreport() {
		
		
			
		ExtentHtmlReporter reporter = new ExtentHtmlReporter("D:\\Telia\\Testing\\Selenium\\screenshots/Report.html");
		Utility.constant.extent = new ExtentReports();
		Utility.constant.extent.attachReporter(reporter);
		Utility.constant.logger = Utility.constant.extent.createTest("LoginTest");
		
	}

	@Test(priority = 0)
	public void opewebshop() throws Exception {
		
		
		
		Utility.constant.driver.get(Utility.constant.baseurl);
		Utility.Utils.waitForLoad(Utility.constant.driver); // Wait for page
															// load for max of 5
															// minutes
		//Thread.sleep(180);
		String actualTitle = Utility.constant.driver.getTitle();
		Assert.assertEquals(actualTitle, constant.expectedTitle);
		System.out.println("actualTitle is :" + actualTitle + " and expectedTitle is: " + constant.expectedTitle);
	}

	@Test(priority = 1)
	public void Filteringfunction() throws InterruptedException {
		
		

		Utility.Utils.functionMain(Utility.constant.driver, "XPATH", Utility.Utils.readExcelFile(0, 1, 2), "CLICK");
		Utility.Utils.functionMain(Utility.constant.driver, "XPATH", Utility.Utils.readExcelFile(0, 2, 2), "CLICK");
		Utility.Utils.functionMain(Utility.constant.driver, "XPATH", Utility.Utils.readExcelFile(0, 3, 2), "CLICK");

		Utility.Utils.functionMain(Utility.constant.driver, "XPATH", Utility.Utils.readExcelFile(0, 7, 2), "CLICK");
		Utility.Utils.printM("Element count is After Filtering Apple Mobiler"
				+ Utility.Utils.elementCnt(Utility.constant.driver, "//*[contains(text(),'Apple')]"));

		Utility.Utils.functionMain(Utility.constant.driver, "XPATH", Utility.Utils.readExcelFile(0, 5, 2), "CLICK");

		Utility.Utils.functionMain(Utility.constant.driver, "XPATH", Utility.Utils.readExcelFile(0, 6, 2), "CLICK");
		Utility.Utils.printM("Element count is After Filtering Samsung Mobiler"
				+ Utility.Utils.elementCnt(Utility.constant.driver, "//*[contains(text(),'Samsung')]"));
	}

	@Test(priority = 2)
	public void comparettwohandsets() throws InterruptedException {
		
		

		Utility.Utils.functionMain(Utility.constant.driver, "XPATH", Utility.Utils.readExcelFile(0, 5, 2), "CLICK");

		Utility.Utils.functionMain(Utility.constant.driver, "XPATH", Utility.Utils.readExcelFile(0, 7, 2), "CLICK");
		Utility.Utils.functionMain(Utility.constant.driver, "XPATH", Utility.Utils.readExcelFile(0, 10, 2), "CLICK");
		Utility.Utils.functionMain(Utility.constant.driver, "XPATH", Utility.Utils.readExcelFile(0, 11, 2), "CLICK");
		Utility.Utils.functionMain(Utility.constant.driver, "XPATH", Utility.Utils.readExcelFile(0, 13, 2), "CLICK");

	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			String temp = Utility.Utils.getScreenshot(Utility.constant.driver);
			Utility.constant.logger.fail(result.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}

		Utility.constant.extent.flush();
		//Utility.constant.driver.close();
		//Utility.constant.driver.quit();

	}

	@AfterTest

	public void endSession() {
		Utility.constant.driver.close();
	}

}

/*
 * 
 * 
 * public static void takeScreenshot() throws Exception { String timeStamp; File
 * screenShotName;
 * 
 * 
 * FirefoxDriver driver = new FirefoxDriver(); //WebDriver augmentedDriver = new
 * Augmenter().augment(driver); //File scrFile =
 * ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
 * 
 * File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
 * //The below method will save the screen shot in d drive with name
 * "screenshot.png" timeStamp = new
 * SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
 * screenShotName = new
 * File("D:\\Telia\\Testing\\Selenium\\screenshots\\"+timeStamp+".png");
 * FileUtils.copyFile(scrFile, screenShotName);
 * 
 * String filePath = screenShotName.toString(); String path =
 * "<img src=\"file://" + filePath + "\" alt=\"\"/>"; Reporter.log(path);
 * 
 * } }
 * 
 */
