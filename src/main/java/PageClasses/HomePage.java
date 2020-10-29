package PageClasses;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import BaseClass.BaseClass;

public class HomePage extends BaseClass{
	 Properties prop = readProperties();

	public HomePage(WebDriver driver) {
		this.driver=(RemoteWebDriver) driver;
	}

	@FindBy(xpath="//*[@id='c-omni-container']/div/div[1]/div/input")
	public WebElement citylink;
	
	@FindBy(xpath="//div[contains(text(),'Bangalore')]")
	public WebElement bang;
	
	@FindBy(xpath="//*[@id='c-omni-container']/div/div[2]/div[1]/input")
	public WebElement hosplink;
	
	@FindBy(xpath="//*[@id='c-omni-container']/div/div[2]/div[2]/div[1]/div[1]/span[1]/div")
	public WebElement hospt;
	
	public void city() {
	
		//search city Bangalore
		System.out.println(getTitle(prop.getProperty("expected_HomePage_Title"),driver));
		citylink.clear();
		timewait(5);
		
		citylink.sendKeys("Bangalore");
		timewait(5);
	    System.out.println(bang.getText()+" City has been chosen");

		bang.click();
	}
	
	public HospitalSearch hospital() {
		//search hospitals in Bangalore
		hosplink.sendKeys("Hosp");
		timewait(5);
		System.out.println(hospt.getText()+" of Bangalore City has been selected");
		
		timewait(5);
		hospt.click();
		
		return PageFactory.initElements(driver, HospitalSearch.class);
	}
}
