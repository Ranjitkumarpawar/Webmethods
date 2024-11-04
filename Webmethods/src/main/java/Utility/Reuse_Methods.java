package Utility;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.xpath.XPath;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.inject.Key;

import Base.Driver;

public class Reuse_Methods {
	public static WebDriverWait wait;
	public static Select select;
	public static JavascriptExecutor JS;
	public static Alert alert;

	public static void wait_until_element_visible(WebDriver driver, int seconds, WebElement element) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void selectBy_visibilityof_text(WebElement element, String Patner_type) {
		select = new Select(element);
//		select.selectByVisibleText(Patner_type);
		select.selectByValue(Patner_type);
	}

	public static void click_element_usingJS(WebElement element) {
		WebDriver driver = Driver.driver;

	}

	public static void Select_By_Index(WebElement element, int index) {
		select = new Select(element);
		select.selectByIndex(index);
	}

	public static void select_options(WebElement element, String type) {
		select = new Select(element);
		List<WebElement> dropdown = select.getOptions();
		for (WebElement E : dropdown) {
			String name = E.getText();
			if (name.equalsIgnoreCase(type)) {
				E.click();
			} else {
				System.out.println("element not found");
			}
		}

	}

	public static void select_By_loop(String xapth, WebDriver driver, String type) {
		List<WebElement> elements = driver.findElements(By.xpath(xapth));
		for (WebElement a : elements) {
			String b = a.getText();
			System.out.println(b);
			if (b.contains(type)) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				a.click();
			} else {
				System.out.println("the element does not exist");
			}
		}
	}

	public static void select_By_index_by_passingvalue(WebElement element, String type) {
		element.sendKeys(type);
		element.sendKeys(Keys.ENTER);
	}

	public static void select_Base_map(String xapth, WebDriver driver, String type) {
		List<WebElement> elements = driver.findElements(By.xpath(xapth));
		ArrayList<String> Basemap_list = new ArrayList<String>();
		for (WebElement a : elements) {
			String c = a.getText();
			System.out.println(c);
			Basemap_list.add(c);
		}
		System.out.println(Basemap_list);
		System.out.println("the index 3 element is: " + Basemap_list.get(3));

	}

	public static void Alert_accept(WebDriver driver) {
		try {
			alert = driver.switchTo().alert();
			alert.accept();
			driver.switchTo().defaultContent();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	public static void Alert_dismiss(WebDriver driver) {
		try {
			alert = driver.switchTo().alert();
			alert.dismiss();
			driver.switchTo().defaultContent();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

}
