package TestCases;

import java.io.IOException;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import BaseClass.BaseClass;
/*import PageClasses.CorporateWellness;*/
import PageClasses.Diagnostics;
import PageClasses.HomePage;
import PageClasses.HospitalSearch;

public class Objects extends BaseClass{

	HomePage home;
	HospitalSearch hosp;
	Diagnostics diag;
	/*CorporateWellness wellness;*/
	public RemoteWebDriver driver;
	

	@Test(priority=1)
	public void test() throws IOException, InterruptedException  {
		
		//Extent report 
		logger = report.createTest("Finding Hospitals");
		
		/*BaseClass base=new BaseClass();*/
		/*HomePage home = new HomePage(driver);*/
		logger.log(Status.INFO, "Opening the browser");
		//base.invokebrowser("browser");
		logger.log(Status.PASS, "Browser opened");
		logger.log(Status.INFO, "Open the practo.com");
		logger.log(Status.PASS, "Site opened");
		
		home=openUrl();
		
		logger.log(Status.INFO, "Searching Hospitals of Bangalore City ");
		home.city();
		logger.log(Status.PASS, "Hospitals selected");
		
		hosp=home.hospital();
		
		logger.log(Status.INFO, "Check for 24*7 service availability");
		hosp.availability();
		logger.log(Status.PASS, "Hospitals with 24*7 services");
		logger.log(Status.INFO, "Check for parking , cafeteria services availability");
		hosp.parking();
		logger.log(Status.INFO, "Hospitals selected");
	
		hosp.Hos_with_rating();
		diag=hosp.click_diagnostics();
		
		logger.log(Status.INFO, "Selecting Top Cities which has Diagnostics service");
		diag.Search_city();
		diag.Top_cities();
		logger.log(Status.PASS, "8 top cities has been selected");
		
		logger.log(Status.INFO, "Passing Invalid email data");
		diag.func("shalu","cognizant","shalu","9876543210");
		logger.log(Status.PASS, "Getting an alert-Please enter valid email address");
		
		logger.log(Status.INFO, "Leaving organization field empty");
		diag.func("shalu","","shalu@gmail.com","9876543210");
		logger.log(Status.PASS, "Getting an alert-Please enter Organization name");
		
		logger.log(Status.INFO, "Passing Invalid phone number data");
		diag.func("Shalu", "Cognizant", "cshalu@gmail.com", "987654321");
		logger.log(Status.PASS, "Getting an alert-Please enter valid phone no");
		
		logger.log(Status.INFO, "Passing Valid data");
		diag.func("Shalu", "Cognizant", "cshalu@gmail.com", "9876543210");
		logger.log(Status.PASS, "Thanks, for showing We have received your request, our team will contact you shortly.");
	}
}
