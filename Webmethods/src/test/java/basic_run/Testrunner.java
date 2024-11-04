package basic_run;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Base.Driver;
import PageFactory.B2B_Map;
import PageFactory.B2B_Translation_Table;
import Utility.Reports;


public class Testrunner extends Driver{
	@BeforeTest
	public void initiating() {
		Data_prop();
		Browser(Driver.prop.getProperty("Browser"));
		driver.get("https://test-lc.fishersci.com/ccg_wm_b2b_map/DashBoard");
	}
	
	
	@Test
	public void name() throws IOException {
		if(driver==null) {
			  System.out.println("the driver is null in testrunner");
		  }else {
			   System.out.println("driver is not null");
		  }
		B2B_Map obj=new B2B_Map(driver);
		B2B_Translation_Table table=new B2B_Translation_Table(driver); 
		obj.Login();
		obj.Read_B2B_patnerdata();
		table.Creating_B2BTranslation_Table();
		
		Reports.extentreport.flush();
		Desktop.getDesktop().browse(new File("reports.html").toURI());
	}
	
}
