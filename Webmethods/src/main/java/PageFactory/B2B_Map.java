package PageFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.github.dockerjava.api.model.Info;

import Base.Driver;
import Utility.Reports;
import Utility.Reuse_Methods;

public class B2B_Map {
	WebDriver driver;

	public static @FindBy(id = "otherTile") WebElement Use_Account;
	public static @FindBy(id = "i0116") WebElement email;
	public static @FindBy(id = "idSIButton9") WebElement Next;
	public static @FindBy(id = "i0118") WebElement pass;
	public static @FindBy(id = "idSIButton9") WebElement remember_me;
	public static @FindBy(xpath = "//div[@class='inline-block button-item ext-button-item']") WebElement sign_in;

	public static @FindBy(xpath = "//button[text()='PARTNER MAP']") WebElement Create_Partner_Map;
	public static @FindBy(linkText = "B2B_Partner") WebElement Partner_Map;
	public static @FindBy(className = "btn") WebElement create_profile;
	public static @FindBy(id = "b18-PartnerNickName") WebElement partner_nickname;
	public static @FindBy(id = "b18-PartnerNameField") WebElement partner_name;
	public static @FindBy(xpath = "//*[@id=\"b18-$b10\"]/div/div[1]/div") WebElement partner_type;
	public static @FindBy(xpath = "//*[@id=\"b18-$b2\"]/div/div[1]/div/div") WebElement direction;
	public static @FindBy(xpath = "//*[@id=\"b18-$b6\"]/div/div[1]/div/div") WebElement doc_type;
	public static @FindBy(xpath = "//*[@id=\"b18-b2b_lIst_RECEIVER_QUALIFIERFiled\"]") WebElement receiver_qualifier;
	public static @FindBy(xpath = "//*[@id=\"b18-RECEIVER_IDENTITYField\"]") WebElement receiver_identity;
	public static @FindBy(xpath = "//*[@id=\"b18-$b7\"]/div/div[1]/div/div") WebElement base_map_name;
	public static @FindBy(xpath = "//*[@id=\"b18-$b7\"]/div/div[2]/div[1]/input") WebElement Basemap_value;
	public static @FindBy(xpath = "//*[@id=\"b18-$b12\"]/div/div[1]/div/div") WebElement is_custom_map;
	public static @FindBy(xpath = "//*[@id=\"b18-$b12\"]/div/div[2]/div[1]/input") WebElement is_send_custom;
	public static @FindBy(xpath = "//*[@id=\"b18-$b4\"]/div/div[1]/div[2]/input") WebElement custom_map_name;
	public static @FindBy(id = "b18-SENDER_IDENTITYFiled") WebElement sender_identity;
	public static @FindBy(xpath = "//*[@id=\"b18-$b13\"]/div/div[1]/div/div") WebElement country_code;
	public static @FindBy(xpath = "//*[@id=\"b18-$b13\"]/div/div[2]/div[1]/input") WebElement Country;
	public static @FindBy(id = "b18-SENDER_QUALIFIERFiled") WebElement sender_qualifier;
	public static @FindBy(id = "b18-b14-DropdownSelect") WebElement doc_format;
	public static @FindBy(xpath = "//button[text()=\"Create\"]") WebElement create;
	public static @FindBy(xpath = "//i[@class='icon fa fa-times fa-2x']") WebElement cancel;

	public static String type = "//*[@id=\"b18-$b10\"]/div/div[2]/div[2]";
	public static String dir = "//*[@id=\"b18-$b2\"]/div/div[2]/div[2]";
	public static String doc = "//*[@id=\"b18-$b6\"]/div/div[2]/div[2]";
	public static String Base_map = "//*[@id=\"b18-$b7\"]/div/div[2]/div[2]";
	public static String custom_map = "//*[@id=\"b18-$b12\"]/div/div[2]/div[2]";
//	public static String Country="//*[@id=\"b18-$b13\"]/div/div[2]/div[2]";
	public static ExtentTest log;

