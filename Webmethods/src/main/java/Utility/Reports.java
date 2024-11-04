package Utility;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Base.Driver;

public class Reports extends Driver{
	
	public static ExtentReports extentreport;
	public static ExtentSparkReporter extentSpark;
	public String screen;
	public static ExtentTest test;
	
//	public static void TestReport_logleve() throws IOException {
//		extentreport=new ExtentReports();
//		File file=new File("reports.html");
//		extentSpark=new ExtentSparkReporter(file);
//		extentreport.attachReporter(extentSpark);
//	}
	
	public static void TestReport_logleve() throws IOException {
        if (extentreport == null) {
            extentreport = new ExtentReports();
            File file = new File("reports.html");
            extentSpark = new ExtentSparkReporter(file);
            extentreport.attachReporter(extentSpark);
        }
    }

	
	public static void createTest(String testName, String description) throws IOException {
		Reports.TestReport_logleve();
        test = extentreport.createTest(testName, description);
    }

    public static void addTestInfo(String infoMessage, String screen,String screen_mess) {
        test.info(infoMessage);
        test.pass(MediaEntityBuilder.createScreenCaptureFromBase64String((screen),screen_mess).build());
    }

	

	public static String capture_Screenshot() {
		TakesScreenshot takescreenshot=(TakesScreenshot) driver;
		String base64Code=takescreenshot.getScreenshotAs(OutputType.BASE64);
		System.out.println("Thw screen shot saved success");
		return base64Code;
	}

}
