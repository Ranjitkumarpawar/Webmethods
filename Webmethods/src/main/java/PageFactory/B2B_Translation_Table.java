package PageFactory;

import java.io.IOException;
import java.util.ArrayList;

import javax.naming.spi.InitialContextFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.Driver;
import Utility.Data_Reading;
import Utility.Reports;
import Utility.Reuse_Methods;

public class B2B_Translation_Table {
	WebDriver driver;

	public static @FindBy(linkText = "B2B Translation") WebElement B2B_translation;
	public static @FindBy(id = "b7-DropdownSelect") WebElement Select_element;
	public static @FindBy(xpath = "//div[@class='choices__item needsclick choices__item--selectable choices__placeholder'][normalize-space()='Select a Group']") WebElement Select_group;
	public static @FindBy(xpath = "//*[@id=\"$b7\"]/div/div[2]/div[1]/input") WebElement enter_group;
	public static @FindBy(xpath = "//*[@id=\"b10-Column2\"]/button") WebElement Addkey_element;
	public static @FindBy(id = "Input_KEY") WebElement key;
	public static @FindBy(id="Input_Value") WebElement value;
	public static @FindBy(xpath = "/html/body/div[2]/div/div/div/div/div[1]/a/i") WebElement cancel;
	
	public B2B_Translation_Table(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void Creating_B2BTranslation_Table() throws IOException {
		String testName="B2B Translation table";
		String Description="please find the profile created screenshot below in  Base64code format at log level";
		Reports.createTest(testName,Description);
		
      Data_Reading.importRowData(Driver.path + Driver.prop.getProperty("Excel_path"), 1, 2);
      int n=Data_Reading.num;
      int count=0;
      for(int i=1;i<n;i++) {
    	  ArrayList<String> list = Data_Reading.importRowData(Driver.path + Driver.prop.getProperty("Excel_path"), i,2);
    	  int a=list.size();
    	  if(a>=1) {
    		  System.out.println(" -------    "+list);
    		  B2B_translation.click();
    		  Reuse_Methods.wait_until_element_visible(driver,5, Select_group);
    		  Select_group.click();
    		  Reuse_Methods.select_By_index_by_passingvalue(enter_group,list.get(0));
    		  Reuse_Methods.wait_until_element_visible(driver,5,Addkey_element);
    		  Addkey_element.click();
    		  key.sendKeys(list.get(1));
    		  value.sendKeys(list.get(2));
    		  String screen=Reports.capture_Screenshot();
  			 Reports.addTestInfo("data entered screenshots for customer table of groupname:"+list.get(0), screen,"Screeshot is above");
  			 cancel.click();
  			 count++;
  			 if(count==2) {
    		  break;
  			 }
    	  }else {
    		  continue;
    	  }
    	  
      }
	}

}
