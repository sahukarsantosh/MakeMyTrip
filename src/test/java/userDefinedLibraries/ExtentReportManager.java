package userDefinedLibraries;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.testng.ITestContext;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportManager {
	public static ExtentReports report;

	public static ExtentReports getReportInstance() {
		if (report == null) {

			String reportName = System.currentTimeMillis() + ".html";
			ExtentHtmlReporter htmlRepoter = new ExtentHtmlReporter("./reports/extent" + reportName);
			report = new ExtentReports();
			report.attachReporter(htmlRepoter);
			
			
		}
		

		return report;
	}
	
}