	public B2B_Map(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	int count = 0;

	public static XSSFWorkbook Workbook;
	public static XSSFSheet Sheet;
	public static Row row;
	public static Cell cell;
	public static FileInputStream file;
	public static ArrayList<String> B2B_map_list;
	public static String Name;

	public void Read_B2B_patnerdata() throws IOException {
		String testName="Profile creation Screenshots";
		String Description="please find the profile created screenshot below in  Base64code format at log level";
		Reports.createTest(testName,Description);

		Reuse_Methods.wait_until_element_visible(driver, 5, Create_Partner_Map);
		Create_Partner_Map.click();
//		try {
//			Partner_Map.click();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
		B2B_map_list = new ArrayList();
		file = new FileInputStream(Driver.path + Driver.prop.getProperty("Excel_path"));
		Workbook = new XSSFWorkbook(file);
		Sheet = Workbook.getSheetAt(1);
		Iterator Ritr = Sheet.rowIterator();
		Ritr.next();
		while (Ritr.hasNext()) {
			row = (Row) Ritr.next();
			Iterator Citr = row.iterator();
			while (Citr.hasNext()) {
				cell = (Cell) Citr.next();
				String data = cell.getStringCellValue();
				B2B_map_list.add(data);
			}
//			Name=B2B_map_list.get(1);
//			System.out.println(Name);

			// implement code for B2B from now

			System.out.println(B2B_map_list);

			create_profile.click();
			Reuse_Methods.wait_until_element_visible(driver, 5, partner_nickname);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			partner_nickname.sendKeys(B2B_map_list.get(0));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			partner_name.sendKeys(B2B_map_list.get(1));
			partner_type.click();
			Reuse_Methods.Alert_dismiss(driver);
			Reuse_Methods.select_By_loop("//*[@id=\"b18-$b10\"]/div/div[2]/div[2]", driver, B2B_map_list.get(2));
			direction.click();
			Reuse_Methods.select_By_loop(dir, driver, B2B_map_list.get(3));
			doc_type.click();
			Reuse_Methods.select_By_loop(doc, driver, B2B_map_list.get(4));
			receiver_qualifier.sendKeys(B2B_map_list.get(5));
			receiver_identity.sendKeys(B2B_map_list.get(6));
			base_map_name.click();
			Reuse_Methods.select_By_index_by_passingvalue(Basemap_value, B2B_map_list.get(7));
			is_custom_map.click();
			Reuse_Methods.select_By_index_by_passingvalue(is_send_custom,B2B_map_list.get(8));
			
			if (B2B_map_list.get(8).equalsIgnoreCase("Yes")) {
				custom_map_name.click();
				Reuse_Methods.select_By_index_by_passingvalue(custom_map_name, B2B_map_list.get(9));
				System.out.println(
						"The custom map is add:--  " + B2B_map_list.get(9) + "is added to customer:-- " + B2B_map_list.get(1));
			} else {
				System.out.println(" No custom map is provided to this customer");
			}
			sender_identity.sendKeys(B2B_map_list.get(11));
			country_code.click();
			Reuse_Methods.select_By_index_by_passingvalue(Country, B2B_map_list.get(12));

			sender_qualifier.sendKeys(B2B_map_list.get(10));
			String screen=Reports.capture_Screenshot();
			Reports.addTestInfo("data entered screenshots for customer "+B2B_map_list.get(1)+" for the document type "+ B2B_map_list.get(4), screen,"Screeshot is below");
			cancel.click();
			count++;

			if (count == 2) {
				break;
			}
		B2B_map_list.clear();
		}
	}

	public void Login() {
		Use_Account.click();
		email.sendKeys(Driver.prop.getProperty("email"));
		Next.click();
		Reuse_Methods.wait_until_element_visible(driver, 5, pass);
		pass.sendKeys(Driver.prop.getProperty("password"));
		pass.sendKeys(Keys.ENTER);
		try {
			sign_in.click();
		} catch (Exception e) {
			// TODO: handle exception
			driver.navigate().refresh();
		}
	}
}
