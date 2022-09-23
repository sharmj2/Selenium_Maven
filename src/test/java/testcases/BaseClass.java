package testcases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class BaseClass {

	WebDriver driver;
	
	XSSFWorkbook wbook;
	XSSFSheet sheet;
	
	ExtentReports report;
	ExtentTest test;
	
	@BeforeTest
	public void dataSetup() throws IOException
	{
		System.out.println("Before Test");
		FileInputStream fis = new FileInputStream("exceldata.xlsx");
        
        wbook = new XSSFWorkbook(fis);
        sheet = wbook.getSheet("Sheet1");
        
        report = new ExtentReports("ExtentReport.html");
        
	}
	@AfterTest
	public void cleanup() throws IOException
	{
		System.out.println("After Test");
		wbook.close();
		
		report.flush();
		report.close();
		
	}
	
	@BeforeMethod
	public void setup(Method method)
	{
		test = report.startTest(method.getName());
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		
		driver = new ChromeDriver();
		
		driver.get("https://www.simplilearn.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
	}
	@AfterMethod
	public void close()
	{
		report.endTest(test);
		//Step7: Close the browser
				driver.close();
	}
}
