package testcases;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import pages.LoginPage;

public class SimplilearnLoginTest extends BaseClass {
	
	
	@Test
	public void Test1() {
		test.log(LogStatus.INFO, "Test 1 started");

		LoginPage lp = new LoginPage(driver);
		lp.login("abc@xyz.com","Test@1234");
		
		//Step6: Validate the error message on screen
		WebElement Error = driver.findElement(By.id("msg_box"));
		
		String ActError = Error.getText();
		String ExpError = "The email or password you have entered is invalid.";
		
		Assert.assertTrue(Error.isDisplayed());
		//Assert.assertEquals(ActError, ExpError);
		
		/*if(ActError.equals(ExpError)) {
			System.out.println("TC PAssed");
		}else {
			System.out.println("Tc Failed");
		}*/
		
		
		List<WebElement> Links = driver.findElements(By.tagName("a"));
		System.out.println("Total num of links are " + Links.size());
		
		for(int index=0;index<Links.size();index++) {
			
			System.out.println(Links.get(index).getAttribute("href"));
			
		}
		
	}
		@Test
		@Parameters({"uname","pwd"})
	public void Test2(String username, String password) {
			
		test.log(LogStatus.INFO, "Test 2 started");	
		LoginPage lp = new LoginPage(driver);
		lp.login(username,password);
			
		System.out.println("In Test 2 method");
	}
		
		@Test
	public void Test3() {
			
			test.log(LogStatus.INFO, "Test 3 started");
			String username = sheet.getRow(1).getCell(0).getStringCellValue();
			String password = sheet.getRow(1).getCell(1).getStringCellValue();
			LoginPage lp = new LoginPage(driver);
			lp.login(username,password);
			
		System.out.println("In Test 3 method");
	}

}

