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
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Utility.constant;

public class Category {
	
    ExtentHtmlReporter htmlReporter;
    ExtentReports extent;
    ExtentTest test;
	ExtentTest parentTest;
	ExtentTest childTest;
	ExtentColor colour;
	String path;


	@BeforeTest

	public void setBaseURL() throws Exception {

		path = System.getProperty("user.dir") + "/test-output/MyOwnReport.html";
		System.setProperty("webdriver.gecko.driver", Utility.constant.FirefoxP);

		
		htmlReporter = new ExtentHtmlReporter(path);
		htmlReporter.setAppendExisting(false);
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User Name", "Automation Team");

		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("AutomationTesting.in Demo Report");
		htmlReporter.config().setReportName("My Own Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		htmlReporter.config().setTheme(Theme.DARK);
		
		
		Utility.constant.driver = new FirefoxDriver();
		

	}
	
	@BeforeMethod
	public void setupreport() {

		System.out.println("Before Method");

	}

	@Test(priority = 0)
	public void opewebshop() throws Exception {

	    parentTest= extent.createTest("opewebshop","This is Demo for webShop opening URL");
	   	childTest = parentTest.createNode("webShop opening URL");
		
		parentTest.assignCategory("Regression");
		parentTest.assignAuthor("Abhay");   
		
		childTest.info("Opening the WebShop");
		
		//childTest.log(Status.INFO, "Opening the WebShop URL");
		 childTest.log(Status.INFO,MarkupHelper.createLabel("Opening the WebShop URL", ExtentColor.BLUE));

        Utility.constant.driver.get(Utility.constant.baseurl);
		Utility.Utils.waitForLoad(Utility.constant.driver); // Wait for page
															// load for max of 5
															// minutes
		// Thread.sleep(180);
		String actualTitle = Utility.constant.driver.getTitle();

		childTest.log(Status.INFO,MarkupHelper.createLabel("Comparing the Title", ExtentColor.BLUE));

		Assert.assertEquals(actualTitle, constant.expectedTitle);
		System.out.println("actualTitle is :" + actualTitle + " and expectedTitle is: " + constant.expectedTitle);
	}

	
	@Test(priority = 1)
	public void Filteringfunction() throws InterruptedException {
		  
		    parentTest = extent.createTest("Filteringfunction","This is Demo for testing the Filteringfunction");
		   	childTest = parentTest.createNode("Testing the Filteringfunction");
			
			parentTest.assignCategory("Regression");
			parentTest.assignAuthor("Abhay");   
			
			childTest.info("Clicking on the google TExt Box");
		
		// For testing
		Utility.constant.driver.findElement(By.xpath("//input[@title='Search']")).click();
		Thread.sleep(10);
		Utility.constant.driver.findElement(By.xpath("//div[@class='FPdoLc VlcLAe']//input[@value='Google Search']")).click();  
		//Utility.Utils.functionMain(Utility.constant.driver, "XPATH", Utility.Utils.readExcelFile(0, 14, 2), "CLICK");
		//Utility.Utils.functionMain(Utility.constant.driver, "XPATH", Utility.Utils.readExcelFile(0, 15, 2), "CLICK");  
		
		
/*
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
*/				
	}

	/*
	@Test(priority = 2)
	public void comparettwohandsets() throws InterruptedException {
		
		

		Utility.Utils.functionMain(Utility.constant.driver, "XPATH", Utility.Utils.readExcelFile(0, 5, 2), "CLICK");

		Utility.Utils.functionMain(Utility.constant.driver, "XPATH", Utility.Utils.readExcelFile(0, 7, 2), "CLICK");
		Utility.Utils.functionMain(Utility.constant.driver, "XPATH", Utility.Utils.readExcelFile(0, 10, 2), "CLICK");
		Utility.Utils.functionMain(Utility.constant.driver, "XPATH", Utility.Utils.readExcelFile(0, 11, 2), "CLICK");
		Utility.Utils.functionMain(Utility.constant.driver, "XPATH", Utility.Utils.readExcelFile(0, 13, 2), "CLICK");

	}
	*/


	@AfterMethod
	public void getResult(ITestResult result) throws IOException {

		try {
			String screenshotPath = Utility.Utils.getScreenshot(Utility.constant.driver, result.getName());

			if (result.getStatus() == ITestResult.FAILURE) {
				childTest.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAILED ", ExtentColor.RED))
						.addScreenCaptureFromPath(screenshotPath);
				childTest.fail(result.getThrowable());
			} else if (result.getStatus() == ITestResult.SKIP) {
				childTest.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIPPED ", ExtentColor.ORANGE));						
				childTest.skip(result.getThrowable());
			} else {
				childTest.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASSED ", ExtentColor.GREEN));						

			}
			
			extent.flush();
			
			//extent.removeTest(childTest);
			//extent.removeTest(parentTest);
		}

		catch (IOException e) {
			System.out.println("Failed due to : " + e.getMessage());
		}
	}
	
	 
	@AfterTest
	public void tearDown() {
		extent.removeTest(test);
		Utility.constant.driver.close();
	}

}


