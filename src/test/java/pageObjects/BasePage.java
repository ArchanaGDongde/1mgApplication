package pageObjects;

import static org.testng.Assert.assertTrue;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.model.Log;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.google.common.io.Files;

import config.ConfigFileReader;
import helper.LoggerHelper;

import util.DateUtil;

public class BasePage 
{	
   public static WebDriver webDriver;
	Logger log=LoggerHelper.getLogger(getClass());
	// Constructor
	public BasePage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	
	// Click Method
	public void click(WebElement webElement) {
		try {
			screenshot("clicked");
			webElement.click();
			waitFor(2000);
		} catch (Exception e) {
			e.printStackTrace();
//			Assert.fail();
			throw e;
		}
	}

	// Read Text
	public String readText(WebElement webElement) {
		try {
			//screenshot("reading text");
			return webElement.getText();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			//return null;
		}

	}
	
	public String writeText(WebElement webElement, String text) {
		try {
			//screenshot("written text");
			clear(webElement);
			webElement.sendKeys(text);
		} catch (Exception e) {
			e.printStackTrace();
//			Assert.fail();
			throw e;
			}
		return text;
	}
		
	public void clear(WebElement webElement) {
		try {
			//screenshot("cleared");
			webElement.clear();
			waitFor(2000);
		} catch (Exception e) {
			e.printStackTrace();
//			Assert.fail();
			throw e;
		}
	}
	
	public void writeText1(WebElement webElement, String text) {
		try {
			//screenshot("written text");
			clear(webElement);
			webElement.sendKeys(text);
			clear(webElement);
			webElement.sendKeys(text);
		} catch (Exception e) {
			e.printStackTrace();
//			Assert.fail();
			throw e;
		}
	}

	public static String readTextNode(WebElement w) {
		try {
			String text = w.getText().trim();
			List<WebElement> children = w.findElements(By.xpath("./*"));
			for (WebElement child : children) {
				String childText = child.getText();
				if (childText.contains("(")) {
					childText = childText.replaceAll("\\(", "\\\\(");
					childText = childText.replaceAll("\\)", "\\\\)");
				}
				text = text.replaceFirst(childText, "").trim();
			}
			return text;
		} catch (Exception e) {
			e.printStackTrace();
			//Assert.fail();
			//return null;
			throw e;
		}

	}

	// Hover element
	public void doHover(WebElement string) {
		try {
			Actions builder = new Actions(webDriver);
			builder.moveToElement(string).build().perform();
			//screenshot("hovering");
		} catch (Exception e) {
			e.printStackTrace();
			//Assert.fail();
			throw e;
		}
	}

	public void doHoverandClick(WebElement webElement) {
		try {
			//screenshot("hoverclick");
			Actions a = new Actions(webDriver);
			//builder.moveToElement(webElement).click().build().perform();

			a.moveToElement(webElement).click().build().perform();
			waitFor(5000);
		} catch (Exception e) {
			e.printStackTrace();
			//Assert.fail();
			throw e;
		}

	}

	public void setValueInDropdownText(WebElement dropDownElement, String Text) {
		try {
			Select selObj = new Select(dropDownElement);
			selObj.selectByVisibleText(Text);
			//screenshot("setValueInDropdownText");
		} catch (Exception e) {
			e.printStackTrace();
			//Assert.fail();
			throw e;
		}
	}

	public void setValueInDropdownByIndex(WebElement dropDownElement, int value) {
		try {
			Select selObj = new Select(dropDownElement);
			selObj.selectByIndex(value);
			//screenshot("setValueInDropdownByIndex");
		} catch (Exception e) {
			e.printStackTrace();
			//Assert.fail();
			throw e;
		}
	}

	public void setValueInDropdownByValue(WebElement dropDownElement, String value) {
		try {
			Select selobj = new Select(dropDownElement);
			selobj.selectByValue(value);
			//screenshot("setValueInDropdownByValue");
		} catch (Exception e) {
			e.printStackTrace();
			//Assert.fail();
			throw e;
		}
	}

	public String getValueInDropdown(WebElement dropDownElement) {
		try {
			Select selObj = new Select(dropDownElement);
			String value = readText(selObj.getFirstSelectedOption());
			//screenshot("getValueInDropdown");
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			//Assert.fail();
			//return null;
			throw e;
		}

	}




