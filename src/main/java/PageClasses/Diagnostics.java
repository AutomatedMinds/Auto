package PageClasses;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import BaseClass.BaseClass;

public class Diagnostics extends BaseClass {
    
	
	public Diagnostics(WebDriver driver) {
		this.driver=(RemoteWebDriver)driver;
	
	}
	@FindBy(xpath="//a[contains(text(),'Corporate wellness')]")
	public WebElement corWell;
	@FindBy(xpath="//div[contains(text(),'Diagnostics')]")
	public WebElement diaganos;
	@FindBy(xpath="//span[contains(text(),'For Providers')]/following::span[1]")
	public WebElement provider;
	
	@FindBy(xpath="//input[@data-aid='search-input']")
	public WebElement topcity;
	
	@FindBys(@FindBy(xpath="//div[@class='u-margint--standard o-f-color--primary']"))
	public List<WebElement> cities;
	
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
	public void Search_city() {
		//click on search bar to get top cities
		
		diaganos.click();
		
	}
	
	public void Top_cities() throws IOException {
		//display all top cities
     
     	System.out.println("There are "+cities.size()+" top cities");
		cities.size();
		
	 	@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook();
	 	//Create Excel Sheet
	    XSSFSheet sheet = workbook.createSheet("Details");
   	    //Iterate over the Data
	    Row row = sheet.createRow(0);
 	    row.createCell(0).setCellValue("SNo.");
 	    row.createCell(1).setCellValue("Top Cities");
	 	    
	    for(int i=0;i<cities.size();i++)
	 	    {
	 	    	row = sheet.createRow(i+1);
	 	    	row.createCell(0).setCellValue(i+1);
	 	    	row.createCell(1).setCellValue(cities.get(i).getText());
	 	    	System.out.println(cities.get(i).getText());
	 	    }
	 	
	    //Write Down file on HardDisk
         FileOutputStream foss = new FileOutputStream(new File("Top_Cities.xlsx"));
          workbook.write(foss);
	 	  System.out.println("Excel file is being created Successfully......Logging Out");
		  cities.get(0).click();
	}
	public void func(String name,String org,String email,String ph){
		  provider.click();
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
	
	
		//move to next Corporate Wellness feature
	/*public CorporateWellness provider() {
		//provider.click();
		return PageFactory.initElements(driver, CorporateWellness.class);
	}*/
}
