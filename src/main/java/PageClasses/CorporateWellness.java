/*package PageClasses;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import BaseClass.BaseClass;

public class CorporateWellness extends BaseClass {
	
 
	public CorporateWellness(RemoteWebDriver driver) {
		this.driver=driver;
	
	}
	@FindBy(xpath="//span[contains(text(),'For Providers')]/following::span[1]")
	public WebElement provider;
	@FindBy(xpath="//a[contains(text(),'Corporate wellness')]")
	public WebElement corWell;
	
	@FindBy(xpath="//form[@name='corporate-form']/table/tbody/tr/td[2]/div/input")
	public WebElement namee;
	
	@FindBy(xpath="//button[@type='submit']")
	public WebElement submit;
	
	@FindBy(xpath="//form[@name='corporate-form']/table/tbody/tr/td[2]/div/input[2]")
	public WebElement orgg;
	@FindBy(xpath="//form[@name='corporate-form']/table/tbody/tr/td[2]/div/input[3]")
	public WebElement emaill;
	@FindBy(xpath="//form[@name='corporate-form']/table/tbody/tr/td[2]/div/input[4]")
	public WebElement phh;
	
	

	public void sel_corporate(String name,String org,String email,String ph) {
	//fill invalid information & handle alert
	// & then valid information and schedule a demo
	    //to handle multiple windows
		corWell.click();
		Set<String> id = driver.getWindowHandles();
		Iterator<String> it= id.iterator();
	    String id1= it.next();
	    String id2= it.next();
	    //for window switch
	    driver.switchTo().window(id2);
		
	    namee.sendKeys(name);
	    orgg.sendKeys(org);
	    emaill.sendKeys(email);
	    phh.sendKeys(ph);
	    submit.click();
	
	    timewait(10);
	    //handle alert
		Alert alert=driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.accept();
		driver.close();
		driver.switchTo().window(id1);
		
		
    }
	
}
*/