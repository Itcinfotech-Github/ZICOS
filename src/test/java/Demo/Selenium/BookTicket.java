package Demo.Selenium;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URI;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.property.complex.MessageBody;

@Listeners(Demo.Selenium.AppTest.class)
public class BookTicket extends BaseFrame {
	ExtentReports report;
	ExtentTest logger;
	String url = "https://www.goibibo.com/";
	By source = By.id("gosuggest_inputSrc");
	By destination = By.id("gosuggest_inputDest");
	By daypickerNavigation = By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--next']");
	By departDate = By.xpath("//div[text()='December 2017']/..//div[text()='15']");
	By returnDate = By.xpath("//div[text()='January 2018']/..//div[text()='1']");
	By calender = By.xpath("//div[@class='DayPicker DayPicker--en']");
	By getSetGo = By.xpath("//button[text()='Get Set Go']");
	By departFlight = By.xpath("//span[text()='12:10']/ancestor::div[contains(@class,'col-md-7 col-sm-7 col-xs-7')]/following-sibling::div//div[@class='control__indicator']");
	By returnFlight = By.xpath("//span[text()='23:05']/ancestor::div[contains(@class,'col-md-7 col-sm-7 col-xs-7')]/following-sibling::div//div[@class='control__indicator']");
	By totalPrice = By.xpath("//div[@id='fltTcktVoucher']//span[text()='9,050']");
	String expectedPrice = "8,550";
	By bookButton = By.xpath("//input[@value='BOOK']");
	By addMeal = By.xpath("//a[text()='Add Meals']");
	By addDish = By.xpath("//span[contains(.,'Masala Omelette With Chicken')]/following-sibling::span//a");
	By hotel = By.xpath("//span[text()='Hotels']");
	By hotelName = By.id("gosuggest_inputL");
	By checkInDate = By.xpath("//div[text()='27']");
	By checkoutDate = By.xpath("//div[text()='28']");
	
	@Parameters({ "browserName" })
	@BeforeMethod
	public void initialize(String browserName) {
		
		if(browserName.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\22706\\Downloads\\geckodriver-v0.19.0-win64\\geckodriver.exe");
			driver = new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\22706\\Downloads\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		
	}
	
	@Test(groups={"Regression"})
	public void bookTicket() {
		report = new ExtentReports("C:\\ReportFiles\\BookTicket.html");
		logger = report.startTest("BookTicket");
		navigateTo(url);
		logger.log(LogStatus.INFO, "Navigated to url");
		Assert.assertTrue(click(source), "Source field clicked");
		logger.log(LogStatus.INFO, "Source field clicked");
		Assert.assertTrue(setText(source, "Bangalore (BLR)"), "Source place is set");
		logger.log(LogStatus.INFO, "Source place is set");
		halt(2000);
		Assert.assertTrue(setText(destination, "Bhubaneswar (BBI)"), "Destination place is set");
		logger.log(LogStatus.INFO, "Destination place is set");
		halt(2000);
		click(By.xpath("(//i[@class='icon-calendar1 ico22 widgetCalIcon '])[1]"));
		do {
			try{
			List<WebElement> count = getElements(departDate);
			 if(count.size() > 0) {
				click(departDate);
				logger.log(LogStatus.INFO, "Depart date is selected");
			 }
			}catch(Exception e) {
			   click(daypickerNavigation);
			}
			
		}while(isElementDisplayed(calender));
		
		click(By.xpath("(//i[@class='icon-calendar1 ico22 widgetCalIcon '])[2]"));
		
		do {
			try{
			List<WebElement> count = getElements(returnDate);
			 if(count.size() > 0) {
				click(returnDate);
				logger.log(LogStatus.INFO, "Return date is selected");
			 }
			}catch(Exception e) {
			   click(daypickerNavigation);
			}
			
		}while(isElementDisplayed(calender));
		
		Assert.assertTrue(click(getSetGo), "search button clicked");
		Assert.assertTrue(clickUsingJS(departFlight), "Depart Flight is selected");
		logger.log(LogStatus.INFO, "Depart Flight is selected");
		Assert.assertTrue(clickUsingJS(returnFlight), "Return Flight is selected");
		logger.log(LogStatus.INFO, "Return Flight is selected");
		Assert.assertEquals(expectedPrice, getText(totalPrice),"Right flights are selected");
		logger.log(LogStatus.INFO, "Expected price is equal to Actual price");
		click(bookButton);
		Assert.assertTrue(click(addMeal), "Add meal is clicked");
		Assert.assertTrue(click(addDish), "Dish is selected");
		logger.log(LogStatus.INFO, "Dish is selected");
		
		
	}
	
	@Test(groups={"Regression"})
	public void bookHotel() {
		report = new ExtentReports("C:\\ReportFiles\\BookHotel.html");
		logger = report.startTest("BookHotel");
		navigateTo(url);
		Assert.assertTrue(click(hotel), "Hotel icon is selected");
		Assert.assertTrue(setText(hotelName, "coorg"), "Area is selected");
		click(By.xpath("//input[@placeholder='Choose Checkin']"));
		Assert.assertTrue(click(checkInDate), "Checked In date is selected");
		Assert.assertTrue(click(checkoutDate), "Check out date is selected");
		click(getSetGo);
		halt(5000);
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		report.endTest(logger);
		report.flush();
		//navigateTo("C:\\ReportFiles\\BookTicket.html");
		/*driver.manage().window().maximize();
		waitForElement(By.xpath("//i[@class='mdi-action-track-changes']")).click();
		File screenshotAs = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotAs, new File("C:\\ReportFiles\\Report.png"));
		StringBuffer builder = new StringBuffer();
		builder.append("<html><head><title>Test Result</title></head>");
		builder.append("<body>");
		builder.append("Test Results");
		builder.append("<img src=\"C:\\ReportFiles\\Report.png\"");
		builder.append("</body></html>");
		//sendMail(builder.toString());
		//driver.close();
		File file = new File("C:\\ReportFiles\\first1234.html");
		FileOutputStream out = new FileOutputStream(file);
		Writer write = new OutputStreamWriter(out);
		write.write(builder.toString());
		write.close();*/
	}
	
	public void sendMail(String subject) throws Exception {
		String to = "Akshaya.Panigrahi@itcinfotech.com";

		System.out.println("Enter sendMailViaExchnageService");

        ExchangeService service = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
        ExchangeCredentials credentials = new WebCredentials("Satyajit.Samal@itcinfotech.com","amrit@90");
        service.setCredentials(credentials);
        service.setUrl(new URI("https://outlook.office365.com/EWS/Exchange.asmx"));

        EmailMessage msg = new EmailMessage(service);
        msg.setSubject(subject);
        msg.setBody(MessageBody.getMessageBodyFromText(source.toString()));
        List<String> toAddressList = Arrays.asList(to.split("\\s*,\\s*"));
        Iterator<String> mailList = toAddressList.iterator();

        msg.getToRecipients().addSmtpAddressRange(mailList);
      
        msg.send();
				
		System.out.println("\nMail was sent successfully.");

		System.out.println("Exit sendMailViaExchnageService");
	}


}