	public String firstElement(WebElement value) {
		try {
			Select select = new Select(value);
			WebElement option = select.getFirstSelectedOption();
			String defaultItem = option.getText();
			//screenshot("firstElement");
			return defaultItem;
		} catch (Exception e) {
			e.printStackTrace();
			//Assert.fail();
			//return null;
			throw e;
		}

	}

	public void setDateInDatePicker(WebElement calenderBtn, int year, int month, int day) {
		try {
			click(calenderBtn);
			waitFor(2000);
			WebElement yearDrpDwnEle = webDriver.findElement(By.xpath("//ngb-datepicker-navigation-select/select[2]"));//// ngb-datepicker//select[2]
			Select selObj = new Select(yearDrpDwnEle);
			selObj.selectByVisibleText(String.valueOf(year));

			WebElement monthDrpDwn = webDriver.findElement(By.xpath("//ngb-datepicker-navigation-select/select[1]"));//// ngb-datepicker//select[1]
			Select selObj1 = new Select(monthDrpDwn);
			selObj1.selectByValue(month + "");
			WebElement dayEle = webDriver.findElement(By.xpath("(//div[contains(@class,'ngb-dp-day')]/div[text()=" + day + "])[1]"));
			//dayEle.click();
			//screenshot("setDateInDatePicker");
			clickOnElementByJs(dayEle);
		} catch (Exception e) {
			e.printStackTrace();
			//Assert.fail();
			throw e;
		}
	}

