package config;

import java.io.*;
import java.time.Duration;
import java.util.Properties;
import enums.DriverType;


//import org.apache.log4j.Logger;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.PageFactory;


public class ConfigFileReader {  

	private  static final Properties properties = new Properties();
	private static final  Properties properties_env = new Properties();
	static {
	try {
			try {
				InputStream inputStream = ConfigFileReader.class.getResourceAsStream("/configs/Configuration.properties");
				properties.load(inputStream);
				InputStream inputStream_env = ConfigFileReader.class.getResourceAsStream("/configs/Environment.properties");
				properties_env.load(inputStream_env);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
		 catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found.");
		}
}

	public static  String getDriverPath(){
		String driverPath = null;
		String browserName = properties.getProperty("browser");
		if(DriverType.CHROME.name().equalsIgnoreCase(browserName)) {
			driverPath = properties.getProperty("chromeDriverPath");
		}else if(DriverType.FIREFOX.name().equalsIgnoreCase(browserName)){
			driverPath = properties.getProperty("firefoxDriverPath");
		}
		if(driverPath!= null) return driverPath;
		else throw new RuntimeException("Driver Path not specified in the Configuration.properties file for the Key:driverPath");
	}

	public static  long getImplicitlyWait() {
		String implicitlyWait = properties.getProperty("implicitlyWait");
		if(implicitlyWait != null) {
			try{
				return Long.parseLong(implicitlyWait);
			}catch(NumberFormatException e) {
				throw new RuntimeException("Not able to parse value : " + implicitlyWait + " in to Long");
			}
		}
		return 30;
	}

	public static  Duration getExplicitWait() {
		String explicitWait = properties.getProperty("explicitWait");
		if(explicitWait != null) {
			try{
				//return Duration.parse(explicitWait);
				//return Long.parseLong(explicitWait);
			}catch(NumberFormatException e) {
				throw new RuntimeException("Not able to parse value : " + explicitWait + " in to Long");
			}
		}
		//return 30;
		return Duration.parse(explicitWait);
	}

	public static  String getApplicationUrl() {
		//        String url = properties.getProperty("url");

		String targetEnv;
		String url;
		String urlKey;
//		String targetUrl;	 

		targetEnv = properties_env.getProperty("target_env");
		urlKey = targetEnv+"_URL";
		url = properties_env.getProperty(urlKey);

		if(url != null) return url;
		else throw new RuntimeException("Application Url not specified in the Configuration.properties file for the Key:url");
	}

	public static String getApplicationUrlForReset(){
		String ApplicationUrlForReset = properties.getProperty("ApplicationUrlForReset");
		if(ApplicationUrlForReset != null) return  ApplicationUrlForReset;
		else throw new RuntimeException("ApplicationUrlForReset not specified in the  Configuration.properties file for the Key: ApplicationUrlForReset");
	}

	public static DriverType getBrowser() {
		String browserName = properties.getProperty("browser");
		if(browserName == null || browserName.equals("chrome")) return DriverType.CHROME;
		else if(browserName.equalsIgnoreCase("firefox")) return DriverType.FIREFOX;
		else if(browserName.equals("iexplorer")) return DriverType.INTERNETEXPLORER;
		else throw new RuntimeException("Browser Name Key value in Configuration.properties is not matched : " + browserName);
	}

	public static  Boolean getBrowserWindowSize() {
		String windowSize = properties.getProperty("windowMaximize");
		if(windowSize != null) return Boolean.valueOf(windowSize);
		return true;
	}


	public static String getmailtrap_url() {
		String mailtrap_url=properties.getProperty("mailtrap_url");
		if(mailtrap_url!=null)
			return mailtrap_url;
		else throw new RuntimeException("mailtrap_url not specified in the Configuration.properties file for the Key:mailtrap_url");
	}

	public static String getDBHost() {
		String dbHost=properties.getProperty("dbHost");
		if(dbHost!=null)
			return dbHost;
		else throw new RuntimeException("dbHost not specified in the Configuration.properties file for the Key:dbHost");
	}

	public static String getDBName() {
		String dbName=properties.getProperty("dbName");
		if(dbName!=null)
			return dbName;
		else throw new RuntimeException("dbName not specified in the Configuration.properties file for the Key:dbName");
	}

	public static String getDBUser() {
		String dbUser=properties.getProperty("dbUser");
		if(dbUser!=null)
			return dbUser;
		else throw new RuntimeException("dbUser not specified in the Configuration.properties file for the Key:dbUser");
	}

	public static String getDBPassword() {
		String dbPwd=properties.getProperty("dbPwd");
		if(dbPwd!=null)
			return dbPwd;
		else throw new RuntimeException("dbPwd not specified in the Configuration.properties file for the Key:dbPwd");
	}

	public static String getRecordVideo() {
		String recordVideo=properties.getProperty("recordVideo");
		if(recordVideo!=null)
			return recordVideo;
		else throw new RuntimeException("recordVideo not specified in the Configuration.properties file for the Key:recordVideo");
	}


	public static String getRecordScenarioes() {
		String recordScenarioes=properties.getProperty("recordScenarioes");
		if(recordScenarioes!=null)
			return recordScenarioes;
		else throw new RuntimeException("recordScenarioes not specified in the Configuration.properties file for the Key:recordScenarioes");
	}	
	
	public static String ExecutionRoundCount() throws Exception {
		System.out.println("********setup class entered**************");
//		Log.info("ExecutionRoundCount started successfully");
		String targetEnv;
		String runCount;
		String runCountKey;
		int runNewCount;
		FileInputStream in;
		FileOutputStream fos;
		Properties p;
		File file;
		
		String filePath = "src/test/resources/configs/Environment.properties";
		in = new FileInputStream(filePath);
	    p=new Properties();  
	    try {
			p.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}  
	    in.close();
	    
	      targetEnv = p.getProperty("target_env");
	      
	      runCountKey = targetEnv+"_ExecutionRoundCount";
	      System.out.println("=============runCountKey============"+runCountKey);      
	      runCount = p.getProperty(runCountKey);
	      System.out.println("=============runCount============"+runCount);
	      runNewCount=Integer.parseInt(runCount)+1;
	      runCount=String.valueOf(runNewCount);
	      System.out.println("=============runCountAfter============"+runCount);
//	      Log.info("RunNewCount added successfully"+runCount);
	      
//	      System.out.println("runcountkey...."+runCountKey);
	      file = new File(filePath);
	      fos = new FileOutputStream(file);
	      p.setProperty(runCountKey, runCount);
	      p.store(fos, null);
	      fos.close();
//	      System.out.println("fos closed");
//	      Log.info("Exiting the ExecutionRoundCount successfully");
	      
		return runCount;
		
	}
	
	public static String getTargetData() throws Exception {
		System.out.println("+++++++++++++++++getTargetData entered+++++++++++++++");
		String targetEnv;
		String dataKey;
		FileInputStream in;
		Properties p;
		
		String filePath = "src/test/resources/configs/Environment.properties";
		in = new FileInputStream(filePath);
	    p=new Properties();  
	    try {
			p.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}  
	    in.close();
	    
	      targetEnv = p.getProperty("target_env");
	      System.out.println(targetEnv);
	      dataKey = targetEnv+"_data";
	      System.out.println(dataKey+" ==================getTargetData================");
		return dataKey;
	
	}
	
	public static String getDataDir() throws Exception {
		String targetEnvironment = getPropertyValue("target_env");
		String dataKey = targetEnvironment+"_data";
		String getDataValue = getPropertyValue(dataKey).trim();
		//System.out.println(getDataValue+"++_+_+_+_+_+_+__+getDataDir+_++_++_+_+_+_+_");
		return getDataValue;
	}
	
	
	public static String getPropertyValue(String key) throws Exception {
		//System.out.println("********GetPropertyValue class entered**************");

		String value;
		FileInputStream in;
		Properties p;
		
		String filePath = "src/test/resources/configs/Environment.properties";
		System.out.println("the file path is:"+filePath);
		in = new FileInputStream(filePath);
	    p=new Properties();  
	    try {
			p.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}  
	    in.close();
	    
	    value = p.getProperty(key);
	    
	    return value;
		
	}
	
	public static String getExecutionRunCount() throws Exception {
		String targetEnvironment = getPropertyValue("target_env");
		String runCountKey = targetEnvironment+"_ExecutionRoundCount";
		String getRunCount = getPropertyValue(runCountKey);
		return getRunCount;
	}

	

}
