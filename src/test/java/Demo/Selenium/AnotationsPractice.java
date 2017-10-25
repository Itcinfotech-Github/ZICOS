package Demo.Selenium;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AnotationsPractice {

	WebDriver driver;
	XSSFWorkbook book;
	@BeforeSuite
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\22706\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		System.out.println("go jop");
	}
	
	@Test(dataProvider="provideData")
	public void test1(String username,String pwd) throws InterruptedException {
		driver.get("https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
		driver.findElement(By.xpath("//input[@autocomplete='username']")).click();
		driver.findElement(By.xpath("//input[@autocomplete='username']")).sendKeys(username);
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@autocomplete='current-password']")).sendKeys(pwd);
	}
	
	
	/*@Test
	public void test2() {
		driver.get("http://flipkart.com");
	}*/
	
	
	@AfterSuite
	public void afterClass() {
		driver.quit();
	}
	
	@DataProvider
	public Object[][] provideData() throws IOException {
		/*Object[][] obj = new Object[3][2];
		obj[0][0] = "username1";
		obj[0][1] = "pwd1";
		
		obj[1][0] = "username2";
		obj[1][1] = "pwd2";
		
		obj[2][0] = "usename3";
		obj[2][1] = "pwd3";*/
		
		File src = new File("C:\\Users\\22706\\Documents\\DataProvider.xlsx");
		FileInputStream fis = new FileInputStream(src);
		book = new XSSFWorkbook(fis);
		XSSFSheet sheet = book.getSheetAt(0);
		
		Object[][] obj = new Object[3][2];
		/*obj[0][0] = sheet.getRow(0).getCell(0).getStringCellValue();
		obj[0][1] = sheet.getRow(0).getCell(1).getStringCellValue();
		
		obj[1][0] = sheet.getRow(1).getCell(0).getStringCellValue();
		obj[1][1] = sheet.getRow(1).getCell(1).getStringCellValue();
		
		obj[2][0] = sheet.getRow(2).getCell(0).getStringCellValue();
		obj[2][1] = sheet.getRow(2).getCell(1).getStringCellValue();*/
		
		for(int i = 0; i<= sheet.getLastRowNum(); i++) {
			for(int j = 0; j < 2; j++) {
				obj[i][j] = sheet.getRow(i).getCell(j).getStringCellValue();
			}
		}
		
		return obj;
	}
}
