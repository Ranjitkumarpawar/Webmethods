package Base;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Driver {
	
	public static WebDriver driver;
	public static Properties prop;
	public static String path=System.getProperty("user.dir");

	public static void Browser(String Browse_name){
		if(Browse_name.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromiumdriver().setup();
			driver=new ChromeDriver();
		}else if(Browse_name.equalsIgnoreCase("Edge")){
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}else if(Browse_name.equalsIgnoreCase("Firfox")) {
			WebDriverManager.firefoxdriver().setup();
			driver =new FirefoxDriver();
		}else {
			System.out.println("The Browser you want to run is not installed in your system");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
	}
	
	public WebDriver Getdriver() {
		return driver;
	}
	
	public static void Data_prop() {
		try {
			FileReader read=new FileReader(path+"\\src\\main\\resources\\prop.properties");
			prop=new Properties();
			try {
				prop.load(read);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
