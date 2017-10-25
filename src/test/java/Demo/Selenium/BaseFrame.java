package Demo.Selenium;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class BaseFrame {
	WebDriver driver;
	
	
	public WebElement getElement(By by) {
		waitForElement(by);
		return driver.findElement(by);
	}
	
	public List<WebElement> getElements(By by) {
		waitForElement(by);
		return driver.findElements(by);
	}
	public WebElement getElementUsingXpath(String xpath) {
		waitForElementXpath(xpath);
		return driver.findElement(By.xpath(xpath));
	}
	
	public WebElement getElementUsingCss(String css) {
		waitForElementCss(css);
		return driver.findElement(By.xpath(css));
	}
	
	public WebElement waitForElement(By by) {
		return new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(by));
	}
	
	public void waitForElementXpath(String xpath) {
		new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
	}
	public void waitForElementCss(String css) {
		new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(css)));
	}
	
	public WebElement waitForElementVisible(WebElement ele) {
		return new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(ele));
	}
	
	public String getTitleOfPage() throws InterruptedException {
		String title = "";
		boolean status = hasPageLoaded();
		if(status) {
			 title = driver.getTitle();
		}else {
			Reporter.log("Page is not Loaded");
			status = false;
		}
		
		return title;
		
	}
	
	public boolean hasPageLoaded() throws InterruptedException {
		long timeOut = System.currentTimeMillis()
				+(1000);
		boolean result;
		do {
			result = String.valueOf(
					executeJavascript("return document.readyState")).equals(
					"complete");
			if (result) {
				return result;
			} else {
				wait(250);
			}
		} while (System.currentTimeMillis() < timeOut);
		Reporter.log("page failed to load");
		return result;
	}
	
	protected String executeJavascript(String javascript, WebElement el) {
		String output = "";
		try {
			output = String.valueOf(((JavascriptExecutor) driver)
					.executeScript(javascript, el));
		} catch (Exception e) {
			Reporter.log("Unable to Execute Script");
		}
		return output;
	}
	
	protected String executeJavascript(String javascript) {
		String output = "";
		try {
			output = String.valueOf(((JavascriptExecutor) driver)
					.executeScript(javascript));
		} catch (Exception e) {
			Reporter.log("Unable to Execute Script");
		}
		return output;
	}
	
	public boolean click(WebElement element) {
		waitForElementVisible(element);
		try{
		    scrollTo(element);
		    waitForElementClickable(element).click();
		    return true;
		}catch(Exception e) {
			Reporter.log("Unable to click on element");
			return false;
		}
	}
	
	public boolean click(By by) {
		waitForElementVisible(getElement(by));
		try{
		    //scrollTo(getElement(by));
		    waitForElementClickable(getElement(by)).click();
		    return true;
		}catch(Exception e) {
			Reporter.log("Unable to click on element");
			return false;
		}
	}
	
	public boolean clickUsingJS(WebElement element) {
		boolean status = false;
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", element);
			status = true;
		}catch(Exception e) {
			Reporter.log("Exception in clickJS - " +e);
			status = false;
		}
		
		return status;
	}
	
	public boolean clickUsingJS(By by) {
		boolean status = false;
		waitForElement(by);
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", getElement(by));
			status = true;
		}catch(Exception e) {
			Reporter.log("Exception in clickJS - " +e);
			status = false;
		}
		
		return status;
	}
	
	public WebElement waitForElementClickable(WebElement element) {
		return new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public boolean scrollTo(WebElement ele) {
		boolean status = false;
		try{
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);",
				ele);
		status = true;
	} catch (Exception e) {
		Reporter.log("scrollTOElement - Eexception - " + e);
		status = false; 
	}
	return status;
	}
	
	public boolean isElementDisplayed(WebElement ele) {
		return waitForElementVisible(ele).isDisplayed();
	}
	
	public boolean isElementDisplayed(By by) {
		boolean status = false;
		try{
			status = waitForElement(by).isDisplayed();
		}catch(Exception e) {
			Reporter.log("No such Element present:" + e);
			status = false;
		}
		
		return status;
	}
	
	public boolean isCheckBoxSelected(By by) {
		return getElement(by).isSelected();
	}
	
	public boolean isRadioButtonEnabled(By by) {
		return getElement(by).isEnabled();
	}
	
	public String getText(WebElement ele, String css) {
		return ele.findElement(By.cssSelector(css)).getText().trim();
	}
	
	public String getText(By by) {
		return getElement(by).getText().trim();
	}
	
	public String getAttribute(String css, String attribute) {
		return getElementUsingCss(css).getAttribute(attribute);
	}
	
	public String getAttribute(By by, String attribute) {
		return getElement(by).getAttribute(attribute);
	}
	
	public boolean setText(WebElement ele, String text) {
		try{
			waitForElementVisible(ele).sendKeys(text);
			return true;
		}catch(Exception e) {
			Reporter.log("Unable to set Text: - " +e);
			return false;
		}
		
		
	}
	
	public boolean setText(By by, String text) {
		try{
			waitForElement(by).sendKeys(text);
			return true;
		}catch(Exception e) {
			Reporter.log("Unable to set Text: - " +e);
			return false;
		}
		
		
	}
	
	public boolean selectDropdownValue(WebElement ele, String text) {
		try{
			Select sel = new Select(ele);
			sel.selectByVisibleText(text);
			return true;
		}catch(Exception e) {
			Reporter.log("Unable to select text in Dropdown - " +e);
			return false;
		}
		
	}
	
	public void mouseHover(WebElement ele) {
		Actions act = new Actions(driver);
		act.moveToElement(ele).perform();
		
	}
	
	public void mouseHover(By by) {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(by)).perform();
		
	}
	
	public void halt(long millis) {
		try{
			Thread.sleep(millis);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean performKeyOperation(Keys key) {
		try{
			Actions act = new Actions(driver);
			act.sendKeys(key).perform();
			return true;
		}catch(Exception e) {
			Reporter.log("Unable to send keys - " +e);
			return false;
		}
	}
	
	public void switchToWindow() {
		String currentWindow = driver.getWindowHandle();
		
		Set<String> windows = driver.getWindowHandles();
		for(String temp : windows) {
			if(!temp.equals(currentWindow)) {
				driver.switchTo().window(temp);
			}
		}
	}
	
	public void switchToParentWindow(String parentWindow) {
		driver.switchTo().window(parentWindow);
	}
	
	public boolean switchToFrame(String frameName) {
		try {
			driver.switchTo().frame(frameName);
			return true;
		}catch(NoSuchFrameException e) {
			Reporter.log("Unable to switch to Frame - " +e);
			return false;
		}
	}
	
	public boolean switchToFrame(WebElement ele) {
		try {
			driver.switchTo().frame(ele);
			return true;
		}catch(NoSuchFrameException e) {
			Reporter.log("Unable to switch to Frame - " +e);
			return false;
		}
	}
	
	public void returnFromFrame() {
		driver.switchTo().defaultContent();
	}
	
	public void waitAndSwitchToFrame(By by) {
		new WebDriverWait(driver, 30).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));
	}
	
	public void navigateTo(String url) {
		driver.navigate().to(url);
	}
	
	public void navigateBack() {
		driver.navigate().back();
		halt(3000);
	}
	
	public Alert switchToAlert() {
		Alert alert = driver.switchTo().alert();
		return alert;
	}

}
