package BaseClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import PageClasses.HomePage;
import Utilities.ExtentReportManager;

public class BaseClass {
//test
	public RemoteWebDriver driver;
	public ExtentReports report = ExtentReportManager.getReportInstance();
	public ExtentTest logger;
	 Properties prop = readProperties();
	DesiredCapabilities cap=null;
  
	//invoke browser
	@Parameters("browser")
	@Test(priority=0)
	public void invokebrowser(String browser) {
		try {
			if(browser.equalsIgnoreCase("chrome"))
			{
		     	cap=DesiredCapabilities.chrome();
				/*System.setProperty("webdriver.chrome.driver",prop.getProperty("chrome_path"));*/
		        /*driver=new ChromeDriver();*/
		       }
			else if(browser.equalsIgnoreCase("edge")) {
				cap=DesiredCapabilities.edge();
				/*System.setProperty("webdriver.gecko.driver",prop.getProperty("firefox_path"));*/
		        /*driver=new FirefoxDriver();*/
			     }
		     }catch(Exception e) {
		    	 System.out.println(e.getMessage());
		     }
		try {
			driver=new RemoteWebDriver(new URL("http://192.168.1.3:4444/wd/hub"),cap);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		String URL = prop.getProperty("URL");
	    driver.get(URL);
		}
	
	//handle property file 
	public  Properties readProperties() {
		File file = new File("config.properties");
		  
		FileInputStream fileInput = null;
		
		try {
			fileInput = new FileInputStream(file);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Properties prop = new Properties();
		
		//load properties file
		try {
			prop.load(fileInput);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
	}
	//URL 
	public HomePage openUrl() {
	     return PageFactory.initElements(driver, HomePage.class);
	}
	//Title validation
	public  boolean getTitle(String expectedTitle,RemoteWebDriver driver) {
		
		Assert.assertEquals(driver.getTitle(), expectedTitle);
		return true;
	}
	public void timewait(int time) {
			try {
				Thread.sleep(time*500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	@AfterSuite
	public void close() {
		
		driver.close();
		report.flush();
	}
	

	}