	public void waitForPageLoad() {
		ExpectedCondition<Boolean> pageLoadCondition = new
				ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver driver) {
						return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
					}
				};
		WebDriverWait wait = new WebDriverWait(webDriver, ConfigFileReader.getExplicitWait());
		wait.until(pageLoadCondition);
	}

	public void setDateInDatePicker(WebElement calenderBtn, int year, String month, int day) {
		try {
			click(calenderBtn);
			WebElement yearDrpDwnEle = webDriver.findElement(By.xpath("(//ngb-datepicker//select)[2]"));
			Select selObj = new Select(yearDrpDwnEle);
			selObj.selectByVisibleText(String.valueOf(year));
			WebElement monthDrpDwn = webDriver.findElement(By.xpath("(//ngb-datepicker//select)[1]"));
			Select selObj1 = new Select(monthDrpDwn);
			System.out.println(month);
			selObj1.selectByVisibleText(month);
//			WebElement dayEle = webDriver.findElement(By.xpath(
//					"//div[@class='btn-light' or @class='btn-light bg-primary text-white' and text()='" + day + "']"));
			WebElement dayEle = webDriver
					.findElement(By
							.xpath("(//div[contains(@class,'ngb-dp-day')]/div[contains(@class,'btn-light') and text()='"
									+ day + "'])[1]"));
			 //dayEle.click();
			System.out.println(dayEle.getText());
			clickOnElementByJs(dayEle);
		} catch (Exception e) {
			e.printStackTrace();
			//Assert.fail();
			throw e;
		}
	}

	public void setDateInDatePickerForStartDate(WebElement calenderBtn, int year, String month, int day) {
		try {
			click(calenderBtn);
			WebElement yearDrpDwnEle = webDriver.findElement(By.xpath("(//ngb-datepicker//select)[2]"));
			Select selObj = new Select(yearDrpDwnEle);
			selObj.selectByVisibleText(String.valueOf(year));
			WebElement monthDrpDwn = webDriver.findElement(By.xpath("(//ngb-datepicker//select)[1]"));
			Select selObj1 = new Select(monthDrpDwn);
			selObj1.selectByVisibleText(month);
			WebElement dayEle = webDriver
					.findElement(By.xpath("//div[@class='ngb-dp-day']/div[text()=" + day + "][1]"));
			dayEle.click();
			//screenshot("setDateInDatePicker");
		} catch (Exception e) {
			e.printStackTrace();
			//Assert.fail();
			throw e;
		}
	}

	public void setDateInDatePickerForEndState(WebElement calenderBtn, int year, String month, int day) {
		try {
			click(calenderBtn);
			WebElement yearDrpDwnEle = webDriver.findElement(By.xpath("(//ngb-datepicker//select)[2]"));
			Select selObj = new Select(yearDrpDwnEle);
			selObj.selectByVisibleText(String.valueOf(year));
			WebElement monthDrpDwn = webDriver.findElement(By.xpath("(//ngb-datepicker//select)[1]"));
			Select selObj1 = new Select(monthDrpDwn);
			selObj1.selectByVisibleText(month);
			List<WebElement> dayEle = webDriver
					.findElements(By.xpath("//div[@class!='btn-light text-muted outside' and text()='" + day + "']"));
			if (dayEle.size() > 1) {
				click(dayEle.get(1));
			} else {
				click(dayEle.get(0));
			}
			//screenshot("setDateInDatePicker");
		} catch (Exception e) {
			e.printStackTrace();
			//Assert.fail();
			throw e;
		}
	}

	public String getDateInDatePicker(WebElement webElement) {
		try {
			//screenshot("getDateInDatePicker");
			return webElement.getAttribute("value");
		} catch (Exception e) {
			e.printStackTrace();
			//Assert.fail();
			//return null;
			throw e;
		}
	}

	public void waitUntilElementVisible(WebElement webElement) {
		try {
			WebDriverWait wait = new WebDriverWait(webDriver, ConfigFileReader.getExplicitWait());
			wait.until(ExpectedConditions.visibilityOf(webElement));
			//screenshot("waitUntilElementVisible");
		} catch (Exception e) {
			e.printStackTrace();
			//Assert.fail()
			throw e;
		}
	}

	public static void waitUntilElementToBeClickable(WebElement webElement) {
		try {
			WebDriverWait wait = new WebDriverWait(webDriver, ConfigFileReader.getExplicitWait());
			wait.until(ExpectedConditions.elementToBeClickable(webElement));
			//screenshot("waitUntilElementToBeClickable");
		} catch (Exception e) {
			e.printStackTrace();
			//Assert.fail();
			throw e;
		}
	}

	public static void waitUntilElementToBeClickableScrolling(WebElement webelement) {
		try {
			for (int i=0;i<3;i++) {
				if (isElementPresent(webelement)) {
					break;
				}
				waitFor(2000);
			}
			for (int i=0;i<30;i++) {
				if (!isElementPresent(webelement)) {
					scrollDownByAction();
					waitFor(3000);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void waitUntilElementVisibleRepeated(WebElement webElement, int count) {
		try {
			WebDriverWait wait = new WebDriverWait(webDriver,
					ConfigFileReader.getExplicitWait());
			for (int i = 0; i < count; i++) {
				try {
					wait.until(ExpectedConditions.visibilityOf(webElement));
					break;
				} catch (Exception e) {
				}
				waitFor(500);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void waitUntilElementToBeClickableRepeated(WebElement webElement, int count) {
		try {
			WebDriverWait wait = new WebDriverWait(webDriver,
					ConfigFileReader.getExplicitWait());
			for (int i = 0; i < count; i++) {
				try {
					wait.until(ExpectedConditions.elementToBeClickable(webElement));
					break;
				} catch (Exception e) {
				}
				waitFor(500);
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
	}

	public void waitUntilElementToBeClickableRepeated(By by, int count) {
		try {
			WebDriverWait wait = new WebDriverWait(webDriver,
					ConfigFileReader.getExplicitWait());
			for (int i = 0; i < count; i++) {
				try {
					wait.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(by)));
					break;
				} catch (Exception e) {
				}
				waitFor(500);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public boolean waitForMessage(String expecteMessage) {
		try {
			return waitForMessage(expecteMessage);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public boolean waitForMessageLonger(String expecteMessage) {
		try {
			
			return waitForMessage(expecteMessage);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	
	public boolean waitForMessage(long waitTime, long incrTime, String expectedMessage) {
		try {
			long startTime = System.currentTimeMillis();
			long targetTime = startTime + waitTime;
			long currTime = startTime;
			while (currTime <= targetTime) {
				if (webDriver.getPageSource().contains(expectedMessage)) {
					return true;
				}
				waitFor(incrTime);
				currTime = System.currentTimeMillis();
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean waitForOneOfMessages(String[] expectedMessages) {
		try {
			return waitForOneOfMessages(expectedMessages);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public boolean waitForOneOfMessages(long waitTime, long incrTime, String[] expectedMessages) {
		try {
			long startTime = System.currentTimeMillis();
			long targetTime = startTime + waitTime;
			long currTime = startTime;
			while (currTime <= targetTime) {
				for (String expectedMessage : expectedMessages) {
					if (webDriver.getPageSource().contains(expectedMessage)) {
						return true;
					}
				}
				waitFor(incrTime);
				currTime = System.currentTimeMillis();
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	public void waitUntilElementToBeLocated(By by) {
		try {
			WebDriverWait wait = new WebDriverWait(webDriver,
				ConfigFileReader.getExplicitWait());
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void waitUntilElementToBeLocatedRepeated(WebElement webElement, int count) {
		waitUntilElementToBeLocatedRepeated(toByVal(webElement), count);
	}

	public void waitUntilElementToBeLocatedRepeated(By by, int count) {
		try {
			WebDriverWait wait = new WebDriverWait(webDriver, ConfigFileReader.getExplicitWait());
			for (int i = 0; i < count; i++) {
				try {
					wait.until(ExpectedConditions.presenceOfElementLocated(by));
					break;
				} catch (Exception e) {
				}
				waitFor(500);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public WebElement reEvalWebElement(WebElement webElement) {
		try {
			By by = toByVal(webElement);
			return webDriver.findElement(by);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}



	public By toByVal(WebElement we) {
		try {
			String[] data = we.toString().split(" -> ")[1].replace("]", "").split(": ");
			String locator = data[0];
			String term = data[1];

			switch (locator) {
				case "xpath":
					return By.xpath(term);
				case "css selector":
					return By.cssSelector(term);
				case "id":
					return By.id(term);
				case "tag name":
					return By.tagName(term);
				case "name":
					return By.name(term);
				case "link text":
					return By.linkText(term);
				case "class name":
					return By.className(term);
			}
			return (By) we;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void waitForJQToLoad() {
		try {
			int count =0; if((Boolean) ((JavascriptExecutor) webDriver)
					.executeScript("return window.jQuery != undefined"))
			{ while(!(Boolean) ((JavascriptExecutor) webDriver).executeScript("return jQuery.active == 0"))
			{
				waitFor(4000);
				if(count>4)
					break;
				count++;
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void actionWithMouseOnElement() {
		try {
			Actions builder = new Actions(webDriver);
			Action mouseOverHome = builder.moveToElement(reEvalWebElement(null)).build();
			mouseOverHome.perform();
			//screenshot("actionWithMouseOnElement");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void scrollUpAndDown(int value) {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) webDriver;
			jse.executeScript("window.scrollBy(0," + value + ")", "");
		jse.executeScript("document.querySelector('.grid gap-x-4 grid-cols-2 2xl:grid-cols-3 h-32 overflow-auto s:pt-1').scrollDown=4000");
			
			//screenshot("scrollUpAndDown");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void scrollUp() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) webDriver;
			js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
			//screenshot("scrollUp");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public void scrollUpPage() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) webDriver;
			js.executeScript("window.scroll(0,-500)");
			//screenshot("scrollUp");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void scrollDown() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) webDriver;
			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
			//screenshot("scrollDown");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public static void scrollDownByAction() {
		try {
			Actions act = new Actions(webDriver);
			act.sendKeys(Keys.PAGE_DOWN).build().perform();
			//screenshot("scrollDownByAction");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}



	public static String getTimestamp() {
		try {
			return new Date().getTime() + "";
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			//return null;
		}

	}

	public static void softAssert(WebElement Actual, String Expected) {
		SoftAssert sa = new SoftAssert();
		try {
			sa.assertEquals(Actual.getText(), Expected);
		} catch (Exception e) {
			e.printStackTrace();
			//Assert.fail();
			System.out.println("SoftAssert for : " + Actual + "and " + Expected + "Fails");
			throw e;
		}

	}

	public static void typeDownKey(WebElement webElement) {
		try {
			webElement.sendKeys(Keys.ARROW_DOWN);
		} catch (Exception e) {
			e.printStackTrace();
			//Assert.fail();
			throw e;
		}
	}

	public static void typeEnterKey(WebElement webElement) {
		try {
			webElement.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			e.printStackTrace();
			//Assert.fail();
			throw e;
		}
	}

	public static void typeBackspaceKey(WebElement webElement) {
		try {
			webElement.sendKeys(Keys.BACK_SPACE);
		} catch (Exception e) {
			e.printStackTrace();
			//Assert.fail();
			throw e;
		}
	}

	public static void typeDeleteKey(WebElement webElement) {
		try {
			webElement.sendKeys(Keys.DELETE);
		} catch (Exception e) {
			e.printStackTrace();
			//Assert.fail();
			throw e;
		}
	}

	public void typeDeleteToClearTextInDropdown(WebElement locatorForDeletingTxtInTxtBx) {
		try {
			int noOfChars = locatorForDeletingTxtInTxtBx.getText().length();
			doubleClick(locatorForDeletingTxtInTxtBx);
			for (int i = 0; i < noOfChars; i ++) {
				typeBackspaceKey(locatorForDeletingTxtInTxtBx);
			}
			typeDeleteKey(locatorForDeletingTxtInTxtBx);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	public static void typeTextToGetDropdown(WebElement locatorForTypingTxtInTxtBx, String textToTypeInTxtBx,
											 List<WebElement> locatorDropdown) {
		try {
			locatorForTypingTxtInTxtBx.clear();
			String append[] = textToTypeInTxtBx.split("");
			for (int i = 0; i < append.length; i++) {
				waitFor(500);
				locatorForTypingTxtInTxtBx.sendKeys(append[i]);
			}
			if (locatorDropdown!=null) {
				List<WebElement> options = locatorDropdown;
				for (WebElement option : options) {
					if (option.getText().equalsIgnoreCase(textToTypeInTxtBx)) {
						System.out.println(option.getText());
						WebDriverWait wait = new WebDriverWait(webDriver, ConfigFileReader.getExplicitWait());
						wait.until(ExpectedConditions.elementToBeClickable(option));
						option.click();
						break;
					}
					//screenshot("typeTextToGetDropdown");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static void getSelectValueFromList(WebDriver driver, List<WebElement> elements, String value) {
		try {
			List<WebElement> options = elements;
			System.out.println(options.size());
			for (WebElement option : options) {
				if (option.getText().equalsIgnoreCase(value)) {
					System.out.println(option.getText());
					option.click();
					break;
				}
			}
			//screenshot("getSelectValueFromList");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public static void UploadFiles(String filepath) {
		try {
			StringSelection stringSelection = new StringSelection(filepath);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			//screenshot("UploadFiles");
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (Exception e)	{
			System.out.println("File Upload");
			e.printStackTrace();
			throw e;
		}
	}

	public static void scroll(WebDriver driver, String move) {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			if (move.equals("up")) {
				jse.executeScript(
						"window.scrollTo(Math.max(document.documentElement.scrollHeight, document.body.scrollHeight, document.documentElement.clientHeight), 0);"); // this
				// will
				// scroll
				// up
			} else if (move.equals("down")) {
				jse.executeScript(
						"window.scrollTo(0, Math.max(document.documentElement.scrollHeight, document.body.scrollHeight, document.documentElement.clientHeight));");
			} else {
				System.out.println("Either Scroll 'up' or 'down'");
			}
			//screenshot("scroll");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void SwitchTOFrame(WebElement element) {
		try {
			webDriver.switchTo().frame(element);
			//screenshot("SwitchTOFrame");
		} catch (Exception e) {
			System.out.println("not able to handle frame" + e.getMessage());
			throw e;
		}
	}
	
	

	public static void getWindowHandle(WebDriver driver) {
		try {
			Set<String> allwindows = driver.getWindowHandles();
			Iterator<String> itr = allwindows.iterator();
			while (itr.hasNext()) {
				String ParentWin = itr.next();
				String childWin = itr.next();
				driver.switchTo().window(childWin);
			}
		} catch (Exception e) {
			System.out.println("Exception in window Handling" + e.getMessage());
			throw e;
		}
	}

	public static String getCurrentDay() {
		try {
			String Weekday_name = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(System.currentTimeMillis());
			return Weekday_name;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			//return null;
		}

	}
//
//	public boolean isClickable(WebElement web) {
//		try {
//			WebDriverWait wait = new WebDriverWait(webDriver, 5);
//			wait.until(ExpectedConditions.elementToBeClickable(web));
//			return true;
//		} catch (Exception e) {
//			return false;
//		}
//	}

	public void implicitwaitFor(int seconds) {
		try {
			webDriver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
		} catch (Exception b) {
			b.printStackTrace();
			throw b;
		}

	}

	public static void waitFor(long milliSeconds) {
		try {
			Thread.sleep(milliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean verifyData(String expected, WebElement element) {
		try {
			if (element.getText().trim().equals(expected))
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

	public static void changeSingleToDoubleSlash(String folderpath, WebElement xpathForAttach) {
		try {
			File folder = new File(folderpath);
			File[] listOfFile = folder.listFiles();
			for (int i = 0; i < listOfFile.length; i++) {
				System.out.println(listOfFile[i].getPath());
				String path = listOfFile[i].getAbsolutePath().replace("\\", "\\\\");
				xpathForAttach.sendKeys(path);
				waitFor(3000);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public static void ScrollUpToElement(WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) webDriver;
			js.executeScript("arguments[0].scrollIntoView();", element);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public boolean verifyMessage(String expected) {
		try {
			String actual = webDriver.findElement(By.id("toast-container")).getText().trim();
			if (actual.equals(expected))
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

	public void clickOnElementByJs(WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) webDriver;
			js.executeScript("arguments[0].click()", element);
			//screenshot("clickOnElementByJs");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("not clickable");
			throw e;
		}
	}

	public static void clickOnElementByCoordinates(int x, int y) {
		try {
			Actions act = new Actions(webDriver);
			act.moveByOffset(x, y).click().build().perform();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	// method to click on any card under things to do by entering any input like
	// program name, checkIn name or dates for review cards.
	public static void clickonCard(String programName) {
		try {
			String xpath = "//div[@class='card-body']//b[text()='" + programName
					+ "']//..//..//..//..//..//div[@class='btn-card']";
//			JavascriptExecutor js = (JavascriptExecutor)webDriver;
//			BasePage bp = new BasePage(webDriver);
//			for (int i = 0; i <3; i++) {
//				System.out.println("element search loop count "+i+"++++++++++++++++++++++++++++");
//				if (!isElementPresent(By.xpath(xpath)))	{
//					System.out.println("entered if condition"+"------------------------------");
////					scroll(webDriver, "down");	
//					bp.scrollDown();
//				} else {
//					break;
//				}
//			}
			WebElement element = webDriver.findElement(By.xpath(xpath));
			element.click();
//			js.executeScript("window.scrollBy(0,500)");
//			js.executeScript("arguments[0].click();",element );
//			js.executeScript("scrollIntoView(true);", element);
//			waitFor(1000);
			
//			bp.scrollDown();
			//bp.waitUntilElementVisible(element);
//			bp.waitUntilElementToBeClickable(element);
			
			System.out.println("scrolled down");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	// page refresh method

	public void pageRefresh() {
//		public static void pageRefresh() {

		try {
			waitFor(1000);
			webDriver.navigate().refresh();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void clearDownloadsFolder() {
		String downloadDir = System.getProperty("user.dir") + "/src/test/resources/downloads/";
		try {
			FileUtils.cleanDirectory(new File(downloadDir));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	public String getDownloadedFileName() {
		try {
			String fName = null;
			long elapsedTime = 0L;
			while (fName == null || fName.contains(".crdownload") || elapsedTime >= 60000L) {
				waitFor(500);
				elapsedTime += 500L;
				String downloadDir = System.getProperty("user.dir") + "/src/test/resources/downloads";
				File file = new File(downloadDir);
				File[] fList = file.listFiles();
				fName = fList[0].getName();
			}
			return fName;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			//return null;
		}
	}

	public static void screenshot(String message) {
		try {
			String screenshotName = DateUtil.getTodayDateInStr("yyyy-MM-dd") + "-" + DateUtil.getCurrentHour() + "-"
					+ DateUtil.getCurrentMin();
			TakesScreenshot ts = (TakesScreenshot) webDriver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			String fileName = null;
			int i = 1;
			while(true) {
				File tempFname = new File((System.getProperty("user.dir")
						+ "/src/test/resources/Pass_screenshots/" + message + screenshotName + "-"+i + ".jpg"));
				if (!tempFname.exists()) {
					break;
				}
				i++;
			}
			FileUtils.copyFile(source, new File((System.getProperty("user.dir")
					+ "/src/test/resources/Pass_screenshots/" + message + screenshotName + "-"+i + ".jpg")));
			System.out.println("ScreenShot Taken" + screenshotName+"-"+i);
		} catch (java.io.IOException e) {
			e.printStackTrace();
			//throw e;
		} catch (Exception e) {    
			e.printStackTrace();
			System.out.println("Exception while taking ScreenShot ");
			throw e;
		}
	}
	public void doubleClick(WebElement ele)
	{
		try
		{
			Actions act=new Actions(webDriver);
			act.doubleClick(ele).build().perform();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
	}
	public void verifySuccessMessage(String expected) {
		try {
			String message = webDriver.findElement(By.xpath("//div[@id='toast-container']//div//span")).getText();
			log.info(message);
//			Assert.assertTrue(
//					verifyData(expected, webDriver.findElement(By.xpath("//div[@id='toast-container']//div//span"))),
//					"Expected: " + expected + ", Actual: "
//					+ webDriver.findElement(By.xpath("//div[@id='toast-container']//div//span")).getText());
			Assert.assertTrue(waitForMessage(expected), "Message missing "+expected );
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	public void getSuccessMessage() {
		try {
			String message = webDriver.findElement(By.xpath("//div[@id='toast-container']//div//span")).getText();
			log.info(message);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public  String  getTitle() {
		try {
			waitFor(2000);
			return webDriver.getTitle().trim();
		} catch (Exception e) {
			return null;
		}
	}
	public void scrollHalfScreen() {
		try {
			log.info("scrolling half screen");
			JavascriptExecutor js = (JavascriptExecutor) webDriver;
			js.executeScript("window.scrollTo(0, document.body.scrollHeight/2)");
			log.info("scrolled half screen");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	//to read the image

	public void VerifyTheImage(String pathofImage,WebElement xpathOfVerifyImage)
	{
		try
		{

			String actualimageurl = xpathOfVerifyImage.getAttribute("src");


			BufferedImage actualimage = ImageIO.read(new URL(actualimageurl));
			BufferedImage ExpectedImage = ImageIO.read(new File(pathofImage));

			if(ExpectedImage.getWidth()==actualimage.getWidth() && ExpectedImage.getHeight()==actualimage.getHeight())
			{
				for(int i=0;i<ExpectedImage.getWidth();i++)
				{
					for(int j=0;j<ExpectedImage.getHeight();j++)
					{
						assertTrue(ExpectedImage.getRGB(i, j)==actualimage.getRGB(i, j),"both images are not matching hence failed");				}
				}
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean isElementPresent(By by) {
		try {
			boolean flag = false;
			webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			try {
				webDriver.findElement(by);
				flag = true;
			} catch (org.openqa.selenium.NoSuchElementException e) {
			} catch (StaleElementReferenceException e) {
			} catch (Exception e){
			}
			webDriver.manage().timeouts().implicitlyWait(ConfigFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			return flag;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	public static boolean isElementPresent(WebElement element) {
		try {
			boolean flag = false;
			webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			try {
				if (element.isDisplayed()
						|| element.isEnabled())
					flag = true;
			} catch (org.openqa.selenium.NoSuchElementException e) {
				flag = false;
			} catch (StaleElementReferenceException e) {
				flag = false;
			} catch (Exception e){
				flag = false;
			}
			webDriver.manage().timeouts().implicitlyWait(ConfigFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			return flag;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	/*public static void takingScreenshot(Scenario scenario) throws IOException {
		if(scenario.isFailed()) {
			//take screenshot
			
			
			
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			//byte [] sourcePath = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.BYTES);
			
			File file = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
			
			FileUtils.copyFile(file,new File("D:\\opal_Workspace\\Cucumber_Demo\\src\\test\\resources\\" + screenshotName + ".png"));
			
			
		//	File destinationPath = new File("\"D:\\opal_Workspace\\Cucumber_Demo\\src\\test\\resources\\" + screenshotName + ".png");
			
			
			
			
			
			//scenario.attach(sourcePath,"image/png", screenshotName);
			
			
			
			
			}
	}*/
	
	
		public static void closeWebBrowser(){
			try{
				//log.info("Entered closeWebPage succesfully");	
				webDriver.close();
				webDriver.quit();
				///log.info("Executed closeWebPage Successfully");
			} 
			catch  (Exception e) 
			{

				//log.error("Not Executed closeWebPage"); 
				e.printStackTrace(); 
				throw e;
			}

	}
}

