package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sun.corba.se.spi.orbutil.fsm.Action;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import java.io.FileInputStream;
import java.util.Properties;

public class Utils {
	
	  static FluentWait<WebDriver> wait; 

	// Function to read the data from excel sheet
	public static String readExcelFile (int sheetN, int row, int column) {
		String getval = null;
		try {
			//DataFormatter formatter = new DataFormatter();
			// Specify the path of file
			File src = new File(constant.Path_TestData + constant.File_TestData);
			// load file
			FileInputStream fis = new FileInputStream(src);
			// Load workbook
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			// Load sheet- Here we are loading first sheet only
			XSSFSheet sh1 = wb.getSheetAt(sheetN); // sheetN=0 means the very first sheet

			// getRow() specify which row we want to read.
			// and getCell() specify which column to read.
			// getStringCellValue() specify that we are reading String data.

			//String getval = new String(System.out.println(sh1.getRow(0).getCell(0).getRawValue()));
			 getval = sh1.getRow(row).getCell(column).getStringCellValue();
			//String getval = formatter.formatCellValue(sheetN.getRow(row).getCell(column));
			 System.out.println(getval);
			 	

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return getval;
		
	}
	
	// Function to write into an excel sheet
	public static void writeExcelFile() {
		
		try {
			 
			// Specify the file path which you want to create or write
    		File src = new File(constant.Path_TestData + constant.File_TestData);
			// Load the file
			FileInputStream fis = new FileInputStream(src);
			// load the workbook
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			// get the sheet which you want to modify or create
			XSSFSheet sh1 = wb.getSheetAt(0);
			// getRow specify which row we want to read and getCell which column

			sh1.getRow(0).createCell(2).setCellValue("X");
			sh1.getRow(1).createCell(2).setCellValue("Y");
			sh1.getRow(2).createCell(2).setCellValue("Z");

			// here we need to specify where you want to save file
			FileOutputStream fout = new FileOutputStream(new File(constant.Path_TestData + constant.File_TestData));

			// finally write content
			wb.write(fout);

			// close the file
			fout.close();
			wb.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
    // Function to print the value based on the text passed
	public static void printM(String text) {
		System.out.println("The Message is: " + text);
	}

	// Function is to perform the action based on the identifier passed
	public static void functionMain(WebDriver driver, String Xidentificationby, String Xid, String Xevent)
			throws InterruptedException {

		if (Xidentificationby == "XPATH" && Xevent == "CLICK") {

			WebElement element=driver.findElement(By.xpath(""+Xid+""));
            //driver.manage().window().maximize();
            
			// Zoom in  fucntion
/*			WebElement html = driver.findElement(By.tagName("html"));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD));*/
			
			driver.manage().timeouts().implicitlyWait(180,TimeUnit.SECONDS) ;
			
			// explicit wait - to wait for the compose button to be click-able
			WebDriverWait wait = new WebDriverWait(driver, 300); 
			
			wait.until(ExpectedConditions.visibilityOf(element));
			//Thread.sleep(120);
			wait.until(ExpectedConditions.stalenessOf(element));
			element.click();
			/*html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT)); // Zooming out
*/			//driver.findElement(By.xpath(""+Xid+"")).click();

		}

		else if (Xidentificationby == "XPATH" && Xevent == "SEARCH_DROP_DOWN") {

			driver.findElement(By.xpath(Xid)).click();
			wait = new WebDriverWait(driver, 30);
			driver.findElement(By.xpath(Xid)).sendKeys("Delhi");

			wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@class='select2-results']//li")));
			List<WebElement> list = driver.findElements(By.xpath("//ul[@class='select2-results']//li"));
			System.out.println("Auto Suggest List ::" + list.size());

			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).getText());

				if (list.get(i).getText().equals("Delhi Indira Gandhi Intl (DEL)")) {
					list.get(i).click();
					break;
				}

			}
			;
		}
	}
	
	// Function to get the element count based on the xpath passed
	public static int elementCnt(WebDriver driver, String text) {

		driver.manage().timeouts().implicitlyWait(180,TimeUnit.SECONDS) ;
		List<WebElement> links = driver.findElements(By.xpath(text));
		int linkCount = links.size();
		return linkCount;

	}

	// Page load function which will wait for 300 second
	public static void waitForLoad(WebDriver driver) {

		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 300);
		wait.until(pageLoadCondition);

	}
	
	
	public static String getScreenshot(WebDriver driver)
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		String path=System.getProperty("user.dir","D:\\Telia\\Testing\\Selenium\\")+"/Screenshot/"+System.currentTimeMillis()+".png";
		File destination=new File(path);
		
		try 
		{
			FileUtils.copyFile(src, destination);
		} catch (IOException e) 
		{
			System.out.println("Capture Failed "+e.getMessage());
		}
		
		return path;
	}

}
