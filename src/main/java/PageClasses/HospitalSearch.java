package PageClasses;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import BaseClass.BaseClass;

public class HospitalSearch extends BaseClass  {
	

	public HospitalSearch(WebDriver driver){
		this.driver=(RemoteWebDriver) driver;
	}

	
	
	@FindBy(xpath="//*[@id='container']/div[3]/div/div[1]/div/div/header/div[1]/div/div[3]/label/span/span")
	public WebElement avail;
	
	@FindBy(xpath="//*[@id='container']/div[3]/div/div[1]/div/div/header/div[1]/div/div[4]/span/span")
	public WebElement filter;
	
	@FindBy(xpath="//*[@id='container']/div[3]/div/div[1]/div/div/header/div[2]/div/div/div/label[1]/span/span")
	public WebElement park;
	
	@FindBy(xpath="//*[@id='container']/div[3]/div/div[1]/div/div/header/div[2]/div/div/div/label[4]/span/span")
	public WebElement cafe;
	
	@FindBy(xpath="//*[@id='container']/div[3]/div/div[1]/div/div/header/div[2]/div/div/div/label[3]/span/span")
	public WebElement phar;

	public void availability() {
		//24*7 filter
	   avail.click();
	   timewait(5);
	}
    
	
	public void parking() {
		//has parking filter
		filter.click();
		timewait(5);
		park.click();
		timewait(5);
		//cafetaria filter
		filter.click();
		timewait(5);
		cafe.click();
		timewait(5);
		//pharmacy filter
		filter.click();
		timewait(5);
		phar.click();		
		System.out.println("Hospitals have 24*7 open , parking , cafeteria and pharmacy facilities");
	}
	
	//hospitals with more than 3.5 rating 
	public void Hos_with_rating() throws IOException, InterruptedException {
		
        for(int j=0;j<5;j++){
        	JavascriptExecutor js = (JavascriptExecutor) driver;
            //scrolling to get more hotels 
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        	timewait(5);
        }
        
        //list of hospital
		List<WebElement> name=driver.findElements(By.xpath("//div[@class='u-cushion u-white-fill u-normal-text o-card o-card--separated c-list-card']//descendant::h2"));
    	List<WebElement> location=driver.findElements(By.xpath("//div[@class='u-cushion u-white-fill u-normal-text o-card o-card--separated c-list-card']//descendant::h3"));
    	//list of rating
    	List<WebElement> rating = driver.findElements(By.xpath("//div[@class='u-cushion u-white-fill u-normal-text o-card o-card--separated c-list-card']//descendant::span[@class='common__star-rating__value']"));
    	@SuppressWarnings("resource")
    	//Blank workbook
 		XSSFWorkbook workbook = new XSSFWorkbook();
    	//Create Excel Sheet
 	    XSSFSheet sheet = workbook.createSheet("Details");
 	    //Iterate over the Data
 	    Row row = sheet.createRow(0);
 	    row.createCell(0).setCellValue("SNo.");
 	    row.createCell(1).setCellValue("Name");
 	    row.createCell(2).setCellValue("Location");
 	    row.createCell(3).setCellValue("Rating");
 	    
 	    //hospitals of Bangalore city with rating more than 3.5
 	    for(int i=0,j=0;i<rating.size();i++)
 	    {
 	    	if(!rating.get(i).getText().equals("3") && !rating.get(i).getText().equals("3.5"))
 	    	{	
 	    		j++;
 	    		row = sheet.createRow(j);
 	    		row.createCell(0).setCellValue(j);
 	    		System.out.println(name.get(i).getText());
 	    		row.createCell(1).setCellValue(name.get(i).getText());
 	    		row.createCell(2).setCellValue(location.get(i).getText());
 	    		row.createCell(3).setCellValue(rating.get(i).getText());
 	    	}
 	    }
 	    //Write Down file on HardDisk

 	    FileOutputStream fos = new FileOutputStream(new File("Hostipals.xlsx"));

 	    workbook.write(fos);
 	    System.out.println("Excel file is being created Successfully......Logging Out");
	}
	
	//move to next Diagnostics feature
	public Diagnostics click_diagnostics() {
		
		return PageFactory.initElements(driver,Diagnostics.class);
	}
}
