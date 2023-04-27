package managers;

import config.ConfigFileReader;
import enums.DriverType;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class WebDriverManager {
    private WebDriver driver;
    private  DriverType driverType;
    private  String baseURL;
    private  final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
    private  final String FIREFOX_DRIVER_PROPERTY = "webdriver.gecko.driver";

    public WebDriverManager() {
    	//System.out.println("i am in web driver manager constructor");
        driverType = ConfigFileReader.getBrowser();
        
        baseURL = ConfigFileReader.getApplicationUrl();
        System.out.println(baseURL);
    }


    public WebDriver getDriver() {
        if (driver == null) driver = createDriver();
        return driver;
    }

    public String getApplicationUrl() {
    	
    	driver.get(baseURL);
        return baseURL;
    }

    private WebDriver createDriver() {
        String baseFolderPath = System.getProperty("user.dir");
      //  System.out.println("the project path is:"+baseFolderPath);
        switch (driverType) {
            case FIREFOX:
                System.setProperty(FIREFOX_DRIVER_PROPERTY, baseFolderPath + ConfigFileReader.getDriverPath());
                driver = new FirefoxDriver();
                break;
            case CHROME:
                System.setProperty(CHROME_DRIVER_PROPERTY, baseFolderPath + ConfigFileReader.getDriverPath());
                
                String downloadFilepath = System.getProperty("user.dir")
                        + System.getProperty("file.separator") + "src"
                        + System.getProperty("file.separator") + "test"
                        + System.getProperty("file.separator") + "resources"
                        + System.getProperty("file.separator") + "downloads";
               // System.out.println("hashmap");
                HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
                chromePrefs.put("profile.default_content_settings.popups", 0);
                chromePrefs.put("download.default_directory", downloadFilepath);
                
                System.setProperty("webdriver.http.factory", "jdk-http-client");

                ChromeOptions options=new ChromeOptions();    
                options.addArguments("--remote-allow-origins=*");  
               // options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
                 
                options.setExperimentalOption("prefs", chromePrefs);
  // options.addArguments("headless");
                driver = new ChromeDriver(options);
				 driver.manage().deleteAllCookies();
				 
              driver.get("chrome://settings/clearBrowserData");
              // driver.findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);  
              // System.setProperty(CHROME_DRIVER_PROPERTY, baseFolderPath + ConfigFileReader.getDriverPath());
               //driver = new ChromeDriver();
                break;
            case INTERNETEXPLORER:
                driver = new InternetExplorerDriver();
                break;
        }

        if (ConfigFileReader.getBrowserWindowSize()) driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(ConfigFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
        return driver;
    }

    public void closeDriver() {
        driver.close();
        driver.quit();
    }
}
