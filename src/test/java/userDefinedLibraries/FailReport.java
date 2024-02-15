package userDefinedLibraries;

import java.io.IOException;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class FailReport {

	public static WebDriver driver;
	public ExtentReports report;
	public static ExtentTest logger;
	public static String path;

	public static void reportFail(String reportString) {
		logger.log(Status.FAIL, reportString);
		path = ScreenShot.screenShotTC(driver);
		try {
			logger.addScreenCaptureFromPath(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
